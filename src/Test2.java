import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Test2 {
    public static void main(String[] args) throws Exception {
        String[] file_names = new String[]{};
        int pop_size = 0, generations = 0, elitism_size = 0;
        double io_rate = 0.0, mut_rate = 0.0, cross_rate = 0.0;
        int mut_type = 0, cross_type = 0, sel_type = 0;
        boolean elitism = false, inverover = false;
        int repeat = 1;

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
                            file_names = token_1.trim().split(",");
                        }
                        if (token_0.equals("pop_size")) {
                            pop_size = Integer.parseInt(token_1);
                        }
                        if (token_0.equals("generations")) {
                            generations = Integer.parseInt(token_1);
                        }
                        if (token_0.equals("inverover")) {
                            if (token_1.toLowerCase().equals("true")) inverover = true;
                            if (token_1.toLowerCase().equals("false")) inverover = false;
                        }
                        if (token_0.equals("io_rate")) {
                            io_rate = Double.parseDouble(token_1);
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
                        if (token_0.equals("repeat")) {
                            repeat = Integer.parseInt(token_1);
                        }
                    }
                }
            }
        } finally {
            if (config_reader != null) {
                config_reader.close();
            }
        }

        for (String file_name : file_names) {
            file_name = file_name.trim();

            System.out.println("TSPLIB File: " + file_name);
            String content = "";
            content += file_name + "\n";

            double totalShortest = 0.0;
            long totalTime = 0;

            if (inverover) {
                // Total run for several times defined in repeat

                System.out.println("inverover");
//                for (int r = 1; r <= repeat; r++) {
//                    System.out.println("\nRound: " + r);
//
//                    TSPProblem tsp = new TSPProblem(new File("./data/" + file_name));
//                    //problem.printProblem();
//                    Map map = tsp.getMap();
//                    //map.printNodes();
//                    Population pop = new Population(pop_size, map);
//                    double best_result, temp_result;
//                    int num_generation;
//
//                    long start = System.currentTimeMillis();
//
//                    //Start GA
//                    GA ga = new GA(pop_size, inverover, io_rate, mut_rate, mut_type,
//                            cross_rate, cross_type, sel_type, elitism, elitism_size);
//
//                    pop = ga.run(pop);
//                    Individual best_path = pop.FindShortest();
//                    best_result = pop.FindShortest().TotalDistance();
//                    num_generation = 1;
//
//                    for (int i = 1; i < generations; i++) {
//                        pop = ga.run(pop);
//                        temp_result = pop.FindShortest().TotalDistance();
//
//                        if (temp_result < best_result) {
//                            best_path = pop.FindShortest();
//                            best_result = temp_result;
//                            num_generation = i + 1;
//                        }
//                    }
//
//
////                content = content + "Final Path: " + best_path.toString() + "\n";
////                content = content + "Shortest Distance: " + best_result + " occurs in " + num_generation + "th generation.";
//                    System.out.println("Final Path: " + best_path.toString());
//                    System.out.println("Shortest Distance: " + best_result + " occurs in " + num_generation + "th generation.");
//
//                    totalShortest += best_result;
//                    long end = System.currentTimeMillis();
//                    //Calculate program running time
//                    NumberFormat formatter = new DecimalFormat("#0.00000");
////                    content = content + "\nExecution time is: " + formatter.format((end - start) / 1000d) + " seconds";
//                    totalTime += (end - start);
//                    System.out.println("average time till now (ms): " + totalTime / (r + 1));
//                }
//
//                content = content + "\nAVERAGE shortest distance: " + totalShortest / repeat + "\n";
//                content = content + "\nAVERAGE execution time: " + totalTime / repeat + "\n";
//                System.out.println("\n-- AVERAGE shortest distance: " + totalShortest / repeat);
//                System.out.println("-- AVERAGE execution time (ms): " + totalTime / repeat + "\n");

            } else {
                TSPProblem tsp = new TSPProblem(new File("./data/" + file_name));
                //problem.printProblem();
                Map map = tsp.getMap();
                //map.printNodes();
                Population pop = new Population(pop_size, map);
                double best_result, temp_result;
                int num_generation;

                long start = System.currentTimeMillis();

                //Start GA
                GA ga = new GA(pop_size, inverover, io_rate, mut_rate, mut_type,
                        cross_rate, cross_type, sel_type, elitism, elitism_size);

                pop = ga.run(pop);
                Individual best_path = pop.FindShortest();
                best_result = pop.FindShortest().TotalDistance();
                num_generation = 1;

                for (int i = 1; i <= generations; i++) {
                    pop = ga.run(pop);
                    temp_result = pop.FindShortest().TotalDistance();

                    if (temp_result < best_result) {
                        best_path = pop.FindShortest();
                        best_result = temp_result;
                        num_generation = i + 1;
                    }
                    if (i==5000 || i==10000 || i==20000 ){
                        content = content + "**Till "+i+"th generation, shortest Distance: " + best_result + " occurs in " + num_generation + "th generation.\n";
                        System.out.println("**Till "+i+"th generation, shortest Distance: " + best_result + " occurs in " + num_generation + "th generation.");
                    }else if(i%1000==0) {
                        content = content + "Till "+i+"th generation, best tour length so far: " + best_result + " \n";
                        System.out.println("Till "+i+"th generation, best tour length so far: " + best_result);
                    }
                }

//                content = content + "Final Path: " + best_path.toString() + "\n";
                content = content + "\nFinal Shortest Distance: " + best_result + " occurs in " + num_generation + "th generation.";
                System.out.println("Final Path: " + best_path.toString());
                System.out.println("Final Shortest Distance: " + best_result + " occurs in " + num_generation + "th generation.");

                totalShortest += best_result;
                long end = System.currentTimeMillis();
                //Calculate program running time
                NumberFormat formatter = new DecimalFormat("#0.00000");
//                    content = content + "\nExecution time is: " + formatter.format((end - start) / 1000d) + " seconds";
                totalTime += (end - start);

//                content = content + "\nAVERAGE shortest distance: " + totalShortest / repeat + "\n";
                content = content + "\nExecution time: " + totalTime / repeat + "\n";
//                System.out.println("\n-- AVERAGE shortest distance: " + totalShortest / repeat);
                System.out.println("\nExecution time (ms): " + totalTime / repeat + "\n");
            }

            // write to log file
            try {
                String logFileName = file_name.split("\\.")[0] + ".log";
                File file = new File("./-" + logFileName);

                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();

                System.out.println("\nFinished writing to log file.");

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to write log file!");
            }
        }
    }
}