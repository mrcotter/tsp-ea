import java.io.File;

public class TSP_Driver {
    public static void main(String[] args) throws Exception {

        TSPProblem problem = new TSPProblem(new File("./data/eil51.tsp"));
        //problem.printProblem();

        Map map = problem.getMap();
        //map.printNodes();

        //--------------------- Testing -------------------
        Individual tour = new Individual(map);
        tour.CreateRandomTour();
        System.out.println(tour.toString());
        //System.out.println(tour.TotalDistance());

        //Initialize population
        //Population pop = new Population(50, map);
        //System.out.println(pop.toString());

        Mutation mu = new Mutation(tour);
        mu.Mutation_Insert();
        //mu.Mutation_Swap();
    }
}