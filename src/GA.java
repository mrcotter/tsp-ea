import java.util.ArrayList;

/**
 * GA represents the process of evolving population algorithms
 */

public class GA {

    private int pop_size, mut_type, cross_type, sel_type, elitism_size;
    private double mut_rate, cross_rate;
    private boolean elitism;


    public GA(int pop_size, double mut_rate, int mut_type,
              double cross_rate, int cross_type, int sel_type, boolean elitism, int elitism_size) {

        this.pop_size = pop_size;
        this.mut_rate = mut_rate;
        this.mut_type = mut_type;
        this.cross_rate = cross_rate;
        this.cross_type = cross_type;
        this.sel_type = sel_type;
        this.elitism = elitism;
        this.elitism_size = elitism_size;
    }

    public ArrayList<Individual> runGA(ArrayList<Individual> tours) {

        //Initialisation
        Selection select = new Selection();
        Crossover crossover = new Crossover();
        Mutation mutation = new Mutation();
        ArrayList<Individual> next_generation = new ArrayList<Individual>(pop_size);

        if (!elitism) {
            elitism_size = 0;
        }

        if (elitism) {
            next_generation.addAll(select.Selection_Elitism(tours, elitism_size));
        }


        Individual parent_1 = null, parent_2 = null;
        for (int i = elitism_size; i < pop_size/2; i++) {

            //Select parents for the mating pool
            switch (sel_type) {
                case 1:     //FPS
                    parent_1 = select.Selection_FPS(tours);
                    parent_2 = select.Selection_FPS(tours);
                    break;

                case 2:     //Tournament
                    parent_1 = select.Selection_Tournament(tours, 5);
                    parent_2 = select.Selection_Tournament(tours, 5);
                    break;
            }

            //System.out.println(parent_1.toString() + "  " + parent_1.NumberOfNodes());
            //System.out.println(parent_2.toString() + "  " + parent_2.NumberOfNodes());

            //Crossover parents
            if (Math.random() <= cross_rate) {

                switch (cross_type) {
                    case 1:     //Order
                        crossover.Crossover_Order(parent_1, parent_2);
                        break;

                    case 2:     //PMX
                        crossover.Crossover_PMX(parent_1, parent_2);
                        break;

                    case 3:     //Cycle
                        crossover.Crossover_Cycle(parent_1, parent_2);
                        break;

                    case 4:

                        break;
                }
            } else {
                next_generation.add(parent_1);
                next_generation.add(parent_2);
            }

        }

        next_generation.addAll(crossover.getOffsprings());
        crossover.clear();

        int count = 0;
        //Mutate the next generation a bit
        for (int i = elitism_size; i < pop_size; i++) {

            if(Math.random() <= mut_rate) {
                count++;
                switch (mut_type) {
                    case 1:     //Insert
                        mutation.Mutation_Insert(next_generation.get(i));
                        break;

                    case 2:     //Swap
                        mutation.Mutation_Swap(next_generation.get(i));
                        break;

                    case 3:     //Inversion
                        mutation.Mutation_Inversion(next_generation.get(i));
                        break;

                    case 4:     //Scramble
                        mutation.Mutation_Scramble(next_generation.get(i));
                        break;
                }
            }

        }

        //System.out.println(count);

        //Return next_generation;
        return next_generation;

    }


}
