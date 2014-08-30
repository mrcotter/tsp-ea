import java.util.ArrayList;

/**
 * Selection represents fitness based competition to select parents and offsprings
 */

public class Selection {

    //ArrayList<Individual> ranked_tours;

    public Selection() {}

    //Fitness Proportionate Selection (RWS)
    public Individual Selection_FPS(Population pop) {

        double Total_fitness = pop.SumOfDistance();
        double sum_of_fitness = 0.0;

        double decision = Math.random() * Total_fitness;

        for(int i = 0; i < pop.PopulationSize(); i++)
        {	
        	sum_of_fitness += pop.GetASingleTour(i).TotalDistance();
        	if(sum_of_fitness >= decision)
        	{
        		return pop.GetASingleTour(i);
        	}
        }

        return null;
    }

    //Tournament Selection
    public Individual Selection_Tournament(Population pop, int tournament_size) {
    	
    	Population Tournament_Select = new Population(tournament_size);
    	
    	for(int i = 0; i<tournament_size; i++)
    	{
    		int random_Tour = (int) (Math.random() * pop.PopulationSize());
    		Tournament_Select.AddASingleTour(pop.GetASingleTour(random_Tour));
    	}

        return Tournament_Select.FindShortest();
    }

    //Elitism Selection
    public ArrayList<Individual> Selection_Elitism(Population pop, int elitism_size) {

        return pop.TopN(elitism_size);
    }

}

