import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * COP 3530: Project 1 - Array Searches and Sorts
 * <p>
 * The Project1 Class takes a user-supplied CSV file with data for the 50 
 * States of the U.S., creates an array of State objects from the data,
 * performs calculations on the data, and prints reports of the data based
 * on options picked from a menu.
 * <p>
 * The array of State objects is initialized using data parsed from the CSV
 * file and repeatedly calling the set methods for each State's private
 * instance variables. There are nine data items for each State; additional
 * data is derived, including case rate, death rate, and case-fatality rate.
 * <p>
 * The menu repeats until the user decides to quit, and has seven options:
 * 1) print a report for all States
 * 2) sort the array of States alphabetically by name using bubble sort
 * 3) sort by COVID-19 case fatality rate using selection sort
 * 4) sort by median household income using insertion sort
 * 5) find and print data from a given State (binary or sequential search,
 * depending on the most recent sort method)
 * 6) print a Spearman's rho correlation matrix showing the relationship
 * between case rate, death rate, median household income, and crime rate
 * 7) quit.
 * <p>
 * User input is verified using if/else and try/catch statements where
 * appropriate to ensure few, if any, exceptions occur.
 * 
 * @author Miranda Weathers N01482572
 * @version 9/16/2021
 */

public class Project1 {
	
	/**
	 * Scans in file path and checks if file exists. An array of 50 State
	 * objects is created and data is parsed from the file to initialize
	 * each state. A menu of options for sorting, searching, and displaying
	 * variations of the data is repeatedly given to the user until the
	 * option to quit is selected.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Input file name: ");
		String fileName = scan.next();
		//System.out.println(fileName);
		
		
		Scanner readFile = null;
		try {
			readFile = new Scanner(new File(fileName));
		}
		catch (FileNotFoundException e) {
			System.out.println("\nFile not found.");
			System.exit(1);
		}//end try/catch
		
		State[] states = new State[50]; //create array of State objects
		
		int i = 0;
		readFile.nextLine(); //skip csv row 1 (labels)
		readFile.useDelimiter(",|\n");
		
		while(readFile.hasNext()) {
			
			//create State object from file
			State s = new State( 
					readFile.next(), 		//name
					readFile.next(), 		//capitol
					readFile.next(), 		//region
					readFile.nextInt(), 	//house seats
					readFile.nextInt(), 	//population
					readFile.nextInt(), 	//covid cases
					readFile.nextInt(), 	//covid deaths
					readFile.nextInt(), 	//median household income
					readFile.nextDouble() 	//violent crime rate
					);
			
			states[i++] = s;
			
		}//end while
		
		//menu
		boolean flag = true; //repeat menu (true) or quit (false)
		boolean alphaSorted = false; //keep track of sort order
		int choice = 0;
		Scanner menuIn = new Scanner(System.in);
		
		while(flag == true) {
			
			System.out.println("\n1. Print a States report");
			System.out.println("2. Sort by Name");
			System.out.println("3. Sort by Case Fatality Rate");
			System.out.println("4. Sort by Median Household Income");
			System.out.println("5. Find and print a given State");
			System.out.println("6. Print Spearman's rho matrix");
			System.out.println("7. Quit\n");
			System.out.print("Enter the number of your choice: ");
			
			//check if input matches type int
			if(menuIn.hasNextInt()) {
				choice = menuIn.nextInt();
			} else {
				System.out.println("\nPlease enter an integer next time!");
				menuIn.next();
				continue;
			}//end if else
			
			switch(choice) {
			case 1:
				printStatesReport(states);
				break;
			case 2: 
				sortByName(states);
				alphaSorted = true;
				System.out.println("\nStates sorted by name.");
				break;
			case 3: 
				sortByCFR(states);
				alphaSorted = false;
				System.out.println("\nStates sorted by Case Fatality Rate.");
				break;
			case 4: 
				sortByMHI(states);
				alphaSorted = false;
				System.out.println("\nStates sorted by Median Household Income.");
				break;
			case 5: 
				findState(scan, states, alphaSorted);
				break;
			case 6: 
				printMatrix(states);
				alphaSorted = false;
				break;
			case 7:
				flag = false;
				break;
			default:
				System.out.println("\nPlease enter an integer between 1 - 7.");
				break;
			}//end switch
			
		}//end while (menu)
		
		System.out.println("\nGoodbye!");
		
		//close scanners
		scan.close();
		readFile.close();
		menuIn.close();

	}//end main method
	
	/**
	 * Prints a formatted list of all States' name, median household
	 * income, violent crime rate, case fatality rate, COVID-19 case rate,
	 * and COVID-19 death rate, according to the most recent sort order.
	 * 
	 * @param State[] s: Array of State objects for generating the report
	 */
	public static void printStatesReport(State[] s) {
		//States Report column labels
		System.out.println("\nName           MHI           VCR           CFR           Case Rate     Death Rate");
		System.out.println("---------------------------------------------------------------------------------");
		for(int i = 0; i < 50; i++) {
			//States Report data formatting
			System.out.printf("%-15s%-14d%-14.1f%-14.6f%-14.2f%-14.2f\n",
					s[i].getName(),s[i].getMhi(),s[i].getVcr(),s[i].getCfr(),s[i].getCaseRate(),s[i].getDeathRate());
		}//for
		System.out.println("---------------------------------------------------------------------------------");
	}//printStatesReport()
	
