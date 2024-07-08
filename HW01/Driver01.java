import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver01 {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.out.println("incorrect number of arguments");
            return;
        }

        String filename1 = args[0];
        String filename2 = args[1];

        ExpandableArray<Place> places = LookupZip.readZipCodes(filename1, filename2);

        if (places != null){
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
}