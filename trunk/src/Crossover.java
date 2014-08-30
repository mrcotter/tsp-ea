import java.util.*;
import java.util.Map;

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
	    System.out.println();
	    //System.out.println(offsprings.get(0).toString());
	    //System.out.println(offsprings.get(1).toString());
	    */
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

        /*
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
        */
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

        //System.out.println(offsprings.get(0).toString());
        //System.out.println(offsprings.get(1).toString());
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


    // Edge Recombination Crossover
    public void Crossover_Edge_Recombination(Individual parent_1, Individual parent_2, HashMap<Integer, Node> lookup_table) {

        //Initialisation
        ArrayList<Node> cities_1 = parent_1.getNodeList();
        ArrayList<Node> cities_2 = parent_2.getNodeList();

        int size = cities_1.size();

        //Create the edge map
        HashMap<Integer, HashSet<Integer>> edgeMap = new HashMap<Integer, HashSet<Integer>>();

        for (int index_1 = 0; index_1 < cities_1.size(); index_1++) {

            int city_1_id = cities_1.get(index_1).getID();

            int index_2 = 0;
            //Get corresponding location in parent 2
            for (int i = 0; i < cities_2.size(); i++) {

                if (cities_2.get(i).getID() == city_1_id) {
                    index_2 = i;
                    break;
                }
            }

            edgeMap.put(city_1_id, getEdges(cities_1, cities_2, index_1, index_2));
        }

        ArrayList<Node> childCity = new ArrayList<Node>(size);
        ArrayList<Integer> unvisited_cityIDs = new ArrayList<Integer>(size);
        for (int i = 1; i <= size; ++i) {
            unvisited_cityIDs.add(i);
        }

        //Pick and store the first city
        Node current_city;
        int parent1_connection_degrees = edgeMap.get(cities_1.get(0).getID()).size();
        int parent2_connection_degrees = edgeMap.get(cities_2.get(0).getID()).size();

        if (parent1_connection_degrees >= parent2_connection_degrees) {
            current_city = cities_1.get(0);
        } else {
            current_city = cities_2.get(0);
        }
        childCity.add(current_city);

        //Remove corresponding entries
        unvisited_cityIDs.remove(unvisited_cityIDs.indexOf(current_city.getID()));
        edgeMap = removeFromEdgeMap(edgeMap, current_city.getID());


        while (!unvisited_cityIDs.isEmpty()) {

            if (!edgeMap.get(current_city.getID()).isEmpty()) {
                current_city = pickNextCity(lookup_table, edgeMap, current_city.getID());

            } else {

                int nextCityId;
                if (unvisited_cityIDs.size() == 1) {
                    nextCityId = unvisited_cityIDs.get(0);
                }
                else {
                    Random rand = new Random();
                    nextCityId = unvisited_cityIDs.get(rand.nextInt(unvisited_cityIDs.size() - 1));
                }

                current_city = lookup_table.get(nextCityId);
            }

            childCity.add(current_city);

            unvisited_cityIDs.remove(unvisited_cityIDs.indexOf(current_city.getID()));
            edgeMap = removeFromEdgeMap(edgeMap, current_city.getID());
        }



    }

    private Node pickNextCity(HashMap<Integer, Node> lookup_table, HashMap<Integer, HashSet<Integer>> edgeMap, int id) {

        Node next_city;
        ArrayList<Integer> possible_cities = new ArrayList<Integer>(edgeMap.get(id));

        // 3 Possibilities:
        // 4 cities to consider: all positive.
        // 3 cities to consider: one of them could be negative.
        // 2 cities to consider: both could be negative.

        if (possible_cities.size() == 3) {
            //Pick the negative one if it exists.
            for (int i = 0; i < 3; i++) {

                if (possible_cities.get(i) < 0) {
                    next_city = lookup_table.get(-1 * possible_cities.get(i));
                    return next_city;
                }
            }

        } else if (possible_cities.size() == 1) {
            next_city = lookup_table.get(Math.abs(possible_cities.get(0)));
            return next_city;
        }

        //If 1 of the 2 is negative, pick it for as the next city
        if (possible_cities.size() == 2) {

            int negs = 0;
            int index = 0;

            if (possible_cities.get(0) < 0) {
                ++negs;
                index = 0;
            }

            if (possible_cities.get(1) < 0) {
                ++negs;
                index = 1;
            }

            if (negs == 1) {
                next_city = lookup_table.get(Math.abs(possible_cities.get(index)));
                return next_city;
            }
        }

        //If not picking a negative, or if all are negative, pick the one with the least connections.
        int min_connections = Integer.MAX_VALUE;
        ArrayList<Integer> possible_choices = new ArrayList<Integer>();

        for (Map.Entry<Integer, HashSet<Integer>> edge: edgeMap.entrySet()) {
            // City in edge map listing could be positive or negative.
            if (possible_cities.contains(edge.getKey()) || possible_cities.contains(-1 * edge.getKey())) {

                int num_connections = edge.getValue().size();
                if (num_connections < min_connections) {

                    min_connections = num_connections;
                    possible_choices.clear();
                    possible_choices.add(edge.getKey());

                } else if (edge.getValue().size() == min_connections) {
                    possible_choices.add(edge.getKey());
                }
            }
        }

        //If tie for the least connections, randomly choose.
        if (possible_choices.size() == 1) {
            next_city = lookup_table.get(possible_choices.get(0));
        } else {
            Random rand = new Random();
            next_city = lookup_table.get(Math.abs(possible_choices.get(rand.nextInt(possible_choices.size() - 1))));
        }

        return next_city;
    }

    private HashMap<Integer, HashSet<Integer>> removeFromEdgeMap(HashMap<Integer, HashSet<Integer>> edgeMap, int id) {

        //Remove the given ID from all entries in edge map
        for (Map.Entry<Integer, HashSet<Integer>> edge : edgeMap.entrySet()) {

            HashSet<Integer> connections = edge.getValue();
            connections.remove(id);
            connections.remove(-1 * id);
        }

        return edgeMap;
    }

    private HashSet<Integer> getEdges(ArrayList<Node> cities_1, ArrayList<Node> cities_2, int index_1, int index_2) {

        HashSet<Integer> edges = new HashSet<Integer>();
        int size = cities_1.size();

        //Get parent1's edges
        int front = (index_1 + 1) % size;
        int back = (size + index_1 - 1) % size;
        edges.add(cities_1.get(front).getID());
        edges.add(cities_1.get(back).getID());

        //Get parent2's edges
        front = (index_2 + 1) % size;
        back = (size + index_2 - 1) % size;

        //Check if the city is already in parent1's list, if so, negate it
        int[] frontback = {front, back};
        for (int i = 0; i < 2; i++) {

            int id = cities_2.get(frontback[i]).getID();
            if (edges.contains(id)) {
                edges.remove(id);
                edges.add(-1 * id);
            } else
                edges.add(id);
        }

        return edges;
    }

    public ArrayList<Individual> getOffsprings() {
        return offsprings;
    }

    public void clear() {
        offsprings.clear();
    }

}