	/**
	 * Sorts the array of State objects alphabetically by name
	 * using bubble sort.
	 * 
	 * @param State[] s: Array of State objects to be sorted
	 */
	public static void sortByName(State[] s) {
		for(int i = 0; i < 49; i++) {
			for(int j = 49; j > i; j--) {
				//compare States' names and bubble sort alphabetically A-Z
				if((s[j].getName().compareToIgnoreCase(s[j-1].getName())) < 0) {
					State temp = s[j];
					s[j] = s[j-1];
					s[j-1] = temp;
				}//if
			}//j
		}//i
	}//sortByName()
	
	/**
	 * Sorts the array of State objects in ascending order by
	 * case-fatality rate, denoted by number of deaths per number 
	 * of cases, using selection sort.
	 * 
	 * @param State[] s: Array of State objects to be sorted
	 */
	public static void sortByCFR(State[] s) {
		//use selection sort to sort states by CFR
		for(int i = 0; i < 49; i++) {
			int lowest = i;
			for(int j = i+1; j < 50; j++) {
				if(s[j].getCfr() < s[lowest].getCfr()) {
					lowest = j;
				}//if
			}//j
			if(lowest != i) {
				State temp = s[lowest];
				s[lowest] = s[i];
				s[i] = temp;
			}//if
		}//i
		
		
		
	}//sortByCFR()
	
	/**
	 * Sorts the array of State objects in ascending order by
	 * median household income using insertion sort.
	 * 
	 * @param State[] s: Array of State objects to be sorted
	 */
	public static void sortByMHI(State[] s) {
		//use insertion sort to sort States by MHI
		for(int i = 1; i < 50; i++) {
			State temp = s[i];
			int j = i - 1;
			while(j >= 0 && s[j].getMhi() > temp.getMhi()) {
				s[j+1] = s[j];
				j--;
			}//j while
			s[j+1] = temp;
		}//i for
	}//sortByMHI()
	
	/**
	 * Searches the array of State objects for user-provided State name.
	 * Uses binary search if sorted alphabetically, sequential search if not.
	 * 
	 * @param Scanner scan: Reads in user's input
	 * @param State[] s: Array of State objects to search
	 * @param boolean alphaSorted: Indicates whether States are sorted alphabetically
	 */
	public static void findState(Scanner scan, State[] s, boolean alphaSorted) {
	
		System.out.print("\nEnter state to find: ");
		String input = scan.next();
		boolean found = false;
		
		//determine sort method
		if(alphaSorted == true) {
			
			//binary search if States alphabetically sorted
			System.out.println("\nBinary search.");
			int btm = 0, top = 49;
			while (btm <= top) {
				int mid = (btm + top) / 2;
				if(s[mid].getName().compareToIgnoreCase(input) == 0) {
					s[mid].printState();
					found = true;
					break;
				} else if(s[mid].getName().compareToIgnoreCase(input) < 0) {
					btm = mid + 1;
				} else if(s[mid].getName().compareToIgnoreCase(input) > 0) {
					top = mid - 1;
				} else {
					found = false;
				}//if..else
			}//binary search
			
		} else {
			
			//sequential search if States not alphabetically sorted
			System.out.println("\nSequential search.");
			for(int i = 0; i < 50; i++) {
				if(s[i].getName().compareToIgnoreCase(input) == 0) {
					s[i].printState();
					found = true;
					break;
				} else {
					found = false;
				}//if..else
			}//sequential search
			
		}//if..else to determine sort method
		
		//print message if user input not found in States
		if(found == false) {
			System.out.println("\nNot found.");
		}//if
		
	}//findState()
	
