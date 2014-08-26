import java.util.ArrayList;
import java.util.Collections;

/**
 * GA represents the process of evolving population algorithms
 */

public class GA {

    private int pop_size, mut_type, cross_type, sel_type, elitism_size;
    private double mut_rate, cross_rate;
    private boolean elitism;

    private ArrayList<Double> raw_fitness;
    private ArrayList<Individual> ranked_tours;
    private ArrayList<Integer> ranked_fitness;

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
        ArrayList<Individual> parents = new ArrayList<Individual>(pop_size);


        if (sel_type == 1) {
            //Calculate raw fitness of a population
            getRawFitness(tours);
        }

        if (sel_type == 2) {
            //Calculate ranked fitness of a population
            getRanked_fitness(tours);
        }

        //System.out.println(raw_fitness.get(0).toString());
        //System.out.println(ranked_fitness.get(0).toString());

        if (!elitism) {
            elitism_size = 0;
        }

        if (elitism) {
            next_generation.addAll(select.Selection_Elitism(tours, elitism_size));
        }


        Individual parent_1 = null, parent_2 = null;
        for (int i = elitism_size; i < pop_size/2; i++) {

            //Select parents
            switch (sel_type) {
                case 1:     //FPS
                    parent_1 = select.Selection_FPS(tours, raw_fitness);
                    parent_2 = select.Selection_FPS(tours, raw_fitness);
                    parents.add(parent_1);
                    parents.add(parent_2);
                    break;

                case 2:     //Tournament
                    parent_1 = select.Selection_Tournament(tours, 2, ranked_fitness, ranked_tours);
                    parent_2 = select.Selection_Tournament(tours, 2, ranked_fitness, ranked_tours);
                    parents.add(parent_1);
                    parents.add(parent_2);
                    break;
            }
        }

        Collections.shuffle(parents);
        //System.out.println(parents.size());

        for (int i = 0; i < parents.size(); i = i + 2) {

            //Crossover parents
            if (Math.random() <= cross_rate) {

                switch (cross_type) {
                    case 1:     //Order
                        crossover.Crossover_Order(parents.get(i), parents.get(i+1));
                        break;

                    case 2:     //PMX
                        crossover.Crossover_PMX(parents.get(i), parents.get(i+1));
                        break;

                    case 3:     //Cycle
                        crossover.Crossover_Cycle(parents.get(i), parents.get(i+1));
                        break;

                    case 4:
                        crossover.Crossover_Edge_Recombination(parents.get(i), parents.get(i+1));
                        crossover.Crossover_Edge_Recombination(parents.get(i+1), parents.get(i));
                        break;
                }
            } else {
                next_generation.add(parents.get(i));
                next_generation.add(parents.get(i+1));
            }

        }

        next_generation.addAll(crossover.getOffsprings());
        crossover.clear();

        //int count = 0;
        //Mutate the next generation a bit
        for (int i = elitism_size; i < next_generation.size(); i++) {

            //count++;
            switch (mut_type) {
                case 1:     //Insert
                    mutation.Mutation_Insert(next_generation.get(i), mut_rate);
                    break;

                case 2:     //Swap
                    mutation.Mutation_Swap(next_generation.get(i), mut_rate);
                    break;

                case 3:     //Inversion
                    mutation.Mutation_Inversion(next_generation.get(i), mut_rate);
                    break;

                case 4:     //Scramble
                    mutation.Mutation_Scramble(next_generation.get(i), mut_rate);
                    break;
            }

        }
        //System.out.println(count);

        if (sel_type == 1) {
            raw_fitness.clear();
        }

        if (sel_type == 2) {
            ranked_fitness.clear();
            ranked_tours.clear();
        }

        //Return next_generation;
        return next_generation;

    }

    private void getRanked_fitness(ArrayList<Individual> tours) {

        int size = tours.size();
        ranked_tours = new ArrayList<Individual>(tours);
        ranked_fitness = new ArrayList<Integer>(size);

        //Sort the list based on total distance
        Collections.sort(ranked_tours);

        /*for (Individual tour: ranked_tours) {
            System.out.println(tour.toString() + "    " + tour.TotalDistance());
        }*/

        for (int i = 1; i <= size; i++) {
            ranked_fitness.add(i);
        }

        //System.out.println(ranked_fitness.toString());
    }

    private void getRawFitness(ArrayList<Individual> tours) {

        raw_fitness = new ArrayList<Double>(tours.size());

        for (Individual tour: tours) {
            //System.out.println(tour.toString());
            //System.out.println(tour.TotalDistance());
            raw_fitness.add(tour.TotalDistance());
        }
    }


}
