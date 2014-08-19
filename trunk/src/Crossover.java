
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class Crossover {
	
	Individual Parent_1;
	Individual Parent_2;
	
	public Crossover(Individual Parent_1, Individual Parent_2)
	{
		this.Parent_1 = Parent_1;
		this.Parent_2 = Parent_2;
	}
	
	//Order crossover
	public Individual Crossover_Order()
	{
		Individual Child = new Individual();
		
		int[] Random_Int = new int[Parent_1.NumberOfNodes()];
		
		for (int j = 0; j < Random_Int.length; j++)
		{
			Random_Int[j] = j;
		}
		
		Collections.shuffle(Arrays.asList(Random_Int));
		
		int RandomPos_1 = Random_Int[0];
		long seed = System.nanoTime();
		Random rand = new Random(seed);
		int RandomPos_2 = Random_Int[rand.nextInt(Parent_1.NumberOfNodes()-1) + 1];
		
		if(RandomPos_1 > RandomPos_2)
		{
			for(int i = RandomPos_2; i <= RandomPos_1; i++)
			{
				Child.SetANode(i, Parent_1.GetANode(i));
			}
		}
		
		if(RandomPos_1 < RandomPos_2)
		{
			for(int i = RandomPos_1; i <= RandomPos_2; i++)
			{
				Child.SetANode(i, Parent_1.GetANode(i));
			}
		}
		
		for(int i = 0; i < Parent_2.NumberOfNodes(); i++)
		{
			
			if(Child.IfContainANode(Parent_2.GetANode(i)) == false)
			{
				for(int j = 0; j < Child.NumberOfNodes(); j++)
				{
					if(Child.GetANode(j) == null)
					{
						Child.SetANode(j, Parent_2.GetANode(i));
					}
				}
			}
		}	
		
		
		return Child;
	}
	
	//PMX crossover
	public Individual Crossover_PMX()
	{
		Individual Child = new Individual();
		Individual SubSet = new Individual();
		
		int[] Random_Int = new int[Parent_1.NumberOfNodes()];
		
		for (int j = 0; j < Random_Int.length; j++)
		{
			Random_Int[j] = j;
		}
		
		Collections.shuffle(Arrays.asList(Random_Int));
		
		int RandomPos_1 = Random_Int[0];
		long seed = System.nanoTime();
		Random rand = new Random(seed);
		int RandomPos_2 = Random_Int[rand.nextInt(Parent_1.NumberOfNodes()-1) + 1];
		
		if(RandomPos_1 > RandomPos_2)
		{
			for(int i = RandomPos_2; i <= RandomPos_1; i++)
			{
				SubSet.SetANode(i, Parent_1.GetANode(i));
			}
		}
		
		if(RandomPos_1 < RandomPos_2)
		{
			for(int i = RandomPos_1; i <= RandomPos_2; i++)
			{
				SubSet.SetANode(i, Parent_1.GetANode(i));
			}
		}
		
		for(int i = 0; i < Parent_2.NumberOfNodes(); i++)
		{
			for(int j = 0; j<Child.NumberOfNodes(); j++)
			{
				if(Parent_2.GetANode(i) == SubSet.GetANode(j))
				{
					Child.SetANode(j, Parent_2.GetANode(i));
					Child.SetANode(i, Parent_2.GetANode(j));
				}
			}
		}
		
		for(int i = 0; i < Child.NumberOfNodes(); i++)
		{
			if(Child.GetANode(i) == null)
			{
				Child.SetANode(i, Parent_2.GetANode(i));
			}
		}
		
		return Child;
	}
	

}







