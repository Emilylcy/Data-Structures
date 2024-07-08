import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Driver class.
 */

public class DriverHW05 {
    /**
     * The main method of driver class.
     * @param args args
     * @throws FileNotFoundException exception
     */
    public static void main(String[] args) throws FileNotFoundException {
        for (int i = 0; i < args.length - 1; i++) {
            for (int j = 0; j < args.length - i - 1; j++) {
                String date1 = args[j].split("_")[1];
                String date2 = args[j + 1].split("_")[1];
                if (date1.compareTo(date2) > 0) {
                    String temp = args[j];
                    args[j] = args[j + 1];
                    args[j + 1] = temp;
                }
            }
        }

        LinkedBinaryTree<PollingData> tree = new LinkedBinaryTree<>();

        for (int i = 0; i < args.length; i++) {
            String filename = args[i];
            processFile(filename, tree);
            System.out.println("Tree:");
            System.out.println("Pre: " + tree.toStringPreOrder());
            System.out.println("In: " + tree.toStringInOrder());
            System.out.println("Post: " + tree.toStringPostOrder());
        }
    }

    private static void processFile(String filename, 
        LinkedBinaryTree<PollingData> tree) 
        throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("answer")) {
                continue; 
            }
            String[] parts = line.split(",");
            String lastName = parts[0].trim();
            String fullName = parts[1].trim();
            double percent = Double.parseDouble(parts[2].trim());

            PollingData data = new PollingData(lastName, fullName, percent);
            tree.insert(data); 
        }
        scanner.close();
    }
}
