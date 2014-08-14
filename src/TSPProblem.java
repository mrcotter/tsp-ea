import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TSPProblem {

    private String problem_name;

    private String problem_comment;

    private String problem_type;

    private Individual tour;

    //Constructs an empty TSPLIB problem
    public TSPProblem() {
        super();
        tour = new Individual();
    }

    //Constructs a TSPLIB problem instance from a file
    public TSPProblem(File file) {
        this();
        load(file);
    }

    //Load a problem from a TSPLIB file
    private void load(File file) {
        BufferedReader tsp_reader = null;
        String line = null;

        try {
            tsp_reader = new BufferedReader(new FileReader(file));

            while ((line = tsp_reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.equals("NODE_COORD_SECTION")) {
                    //Do nothing
                } else if (line.equals("EOF")) {
                    break;
                }  else if (line.contains(":")) {
                    String[] tokens = line.split(":");
                    String token_0 = tokens[0].trim();
                    String token_1 = tokens[1].trim();

                    if (token_0.equals("NAME")) {
                        problem_name = "Problem Name: " + token_1;
                    }

                    if (token_0.equals("COMMENT")) {
                        problem_comment = "Comment: " + token_1;
                    }

                    if (token_0.equals("TYPE")) {
                        problem_type = "Problem Type: " + token_1;
                    }
                } else {

                }

            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
