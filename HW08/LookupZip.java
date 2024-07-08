import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LookupZip {
    public static AVLTree<Place> readZipCodes(String filename1, String filename2) throws FileNotFoundException {
        AVLTree<Place> places = new AVLTree<>();
    
        Scanner scanner1 = new Scanner(new File(filename1));
        scanner1.nextLine(); // Skip header
        while (scanner1.hasNextLine()) {
            String line1 = scanner1.nextLine();
            String[] data1 = line1.split(",", 7);
            String zip = data1[0];
            String town = data1[1];
            String state = data1[2];
            String population = data1[3];
            if (!population.isEmpty()){
                places.insert(new PopulatedPlace(zip, town, state, 0.0,0.0,Integer.parseInt(population)));
            } else {
                places.insert(new Place(zip,town,state));
            }
        }
        scanner1.close();
        
        Scanner scanner2 = new Scanner(new File(filename2));
        scanner2.nextLine();
        while (scanner2.hasNextLine()) {
            String line2 = scanner2.nextLine();
            line2 = line2.replaceAll("\"", ""); 
            String[] data = line2.split(",");
            if (data.length >= 7) {
                String zip = data[0];
                String latitude = data[5];
                String longitude = data[6];
                Place foundPlace = places.get(new Place(zip, "", ""));
                if (!latitude.isEmpty() && !longitude.isEmpty()){
                    if (foundPlace instanceof PopulatedPlace){
                        ((PopulatedPlace) foundPlace).setLatitude(Double.parseDouble(latitude));
                        ((PopulatedPlace) foundPlace).setLongitude(Double.parseDouble(longitude));
                    } else if (foundPlace instanceof LocatedPlace) {
                        ((LocatedPlace) foundPlace).setLatitude(Double.parseDouble(latitude));
                        ((LocatedPlace) foundPlace).setLongitude(Double.parseDouble(longitude));
                    }
                }
            }
        }
        scanner2.close();
        return places;
    }
    
    public static Place lookupZip(AVLTree<Place> places, String zip) {
        return places.get(new Place(zip, null, null));
    }
}