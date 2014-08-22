
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

        int start = Math.min(number1, number2);
        int end = Math.max(number1, number2);
        //System.out.println("start: " + start + ", end: " + end);

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
            //System.out.print(current_index + " ");
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

        /*
        //Output test for parents
        System.out.println();
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
        */
    }
	
	//PMX crossover
	public void Crossover_PMX(Individual parent_1, Individual parent_2) {

        int size = parent_1.NumberOfNodes();

        Random rand = new Random();
        //Choose two arbitrary parts for start and end from parents
        int number1 = rand.nextInt(size);
        int number2 = rand.nextInt(size);

        int start = Math.min(number1, number2);
        int end = Math.max(number1, number2);
        System.out.println("start: " + start + ", end: " + end);

        //Initialise children
        List<Node> child_1 = parent_1.getNodeList();
        List<Node> child_2 = parent_2.getNodeList();

        //Get the parts in each tour
        List<Node> section_1 = child_1.subList(start, end);
        List<Node> section_2 = child_2.subList(start, end);

        Node current_node, replace_node;
        int replace_index;
        int count = 0;

        //Iterate each city which is not in the section
        for (int i = end % size; i >= end || i < start; i = (i + 1) % size) {
            count++;
            //--------------- Child 1 ----------------
            current_node = child_1.get(i);

            //If that city is in the random part
            if (section_1.contains(current_node)) {
                //Get the index of the node to replace the repeated node within the section
                replace_index = section_1.indexOf(current_node);

                //Get the node that is intended to replace the repeated node
                replace_node = section_2.get(replace_index);

                //If the repeated node is also contained in the section
                while (section_1.contains(replace_node)) {
                    //Repeat the process again
                    replace_index = section_1.indexOf(replace_node);
                    replace_node = section_2.get(replace_index);
                }

                //Replace the current node with the replacement node
                child_1.set(i, replace_node);
            }

            //--------------- Child 2 ----------------
            current_node = child_2.get(i);

            //If that city is in the random part
            if (section_2.contains(current_node)) {
                //Get the index of the node to replace the repeated node within the section
                replace_index = section_2.indexOf(current_node);

                //Get the node that is intended to replace the repeated node
                replace_node = section_1.get(replace_index);

                //If the repeated node is also contained in the section
                while (section_2.contains(replace_node)) {
                    //Repeat the process again
                    replace_index = section_2.indexOf(replace_node);
                    replace_node = section_1.get(replace_index);
                }

                //Replace the current node with the replacement node
                child_2.set(i, replace_node);
            }
        }


        //Output test for parents
        System.out.println("count: " + count);
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

	

}







