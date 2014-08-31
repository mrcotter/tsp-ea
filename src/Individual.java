import java.util.ArrayList;
import java.util.Collections;

/**
 * Individual represents a possible solution to the TSP as a permutation of the given nodes.
 */

public class Individual implements Comparable<Individual> {
	
	private ArrayList<Node> single_tour = new ArrayList<Node>();
	private Map map;
    

    //Initialize a tour
	public Individual(Map map)
	{
		this.map = map;
	}
	
	//Construct a tour from an ArrayList
	public Individual(ArrayList<Node> nodes)
	{
	    single_tour = new ArrayList<Node>(nodes);
	}
	
	//Create a random tour
	public void CreateRandomTour()
	{
        //System.out.println(map.GetNode(0).getID());
		for (int i=0; i < map.NumberOfNodes(); i++)
		{
			single_tour.add(i, map.GetNode(i));
		}

        Collections.shuffle(single_tour);
    }
	
	//Get a node from a single tour
	public Node GetANode(int pos)
	{
		return single_tour.get(pos);
	}
	
	//Set a node for a single tour
	public void SetANode(int pos, Node node)
	{
		single_tour.set(pos, node);
	}
	
	//Get the number of the nodes in a tour
	public int NumberOfNodes()
	{
		return single_tour.size();
	}

    //Get the list of all nodes in a tour
    public ArrayList<Node> getNodeList() {
        return single_tour;
    }
	
	//Calculate the total distance of a single tour
	public double TotalDistance()
	{
		double TotalDistance = 0.0;
		Node Node_Start;
		Node Node_End;
		
		for (int i = 0; i < single_tour.size(); i++)
		{
			Node_Start = GetANode(i);
			
			if(i+1 < single_tour.size())
			{
				Node_End = GetANode(i+1);
			}
			else
			{
				Node_End = GetANode(0);
			}
			
			TotalDistance += Node_Start.distanceTo(Node_End);
		}

		return TotalDistance;
	}

    @Override
    //Print one permutation of nodes
    public String toString() {
        String result = "";

        for (Node node: single_tour) {
            result += node.getID() + " ";
        }

        return result;
    }

    @Override
    public int compareTo(Individual tour) {
        if (this.TotalDistance() == tour.TotalDistance()) {
            return 0;
        } else {
            return this.TotalDistance() > tour.TotalDistance() ? 1 : -1;
        }
    }
}

