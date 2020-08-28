import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
public class project
{
	static int n = 256;
	static int k = 12;
	private static Scanner scan;

	public static void main(String[] args) throws IOException
	{

/* Optional  - reading from a file containing 256 integers.
		File f = new File("Descending.txt");
		try {
			scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Scanner scan = new Scanner(new FileInputStream("array.dat"));
		int [] L = new int[n];
		for (int i = 0; i < n ; i++)
		{
			L[i] = scan.nextInt();
		}
		Projectsort(L);
*/		

	}

	public static void Projectsort(int [] L)
	{
		int [] [] L1 = new int [k] [n-(n/k*(k-1))]; // The size of the rest of the integers (n-(n/k*(k-1)) will always be bigger than n/k
		int [] ArraySize = new int [k];
		int count = 0;
		// The following loop's time effiency is O(k) * O(N/k) = O(N)
		for (int i=0; i<k-1; i++) // copying all the integers from Array L into K-1 smaller arrays
		{
			for (int j=0; j<n/k ; j++)
			{
				L1 [i] [j] = L[(n/k)*i+j];
			}
			ArraySize[i] = n/k; // Counts the array's size
		}
		for (int i=n/k*(k-1);i<n;++i) // The rest of the integers on array L
			L1[k-1][i-n/k*(k-1)]=L[i];
		ArraySize[k-1] = n-(n/k*(k-1)); // count of the rest of the integers
		for (int i = 0; i < k; i++) // Time efficiency O(k)*O(N/k)
		{
			count = count + SwitchMax(L1[i],ArraySize[i]);
		}
		for(int i = 0; i<n ; i++)
			count = count+ FindandDeleteMax(L1,ArraySize);
		System.out.println("\nThe number of comparison actions is " + count);
	}

	private static int FindandDeleteMax(int[][] L1, int[] ArraySize) {
		// Finding the max value from all the arrays, removes it, updates the current ArraySize, and use switchMax to find the next one
		int i=0;
		int count = 0;
		while(ArraySize[i]==0) // find the first Array that is not empty - Time effiency O(K)
		{
			i++;
		}
		int max = L1[i][0];
		int maxi = i; // to find the index
		for (int j = i+1; j < k; j++)
		{
			if (ArraySize[j] > 0)
			{
				count++;
				if(L1[j][0]>max)
				{
					maxi = j;
					max = L1[j][0];
				}
		}
		}
		System.out.print(max + " ");
		ArraySize[maxi]--; // decreasing the size of the "chosen" array
		if(ArraySize[maxi]>0) // taking the last useable cell and find max
		{

			L1[maxi][0] = L1[maxi][ArraySize[maxi]];
			count = count+SwitchMax(L1[maxi], ArraySize[maxi]);
		}
		return count;
	}

	private static int SwitchMax(int[] L, int len) {
		// Switch the first cell with the maximum value
		// Time efficiency O(len) (Which starts from n/k and gets smaller)
		int count = 0;
		if (len > 0)
		{
			int max = L[0];
			int maxi = 0;
			for(int i=1; i<len; i++)
			{
				count++;
				if(L[i]> max)
				{
					maxi = i;
					max = L[i];
				}
			}
			L[maxi] = L[0];
			L[0] = max;
		}
		return count;

	}


}
