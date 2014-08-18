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

    //Return the id of the node
    public int getID() {
        return id;
    }

    //Return the position of the node
    public int[] getPosition() {
        return position;
    }

    //calculate the distance to a given node
    public double distanceTo (Node node) {
        int node_position[] = node.getPosition();

        int delta_x = Math.abs(position[0] - node_position[0]);
        int delta_y = Math.abs(position[1] - node_position[1]);
        double result = Math.pow((delta_x + delta_y), 2.0);

        return Math.sqrt(result);
    }

}
