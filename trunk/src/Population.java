import java.util.ArrayList;
import java.util.Arrays;

/**
 * Population represents a set of solutions.
 */

public class Population {
	
	//Create a population for individuals
	private Individual multiple_tours[]; 
	
	//Define the constructor to initialize the population
	public Population(int Population_Size, Map map)
	{
		multiple_tours = new Individual[Population_Size];
		
		for(int i = 0; i < Population_Size; i++)
		{
			Individual single_tour = new Individual(map);
			single_tour.CreateRandomTour();
			AddASingleTour(i, single_tour);
		}
	}
	
	//Define the second constructor to create additional population
	public Population(int Population_Size)
	{
		multiple_tours = new Individual[Population_Size];
	}
	
	//Add an individual to the population
	public void AddASingleTour(int index, Individual single_tour)
	{
		multiple_tours[index] = single_tour;
	}
	
	//Get an individual from the population
	public Individual GetASingleTour(int index)
	{
		return multiple_tours[index];
	}

    //Get all tours from population
    public ArrayList<Individual> GetAllTours() {
        return new ArrayList<Individual>(Arrays.asList(multiple_tours));
    }
	
	//Find out the shortest tour from the population
	public Individual FindShortest()
	{
		Individual Shortest = GetASingleTour(0);
		
		for(int i = 0; i < multiple_tours.length; i++)
		{
			if(Shortest.TotalDistance() > GetASingleTour(i).TotalDistance())
			{
				Shortest = GetASingleTour(i);
			}
		}
		
		return Shortest;	
	}
	
	//Return the size of the population
	public int PopulationSize()
	{
		return multiple_tours.length;
	}

    @Override
    public String toString() {
        String result = null;
        for (Individual multiple_tour : multiple_tours) {
            result += multiple_tour.toString() + "\n";
        }
        return result;
    }

}


