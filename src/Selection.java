import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Selection represents fitness based competition to select parents and offsprings
 */

public class Selection {

    private ArrayList<Individual> selections = new ArrayList<Individual>();

    public Selection() {}

    //Fitness-Proportionate Selection (RWS)
    public void Selection_FPS(ArrayList<Individual> tours, ArrayList<Double> raw_fitness, int pool_size) {

        double sum_of_fitness = 0.0;
        for (Double fitness: raw_fitness) {
            sum_of_fitness += fitness;
        }

        for (int i = 0; i < pool_size; i++) {

            double sum_of_probability = 0.0;
            Random rand = new Random();
            double decision = rand.nextDouble() * sum_of_fitness;

            for (int j = 0; j < tours.size(); j++) {

                if ((decision >= sum_of_probability) && (decision <= (sum_of_probability + raw_fitness.get(j)))) {
                    //System.out.println(decision + "   " + sum_of_probability + "   " + (sum_of_probability + raw_fitness.get(i)));
                    selections.add(tours.get(j));
                }

                sum_of_probability += raw_fitness.get(j);
            }
        }

    }

    //Tournament Selection
    public void Selection_Tournament(ArrayList<Individual> ranked_tours, int tournament_size,
                                           ArrayList<Integer> ranked_fitness, int pool_size) {

        ArrayList<Individual> tournament_pool = new ArrayList<Individual>(tournament_size);

        int ranked_sum = 0;
        for (Integer fitness : ranked_fitness) {
            //System.out.println(ranked_sum);
            ranked_sum += fitness;
        }
        //System.out.println(ranked_tours.get(0).toString());

        for (int k = 0; k < pool_size; k++) {

            //Pick i members based on their ranks
            for (int i = 0; i < tournament_size; i++) {

                int sum_of_probability = 0;
                Random rand = new Random();
                int decision = rand.nextInt(ranked_sum);
                //System.out.println(decision);
                for (int j = 0; j < ranked_tours.size(); j++) {

                    //System.out.println(sum_of_probability);
                    if ((decision >= sum_of_probability) && (decision <= (sum_of_probability + ranked_fitness.get(j)))) {
                        //System.out.println(decision + "   " + sum_of_probability + "   " + (sum_of_probability + ranked_fitness.get(i)));
                        tournament_pool.add(ranked_tours.get(j));
                    }

                    sum_of_probability += ranked_fitness.get(j);
                }

            }

            //Select the best of them
            Collections.sort(tournament_pool);

            /*for (Individual tour: tournament_pool) {
                System.out.println(tour.toString() + "    " + tour.TotalDistance());
            }*/
            /*for (Individual tour: ranked_tours) {
                System.out.println(tour.toString() + "    " + tour.TotalDistance());
            }*/

            selections.add(tournament_pool.get(0));
        }


    }

    public ArrayList<Individual> Selection_Elitism(ArrayList<Individual> tours, int num) {

        ArrayList<Individual> elites = new ArrayList<Individual>(num);
        ArrayList<Individual> sorted_tours = new ArrayList<Individual>(tours);

        Collections.sort(sorted_tours);

        for (int i = 0; i < num; i++) {
            elites.add(sorted_tours.get(i));
        }

        return elites;
    }


    public ArrayList<Individual> getSelections() {
        return selections;
    }

    public void clear() {
        selections.clear();
    }

}
