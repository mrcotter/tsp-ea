import java.util.ArrayList;


public class InverOver {
	
	private Population pop;
	
	public InverOver(Population pop)
	{
		this.pop = pop;
	}
	
	public Population Implement(double prob)
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
				
			RandomPos = (int) (Math.random() * Current_Tour.NumberOfNodes());
				
			City = Current_Tour.GetANode(RandomPos);
				
			NextCity = Current_Tour.GetANode(RandomPos+1);
				
			PrevCity = Current_Tour.GetANode(RandomPos-1);

			do
			{
				if(Math.random() <= prob)
				{
					NextPos = (int) (Math.random() * Current_Tour.NumberOfNodes());
					while(RandomPos == NextPos)
					{
						NextPos = (int) (Math.random() * Current_Tour.NumberOfNodes());
					}
					
					City_ = Current_Tour.GetANode(NextPos);
					
				}
				else
				{
					int Random_Individual = (int) (Math.random() * pop.PopulationSize());
					while(Random_Individual == i)
					{
						Random_Individual = (int) (Math.random() * pop.PopulationSize());
					}
					
					Individual Temp_Tour = pop.GetASingleTour(Random_Individual);
					
					City_ = AchieveNEXT(Temp_Tour, City);
					
					NextPos = FindPos(City_, Current_Tour);
					
				}

			}while(City_.getID() != NextCity.getID() && City_.getID() != PrevCity.getID());
				
			InverseSET(RandomPos, NextPos, Current_Tour);
				
			City = City_;
				
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
				return T_Tour.GetANode(k+1);
				
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












