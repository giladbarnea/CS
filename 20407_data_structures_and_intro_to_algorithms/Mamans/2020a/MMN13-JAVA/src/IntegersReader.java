import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IntegersReader {
    
    public static List<Integer> ReadIntegersFromFile(String filePath) throws FileNotFoundException
    {
        List<Integer> Integers = new ArrayList<>();

        // Create file object of file in path
        File fileToRead = new File(filePath);

        // Continue only if file exists and not directory
        if (fileToRead.exists() && !fileToRead.isDirectory())
        {
            Scanner fileScanner = new Scanner(fileToRead);

            // Read all integers and insert them into the list
            while (fileScanner.hasNextInt())
            {
                Integers.add(fileScanner.nextInt());
            }
        }

        return  Integers;
    }

    public static int getIntFromConsole()
    {
        while (true)
        {
            while(true){
                try {
                    System.out.print("Enter your d value: ");
                    return Integer.parseInt(new Scanner(System.in).next());
                } catch(NumberFormatException ne) {
                    System.out.println("You must enter valid integer value, Try again.");
                }
            }
        }
    }
}
