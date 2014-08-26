import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Mutation represents a set of mutation operators for permutations
 */

public class Mutation {
	
	private Individual individual;
	
	public Mutation() {}
	
	/*//Mutation by using insert method
	public void Mutation_Insert(Individual individual)
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
			individual.AddANode(RandomPos_1+1, temp.get(RandomPos_2));
			individual.DeleteANode(RandomPos_2);
		}

        //System.out.println(individual.toString());
			
	}*/

    //Mutation by using insert method
    public void Mutation_Insert(Individual individual, double mut_rate) {

        int size = individual.NumberOfNodes();
        ArrayList<Node> nodes = individual.getNodeList();

        for (Node node_1: nodes) {

            if (Math.random() <= mut_rate) {

                int index_1 = nodes.indexOf(node_1);
                int index_2;

                do {
                    Random rand = new Random();
                    index_2 = rand.nextInt(size);
                } while (index_1 == index_2);

                if ((index_1 + 1) == index_2 || (index_1 - 1) == index_2) {
                    Collections.swap(nodes, index_1, index_2);
                }

                //Keep swapping the node with bigger index
                while ((index_1 + 1) < index_2) {
                    Collections.swap(nodes, index_2, index_2 - 1);
                    index_2--;
                }

                while ((index_1 - 1) > index_2) {
                    Collections.swap(nodes, index_2, index_2 + 1);
                    index_2++;
                }

            }
        }
    }

    //Mutation by using swap method
    public void Mutation_Swap(Individual individual, double mut_rate) {

        int size = individual.NumberOfNodes();
        ArrayList<Node> nodes = individual.getNodeList();

        for (Node node_1: nodes) {

            if (Math.random() <= mut_rate) {

                int index_1 = nodes.indexOf(node_1);
                int index_2;

                do {
                    Random rand = new Random();
                    index_2 = rand.nextInt(size);
                } while (index_1 == index_2);

                //Swap their positions
                Collections.swap(nodes, index_1, index_2);
            }
        }

    }

    //Mutation by using inversion
    public void Mutation_Inversion(Individual individual, double mut_rate) {

        int size = individual.NumberOfNodes();
        ArrayList<Node> nodes = individual.getNodeList();

        for (Node node_1: nodes) {

            if (Math.random() <= mut_rate) {

                int index_1 = nodes.indexOf(node_1);
                int index_2;

                do {
                    Random rand = new Random();
                    index_2 = rand.nextInt(size);
                } while (index_1 == index_2);

                int lower_index = Math.min(index_1, index_2);
                int bigger_index = Math.max(index_1, index_2);

                //Do invert the substring among these two indices
                while (lower_index <= bigger_index) {
                    Collections.swap(nodes, lower_index, bigger_index);
                    lower_index++;
                    bigger_index--;
                }

            }
        }
    }

    //Mutation by using Scramble
    public void Mutation_Scramble(Individual individual, double mut_rate) {

        int size = individual.NumberOfNodes();
        ArrayList<Node> nodes = individual.getNodeList();

        for (Node node_1: nodes) {

            if (Math.random() <= mut_rate) {

                int index_1 = nodes.indexOf(node_1);
                int index_2;

                do {
                    Random rand = new Random();
                    index_2 = rand.nextInt(size);
                } while (index_1 == index_2);

                int lower_index = Math.min(index_1, index_2);
                int bigger_index = Math.max(index_1, index_2);

                //Shuffle the sublist ranged from lower index to bigger index
                Collections.shuffle(nodes.subList(lower_index, bigger_index));
            }
        }

    }

	
	/*//Mutation by using swap method
	public void Mutation_Swap(Individual individual)
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
	public void Mutation_Inversion(Individual individual)
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
	public void Mutation_Scramble(Individual individual)
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
	}*/

}











