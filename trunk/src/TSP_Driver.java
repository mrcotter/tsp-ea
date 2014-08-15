import java.io.File;

public class TSP_Driver {
    public static void main(String[] args) throws Exception {

        TSPProblem problem = new TSPProblem(new File("./data/eil51.tsp"));
        problem.printProblem();

    }
}
