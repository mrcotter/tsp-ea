import java.util.ArrayList;
import java.util.Collections;

/**
 * GA represents the process of evolving population algorithms
 */

public class GA {

    private int pop_size, generation, mut_type, cross_type, sel_type, elitism_size;
    private double mut_rate, cross_rate;
    private boolean elitism;

    private TSPProblem tsp;
    private ArrayList<Individual> next_generation;



    public GA(TSPProblem tsp, int pop_size, int generations, double mut_rate, int mut_type,
              double cross_rate, int cross_type, int sel_type, boolean elitism, int elitism_size) {

        this.tsp = tsp;
        this.pop_size = pop_size;
        this.generation = generations;
        this.mut_rate = mut_rate;
        this.mut_type = mut_type;
        this.cross_rate = cross_rate;
        this.cross_type = cross_type;
        this.sel_type = sel_type;
        this.elitism = elitism;
        this.elitism_size = elitism_size;
    }

    public void runGA() {
        //Initialisation
        Population pop = new Population(pop_size, tsp.getMap());
        Selection select = new Selection();
        Crossover crossover = new Crossover();
        Mutation mutation = new Mutation();
        next_generation = new ArrayList<Individual>(pop_size);

        if (!elitism) {
            elitism_size = 0;
        }

        //Select parents for the mating pool

        //ArrayList<Individual> parents = new ArrayList<Individual>(pop.GetAllTours().size());

        if (elitism) {
            next_generation.addAll(select.Selection_Elitism(pop.GetAllTours(), elitism_size));
        }

        for (int i = elitism_size; i < pop_size/2; i++) {

            switch (sel_type) {

                case 1:     //FPS
                    Individual parent
            }
        }

        //Shuffle the mating pool
        Collections.shuffle(next_generation);

        //For each consecutive pair apply crossover with a given probability, otherwise copy parents


    }


}
