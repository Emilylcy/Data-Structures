public class PopulatedPlace extends LocatedPlace {
    private int population;

    public PopulatedPlace(String zip, String town, String state, double latitude, double longitude, int population) {
        super(zip, town, state, latitude, longitude);
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String toString() {
        return getTown() + ", " + getState() + " " + getLatitude() + " " + getLongitude() + " " + population;
    }
}