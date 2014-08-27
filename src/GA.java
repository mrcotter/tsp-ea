import java.util.ArrayList;
import java.util.Collections;

/**
 * GA represents the process of evolving population algorithms
 */

public class GA {

    private int mut_type, cross_type, sel_type, elitism_size;
    private double mut_rate, cross_rate;
    private boolean elitism;

    public GA(double mut_rate, int mut_type, double cross_rate, int cross_type,
              int sel_type, boolean elitism, int elitism_size) {

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
        Selection p_select = new Selection();
        Selection c_select = new Selection();
        Crossover crossover = new Crossover();
        Mutation mutation = new Mutation();

        int pop_size = tours.size();

        ArrayList<Individual> next_generation = new ArrayList<Individual>();
        ArrayList<Individual> parents = new ArrayList<Individual>();
        ArrayList<Individual> children = new ArrayList<Individual>();

        ArrayList<Double> p_raw_fitness = new ArrayList<Double>();
        ArrayList<Individual> p_ranked_tours = null;
        ArrayList<Integer> p_ranked_fitness = new ArrayList<Integer>();

        ArrayList<Double> c_raw_fitness = new ArrayList<Double>();
        ArrayList<Individual> c_ranked_tours = null;
        ArrayList<Integer> c_ranked_fitness = new ArrayList<Integer>();


        if (sel_type == 1) {
            //Calculate raw fitness of a population
            getRawFitness(tours, p_raw_fitness);
        }

        if (sel_type == 2) {
            //Calculate ranked fitness of a population
            p_ranked_tours = getRanked_fitness(tours, p_ranked_fitness);
        }

        //System.out.println(raw_fitness.get(0).toString());
        //System.out.println(ranked_fitness.get(0).toString());

        if (!elitism) {
            elitism_size = 0;
        }

        if (elitism) {
            next_generation.addAll(p_select.Selection_Elitism(tours, elitism_size));
        }


        //Select parents for the mating pool
        switch (sel_type) {
            case 1:     //FPS
                p_select.Selection_FPS(tours, p_raw_fitness, pop_size);
                break;

            case 2:     //Tournament
                p_select.Selection_Tournament(p_ranked_tours, 2, p_ranked_fitness, pop_size);
                break;
        }

        parents.addAll(p_select.getSelections());
        p_select.clear();

        //Shuffle the mating pool
        Collections.shuffle(parents);
        //System.out.println(parents.size());

        for (int i = 0; i < parents.size() - 1; i++) {

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
                children.add(parents.get(i));
                children.add(parents.get(i+1));
            }

        }

        children.addAll(crossover.getOffsprings());
        crossover.clear();
        //System.out.println(children.size());

        //int count = 0;
        //Mutate the next generation a bit
        for (int i = elitism_size; i < children.size(); i++) {

            //count++;
            switch (mut_type) {
                case 1:     //Insert
                    mutation.Mutation_Insert(children.get(i), mut_rate);
                    break;

                case 2:     //Swap
                    mutation.Mutation_Swap(children.get(i), mut_rate);
                    break;

                case 3:     //Inversion
                    mutation.Mutation_Inversion(children.get(i), mut_rate);
                    break;

                case 4:     //Scramble
                    mutation.Mutation_Scramble(children.get(i), mut_rate);
                    break;
            }
        }
        //System.out.println(count);

        //Get children's fitness
        if (sel_type == 1) {
            getRawFitness(children, c_raw_fitness);
        }

        if (sel_type == 2) {
            //System.out.println(c_ranked_fitness);
            c_ranked_tours = getRanked_fitness(children, c_ranked_fitness);
            //System.out.println(c_ranked_fitness);
        }


        //Select children to the next generation
        switch (sel_type) {
            case 1:     //FPS
                c_select.Selection_FPS(children, c_raw_fitness, pop_size);
                break;

            case 2:     //Tournament
                c_select.Selection_Tournament(c_ranked_tours, 2, c_ranked_fitness, pop_size);
                break;
        }

        next_generation.addAll(c_select.getSelections());
        c_select.clear();
        //System.out.println(next_generation.size());


        if (sel_type == 1) {
            p_raw_fitness.clear();
            c_raw_fitness.clear();
        }

        if (sel_type == 2) {
            p_ranked_fitness.clear();
            if (p_ranked_tours != null) {
                p_ranked_tours.clear();
            }

            c_ranked_fitness.clear();
            if (c_ranked_tours != null) {
                c_ranked_tours.clear();
            }
        }

        //Return next_generation;
        return next_generation;

    }

    private ArrayList<Individual> getRanked_fitness(ArrayList<Individual> tours, ArrayList<Integer> ranked_fitness) {

        ArrayList<Individual> ranked_tours = new ArrayList<Individual>(tours);

        //Sort the list based on total distance
        Collections.sort(ranked_tours);

        /*for (Individual tour: ranked_tours) {
            System.out.println(tour.toString() + "    " + tour.TotalDistance());
        }*/

        for (int i = 1; i <= ranked_tours.size(); i++) {
            ranked_fitness.add(i);
        }

        /*System.out.println(ranked_fitness.toString());
        for (Individual tour: ranked_tours) {
            System.out.println(tour.TotalDistance());
        }*/

        return ranked_tours;

    }

    private void getRawFitness(ArrayList<Individual> tours, ArrayList<Double> raw_fitness) {

        for (Individual tour: tours) {
            //System.out.println(tour.toString());
            //System.out.println(tour.TotalDistance());
            raw_fitness.add(tour.TotalDistance());
        }
    }


}
