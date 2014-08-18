import java.util.ArrayList;
import java.util.Random;



public class Individual {
	
	private ArrayList<Node> single_tour = new ArrayList<Node>();
	
	private Map map;
	
	public Individual(Map map)
	{
		this.map = map;
	}
	
	//Create a random tour
	public void CreateRandomTour()
	{
		for(int i=0; i<map.NumberOfNodes(); i++)
		{
			single_tour.set(i, map.GetNode(i));
		}
		
		long seed = System.nanoTime();
		
		//Shuffle the the list
		Random random = new Random(seed);
		random.nextInt();
		for (int i = 0; i < single_tour.size(); i++) 
		{
		    int shuffle_Num = i + random.nextInt(single_tour.size() - i);
		    single_tour.set(i, single_tour.get(shuffle_Num));
		    single_tour.set(shuffle_Num, single_tour.get(i));
		}
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
	
	//Delete a node from a single tour
	public void DeleteANode(int pos)
	{
		single_tour.remove(pos);
	}
	
	//Get the number of the nodes in a tour
	public int NumberOfNodes()
	{
		return single_tour.size();
	}
	
	//Calculate the total distance of a single tour
	public double TotalDistance()
	{
		double TotalDistance = 0;
		Node Node_Start;
		Node Node_End;
		
		for(int i = 0; i < single_tour.size(); i++)
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
			
			TotalDistance = TotalDistance + Node_Start.distanceTo(Node_End);
		}
		
		
		return TotalDistance;
	}

}








