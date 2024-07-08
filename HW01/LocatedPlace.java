public class LocatedPlace extends Place {
    protected double latitude;
    protected double longitude;

    public LocatedPlace(String zip, String town, String state, double latitude, double longitude) {
        super(zip, town, state);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String toString() {
        return getTown() + ", " + getState() + " " + latitude + " " + longitude;
    }
}