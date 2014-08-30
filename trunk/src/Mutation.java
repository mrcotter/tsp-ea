import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Mutation represents a set of mutation operators for permutations
 */

public class Mutation {

    //private Individual individual;

    public Mutation() {}

    //Mutation by using insert method
    public void Mutation_Insert(Individual individual, double mut_rate) {

        int size = individual.NumberOfNodes();
        ArrayList<Node> nodes = individual.getNodeList();

        for (Node node_1: nodes) {

            Random rand = new Random();
            if (rand.nextDouble() <= mut_rate) {

                int index_1 = nodes.indexOf(node_1);
                int index_2;

                do {
                    Random rand_index = new Random();
                    index_2 = rand_index.nextInt(size);
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

            Random rand = new Random();
            if (rand.nextDouble() <= mut_rate) {

                int index_1 = nodes.indexOf(node_1);
                int index_2;

                do {
                    Random rand_index = new Random();
                    index_2 = rand_index.nextInt(size);
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

            Random rand = new Random();
            if (rand.nextDouble() <= mut_rate) {

                int index_1 = nodes.indexOf(node_1);
                int index_2;

                do {
                    Random rand_index = new Random();
                    index_2 = rand_index.nextInt(size);
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
        //System.out.println(individual.toString());

        for (Node node_1: nodes) {

            Random rand = new Random();
            if (rand.nextDouble() <= mut_rate) {

                int index_1 = nodes.indexOf(node_1);
                int index_2;

                do {
                    Random rand_index = new Random();
                    index_2 = rand_index.nextInt(size);
                } while (index_1 == index_2);

                int lower_index = Math.min(index_1, index_2);
                int bigger_index = Math.max(index_1, index_2);

                //Shuffle the sublist ranged from lower index to bigger index
                Collections.shuffle(nodes.subList(lower_index, bigger_index));
            }
        }

        //System.out.println(individual.toString() + "\n");

    }

}
