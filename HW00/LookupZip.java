import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LookupZip {

    public static Place parseLine(String line) {
        String[] data = line.split(",");
        String zip = data[0];
        String town = data[1];
        String state = data[2];
        return new Place(zip, town, state);
    }

    public static Place[] readZipCodes(String filename, int numEntries) throws FileNotFoundException {
        Place[] places = new Place[numEntries];
        Scanner scanner = new Scanner(new File(filename));

        scanner.nextLine(); // Skip the header

        for (int i = 0; i < numEntries; i++) {
            String line = scanner.nextLine();
            places[i] = parseLine(line);
        }

        scanner.close();
        return places;
    }

    public static Place lookupZip(Place[] places, String zip) {
        for (int i = 0; i < places.length; i++) {
            Place place = places[i];
            if (place.getZip().equals(zip)) {
                return place;
            }
        }
        return null;
    }
}
