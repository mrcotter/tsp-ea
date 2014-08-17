import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Map represents an area with given cities read from a TSPLIB file.
 */

public class Map {

    //The nodes in the map
    private final ArrayList<Node> nodes;

    //Constructor of a map
    public Map() {
        super();
        nodes = new ArrayList<Node>();
    }

    //Add the contents of this map from the TSPLIB reader
    public void addNodes(Node node) throws IOException {
        nodes.add(node);
    }
    
    //Get a single node within the map
    public Node GetNode(int NodeID)
    {
        Node single_N = nodes.get(NodeID);
        
        return single_N;
    }
    
    //Get the number of the nodes in the map
    public int NumberOfNodes()
    {
        return nodes.size();
    }
    
    //print all of the nodes
    public void printNodes() {
        if (nodes.size() != 0) {
            System.out.println("ID  Postion");

            for (Node city: nodes) {
                System.out.println(city.getID() + "   " + Arrays.toString(city.getPosition()));
            }
        } else {
            System.err.println("The map is empty! Read a TSPLIB file first!");
        }
    }

}

