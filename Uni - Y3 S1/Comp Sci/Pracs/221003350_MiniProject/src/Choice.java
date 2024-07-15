import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;

public class Choice implements Serializable
{

	private int id;
	private static int idCounter = 0;
	private boolean visited;
	private boolean displayed;
	private boolean edgeDisplayed;
	private int x;
	private int y;
	private int childnumber = 0;
	private int arrow;
	
	
	private String name;
	private String description;
	private ArrayList<String> advantages = new ArrayList<String>();
	private ArrayList<String> disadvantages = new ArrayList<String>();
	private double monthlyRandCost;
	private int numMonths;
	private double totalRandCost;
	private int monthlyTimeCost;
	private int totalTimeCost;
	
	private boolean isDeleted;
	/**
	 * Name
	 * Description
	 * Advantages
	 * Disadvantages
	 * Monthly Cost
	 * Number Months
	 * Total Cost
	 * 
	 * Monthly Time Cost
	 * Total Time Cost
	 * 
	 */
	
	public Choice()
	{
		idCounter++;
		if(idCounter == 0)
		{
			id = idCounter;
			setResults(id);
		}else
		{
			id = idCounter;
			setResults(id);
		}
		visited = false;
		edgeDisplayed = false;
		isDeleted = false;

	}
	

	
	public static void idCounterSet(int num)
	{
		idCounter = num;
	}
	
