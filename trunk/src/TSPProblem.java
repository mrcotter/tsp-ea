import java.io.File;
import java.io.IOException;

public class TSPProblem {

    private Population tour;

    //Constructs a new, empty TSPLIB problem
    public TSPProblem() {
        super();
        tour = new Population();
    }

    //Constructs a TSPLIB problem instance from a file
    public TSPProblem(File file) throws IOException {
        this();
        load(file);
    }

    //Load a problem from a TSPLIB file
    public void load(File file) throws IOException {

    }
}
