
import java.util.*;


public class Crossover {

    ArrayList<Individual> tours;

    public Crossover(Population pop) {
        tours = pop.GetAllTours();
    }

	//Order crossover
	public void Crossover_Order(Individual parent_1, Individual parent_2) {

        List<Node> tour_1 = parent_1.getNodeList();
        List<Node> tour_2 = parent_2.getNodeList();

        int size = parent_1.NumberOfNodes();

        Random rand = new Random();
        //Choose two arbitrary parts for start and end from parents
        int number1 = rand.nextInt(size);
        int number2 = rand.nextInt(size);
        //System.out.println(number1 + ", " + number2);

        int start = Math.min(number1, number2);
        int end = Math.max(number1, number2);

        System.out.println("start: " + start + ", end: " + end);

        //Initialise children
        List<Node> child_1 = new Vector<Node>();
        List<Node> child_2 = new Vector<Node>();

        //Copy the part in between the start and end to the children
        child_1.addAll(tour_1.subList(start, end));
        child_2.addAll(tour_2.subList(start, end));

        //Do order procedure
        int current_index;
        Node current_node_in_tour1, current_node_in_tour2;

        for (int i = 0; i < size; i++) {
            current_index = (end + i) % size;
            current_node_in_tour1 = tour_1.get(current_index);
            current_node_in_tour2 = tour_2.get(current_index);

            //If child 1 does not contain the current node in tour 2, then add it
            if (!child_1.contains(current_node_in_tour2)) {
                child_1.add(current_node_in_tour2);
            }

            //If child 2 does not contain the current node in tour 1, then add it
            if (!child_2.contains(current_node_in_tour1)) {
                child_2.add(current_node_in_tour1);
            }
        }

        //Rotate the list so that the elements are in the right place
        Collections.rotate(child_1, start);
        Collections.rotate(child_2, start);


        //Output test for parents
        System.out.println(parent_1.toString());
        System.out.println(parent_2.toString());
        System.out.println();
        //Output test for children
        for (int i = 0; i < size; i++) {
            System.out.print(child_1.get(i).getID() + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(child_2.get(i).getID() + " ");
        }

    }
	
	//PMX crossover
	public Individual Crossover_PMX(Individual parent_1, Individual parent_2)
	{
		Individual Child = new Individual();
		Individual SubSet = new Individual();
		
		int[] Random_Int = new int[parent_1.NumberOfNodes()];
		
		for (int j = 0; j < Random_Int.length; j++)
		{
			Random_Int[j] = j;
		}
		
		Collections.shuffle(Arrays.asList(Random_Int));
		
		int RandomPos_1 = Random_Int[0];
		long seed = System.nanoTime();
		Random rand = new Random(seed);
		int RandomPos_2 = Random_Int[rand.nextInt(parent_1.NumberOfNodes()-1) + 1];
		
		if(RandomPos_1 > RandomPos_2)
		{
			for(int i = RandomPos_2; i <= RandomPos_1; i++)
			{
				SubSet.SetANode(i, parent_1.GetANode(i));
			}
		}
		
		if(RandomPos_1 < RandomPos_2)
		{
			for(int i = RandomPos_1; i <= RandomPos_2; i++)
			{
				SubSet.SetANode(i, parent_1.GetANode(i));
			}
		}
		
		for(int i = 0; i < parent_2.NumberOfNodes(); i++)
		{
			for(int j = 0; j<Child.NumberOfNodes(); j++)
			{
				if(parent_2.GetANode(i) == SubSet.GetANode(j))
				{
					Child.SetANode(j, parent_2.GetANode(i));
					Child.SetANode(i, parent_2.GetANode(j));
				}
			}
		}
		
		for(int i = 0; i < Child.NumberOfNodes(); i++)
		{
			if(Child.GetANode(i) == null)
			{
				Child.SetANode(i, parent_2.GetANode(i));
			}
		}
		
		return Child;
	}
	

}







