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
			Node[] temp = new Node[RandomPos_1 - RandomPos_2 + 1];
			
		}
		
		
		return Child;
	}

}







