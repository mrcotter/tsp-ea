import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TSP_Driver {
    public static void main(String[] args) throws Exception {

        String file_name = "";
        int pop_size = 0, generations = 0, elitism_size = 0;
        double mut_rate = 0.0, cross_rate = 0.0;
        int mut_type = 0, cross_type = 0, sel_type = 0;
        boolean elitism = false;

        //Read config file
        BufferedReader config_reader = null;
        String line;

        try {
            config_reader = new BufferedReader(new FileReader("./config.txt"));

            while ((line = config_reader.readLine()) != null) {
                line = line.trim();

                if (!line.isEmpty() && !line.contains("#")) {
                    if (line.contains("=")) {

                        String[] tokens = line.split("=");
                        String token_0 = tokens[0].trim();
                        String token_1 = tokens[1].trim();

                        if (token_0.equals("tsp")) {
                            file_name = token_1;
                        }

                        if (token_0.equals("pop_size")) {
                            pop_size = Integer.parseInt(token_1);
                        }

                        if (token_0.equals("generations")) {
                            generations = Integer.parseInt(token_1);
                        }

                        if (token_0.equals("mut_rate")) {
                            mut_rate = Double.parseDouble(token_1);
                        }

                        if (token_0.equals("cross_rate")) {
                            cross_rate = Double.parseDouble(token_1);
                        }

                        if (token_0.equals("mut_type")) {
                            mut_type = Integer.parseInt(token_1);
                        }

                        if (token_0.equals("cross_type")) {
                            cross_type = Integer.parseInt(token_1);
                        }

                        if (token_0.equals("sel_type")) {
                            sel_type = Integer.parseInt(token_1);
                        }

                        if (token_0.equals("elitism")) {
                            if (token_1.toLowerCase().equals("true")) elitism = true;
                            if (token_1.toLowerCase().equals("false")) elitism = false;
                        }

                        if (token_0.equals("elitism_size")) {
                            elitism_size = Integer.parseInt(token_1);
                        }
                    }
                }
            }
        } finally {
            if (config_reader != null) {
                config_reader.close();
            }
        }

        TSPProblem tsp = new TSPProblem(new File("./data/" + file_name));
        //problem.printProblem();
        Map map = tsp.getMap();
        //map.printNodes();
        Population pop = new Population(pop_size, map);
        double best_result, temp_result;
        int num_generation;

        long start = System.currentTimeMillis();

        //Start GA
        GA ga = new GA(pop_size, mut_rate, mut_type,
                        cross_rate, cross_type, sel_type, elitism, elitism_size);
        
        pop = ga.runGA(pop);
        Individual best_path = pop.FindShortest();
        best_result = pop.FindShortest().TotalDistance();
        num_generation = 1;

        for (int i = 1; i < generations; i++) {
            pop = ga.runGA(pop);
            temp_result = pop.FindShortest().TotalDistance();

            if (temp_result < best_result) {
                best_path = pop.FindShortest();
                best_result = temp_result;
                num_generation = i+1;
            }
        }


        //Print best path and result
        DecimalFormat df = new DecimalFormat("#.####");
        System.out.println("\nFinal Path: " + best_path.toString());
        System.out.println("Shortest Distance: " + df.format(best_result) + " occurs in " + num_generation + "th generation.");

        long end = System.currentTimeMillis();
        //Calculate program running time
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.println("\nExecution time is: " + formatter.format((end - start) / 1000d) + " seconds");


    }
}