import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args)
    {
        final String FILE_PATH = "integers.txt";

        // Ask the user for d value
        int d = IntegersReader.getIntFromConsole();

        // Read integers from the file and insert them into the list (need to ask the user from path?)
        List<Integer> heapIntegers = new ArrayList<>();

        try {
            heapIntegers = IntegersReader.ReadIntegersFromFile(FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        }

        // Build DHeap with all integers from list
        DHeap dHeap = new DHeap(heapIntegers.size(), d);
        heapIntegers.forEach(integer -> dHeap.insert(integer));

        ///*
        System.out.println("Print heap example: ");

        System.out.print(dHeap);

        System.out.println();

        for (int i = 1; i <= heapIntegers.size(); i++)
        {
            System.out.print(dHeap.ExtractMax() + " -> ");
        }
        //*/

        // Check extract max function
        /*
        System.out.println();

        System.out.println("Extract Max = " + dHeap.ExtractMax());

        System.out.println("Print heap after ExtractMax: ");

        System.out.print(dHeap);

        System.out.println();

        for (int i = 1; i <= heapIntegers.size(); i++)
        {
            System.out.print(dHeap.ExtractMax() + " -> ");
        }
        */


    }
}