	/** 
	 * Calculates and prints Spearman's Rho matrix comparing all State
	 * rankings for four values: COVID-19 Case Rate and Median Household
	 * Income, Case Rate and Violent Crime Rate, COVID-19 Death Rate and
	 * Median Household Income, and Death Rate and Violent Crime Rate.
	 * 
	 * @param State[] s: Array of States from which calculations are made
	 */	
	public static void printMatrix(State[] s) {
		//track sum of squared differences
		int sum = 0;
		
		//arrays for State rankings - index = rank, item = state name
		String[] caseRank = new String[50];
		String[] mhiRank = new String[50];
		String[] deathRank = new String[50];
		String[] crimeRank = new String[50];
		
		//Spearman's Rho values variables:
		double caseIncomeRho = 0.0;
		double caseCrimeRho = 0.0;
		double deathIncomeRho = 0.0;
		double deathCrimeRho = 0.0;
		
	//---- Case Rate and Median Household Income ----//
		
		//first, sort States by case rate
		for(int i = 1; i < 50; i++) {
			State temp = s[i];
			int j = i - 1;
			while(j >= 0 && s[j].getCaseRate() > temp.getCaseRate()) {
				s[j+1] = s[j];
				j--;
			}//j
			s[j+1] = temp;
		}//i
		
		//initialize array to store each State's Case Rate rank
		for(int i = 0; i < 50; i++) {
			caseRank[i] = s[i].getName();
		}//i
		
		//next, sort by Median Household Income
		sortByMHI(s);
		
		//initialize array to store each State's MHI rank
		for(int i = 0; i < 50; i++) {
			mhiRank[i] = s[i].getName();
		}//i
		
		//next, compare Case Rate and MHI rankings
		for(int i = 0; i < 50; i++) {
			for(int j = 0; j < 50; j++) {
				if(caseRank[i] == mhiRank[j]) {
					//add 1 to i + j for accurate ranking and calculations
					sum += ((i+1)-(j+1))*((i+1)-(j+1));
				}//if
			}//j
		}//i
		
		caseIncomeRho = 1 - ((6*(double)sum)/(50*((50*50)-1)));
		
	//---- Case Rate and Violent Crime Rate ----//
		
		//Case Rate rankings already determined; now sort by VCR
		for(int i = 1; i < 50; i++) {
			State temp = s[i];
			int j = i - 1;
			while(j >= 0 && s[j].getVcr() > temp.getVcr()) {
				s[j+1] = s[j];
				j--;
			}//j
			s[j+1] = temp;
		}//i
		
		//initialize array to store each state's VCR rank
		for(int i = 0; i < 50; i++) {
			crimeRank[i] = s[i].getName();
		}//i
		
		//next, compare Case Rate and VCR rankings
		sum = 0; //reset sum
		for(int i = 0; i < 50; i++) {
			for(int j = 0; j < 50; j++) {
				if(caseRank[i] == crimeRank[j]) {
					//add 1 to i + j for accurate ranking and calculations
					sum += ((i+1)-(j+1))*((i+1)-(j+1));
				}//if
			}//j
		}//i
		
		caseCrimeRho = 1 - ((6*(double)sum)/(50*((50*50)-1)));
		
	//---- Death Rate and Median Household Income ----//
		
		//first, sort States by death rate
		for(int i = 1; i < 50; i++) {
			State temp = s[i];
			int j = i - 1;
			while(j >= 0 && s[j].getDeathRate() > temp.getDeathRate()) {
				s[j+1] = s[j];
				j--;
			}//j while
			s[j+1] = temp;
		}//i for
		
		//initialize array to store each state's death rate rank
		for(int i = 0; i < 50; i++) {
			deathRank[i] = s[i].getName();
		}//i
		
		//next, compare Death Rate and MHI rankings
		sum = 0; //reset sum
		for(int i = 0; i < 50; i++) {
			for(int j = 0; j < 50; j++) {
				if(deathRank[i] == mhiRank[j]) {
					//add 1 to i + j for accurate ranking and calculations
					sum += ((i+1)-(j+1))*((i+1)-(j+1));
				}//if
			}//j
		}//i
		
		deathIncomeRho = 1 - ((6*(double)sum)/(50*((50*50)-1)));
		
	//---- Death Rate and Violent Crime Rate ----//
		
		sum = 0; //reset sum
		for(int i = 0; i < 50; i++) {
			for(int j = 0; j < 50; j++) {
				if(deathRank[i] == crimeRank[j]) {
					//add 1 to i + j for accurate ranking and calculations
					sum += ((i+1)-(j+1))*((i+1)-(j+1));
				}//if
			}//j
		}//i
		
		deathCrimeRho = 1 - ((6*(double)sum)/(50*((50*50)-1)));
		
	//---- Print Matrix ----//

		System.out.println("\n--------------------------------------");
		System.out.println("|            |    MHI    |    VCR    |");
		System.out.println("--------------------------------------");
		System.out.printf("| Case Rate  |  %-7.4f  |   %-7.4f |\n", caseIncomeRho, caseCrimeRho);
		System.out.println("--------------------------------------");
		System.out.printf("| Death Rate |  %-7.4f  |   %-7.4f |\n", deathIncomeRho, deathCrimeRho);
		System.out.println("--------------------------------------");
		
	}//printMatrix()

}//end Class Project1
