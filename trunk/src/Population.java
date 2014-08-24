import java.util.ArrayList;

/**
 * Population represents a set of solutions.
 */

public class Population {
	
	//Create a population for individuals
	private ArrayList<Individual> multiple_tours;
	
	//Define the constructor to initialize the population
	public Population(int Population_Size, Map map)
	{
		multiple_tours = new ArrayList<Individual>(Population_Size);
		
		for (int i = 0; i < Population_Size; i++)
		{
			Individual single_tour = new Individual(map);
			single_tour.CreateRandomTour();
			AddASingleTour(single_tour);
		}
	}
	
	//Define the second constructor to create additional population
	public Population(int Population_Size)
	{
        multiple_tours = new ArrayList<Individual>(Population_Size);
	}
	
	//Add an individual to the population
	public void AddASingleTour(Individual single_tour)
	{
		multiple_tours.add(single_tour);
	}
	
	//Get an individual from the population
	public Individual GetASingleTour(int index)
	{
		return multiple_tours.get(index);
	}

    //Get all tours from population
    public ArrayList<Individual> GetAllTours() {
        return multiple_tours;
    }

	
	//Return the size of the population
	public int PopulationSize()
	{
		return multiple_tours.size();
	}

    @Override
    public String toString() {
        String result = "";
        for (Individual multiple_tour : multiple_tours) {
            result += multiple_tour.toString() + "\n";
        }
        return result;
    }

}


