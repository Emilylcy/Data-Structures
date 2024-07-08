/**
 * Represents the polling data for a political candidate, implements comparable.
 */
public class PollingData implements Comparable<PollingData> {
    private String lastName;
    private String fullName;
    private double percent;

    /**
     * Constructs a new PollingData object 
     * with last name, full name, and polling percentage.
     *
     * @param lastName The candidate's last name.
     * @param fullName The candidate's full name.
     * @param percent The percentage of polled support for the candidate.
     */
    public PollingData(String lastName, String fullName, double percent) {
        this.lastName = lastName;
        this.fullName = fullName;
        this.percent = percent;
    }

    /**
     * Returns the last name of the candidate.
     *
     * @return The last name of the candidate.
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * Returns the full name of the candidate.
     *
     * @return The full name of the candidate.
     */
    public String getFullName() {
        return fullName;
    }


    /**
     * Returns the polling percentage of the candidate.
     *
     * @return The polling percentage.
     */
    public double getPercent() {
        return percent;
    }


    /**
     * Compares with other.
     *
     * @param other The PollingData instance to be compared.
     * @return integer
     */
    @Override
    public int compareTo(PollingData other) {
        return this.lastName.compareTo(other.lastName);
    }
    /**
     * Returns a string representation.
     *
     * @return A string representation of the polling data.
     */
    
    @Override
    public String toString() {
        return String.format("%s:%.1f", this.fullName, this.percent);
    }
}
