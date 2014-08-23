import java.util.ArrayList;

/**
 * Selection represents fitness based competition to select parents and offsprings
 */

public class Selection {

    private ArrayList<Double> raw_fitness;

    public Selection() {}

    //Fitness-Proportionate Selection
    public Individual Selection_FPS(ArrayList<Individual> tours) {

        getRawFitness(tours);

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

    //Calculate the fitness for all tours in given list containing tours
    private void getRawFitness(ArrayList<Individual> tours) {
        raw_fitness = new ArrayList<Double>(tours.size());

        for (Individual tour: tours) {
            //System.out.println(tour.toString());
            //System.out.println(tour.TotalDistance());
            raw_fitness.add(tour.TotalDistance());
        }
    }


}
