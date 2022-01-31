import java.util.ArrayList;
import java.util.Collections;

/**
 * This is a Java class to keep the information of cities.
 */
public class City {

    private String name;
    private int code;
    private double area;
    private int population;
    private boolean isBorder;
    private ArrayList<City> neighbors;
    private Person mayor;
    private CityType cityType;
    private static int lastAssignedCityCode = 1000;

    /**
     * Default constructor
     * It initializes the neighbors' ArrayList, mayor's name, and cityType
     * It also initializes the city code with the last assigned code plus one
     */
    public City() {
        this.neighbors = new ArrayList<>();
        this.mayor = new Person("No mayor yet");
        this.cityType = CityType.NoType;
        this.code = ++lastAssignedCityCode;
    }

    /**
     * Second constructor
     * @param name City's name
     * It will first call the default constructor.
     */
    public City(String name) {
        this();
        this.name = name;
    }

    /**
     * Third constructor
     * @param name City's name
     * @param area City's area
     * @param population City's population
     * @param mayor City's mayor
     * @param cityType City's type
     * It will first call the second constructor.
     */
    public City(String name, double area, int population, Person mayor, CityType cityType) {
        this(name);
        this.area = area;
        this.population = population;
        this.mayor = mayor;
        this.cityType = cityType;
    }

    /**
     * Accessor method for the City's name
     * @return City's name
     */
    public String getName() {
        return name;
    }

    /**
     * Mutator method for the City's name
     * @param name City's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accessor method for the City's code
     * @return City's code
     */
    public int getCode() {
        return code;
    }

    /**
     * Accessor method for the City's area
     * @return City's area
     */
    public double getArea() {
        return area;
    }

    /**
     * Mutator method for the City's area
     * @param area City's area
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * Accessor method for the City's population
     * @return City's population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Mutator method for the City's population
     * @param population City's population
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * Accessor method for the City's border type (true or false)
     * @return City's isBorder
     */
    public boolean isBorder() {
        return isBorder;
    }

    /**
     * Mutator method for the City's border type (true or false)
     * @param border City's isBorder
     */
    public void setBorder(boolean border) {
        isBorder = border;
    }

    /**
     * Accessor method for the City's mayor
     * @return City's mayor
     */
    public Person getMayor() {
        return mayor;
    }

    /**
     * Mutator method for the City's mayor
     * @param mayor City's mayor
     */
    public void setMayor(Person mayor) {
        this.mayor = mayor;
    }

    /**
     * Accessor method for the City's type
     * @return City's cityType
     */
    public CityType getCityType() {
        return cityType;
    }

    /**
     * Mutator method for the City's type
     * @param cityType City's type
     */
    public void setCityType(CityType cityType) {
        this.cityType = cityType;
    }

    /**
     * Accessor method for the City's neighbor
     * @return City's neighbor
     */
    public ArrayList<City> getNeighbors() {
        return neighbors;
    }

    /**
     * Mutator method for the City's neighbor
     * @param neighbors City's neighbor
     */
    public void setNeighbors(ArrayList<City> neighbors) {
        this.neighbors = neighbors;
    }

    /**
     * This method will add one city's neighbor
     * @param anotherCity a City object to be added to the City's neighbor
     */
    public void addNeighbor(City anotherCity) {
        this.neighbors.add(anotherCity);
    }

    /**
     * Remove one City's neighbor. The method will receive a String containing a city's name.
     * If it is found inside the City's neighbor, it will be removed and the method returns true.
     * Otherwise, the method returns false.
     * @param cityName a City's name
     * @return
     */
    public boolean removeNeighbor(String cityName) {

        for (int i = 0; i < this.neighbors.size(); i++) {
            if (this.neighbors.get(i).getName().equals(cityName)) {
                this.neighbors.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * This method will sort the City's neighbors' names, descending if order is equal to 'D', and otherwise ascending.
     * @param order If it is equal to 'D' the order will be descending, otherwise ascending.
     * @return
     */
    public ArrayList<String> neighborsSortedList(char order) {

        ArrayList<String> neighborsNames = new ArrayList<>();
        for (City c : this.neighbors) {
            neighborsNames.add(c.getName());
        }
        if (order == 'D')
            Collections.sort(neighborsNames,Collections.reverseOrder());
        else
            Collections.sort(neighborsNames);
        return neighborsNames;
    }

    public String cityMayorInfo() {
        return this.mayor.getName() + (this.mayor.getAge()>0 ? ", Age: " + this.mayor.getAge() : "");
    }
}

enum CityType {Industrial, Touristic, Global, Port, Island, NoType}