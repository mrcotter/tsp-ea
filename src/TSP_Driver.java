import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TSP_Driver {
    public static void main(String[] args) throws Exception {

        TSPProblem problem = new TSPProblem(new File("./data/eil10.tsp"));
        //problem.printProblem();

        //Map map = problem.getMap();
        //map.printNodes();


        long start = System.currentTimeMillis();

        String pop_size = null, generations;
        double mut_rate, cross_rate;

        //--------------------- Testing -------------------
        //Individual tour = new Individual(map);
        //tour.CreateRandomTour();
        //System.out.println(tour.toString());
        //System.out.println(tour.TotalDistance());

        //Initialize population
        //Population pop = new Population(50, map);
        //System.out.println(pop.toString());

        //Mutation mu = new Mutation(tour);
        //mu.Mutation_Insert();
        //mu.Mutation_Swap();

        //Crossover cross = new Crossover(pop);
        //cross.Crossover_Order(pop.GetASingleTour(0), pop.GetASingleTour(2));
        //cross.Crossover_PMX(pop.GetASingleTour(0), pop.GetASingleTour(2));

        //Selection select= new Selection();
        //select.Selection_FPS(pop.GetAllTours());
        //select.getRanked_fitness(pop.GetAllTours());
        //select.Selection_Tournament(pop.GetAllTours(), 5);

        long end = System.currentTimeMillis();
        //Calculate program running time
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.println("\nExecution time is " + formatter.format((end - start) / 1000d) + " seconds");

    }
}