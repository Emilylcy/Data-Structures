import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main driver class for handling polling data.
 */
public class DriverHW06 {
    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        CommandLineOptions options = parseArguments(args);

        ArrayHeap<PollingData> heap = new ArrayHeap<>();
        for (String file : options.files) {
            processFile(file, heap);
        }

        for (String candidateToRemove : options.removeCandidates) {
            heap.remove(new PollingData(candidateToRemove, "", 0));
        }

        if (options.topN > 0) {
            System.out.println("Top " + options.topN + " Candidates:");
            List<PollingData> topCandidates = heap.peekTopN(options.topN);
            for (PollingData candidate : topCandidates) {
                System.out.println(candidate.toString());
            }
        }
    }

    private static CommandLineOptions parseArguments(String[] args) {
        int topN = 0;
        List<String> files = new ArrayList<>();
        List<String> removeCandidates = new ArrayList<>();
        boolean expectingNumber = false;
        boolean collectingCandidates = false;

        for (String arg : args) {
            if (arg.startsWith("-")) {
                if ("-n".equals(arg)) {
                    expectingNumber = true;
                } else if ("-r".equals(arg)) {
                    collectingCandidates = true;
                }
            } else if (expectingNumber) {
                topN = Integer.parseInt(arg);
                expectingNumber = false;
            } else if (collectingCandidates) {
                if (arg.contains(".csv")) {
                    files.add(arg);
                    collectingCandidates = false;
                } else {
                    removeCandidates.add(arg);
                }
            } else {
                files.add(arg);
            }
        }

        return new CommandLineOptions(topN, files, removeCandidates);
    }

    private static void 
        processFile(String fileName, ArrayHeap<PollingData> heap) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip header line
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String lastName = parts[0].trim();
                    String fullName = parts[1].trim();
                    double percent = Double.parseDouble(parts[2].trim());
                    heap.insert(new PollingData(lastName, fullName, percent));
                }
            }
            System.out.println(heap.toString());
        } catch (FileNotFoundException e) {
            System.out.println("Error processing file: " + fileName);
        }
    }

    static class CommandLineOptions {
        int topN;
        List<String> files;
        List<String> removeCandidates;

        CommandLineOptions(int topN, 
            List<String> files, List<String> removeCandidates) {
            this.topN = topN;
            this.files = files;
            this.removeCandidates = removeCandidates;
        }
    }
}
