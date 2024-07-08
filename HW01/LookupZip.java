import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LookupZip {
    
    public static ExpandableArray<Place> readZipCodes(String filename1, String filename2) throws FileNotFoundException {
        ExpandableArray<Place> places = new ExpandableArray<>();
        
        Scanner scanner1 = new Scanner(new File(filename1));
        scanner1.nextLine(); // Skip header
        Scanner scanner2 = new Scanner(new File(filename2));
        scanner2.nextLine(); // Skip header
        
        while (scanner1.hasNextLine()) {
            String line1 = scanner1.nextLine();
            String[] data1 = line1.split(",", 7);
            String zip = data1[0];
            String town = data1[1];
            String state = data1[2];
            String population = data1[3];
            if (!population.isEmpty()) {
                double latitude = 0.0; 
                double longitude = 0.0; 
                places.insert(new PopulatedPlace(zip, town, state, latitude, longitude, Integer.parseInt(population)));
            } else {
                places.insert(new Place(zip, town, state));
            }
        }
        scanner1.close();
        
        while (scanner2.hasNextLine()) {
            String line2 = scanner2.nextLine();
            line2 = line2.replaceAll("\"", ""); // Remove quotes
            String[] data2 = line2.split(",");
            String zip = data2[0];
            String latitude = data2[5];
            String longitude = data2[6];
            for (int i = 0; i < places.size(); i++) {
                Place place = places.get(i);
                if (place.getZip().equals(zip)) {
                    String town = place.getTown();
                    String state = place.getState();
                    if (!latitude.isEmpty() && !longitude.isEmpty()) {
                        if (place instanceof PopulatedPlace) {
                            ((PopulatedPlace) place).setLatitude(Double.parseDouble(latitude));
                            ((PopulatedPlace) place).setLongitude(Double.parseDouble(longitude));
                        } else {
                            places.set(new LocatedPlace(zip, town, state, Double.parseDouble(latitude), Double.parseDouble(longitude)), i);
                        }
                    }
                }
            }
        }
        scanner2.close();
        
        return places;
    }

    public static Place lookupZip(ExpandableArray<Place> places, String zip) {
        for (int i = 0; i < places.size(); i++) {
            Place place = places.get(i);
            if (place.getZip().equals(zip)) {
                return place;
            }
        }
        return null;
    }
}
