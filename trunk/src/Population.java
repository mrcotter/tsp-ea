import java.util.ArrayList;
import java.util.Collections;

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
	
	//Add all of the individuals
	public void AddAllTours(ArrayList<Individual> multi_tours)
	{
		multiple_tours.addAll(multi_tours);
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
	
	//Find out the shortest tour
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
	
	
	//Get top N individuals
	public ArrayList<Individual> TopN(int num)
	{
		ArrayList<Individual> TopN = new ArrayList<Individual>(num);
		ArrayList<Individual> Temp = new ArrayList<Individual>(multiple_tours);
		
		Collections.sort(Temp);
		
		for(int i = 0; i < num; i++)
		{
			TopN.add(Temp.get(i));
		}
		
		return TopN;	
	}
	
	
	//Get the total distance in a population
	public double SumOfDistance()
	{
		double sumUp = 0.0;

        for (Individual multiple_tour : multiple_tours) {
            sumUp += multiple_tour.TotalDistance();
        }
		
		return sumUp;
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


