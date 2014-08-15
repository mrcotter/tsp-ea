
/**
 * Node represents a city with its ID and position read from a TSPLIB file.
 */

public class Node {

    //The id of the node.
    private final int id;

    //The position of the node.
    private final int[] position;

    //Node constructor
    public Node(int id, int[] position) {
        super();
        this.id = id;
        this.position = position;
    }

    //Return the id of the no
    public int getID() {
        return id;
    }

    public int[] getPosition() {
        return position;
    }

}
