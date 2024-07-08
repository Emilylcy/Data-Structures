import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver00 {
    public static void main(String[] args) {
        String filename = args[0];
        int numEntries = Integer.parseInt(args[1]);

        Place[] places;
        try {
            places = LookupZip.readZipCodes(filename, numEntries);
        } catch (FileNotFoundException e) {
            System.out.println("No such file");
            return;
        }

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("zipcode: ");
            String zipcode = input.nextLine();
            
            if (zipcode.equals("00000")) {
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
