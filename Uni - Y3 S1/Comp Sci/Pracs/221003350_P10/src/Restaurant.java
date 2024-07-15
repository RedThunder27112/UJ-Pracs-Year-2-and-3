import java.io.Serializable;

@SuppressWarnings("serial")
public class Restaurant implements Serializable{
	private String name;
	private String description;
	private GPSPoint location;
	
	/*
	 * Default constructor
	 */
	public Restaurant(){
		new Restaurant("","",new GPSPoint(27.998614,-26.183000));
	}
	
	/*
	 * The overloaded constructor
	 * @param the name, description and location of the restaurant
	 */
	public Restaurant(String name, String description, GPSPoint location){
		this.name  = name;
		this.description = description;
		this.location = location;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the location
	 */
	public GPSPoint getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(GPSPoint location) {
		this.location = location;
	}
	
	/*
	 * The overridden toString method used for serialisation
	 */
	public String toString(){
		return name+ ": "+description+"\n"+location;
	}

}
