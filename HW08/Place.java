public class Place implements Comparable<Place> {
    // Private instance variables
    private String zip;
    private String town;
    private String state;

    // Constructor
    public Place(String zip, String town, String state) {
        // Initialize instance variables
        this.zip = zip;
        this.town = town;
        this.state = state;
    }

    // Accessors
    public String getZip() {
        return zip;
    }

    public String getTown() {
        return town;
    }

    public String getState() {
        return state;
    }

    // Override toString method
    public String toString() {
        return zip;
    }

    @Override
    public int compareTo(Place other) {
        return this.zip.compareTo(other.zip);
    }
}
