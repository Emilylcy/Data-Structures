import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Driver class for reading zip codes and looking up places.
 */

public class DriverHW08 {
    /**
     * Read and Look zip codes from files.
     * 
     * @param args Command line arguments.
     * @throws FileNotFoundException if the specified files cannot be found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1) {
            System.out.println("incorrect number of arguments");
            return;
        }

        boolean debugMode = false;
        String filename1 = null;
        String filename2 = null;

        for (String arg : args) {
                if ("-d".equals(arg)) {
                    debugMode = true;
                } else if (filename1 == null) {
                    filename1 = arg;
                } else if (filename2 == null) {
                    filename2 = arg;
                }
            }

            AVLTree<Place> places = LookupZip.readZipCodes(filename1, filename2);

            if (debugMode) {
                System.out.println(places.height());
                System.out.println(places.toString());
            }
            

        if (places != null) {
            Scanner input = new Scanner(System.in);
            while (true) {
                System.out.print("zipcode: ");
                String zipcode = input.nextLine();

                if ("00000".equals(zipcode)) {
                    System.out.println("Good Bye!");
                    break;
                }

                Place output = LookupZip.lookupZip(places, zipcode);

                if (output == null) {
                    System.out.println("No such zipcode");
                    System.out.println();
                } else {
                    System.out.println(output);
                    System.out.println();
                }
            }
            input.close();
        }
    }
}