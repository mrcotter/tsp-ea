import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Selection represents fitness based competition to select parents and offsprings
 */

public class Selection {

    private ArrayList<Double> raw_fitness;
    ArrayList<Individual> ranked_tours;
    private ArrayList<Integer> ranked_fitness;

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

        int ranked_sum = getRanked_fitness(tours);

        double sum_of_probability = 0.0;
        double decision = Math.random() * ranked_sum;

        for (int i = 0; i < tournament_size; i++) {
            if (decision >= sum_of_probability && decision <= (sum_of_probability + ranked_fitness.get(i))) {
                System.out.println(ranked_tours.get(i).toString());
                return ranked_tours.get(i);
            }

            sum_of_probability += ranked_fitness.get(i);
        }

        return null;
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

    //Calculate the ranks for given tours
    private int getRanked_fitness(ArrayList<Individual> tours) {

        int size = tours.size();
        ranked_tours = new ArrayList<Individual>(tours);
        ranked_fitness = new ArrayList<Integer>(size);

        //Sort the list based on total distance
        Collections.sort(ranked_tours, new Comparator<Individual>() {
            @Override
            public int compare(Individual tour_1, Individual tour_2) {

                if (tour_1.TotalDistance() == tour_2.TotalDistance()) {
                    return 0;
                } else {
                    return tour_1.TotalDistance() > tour_2.TotalDistance() ? 1 : -1;
                }
            }
        });

        /*
        for (Individual tour: copy_tours) {
            System.out.println(tour.toString() + "    " + tour.TotalDistance());
        }*/

        int sum = 0;
        for (int i = 1; i <= size; i++) {
            sum += i;
            ranked_fitness.add(i);
        }

        return sum;
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
