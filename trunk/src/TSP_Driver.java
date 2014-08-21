import java.io.File;

public class TSP_Driver {
    public static void main(String[] args) throws Exception {

        TSPProblem problem = new TSPProblem(new File("./data/eil51.tsp"));
        //problem.printProblem();

        Map map = problem.getMap();
        //map.printNodes();

        // Initialize population
        //Population pop = new Population(50, map);
        //pop.toString();

        Individual tour = new Individual(map);
        tour.CreateRandomTour();
        System.out.println(tour.toString());
        System.out.println(tour.TotalDistance());

    }
}
