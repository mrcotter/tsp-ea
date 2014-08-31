import java.util.ArrayList;
import java.util.Random;


public class InverOver {
		
	public InverOver()
	{
		
	}
	
	public Population Implement(double prob, Population pop)
	{
		Individual Current_Tour = null;
		Node City = null;
		Node City_ = null;
		Node NextCity = null;
		Node PrevCity = null;
		int RandomPos = 0;
		int NextPos = 0;

		for(int i = 0; i < pop.PopulationSize(); i++)
		{
			Current_Tour = pop.GetASingleTour(i);
			long seed = System.nanoTime();
			Random rand = new Random(seed);
			RandomPos = (rand.nextInt(Current_Tour.NumberOfNodes()-2) + 1);
			
			City = Current_Tour.GetANode(RandomPos);
			
			PrevCity = Current_Tour.GetANode(RandomPos-1);
			
			NextCity = Current_Tour.GetANode(RandomPos+1);
							
			do
			{
				if(Math.random() <= prob)
				{
					NextPos =  (rand.nextInt(Current_Tour.NumberOfNodes()-2) + 1);
					while(RandomPos == NextPos)
					{
						NextPos =  (rand.nextInt(Current_Tour.NumberOfNodes()-2) + 1);
					}
					
					City_ = Current_Tour.GetANode(NextPos);
					
				}
				else
				{
					int Random_Individual = rand.nextInt(pop.PopulationSize()-1);
					while(Random_Individual == i)
					{
						Random_Individual = rand.nextInt(pop.PopulationSize()-1);
					}
					
					Individual Temp_Tour = pop.GetASingleTour(Random_Individual);
					
					City_ = AchieveNEXT(Temp_Tour, City);
					
					NextPos = FindPos(City_, Current_Tour);
					
				}
				
				InverseSET(RandomPos, NextPos, Current_Tour);
				
				City = City_;

			}while(City_.getID() != NextCity.getID() && City_.getID() != PrevCity.getID());
				
			
				
			if(pop.GetASingleTour(i).TotalDistance() >= Current_Tour.TotalDistance())
			{
				for(int j = 0; j < pop.GetASingleTour(i).NumberOfNodes(); j++)
				{
					pop.GetASingleTour(i).SetANode(j, Current_Tour.GetANode(j));
				}
					
			}
				
		}
		
		return pop;
	}
	
	
	private Node AchieveNEXT(Individual T_Tour, Node city)
	{
		
		for(int k = 0; k<T_Tour.NumberOfNodes(); k++)
		{
			if(T_Tour.GetANode(k).getID() == city.getID())
			{
				if(k == T_Tour.NumberOfNodes()-1)
				{
					return T_Tour.GetANode(0);
				}
				else
				{
					return T_Tour.GetANode(k+1);
				}
			}
		}
			
		return null;
	}
	
	
	private int FindPos(Node Next, Individual C_Tour)
	{
		for(int i = 0; i < C_Tour.NumberOfNodes(); i++)
		{
			if(C_Tour.GetANode(i).getID() == Next.getID())
			{
				return i;
			}
		}
		
		return 0;
	}
	
	
	private void InverseSET(int Pos_1, int Pos_2, Individual Current_Tour)
	{
		if(Pos_1 > Pos_2)
		{
			ArrayList<Node> temp = new ArrayList<Node>();
			int temp_num = Pos_1;
			for(int j = Pos_2+1; j <= Pos_1; j++)
			{
				temp.add(Current_Tour.GetANode(j));
			}
			
			for(int j = 0; j < temp.size(); j++)
			{
				Current_Tour.SetANode(temp_num, temp.get(j));
				temp_num--;
			}
		}
		
		if(Pos_2 > Pos_1)
		{
			ArrayList<Node> temp = new ArrayList<Node>();
			int temp_num = Pos_2; 
			for(int j = Pos_1+1; j <= Pos_2; j++)
			{
				temp.add(Current_Tour.GetANode(j));
			}
			
			for(int j = 0; j < temp.size(); j++)
			{
				Current_Tour.SetANode(temp_num, temp.get(j));
				temp_num--;
			}
		}
	}
	
	

}












