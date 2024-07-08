import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Dataset {
    private ArrayList<Incident> incidents;

    public Dataset(String csvFile) {
        incidents = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line = reader.readLine(); // Skip the header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Incident incident = new Incident(
                    data[82], // dob
                    data[80].charAt(0), // sex
                    data[81], // race
                    data[87], // hairColor
                    data[88], // eyeColor
                    data[89], // build
                    Integer.parseInt(data[86]), // weight
                    Integer.parseInt(data[84]), // htFeet
                    Integer.parseInt(data[85])  // htInch
                );
                incidents.add(incident);
            }
        } catch (IOException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage());
        }
    }

    public ArrayList<Incident> getIncidents() {
        return new ArrayList<>(incidents);
    }
    
    public ArrayList<Incident> allPairsDeduplication() {
        ArrayList<Incident> unique = new ArrayList<>();
        for (Incident incident : incidents) {
            boolean isDuplicate = false;
            for (Incident uniqueIncident : unique) {
                if (incident.equals(uniqueIncident)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                unique.add(incident);
            }
        }
        return unique;
    }



    public ArrayList<Incident> hashLinearDeduplication() {
        ProbeHashMap<String, Incident> map = new ProbeHashMap<>(1000003);
        for (Incident incident : incidents) {
            String key = incident.key();
            map.put(key, incident); 
        }
    
        System.out.println("Average number of probes: " + map.getAvgProbes());
        System.out.println("Max number of probes: " + map.getMaxProbes());
        System.out.println("Load factor: " + map.getLoadFactor());
    
        ArrayList<Incident> uniqueIncidents = new ArrayList<>();
        for (Incident incident : map.values()) {
            uniqueIncidents.add(incident);
        }
        return uniqueIncidents;
    }


    public ArrayList<Incident> hashDoubleDeduplication() {
        DoubleHashMap<String, Incident> map = new DoubleHashMap<>(1000003);
        for (Incident incident : incidents) {
            String key = incident.key();
            map.put(key, incident); // In case of a collision, double hashing will be used
        }

        System.out.println("Average number of probes: " + map.getAvgProbes());
        System.out.println("Max number of probes: " + map.getMaxProbes());
        System.out.println("Load factor: " + map.getLoadFactor());
        ArrayList<Incident> uniqueIncidents = new ArrayList<>();
        for (Incident incident : map.values()) {
            uniqueIncidents.add(incident);
        }
        return uniqueIncidents;
    }
    

    public ArrayList<Incident> quickSortDeduplication() {
        Utils.quickSort(incidents);
        return deduplicateSorted(incidents);
    }

    public ArrayList<Incident> builtinSortDeduplication() {
        Collections.sort(incidents);
        return deduplicateSorted(incidents);
    }


    private ArrayList<Incident> deduplicateSorted(ArrayList<Incident> sortedIncidents) {
        ArrayList<Incident> uniqueIncidents = new ArrayList<>();
        for (int i = 0; i < sortedIncidents.size(); i++) {
            Incident current = sortedIncidents.get(i);
            if (i == 0 || !current.equals(sortedIncidents.get(i - 1))) {
                uniqueIncidents.add(current);
            }
        }
        return uniqueIncidents;
    }
}