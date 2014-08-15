import java.io.IOException;
import java.util.ArrayList;

/**
 * Map represents an area with given cities read from a TSPLIB file.
 * It also provide a function that calculate the euclidean distances between cities.
 */

public class Map {

    //The nodes in the map
    private final ArrayList<Integer> cities;

    //Constructor of a map
    public Map() {
        super();
        cities = new ArrayList<Integer>();
    }

    //Add the contents of this map from the TSPLIB reader
    public void addNodes(Node node) throws IOException {
        cities.add(node.getID());
    }

}
