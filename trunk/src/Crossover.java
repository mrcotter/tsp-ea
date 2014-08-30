import java.util.*;

/**
 * Crossover represents a set of crossover operators for permutations
 */

public class Crossover {

    private ArrayList<Individual> offsprings = new ArrayList<Individual>();

    public Crossover() {}

  //Order crossover
	public void Crossover_Order(Individual parent_1, Individual parent_2) {

	    List<Node> tour_1 = parent_1.getNodeList();
	    List<Node> tour_2 = parent_2.getNodeList();

	    int size = parent_1.NumberOfNodes();

	    Random rand = new Random();
        int number1, number2;
	    //Choose two arbitrary parts for start and end from parents
	    do {
            number1 = rand.nextInt(size);
            number2 = rand.nextInt(size);
        } while (number1 == number2);

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

	    //Add to the offspring list
	    offsprings.add(new Individual(new ArrayList<Node>(child_1)));
	    offsprings.add(new Individual(new ArrayList<Node>(child_2)));

	}

    //PMX crossover
    public void Crossover_PMX(Individual parent_1, Individual parent_2) {

        List<Node> tour_1 = parent_1.getNodeList();
        List<Node> tour_2 = parent_2.getNodeList();

        int size = parent_1.NumberOfNodes();

        Random rand = new Random();
        int number1, number2;
        //Choose two arbitrary parts for start and end from parents
        do {
            number1 = rand.nextInt(size);
            number2 = rand.nextInt(size);
        } while (number1 == number2);

        int start = Math.min(number1, number2);
        int end = Math.max(number1, number2);
        //System.out.println("start: " + start + ", end: " + end);

        //Initialise children
        List<Node> child_1 = Arrays.asList(new Node[size]);
        List<Node> child_2 = Arrays.asList(new Node[size]);

        //Copy the section from the parents to the children
        for (int i = start; i <= end; i++) {
            child_1.set(i, tour_1.get(i));
            child_2.set(i, tour_2.get(i));
        }

        //Starting from start point, look for elements on that section of the Parent2 that have not been copied.
        //For each of these (say i) look in the Child1 to see what element (say j) has been copied in its place from Parent1.
        //Place i in the position occupied by j in Parent2.
        Node replaceNode_1, replaceNode_2;

        for (int i = start; i <= end; i++) {
            replaceNode_1 = tour_1.get(i);
            replaceNode_2 = tour_2.get(i);

            //If the node has already been copied to the child, take no action.
            //Otherwise get the gene that is on the same index on the other parent.
            //Find the position that that gene occupies on this parent.

            if (!child_1.contains(replaceNode_2)) {
                int indexP2j = tour_2.indexOf(replaceNode_1);

                Node nodeP2j= child_1.get(indexP2j);

                if (nodeP2j == null){
                    child_1.set(indexP2j, replaceNode_2);
                } else {
                    //If the place occupied by j in Parent2 has already been filled
                    //in child1, by an element k, put i in the position occupied by k in Parent2.
                    int indexP2k = tour_2.indexOf(nodeP2j);

                    //Verify that the child has no node on this position.
                    if (child_1.get(indexP2k) == null){
                        child_1.set(indexP2k, replaceNode_2);
                    }
                }
            }

            if (!child_2.contains(replaceNode_1)) {
                int indexP1j = tour_1.indexOf(replaceNode_2);

                Node nodeP1j= child_2.get(indexP1j);

                if (nodeP1j == null){
                    child_2.set(indexP1j, replaceNode_1);
                } else {
                    int indexP1k = tour_1.indexOf(nodeP1j);

                    if (child_2.get(indexP1k) == null){
                        child_2.set(indexP1k, replaceNode_1);
                    }
                }
            }
        }

        //The remaining elements are placed by verifying which nodes are in Parent2 that don't exist in Child1 and copy them.
        ArrayList<Node> copy_tour_1 = new ArrayList<Node>(tour_1);
        ArrayList<Node> copy_tour_2 = new ArrayList<Node>(tour_2);

        ArrayList<Integer> emptyList_1 = new ArrayList<Integer>();
        ArrayList<Integer> emptyList_2 = new ArrayList<Integer>();

        //For each element in Child1, if it is null, put its index in emptyList1, otherwise remove the element from Parent2.
        for (int i = 0; i < size; i++) {
            Node temp_node1 = child_1.get(i);
            Node temp_node2 = child_2.get(i);

            if (temp_node1 == null) {
                emptyList_1.add(i);
            } else {
                copy_tour_2.remove(temp_node1);
            }

            if (temp_node2 == null) {
                emptyList_2.add(i);
            } else {
                copy_tour_1.remove(temp_node2);
            }
        }

        //Put rest nodes from Parent2 in the empty places
        for (int i: emptyList_1) {
            child_1.set(i, copy_tour_2.remove(0));
        }

        for (int i: emptyList_2) {
            child_2.set(i, copy_tour_1.remove(0));
        }

        //Add to the offspring list
        offsprings.add(new Individual(new ArrayList<Node>(child_1)));
        offsprings.add(new Individual(new ArrayList<Node>(child_2)));

    }

