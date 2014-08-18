import java.util.Arrays;
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
		for(int i = 0; i < individual.NumberOfNodes(); i++)
		{
			int[] Random_Int = new int[individual.NumberOfNodes()];
			
			for (int j = 0; j < Random_Int.length; j++)
			{
				Random_Int[j] = j;
			}
			
			Collections.shuffle(Arrays.asList(Random_Int));
			
			int RandomPos_1 = Random_Int[0];
			long seed = System.nanoTime();
			Random rand = new Random(seed);
			int RandomPos_2 = Random_Int[rand.nextInt(individual.NumberOfNodes()) + 1];
				
			Node temp = individual.GetANode(RandomPos_2);
			individual.DeleteANode(RandomPos_2);
			individual.SetANode(RandomPos_1+1, temp);
		}
	}
	
	
	//Mutation by using swap method
	public void Mutation_Swap()
	{
		for(int i = 0; i < individual.NumberOfNodes(); i++)
		{
			int[] Random_Int = new int[individual.NumberOfNodes()];
			
			for (int j = 0; j < Random_Int.length; j++)
			{
				Random_Int[j] = j;
			}
			
			Collections.shuffle(Arrays.asList(Random_Int));
			
			int RandomPos_1 = Random_Int[0];
			long seed = System.nanoTime();
			Random rand = new Random(seed);
			int RandomPos_2 = Random_Int[rand.nextInt(individual.NumberOfNodes()) + 1];
			
			Node node_1 = individual.GetANode(RandomPos_1);
			Node node_2 = individual.GetANode(RandomPos_2);
			
			individual.SetANode(RandomPos_1, node_2);
			individual.SetANode(RandomPos_2, node_1);	
		}
	}
	
	//Mutation by using inversion
	public void Mutation_Inversion()
	{
		for(int i = 0; i < individual.NumberOfNodes(); i++)
		{
			int[] Random_Int = new int[individual.NumberOfNodes()];
			
			for (int j = 0; j < Random_Int.length; j++)
			{
				Random_Int[j] = j;
			}
			
			Collections.shuffle(Arrays.asList(Random_Int));
			
			int RandomPos_1 = Random_Int[0];
			long seed = System.nanoTime();
			Random rand = new Random(seed);
			int RandomPos_2 = Random_Int[rand.nextInt(individual.NumberOfNodes()) + 1];
			
			if(RandomPos_1 > RandomPos_2)
			{
				Node[] temp = new Node[RandomPos_1 - RandomPos_2 + 1];
				int temp_num = RandomPos_1; 
				for(int j = RandomPos_2; j <= RandomPos_1; j++)
				{
					temp[j] = individual.GetANode(j);
				}
				
				for(int j = 0; j < temp.length; j++)
				{
					individual.SetANode(temp_num, temp[j]);
					temp_num--;
				}
			}
			
			if(RandomPos_1 < RandomPos_2)
			{
				Node[] temp = new Node[RandomPos_2 - RandomPos_1 + 1];
				int temp_num = RandomPos_2; 
				for(int j = RandomPos_1; j <= RandomPos_2; j++)
				{
					temp[j] = individual.GetANode(j);
				}
				
				for(int j = 0; j < temp.length; j++)
				{
					individual.SetANode(temp_num, temp[j]);
					temp_num--;
				}
			}
		}
	}
	
	//Mutation by using Scramble
	public void Mutation_Scramble()
	{
		for(int i = 0; i < individual.NumberOfNodes(); i++)
		{
			int[] Random_Int = new int[individual.NumberOfNodes()];
			
			for (int j = 0; j < Random_Int.length; j++)
			{
				Random_Int[j] = j;
			}
			
			Collections.shuffle(Arrays.asList(Random_Int));
			
			int RandomPos_1 = Random_Int[0];
			long seed = System.nanoTime();
			Random rand = new Random(seed);
			int RandomPos_2 = Random_Int[rand.nextInt(individual.NumberOfNodes()) + 1];
			
			if(RandomPos_1 > RandomPos_2)
			{
				Node[] temp = new Node[RandomPos_1 - RandomPos_2 + 1];
				for(int j = RandomPos_2; j <= RandomPos_1; j++)
				{
					temp[j] = individual.GetANode(j);
				}
				
				Collections.shuffle(Arrays.asList(temp));
				
				for(int j = RandomPos_2; j <= temp.length; j++)
				{
					individual.SetANode(j, temp[j]);
				}
			}
			
			if(RandomPos_1 < RandomPos_2)
			{
				Node[] temp = new Node[RandomPos_2 - RandomPos_1 + 1];
				for(int j = RandomPos_1; j <= RandomPos_2; j++)
				{
					temp[j] = individual.GetANode(j);
				}
				
				Collections.shuffle(Arrays.asList(temp));
				
				for(int j = RandomPos_1; j <= temp.length; j++)
				{
					individual.SetANode(j, temp[j]);
				}
			}
		}
	}

}











