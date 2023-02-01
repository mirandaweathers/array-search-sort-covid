
/**
 * The State class allows for construction of a State object using
 * data read in from a user-supplied file in the main method of the
 * Project1 Class. 
 * <p>
 * State object details are private, accessible only by public
 * get/set methods provided for each State instance variable.
 * <p>
 * The printState method gives a short report of details from an 
 * individual State selected by the user.
 * 
 * @author Miranda Weathers N01482572
 * @version 9/16/2021
 */

public class State 
{
	private String name;
	private String capitol;
	private String region;
	private int houseSeats;
	private int pop; //population
	private int cases; //covid-19 cases
	private int deaths; //covid-19 deaths
	private int mhi; //median household income
	private double vcr; //violent crime rate
	private double cfr; //case fatality rate = deaths / cases
	private double caseRate; // (cases / population) * 100000
	private double deathRate; // (deaths / population) * 100000
	
	/**
	 * Constructs a State object using values parsed in from external
	 * user-supplied file in Project1 main method. Calculates values
	 * for additional instance variables derived from file data.
	 * 
	 * @param n: name
	 * @param c: capitol
	 * @param r: region
	 * @param hs: U.S. House seats
	 * @param p: population
	 * @param cc: COVID-19 cases
	 * @param cd: COVID-19 deaths
	 * @param mhi: median household income
	 * @param vcr: violent crime rate
	 */
	
	public State(String n, String c, String r, int hs, int p, int cc, int cd, int mhi, double vcr)
	{
		name = n;
		capitol = c;
		region = r;
		houseSeats = hs;
		pop = p;
		cases = cc;
		deaths = cd;
		this.mhi = mhi;
		this.vcr = vcr;
		cfr = (double)cd / (double)cc; //case fatality rate = deaths / cases
		caseRate = ((double)cc / (double)p) * 100000; //cases per 100,000 people
		deathRate = ((double)cd / (double)p) * 100000; //deaths per 100,000 people
	}//end State constructor
	
	/**
	 * Prints a State's name, median household income, violent crime
	 * rate, case-fatality rate, case rate, and death rate when "Find
	 * and print a given State" is chosen from the menu and the user-
	 * supplied State is found.
	 */
	
	public void printState() {
		System.out.println("\nName:       " + name);
		System.out.println("MHI:        " + mhi);
		System.out.println("VCR:        " + vcr);
		System.out.println("CFR:        " + String.format("%.6f", cfr));
		System.out.println("Case Rate:  " + String.format("%.2f", caseRate));
		System.out.println("Death Rate: " + String.format("%.2f", deathRate));
		System.out.println();
	}//printState()
	
	/**
	 * Gets State name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets State name.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets State capitol.
	 * @return capitol
	 */
	public String getCapitol() {
		return capitol;
	}

	/**
	 * Sets State capitol.
	 * @param capitol
	 */
	public void setCapitol(String capitol) {
		this.capitol = capitol;
	}

	/**
	 * Gets State region.
	 * @return region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Sets State region.
	 * @param region
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * Gets State number of US House seats.
	 * @return houseSeats
	 */
	public int getHouseSeats() {
		return houseSeats;
	}

	/**
	 * Sets number of US House seats.
	 * @param houseSeats
	 */
	public void setHouseSeats(int houseSeats) {
		this.houseSeats = houseSeats;
	}

	/**
	 * Gets State population.
	 * @return pop
	 */
	public int getPop() {
		return pop;
	}

	/**
	 * Sets State population.
	 * @param pop
	 */
	public void setPop(int pop) {
		this.pop = pop;
	}

	/**
	 * Gets number of COVID-19 cases in the State.
	 * @return cases
	 */
	public int getCases() {
		return cases;
	}

	/**
	 * Sets number of COVID-19 cases in the State.
	 * @param cases
	 */
	public void setCases(int cases) {
		this.cases = cases;
	}

	/**
	 * Gets number of COVID-19 deaths in the State.
	 * @return deaths
	 */
	public int getDeaths() {
		return deaths;
	}

	/**
	 * Sets number of COVID-19 deaths in the State.
	 * @param deaths
	 */
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	/**
	 * Gets State median household income (MHI).
	 * @return mhi
	 */
	public int getMhi() {
		return mhi;
	}

	/**
	 * Sets State median household income (MHI).
	 * @param mhi
	 */
	public void setMhi(int mhi) {
		this.mhi = mhi;
	}

	/**
	 * Gets State violent crime rate (VCR).
	 * @return vcr
	 */
	public double getVcr() {
		return vcr;
	}

	/**
	 * Sets State violent crime rate (VCR).
	 * @param vcr
	 */
	public void setVcr(double vcr) {
		this.vcr = vcr;
	}
	
	/**
	 * Gets State COVID-19 case-fatality rate (CFR).
	 * @return cfr
	 */
	public double getCfr() {
		return cfr;
	}

	/**
	 * Sets State COVID-19 case-fatality rate (CFR).
	 * @param cfr
	 */
	public void setCfr(double cfr) {
		this.cfr = cfr;
	}

	/**
	 * Gets State COVID-19 case rate (cases per 100,000 people).
	 * @return caseRate
	 */
	public double getCaseRate() {
		return caseRate;
	}

	/**
	 * Sets State COVID-19 case rate (cases per 100,000 people).
	 * @param caseRate
	 */
	public void setCaseRate(double caseRate) {
		this.caseRate = caseRate;
	}

	/**
	 * Gets State COVID-19 death rate (deaths per 100,000 people).
	 * @return deathRate
	 */
	public double getDeathRate() {
		return deathRate;
	}

	/**
	 * Sets State COVID-19 death rate (deaths per 100,000 people).
	 * @param deathRate
	 */
	public void setDeathRate(double deathRate) {
		this.deathRate = deathRate;
	}
	
	//end getters and setters
	
}//end class State
