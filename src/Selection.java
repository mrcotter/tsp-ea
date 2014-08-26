import java.util.ArrayList;
import java.util.Collections;

/**
 * Selection represents fitness based competition to select parents and offsprings
 */

public class Selection {

    public Selection() {}

    //Fitness-Proportionate Selection (RWS)
    public Individual Selection_FPS(ArrayList<Individual> tours, ArrayList<Double> raw_fitness) {

        double sum_of_fitness = 0.0;
        double sum_of_probability = 0.0;

        for (Double fitness: raw_fitness) {
            sum_of_fitness += fitness;
        }

        double decision = Math.random() * sum_of_fitness;

        for (int i = 0; i < tours.size(); i++) {

            if ((decision >= sum_of_probability) && (decision <= (sum_of_probability + raw_fitness.get(i)))) {
                //System.out.println(decision + "   " + sum_of_probability + "   " + (sum_of_probability + raw_fitness.get(i)));
                return tours.get(i);
            }

            sum_of_probability += raw_fitness.get(i);
        }

        return null;
    }

    //Tournament Selection
    public Individual Selection_Tournament(ArrayList<Individual> ranked_tours, int tournament_size,
                                           ArrayList<Integer> ranked_fitness) {

        ArrayList<Individual> tournament_pool = new ArrayList<Individual>(tournament_size);

        int ranked_sum = 0;
        for (Integer fitness : ranked_fitness) {
            //System.out.println(ranked_sum);
            ranked_sum += fitness;
        }
        //System.out.println(ranked_tours.get(0).toString());

        //Pick i members based on their ranks
        for (int i = 0; i < tournament_size; i++) {

            int sum_of_probability = 0;
            int decision = (int) (Math.random() * ranked_sum);
            //System.out.println(decision);
            for (int j = 0; j < ranked_tours.size(); j++) {

                //System.out.println(sum_of_probability);
                if ((decision >= sum_of_probability) && (decision <= (sum_of_probability + ranked_fitness.get(j)))) {
                    System.out.println(decision + "   " + sum_of_probability + "   " + (sum_of_probability + ranked_fitness.get(i)));
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

        return tournament_pool.get(0);
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


}