	public static int getCounter()
	{
		return idCounter;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public boolean getVisited()
	{
		return visited;
	}
	
	public void setVisited(boolean isVisit)
	{
		visited = isVisit;
	}
	
	public void setArrow(int arrow)
	{
		this.arrow = arrow;
	}
	
	public void setDeleted()
	{
		isDeleted = true;
	}
	
	public boolean getDeleted()
	{
		return isDeleted;
	}
	
	public int getArrow()
	{
		return arrow;
	}
	
	public boolean getDisplayed()
	{
		return displayed;
	}
	
	public void setDisplayed(boolean isDisplay)
	{
		displayed = isDisplay;
	}
	
	public boolean getEdgeDisplayed()
	{
		return edgeDisplayed;
	}
	
	public void setEdgeDisplayed(boolean isDisplay)
	{
		edgeDisplayed = isDisplay;
	}
	
	
	public int getID()
	{
		return id;
	}

	public int getY()
	{
		return y;
	}

	public int getX()
	{
		return x;
	}
	
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setChildNum(int childnumber)
	{
		
		this.childnumber = childnumber;
	}
	
	public int getChildNum()
	{
		
		return childnumber;
	}
	
	public double getTotalRand()
	{
		return totalRandCost;
	}
	
	public double getTotalTime()
	{
		return totalTimeCost;
	}
	
	public void setTotalRand()
	{
		totalRandCost = monthlyRandCost*numMonths;
	}
	
	public void setTotalTime()
	{
		totalTimeCost = monthlyTimeCost*numMonths;
	}
	
	public String toString()
	{
		String display = "";
		display += "Name: " + name + "\n\n";
		display += "Description: " + description + "\n\n";
		
		if(!advantages.isEmpty())
		{
			display += "Advantages: " + "\n";
			
			int i = 0;
			for(String s : advantages)
			{
				i++;
				display += "Advantage " + i + " :" + s + "\n";
			}
			display += "\n";
			i = 0;
			display += "\nDisadvantages:: " + "\n";
			for(String s : disadvantages)
			{
				i++;
				display += "Disadvantage " + i + " :" + s + "\n";
			}
			display += "\n";
			
			
			display += "Number of Months: " + numMonths + "\n\n";
			display += "Monthly Rand Cost: R" + monthlyRandCost + "\n\n";
			display += "Total Rand Cost: R" + totalRandCost + "\n\n";
			display += "Monthly Time Cost: " + monthlyTimeCost + "hrs\n\n";
			display += "Total Time Cost: " + totalTimeCost + "hrs\n\n";
		}
		
		
		return display;		
	}
	

	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public ArrayList<String> getAdvantages()
	{
		return advantages;
	}
	
	public void setAdvantages(String advantage, int index)
	{
		advantages.set(index, advantage);
	}
	
	public void addAdvantage(String s)
	{
		advantages.add(s);
	}
	
	public void removeAdvantage(int i)
	{
		advantages.remove(i);
	}

	
	public ArrayList<String> getDisadvantages()
	{
		return disadvantages;
	}
	
	public void setDisadvantages(String disadvantage, int index)
	{
		disadvantages.set(index, disadvantage);
	}
	
	public void removeDisadvantage(int i)
	{
		disadvantages.remove(i);
	}
	
	public void addDisadvantages(String s)
	{
		disadvantages.add(s);
	}
	
	
	public int getNumMonths()
	{
		return numMonths;
	}
	
	public void setNumMonths(int numMonths)
	{
		this.numMonths = numMonths;
	}
	
	public int getMonthlyTimeCost()
	{
		return monthlyTimeCost;
	}
	
	public void setMonthlyTimeCost(int monthlyTimeCost)
	{
		this.monthlyTimeCost = monthlyTimeCost;
	}
	
	public double getMonthlyRandCost()
	{
		return monthlyRandCost;
	}
	
	public void setMonthlyRandCost(double monthlyRandCost)
	{
		this.monthlyRandCost = monthlyRandCost;
	}


	
	public void setResults(int choiceNum)
	{
		//only the else is ever used - can manually go to 1 from changing x to false in buildGraph
		//this is done if something happens to the file with the nodes that is read from
		if(choiceNum == 1)
		{
			name = "Schooling";
			description = "Here a decision on what type of schooling you will send your child to, is made ";

		}else
		if(choiceNum == 2)
		{
			name = "Public School";
			description = "This is the middle ground in the terms of R cost.";
			advantages.add("A lot cheaper than private school");
			advantages.add("Fee excemption can occur for underprivledged families");
			
			disadvantages.add("Larger classes can result in your teachers not being able to give a lot of attention to each learner");
			
			monthlyRandCost = 2500;
			monthlyTimeCost = 0;
			numMonths = 144;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}else
		if(choiceNum == 3)
		{
			name = "Private School ";
			description = "This is the most expensive schooling option in R terms";
			advantages.add("Smaller classes lets teachers have more time for each student");
				
			disadvantages.add("Very expensive");
				
			monthlyRandCost = 10000;
			monthlyTimeCost = 0;
			numMonths = 144;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}else
		if(choiceNum == 4)
		{
			name = "Home Schooling ";
			description = "Instead of going to a school, you can teach your child.";
			advantages.add("Little to no teaching/schooling costs");
			advantages.add("Get to spend a lot of time with your child");
				
			disadvantages.add("Get to spend a lot of time with your child");
			disadvantages.add("Uses a lot of your time");
				
			monthlyRandCost = 500;
			monthlyTimeCost = 20*5;
			numMonths = 144;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}else
		if(choiceNum == 5)
		{
			name = "Private Home Tutoring";
			description = "Instead of going to a school, you can hire a private teacher(s) who will teach your child at home.";
			advantages.add("Your child will have 100% of the teachers focus");
			advantages.add("The class pace is set to match what your child knows.");
				
			disadvantages.add("Extremely expensive");
			disadvantages.add("Your child will not have any peer interactions like in a class, potentially resulting in not having friends");
				
			monthlyRandCost = 300*5*20;
			monthlyTimeCost = 0;
			numMonths = 144;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}else
		if(choiceNum == 6)
		{
			name = "No Schooling";
			description = "Instead of going to a school, you don't.";
			advantages.add("No time or money cost");
			advantages.add("Can have your child help around with chores at home");
				
			disadvantages.add("Children by law have to go to school");
			disadvantages.add("Your child will not get an education that would help them secure a job in the future");
				
			monthlyRandCost = 0;
			monthlyTimeCost = -6*20;
			numMonths = 144;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}
		else
		if(choiceNum == 7)
		{
			name = "Medical Aid";
			description = "Here is decision on if you want medical aid for your child";
		}
		else
		if(choiceNum == 8)
		{
			name = "Buy Medical Aid";
			description = "You decide that you want your child to have medical aid, jsut in case.";
			advantages.add("In a medical emergency, your child gets sick or if any chronic illnesses are found, your medical bills will be covoured");
			advantages.add("Private hospitals have less patients, so your child will be seen sooner");
				
			disadvantages.add("Very expensive");
				
			monthlyRandCost = 1500;
			monthlyTimeCost = 0;
			numMonths = 216;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}
		else
		if(choiceNum == 9)
		{
			name = "No Medical Aid";
			description = "You decide that you don't want your child to have medical aid.";
			
			advantages.add("You will save a huge amount of money during your childs child years");

			disadvantages.add("You will have to pay out of pocket for any meds, hospital bills ect");
			disadvantages.add("Private hospitals have more patients, so waiting times are longer");
			
			monthlyRandCost = 0;
			monthlyTimeCost = 0;
			numMonths = 0;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}
		else
		if(choiceNum == 10)
		{
			name = "Transport";
			description = "Here is decision on if how you want to get your child from place A to B. All calculations are done at R20/L and 100km/week";
		}
		else
		if(choiceNum == 11)
		{
			name = "You drive them";
			description = "You personally drive your child. This will cost your time, and fuel money. Need a car for this option.";
			
			advantages.add("You get to spend more time with your child.");
			advantages.add("The fuel cost will not be very high");

			disadvantages.add("You need a car for this option");
			disadvantages.add("Need to get up earlier so as to not be late for work");
			disadvantages.add("School traffic is not fun");
			
			monthlyRandCost = 120*4;
			monthlyTimeCost = 15;
			numMonths = 12*12;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}
		else
		if(choiceNum == 12)
		{
			name = "Uber";
			description = "You uber your child. This will save you time, but is quite expensive.";
			
			advantages.add("You don't use any of your own time");
			advantages.add("You don't have to take or fetch your child");

			disadvantages.add("Very expensive");
			disadvantages.add("Your child needs a phone for this");

			monthlyRandCost = 20*140;
			monthlyTimeCost = 0;
			numMonths = 12*12;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}
		else
		if(choiceNum == 13)
		{
			name = "Lift Club";
			description = "You and some 3 other parents join together in a lift club. Each taking everyones children every fourth week.";
			
			advantages.add("You dont have to drive your child every week");
			advantages.add("Is cheaper and less time consuming than driving them yourself");

			disadvantages.add("The week you are driving, takes a lot more time that week");
			disadvantages.add("Must trust the other parents in group");

			monthlyRandCost = 240;
			monthlyTimeCost = 10;
			numMonths = 12*12;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}
		else
		if(choiceNum == 14)
		{
			name = "Public Transport";
			description = "Your child can take public transport such as busses";
			
			advantages.add("You don't use any of your own time");
			advantages.add("Not very expensive, and your child can go far");

			disadvantages.add("Set bus routes");

			monthlyRandCost = 4*37;
			monthlyTimeCost = 0;
			numMonths = 12*12;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}
		else
		if(choiceNum == 15)
		{
			name = "Walking";
			description = "Your child walks.";
			
			advantages.add("If your child is close, this makes the most sense.");
			advantages.add("No time or money cost involved");

			disadvantages.add("If you live far, your child will have to walk far every day");

			monthlyRandCost = 0;
			monthlyTimeCost = 0;
			numMonths = 12*12;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}else
		if(choiceNum == 16)
		{
			name = "Sports";
			description = "This is where you decide if your schild will do a sport, and where that shall take place. Sports can have an important role in the developement of both your childs mental, and physical wellbeing. It is a great way to socialise with others their age.";

		}else
		if(choiceNum == 17)
		{
			name = "Inside School";
			description = "Your child participates in a sport that the school hosts.";
			
			advantages.add("Sports are good for social and physical developement.");
			advantages.add("Sports at school are often free.");
			advantages.add("If your child is really good, you can boast about them to all the other parents.");
			advantages.add("If your child is really, really good, they might be able to make a career out of the sport.");
			
			disadvantages.add(" You will have to occasionally watch their matches, which while nice, it does use time.");

			monthlyRandCost = 0;
			monthlyTimeCost = 1;
			numMonths = 12*12;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}else
		if(choiceNum == 18)
		{
			name = "Outside School";
			description = "Your child participates in a sport outside of school.";
			
			advantages.add("Sports are good for social and physical developement.");
			advantages.add("Your child will be able to play sports that may have a lower player base, or on a more proffesional level.");
			advantages.add("If your child is really good, you can boast about them to all the other parents.");
			advantages.add("If your child is really, really good, they might be able to make a career out of the sport.");
			
			disadvantages.add(" You will have to occasionally watch their matches, which while nice, it does use time.");
			disadvantages.add("Sports outside school can be expensive.");
			disadvantages.add("Transport to and from the sporting area is required (please see transport section).");
			
			monthlyRandCost = 800;
			monthlyTimeCost = 1;
			numMonths = 12*12;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}else
		if(choiceNum == 19)
		{
			name = "None";
			description = "Your child does no sports.";
			
			advantages.add("No extra time or money need to be invested");

			disadvantages.add("Sports help keep weight controlled, so none may lead to overweight issues.");

			monthlyRandCost = 0;
			monthlyTimeCost = 0;
			numMonths = 12*12;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}else
		if(choiceNum == 20)
		{
			name = "School Lunch";
			description = "Here a decision on what type of school lunch your child will have while at school.";

		}else
		if(choiceNum == 21)
		{
			name = "Home Made";
			description = "You make school lunch for your child the night before, or that morning.";
			
			advantages.add("You can decide exactly what goes in your child's lunch - leading to a healthier alternative.");
			advantages.add("Less checmicals/preservatives.");
			
			disadvantages.add("Takes a lot of time, and you can't forget/miss a day easily.");
			disadvantages.add("Must have all the necessary ingrediants in the house.");

			monthlyRandCost = 20*20;
			monthlyTimeCost = 6;
			numMonths = 12*12;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}else
		if(choiceNum == 22)
		{
			name = "Shop Bought";
			description = "You buy your child's lunch from the shops. egChip packets";
			
			advantages.add("Easy to do/quick.");
			advantages.add("Shop school lunches often have a long shelf life.");

			disadvantages.add("Normally more unhealthy/more preservatives/additives added.");
			disadvantages.add("Normally high in sugar.");

			monthlyRandCost = 25*20;
			monthlyTimeCost = 0;
			numMonths = 12*12;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}else
		if(choiceNum == 23)
		{
			name = "Tuck Shop";
			description = "You buy your child's lunch from the tuck shop.";
			
			advantages.add("Easy to do/quick.");
			advantages.add("Food is normally fresh");

			disadvantages.add("Normally more unhealthy.");

			monthlyRandCost = 30*20;
			monthlyTimeCost = 0;
			numMonths = 12*12;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}else
		if(choiceNum == 24)
		{
			name = "Dinner/Breakfast";
			description = "You need to decide on how you will feed your child when they are not at school.";

		}else
		if(choiceNum == 25)
		{
			name = "Home made food";
			description = "You make your child homemade dinner/breakfast.";
			
			advantages.add("The most economical option.");
			advantages.add("Home made food normally tastes yummy and is healthier.");
			advantages.add("You can control your child's diet more easily.");
			
			disadvantages.add("This can take a while - especially dinners");

			monthlyRandCost = 38*30;
			monthlyTimeCost = 30;
			numMonths = 12*12;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}else
		if(choiceNum == 26)
		{
			name = "Freezer meals";
			description = "You make your child homemade frozen meals";
			
			advantages.add("Very easy to make - just microwave or oven");
			advantages.add("Shelf life is extreemly long.");
			advantages.add("Very tasty in some cases.");
			
			disadvantages.add("Extremely unhealthy, and can lead to obesity");
			disadvantages.add("Might not get all the necessary vitamins and minerals needed.");

			monthlyRandCost = 50*30;
			monthlyTimeCost = 10;
			numMonths = 12*12;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}else
		if(choiceNum == 27)
		{
			name = "Takeout";
			description = "You order takeout every day.";
			
			advantages.add("Very easy to order on an app or phone call");
			advantages.add("Don't have to go to shops for food, or worry about power cuts leading to defrosted freezers.");
			advantages.add("Very tasty");
			
			disadvantages.add("Extremely unhealthy, and can lead to obesity");
			disadvantages.add("Extremely expensive");


			monthlyRandCost = 120*30;
			monthlyTimeCost = 2;
			numMonths = 12*12;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}else
		if(choiceNum == 28)
		{
			name = "Private Chef";
			description = "You hire a private chef to cook and prepare all your meals";
			
			advantages.add("Super tasty and convenient");
			advantages.add("Food is normally fresh, and can be very good for you/healthy");

			disadvantages.add("Insanely expensive");

			monthlyRandCost = 40*30+11000;
			monthlyTimeCost = 0;
			numMonths = 12*12;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
	
		}else
		if(choiceNum == 29)
		{
			name = "Computer";
			description = "You buy a computer for your child. Many school projects require the use of a computer to do (word docs, powerpoint).";
			
	
		}else
		if(choiceNum == 30)
		{
			name = "Low End Laptop";
			description = "You buy a low end laptop for your child to do school work on - buy once every 6 years";
			
			advantages.add("Allows your child to do school assignments more easily");
			advantages.add("Allows your child to get more used to technology and software such as word.");

			disadvantages.add("Not cheap");
			disadvantages.add("Can't play games, and can be slow.");

			monthlyRandCost = 4000;
			monthlyTimeCost = 0;
			numMonths = 2;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
	
		}else
		if(choiceNum == 31)
		{
			name = "Mid range Laptop";
			description = "You buy a mid range laptop for your child to do school work on - buy once every 6 years";
			
			advantages.add("Allows your child to do school assignments more easily");
			advantages.add("Allows your child to get more used to technology and software such as word.");
			advantages.add("Can handle coding programs more easily eg VS");
			advantages.add("Is decently fast.");
			
			disadvantages.add("Not cheap");
			disadvantages.add("Can't play high end games.");

			monthlyRandCost = 9000;
			monthlyTimeCost = 0;
			numMonths = 2;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
	
		}else
		if(choiceNum == 32)
		{
			name = "High range Laptop";
			description = "You buy a high end laptop for your child to do school work on - buy once every 6 years";
			
			advantages.add("Allows your child to do school assignments more easily");
			advantages.add("Allows your child to get more used to technology and software such as word.");
			advantages.add("Can handle coding programs easily eg VS");
			advantages.add("Is fast.");
			disadvantages.add("Can play high end games.");
			
			disadvantages.add("Not cheap");

			monthlyRandCost = 20000;
			monthlyTimeCost = 0;
			numMonths = 2;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
	
		}else
		if(choiceNum == 33)
		{
			name = "No Computer";
			description = "You don'y buy your child a computer";
			
			advantages.add("No cost");

			disadvantages.add("Many modern day school projects require the use of a computer for projects/docs.");
			disadvantages.add("Your child won't get a head start in learning how to use a computer.");
			
			monthlyRandCost = 0;
			monthlyTimeCost = 0;
			numMonths = 0;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
	
		}else
		if(choiceNum == 34)
		{
			name = "Phone";
			description = "You buy a phone for your child. Phones are good for communicating with you and friends. Can call for police/ambulance/help if in trouble.";			
	
		}else
		if(choiceNum == 35)
		{
			name = "Low End Phone";
			description = "You buy a low end phone for communication- buy once every 6 years";
			
			advantages.add("Allows your child to communicate with you for to arrange anything/let you know how they are doing.");
			advantages.add("Can contact authorities/ambulance/get help in an emergency");

			disadvantages.add("Can be quite slow.");

			monthlyRandCost = 2000;
			monthlyTimeCost = 0;
			numMonths = 2;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
	
		}else
		if(choiceNum == 36)
		{
			name = "Mid range Phone";
			description = "You buy a mid rnage phone for communication- buy once every 6 years";
			
			advantages.add("Allows your child to communicate with you for to arrange anything/let you know how they are doing.");
			advantages.add("Can contact authorities/ambulance/get help in an emergency");

			disadvantages.add("Not cheap");

			monthlyRandCost = 5000;
			monthlyTimeCost = 0;
			numMonths = 2;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
	
		}else
		if(choiceNum == 37)
		{
			name = "High End Phone";
			description = "You buy a high end phone for communication- buy once every 6 years";
			
			advantages.add("Allows your child to communicate with you for to arrange anything/let you know how they are doing.");
			advantages.add("Can contact authorities/ambulance/get help in an emergency");
			advantages.add("Fast - can play games and take awesome pictures.");

			disadvantages.add("Very expesnive.");

			monthlyRandCost = 15000;
			monthlyTimeCost = 0;
			numMonths = 2;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
	
		}else
		if(choiceNum == 38)
		{
			name = "No Phone";
			description = "You dont buy your child a phone for communication";
			
			advantages.add("No Cost");

			disadvantages.add("If a timetable/school event time/plans chaned, cannot cantact you to let you know.");
			disadvantages.add("In case of an emergency, your child wont be able to call you/police/ambulance for help.");

			monthlyRandCost = 0;
			monthlyTimeCost = 0;
			numMonths = 0;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
	
		}
		else
		{
			name = "tempName " + idCounter;
			description = "tempDesc " + idCounter;
			advantages.add("tempAdvant 1");
			advantages.add("tempAdvant 2");
			
			disadvantages.add("tempAdvant 1");
			disadvantages.add("tempAdvant 2");
			
			monthlyRandCost = idCounter*10;
			monthlyTimeCost = idCounter*15;
			numMonths = 2;

			totalTimeCost = monthlyTimeCost*numMonths;
			totalRandCost = monthlyRandCost*numMonths;
		}
		
		//possible
		//transport
		//medical aid

	}
	
	

	
	
	

}
