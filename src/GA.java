import java.util.ArrayList;
import java.util.Collections;

/**
 * GA represents the process of evolving population algorithms
 */

public class GA {

    private int pop_size, generation, mut_type, cross_type, sel_type;
    private double mut_rate, cross_rate;

    private TSPProblem tsp;
    private ArrayList<Individual> next_generation;

    public GA(TSPProblem tsp, int pop_size, int generations, double mut_rate,
              int mut_type, double cross_rate, int cross_type, int sel_type) {

        this.tsp = tsp;
        this.pop_size = pop_size;
        this.generation = generations;
        this.mut_rate = mut_rate;
        this.mut_type = mut_type;
        this.cross_rate = cross_rate;
        this.cross_type = cross_type;
        this.sel_type = sel_type;
    }

    public void runGA() {
        //Initialisation
        Population pop = new Population(pop_size, tsp.getMap());

        //Select parents for the mating pool
        Selection select = new Selection();
        ArrayList<Individual> parents = new ArrayList<Individual>();
        switch (sel_type) {

            case 1:     //FPS
                for (int i = 0; i < pop.PopulationSize(); i++) {
                    parents.add(select.Selection_FPS(pop.GetAllTours()));
                }
                break;

            case 2:     //Tournament
                for (int i = 0; i < pop.PopulationSize(); i++) {
                    parents.add(select.Selection_Tournament(pop.GetAllTours(), 5));
                }
                break;
        }

        //Shuffle the mating pool
        Collections.shuffle(parents);

        //For each consecutive pair apply crossover with a given probability, otherwise copy parents


    }


}
