import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Mutation {
	
	private Individual individual;
	
	public Mutation(Individual individual)
	{
		this.individual = individual;
	}
	
	//Mutation by using insert method
	public void Mutation_Insert()
	{
		ArrayList<Integer> Random_Int = new ArrayList<Integer>();
		
		for (int j = 0; j < individual.NumberOfNodes(); j++)
		{
			Random_Int.add(j);
		}
		
		Collections.shuffle(Random_Int);
		
		int RandomPos_1 = Random_Int.get(0);
		long seed = System.nanoTime();
		Random rand = new Random(seed);
		int RandomPos_2 = Random_Int.get(rand.nextInt(individual.NumberOfNodes()-1) + 1);
		
		ArrayList<Node> temp = new ArrayList<Node>();
		
		for(int i = 0; i<individual.NumberOfNodes(); i++)
		{
			temp.add(individual.GetANode(i));
		}
		
		if(RandomPos_1 < RandomPos_2)
		{
			individual.AddANode(RandomPos_1+1, temp.get(RandomPos_2));
			individual.DeleteANode(RandomPos_2+1);
		}
		
		if(RandomPos_1 > RandomPos_2)
		{
			System.out.println("haha_2");
			individual.AddANode(RandomPos_1+1, temp.get(RandomPos_2));
			individual.DeleteANode(RandomPos_2);
		}
			
	}
	
	
	//Mutation by using swap method
	public void Mutation_Swap()
	{
		ArrayList<Integer> Random_Int = new ArrayList<Integer>();
		
		for (int j = 0; j < individual.NumberOfNodes(); j++)
		{
			Random_Int.add(j);
		}
		
		Collections.shuffle(Random_Int);
				
		int RandomPos_1 = Random_Int.get(0);
		long seed = System.nanoTime();
		Random rand = new Random(seed);
		int RandomPos_2 = Random_Int.get(rand.nextInt(individual.NumberOfNodes()-1) + 1);
		
		Node node_1 = individual.GetANode(RandomPos_1);
		Node node_2 = individual.GetANode(RandomPos_2);
		
		individual.SetANode(RandomPos_1, node_2);
		individual.SetANode(RandomPos_2, node_1);	
	}
	
	//Mutation by using inversion
	public void Mutation_Inversion()
	{
		ArrayList<Integer> Random_Int = new ArrayList<Integer>();
		
		for (int j = 0; j < individual.NumberOfNodes(); j++)
		{
			Random_Int.add(j);
		}
		
		Collections.shuffle(Random_Int);
				
		int RandomPos_1 = Random_Int.get(0);
		long seed = System.nanoTime();
		Random rand = new Random(seed);
		int RandomPos_2 = Random_Int.get(rand.nextInt(individual.NumberOfNodes()-1) + 1);
		
		if(RandomPos_1 > RandomPos_2)
		{
			ArrayList<Node> temp = new ArrayList<Node>();
			int temp_num = RandomPos_1; 
			for(int j = RandomPos_2; j <= RandomPos_1; j++)
			{
				temp.add(individual.GetANode(j));
			}
			
			for(int j = 0; j < temp.size(); j++)
			{
				individual.SetANode(temp_num, temp.get(j));
				temp_num--;
			}
		}
		
		if(RandomPos_1 < RandomPos_2)
		{
			ArrayList<Node> temp = new ArrayList<Node>();
			int temp_num = RandomPos_2; 
			for(int j = RandomPos_1; j <= RandomPos_2; j++)
			{
				temp.add(individual.GetANode(j));
			}
			
			for(int j = 0; j < temp.size(); j++)
			{
				individual.SetANode(temp_num, temp.get(j));
				temp_num--;
			}
		}
	}
	
	//Mutation by using Scramble
	public void Mutation_Scramble()
	{
		ArrayList<Integer> Random_Int = new ArrayList<Integer>();
		
		for (int j = 0; j < individual.NumberOfNodes(); j++)
		{
			Random_Int.add(j);
		}
		
		Collections.shuffle(Random_Int);
				
		int RandomPos_1 = Random_Int.get(0);
		long seed = System.nanoTime();
		Random rand = new Random(seed);
		int RandomPos_2 = Random_Int.get(rand.nextInt(individual.NumberOfNodes()-1) + 1);
		
		if(RandomPos_1 > RandomPos_2)
		{
			ArrayList<Node> temp = new ArrayList<Node>();
			int temp_num = RandomPos_2; 
			for(int j = RandomPos_2; j <= RandomPos_1; j++)
			{
				temp.add(individual.GetANode(j));
			}
			
			Collections.shuffle(temp);
			
			for(int j = 0; j < temp.size(); j++)
			{
				individual.SetANode(temp_num, temp.get(j));
				temp_num++;
			}
		}
		
		if(RandomPos_1 < RandomPos_2)
		{
			ArrayList<Node> temp = new ArrayList<Node>();
			int temp_num = RandomPos_1;
			for(int j = RandomPos_1; j <= RandomPos_2; j++)
			{
				temp.add(individual.GetANode(j));
			}
			
			Collections.shuffle(temp);
			
			for(int j = 0; j < temp.size(); j++)
			{
				individual.SetANode(temp_num, temp.get(j));
				temp_num++;
			}
		}
	}

}











