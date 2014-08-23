import java.util.ArrayList;

/**
 * Population represents a set of solutions.
 */

public class Population {
	
	//Create a population for individuals
	private ArrayList<Individual> multiple_tours;

    //Fitness for all the tours in the population
    private ArrayList<Double> raw_fitness;
	
	//Define the constructor to initialize the population
	public Population(int Population_Size, Map map)
	{
		multiple_tours = new ArrayList<Individual>(Population_Size);
		
		for (int i = 0; i < Population_Size; i++)
		{
			Individual single_tour = new Individual(map);
			single_tour.CreateRandomTour();
			AddASingleTour(i, single_tour);
		}

        GetRawFitness();
	}
	
	//Define the second constructor to create additional population
	public Population(int Population_Size)
	{
        multiple_tours = new ArrayList<Individual>(Population_Size);
	}
	
	//Add an individual to the population
	public void AddASingleTour(int index, Individual single_tour)
	{
		multiple_tours.add(index, single_tour);
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
	
	//Find out the shortest tour from the population
	public Individual FindShortest()
	{
		Individual Shortest = GetASingleTour(0);
		
		for(int i = 0; i < multiple_tours.size(); i++)
		{
			if(Shortest.TotalDistance() > GetASingleTour(i).TotalDistance())
			{
				Shortest = GetASingleTour(i);
			}
		}
		
		return Shortest;	
	}

    //Calculate and collect the raw fitness for all the tours
    public ArrayList<Double> GetRawFitness() {
        for (Individual tour: multiple_tours) {
            raw_fitness.add(tour.TotalDistance());
        }

        return raw_fitness;
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


