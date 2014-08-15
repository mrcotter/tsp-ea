import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Map represents an area with given cities read from a TSPLIB file.
 * It also provide a function that calculate the euclidean distances between cities.
 */

public class Map {

    //The nodes in the map
    private final ArrayList<Node> cities;

    //Constructor of a map
    public Map() {
        super();
        cities = new ArrayList<Node>();
    }

    //Add the contents of this map from the TSPLIB reader
    public void addNodes(Node node) throws IOException {
        cities.add(node);
    }

    public void printNodes() {
        if (cities.size() != 0) {
            System.out.println("ID  Postion");

            for (Node city: cities) {
                System.out.println(city.getID() + "   " + Arrays.toString(city.getPosition()));
            }
        } else {
            System.err.println("The map is empty! Read a TSPLIB file first!");
        }
    }

}
