import java.util.ArrayList;
import java.util.Random;

/**
 * Selection represents fitness based competition to select parents and offsprings
 */

public class Selection {

    private ArrayList<Double> raw_fitness;

    public Selection() {}

    //Fitness-Proportionate Selection
    public Individual Selection_FPS(ArrayList<Individual> tours) {

        getRawFitness(tours);    //Move to GA later

        double sum_of_fitness = 0.0;
        double sum_of_probability = 0.0;

        for (Double fitness: raw_fitness) {
            sum_of_fitness += fitness;
        }

        double decision = Math.random() * sum_of_fitness;

        for (int i = 0; i < tours.size(); i++) {

            if (decision >= sum_of_probability && decision <= (sum_of_probability + raw_fitness.get(i))) {
                //System.out.println(tours.get(i).toString());
                return tours.get(i);
            }

            sum_of_probability += raw_fitness.get(i);
        }

        return null;
    }

    //Tournament Selection
    public Individual Selection_Tournament(ArrayList<Individual> tours, int tournament_size) {

        ArrayList<Individual> tournament_pool = new ArrayList<Individual>(tournament_size);

        for (int i = 0; i < tournament_size; i++) {
            //Add random individual to the tournament pool
            Random rand = new Random();
            tournament_pool.add(tours.get(rand.nextInt(tours.size())));
        }

        //System.out.println(findShortest(tournament_pool).toString());

        return findShortest(tournament_pool);
    }


    //Move to GA later
    //Calculate the fitness for all tours in given list containing tours
    private void getRawFitness(ArrayList<Individual> tours) {
        raw_fitness = new ArrayList<Double>(tours.size());

        for (Individual tour: tours) {
            //System.out.println(tour.toString());
            //System.out.println(tour.TotalDistance());
            raw_fitness.add(tour.TotalDistance());
        }
    }

    //Find out the shortest tour from the tournament pool or other collections of tours
    public Individual findShortest(ArrayList<Individual> tours) {

        Individual shortest = tours.get(0);

        for(int i = 1; i < tours.size(); i++) {

            if (shortest.TotalDistance() > tours.get(i).TotalDistance()) {
                shortest = tours.get(i);
            }
        }

        return shortest;
    }


}