    // Cycle Crossover
    public void Crossover_Cycle(Individual parent_1, Individual parent_2) {
        int size = parent_1.NumberOfNodes();
    
        List<Node> tour_1 = parent_1.getNodeList();
        List<Node> tour_2 = parent_2.getNodeList();
        // Initialise children
        List<Node> child_1 = new Vector<Node>(parent_2.getNodeList());
        List<Node> child_2 = new Vector<Node>(parent_1.getNodeList());
        // the current visited indices
        Set<Integer> Visited_Indices = new HashSet<Integer>(size);
        // the indices in current cycle
        List<Integer> Indices = new ArrayList<Integer> (size);
        // initial the starting index to 0 and the cycle to 1
        int index = 0;
        int cycle = 1;
        
        while (Visited_Indices.size() < size) {
            Indices.add(index);
            
            Node node = tour_2.get(index);
            index = tour_1.indexOf(node);
            
            while (index != Indices.get(0)) {
        // add the index to the cycle indices
                Indices.add(index);
        // get the node in the tour_2 follow the index
                node = tour_2.get(index);
        // get the index of the node in the tour_1
                index = tour_1.indexOf(node);
            }
        // swap the child nodes on the indices found in the even cycle
            if ((cycle++ % 2) != 0) {
                for (int i : Indices) {
                    Node temp = child_1.get(i);
                    child_1.set(i, child_2.get(i));
                    child_2.set(i, temp);
                }
            }
            
            Visited_Indices.addAll(Indices);
        // determine the next starting index
            index = (Indices.get(0) + 1) % size;
            while (Visited_Indices.contains(index) && Visited_Indices.size() < size) {
                index ++;
                if (index >= size) {
                    index = 0;
                }
                
            }
            Indices.clear();
        }
        //Add to the offspring list
        offsprings.add(new Individual(new ArrayList<Node>(child_1)));
        offsprings.add(new Individual(new ArrayList<Node>(child_2)));
        
    }

        // Edge Recombination Crossover
        public void Crossover_Edge_Recombination(Individual parent_1, Individual parent_2) {
                
        ArrayList<Node> tour_1 = parent_1.getNodeList();
        ArrayList<Node> tour_2 = parent_2.getNodeList();
         // Initialise children
        ArrayList<Node> child = new ArrayList<Node>();
        // Initialise the adjacency lists for nodes in the tour 1 and tour 2
        HashMap<Node, ArrayList<Node>> neighborsList=new HashMap<Node, ArrayList<Node>>();
        // iterate over each node in tour 1
        for(Node i:tour_1){
        ArrayList<Node>neighbors = new ArrayList<Node>();
        int index=tour_1.indexOf(i);
        int size=tour_1.size();
        if(index==0){
            neighbors.add(tour_1.get(index+1));
            neighbors.add(tour_1.get(size-1));
        }
        else if(index==size-1){
            neighbors.add(tour_1.get(index-1));
            neighbors.add(tour_1.get(0));
        }
        else{
            neighbors.add(tour_1.get(index-1));
            neighbors.add(tour_1.get(index+1));
        }
            neighborsList.put(i, neighbors);
        }
        // iterate over each node in tour 2
        for(Node i:tour_2){
            ArrayList<Node>neighbors = new ArrayList<Node>();
            int index=tour_2.indexOf(i);
            int size=tour_1.size();
                if(index==0){
                    neighbors.add(tour_2.get(index+1));
                    neighbors.add(tour_2.get(size-1));
                }
                else if(index==size-1){
                    neighbors.add(tour_2.get(index-1));
                    neighbors.add(tour_2.get(0));
                }
                else{
                    neighbors.add(tour_2.get(index-1));
                    neighbors.add(tour_2.get(index+1));
                }
                    neighborsList.get(i).removeAll(neighbors);
                    neighborsList.get(i).addAll(neighbors);
                }
                    
                Node s=tour_1.get(0);
                while (true){
                    child.add(s);
                    
                // stop when child is full
                if(child.size()==tour_1.size()){
                    break;
                }
                    
                for(Node i:neighborsList.keySet()){
                    neighborsList.get(i).remove(s);
                }
                    
                ArrayList<Node> neighbors=neighborsList.get(s);
                if(!neighbors.isEmpty()){
                    int min=4;
                    ArrayList<Node> choices = new ArrayList<Node>();
                    for(Node i:neighbors){
                        int size=neighborsList.get(i).size();
                        if(size<min){
                            choices.clear();
                            choices.add(i);
                            min=size;
                        }
                        else if(size==min){
                            choices.add(i);
                        }
                    }
                    s=choices.get((int) (Math.random() * choices.size()));
                }
                else
                {
                    ArrayList<Node> temp=new ArrayList<Node>(tour_1);
                    temp.removeAll(child);
                    s=temp.get((int) (Math.random() * temp.size()));
                }
                
        //Add to the offspring list
        offsprings.add(new Individual(new ArrayList<Node>(child)));
        
        //System.out.println(offsprings.get(0).toString());

            }
                }

    public ArrayList<Individual> getOffsprings() {
        return offsprings;
    }

    public void clear() {
        offsprings.clear();
    }

}







