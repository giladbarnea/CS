
/**
 * Maman - 14
 * Author: Itay Mishan
 * This is the HeapMinMax class 
 **/

public class HeapMinMax {

	 static int heap[][];
	 static int heapCurrentLength=0;  // dinamicaly updated heap size
	 static final int HEIGHT = 4; // the fixed height of the array
	 static final int ref2MinHeap = 1; // the array that contains the reference to the minimum heap
	 private static final int maxVector = 0; // the line that contains the values of the maximum heap
	private static final int minVector = 2; // the line that contains the values of the minimum heap
	private static final int ref2MaxHeap = 3; // the line that contains the reference to the maximum heap

	
	/**
	 * The contractor
	 * @ param arr, the temporary array that contains the numbers 
	 * @ param n, the temporary arrayheapMaxLength (occupied cells in array)
	 */
	
	public  HeapMinMax (int [] arr, int n) 
    {
      heapCurrentLength = n;
      heap = new int [HEIGHT] [20]; // builds an array of ints (100 X 4)

      for ( int i=0; i<heapCurrentLength; i++) // works only with the occupied array cells
      {
    	  heap [maxVector] [i] = arr[i];
      	  heap [minVector] [i] = arr[i];
      	  heap [ref2MinHeap] [i] = i;
      	  heap [ref2MaxHeap] [i] = i;
      }
      Build();  // starts the Building operation
     }
	
	
	/**
	 * Build function
	 * Builds the Heap based on minimum heap and maximum heap that are connected between them O(n)
	**/
	
//     public static void Build()
//     {
//     	int maxLimit=((heapCurrentLength/2)-1);
// 		int minLimit=((heapCurrentLength/2)-1);
//     	
// 		while ( maxLimit>=0)
// 			{
// 			MaxHeapify(maxLimit);
// 			maxLimit--;
// 			}
//     	//for (; maxLimit>=0; maxLimit--) // performs the heapify operation on the maximum heap
//     	//{
//     	//	MaxHeapify(maxLimit);
//     	//}
//     	while ( minLimit>=0)
// 			{
// 			MaxHeapify(minLimit);
// 			minLimit--;
// 			}
//     	//for (; minLimit>=0; minLimit--) // performs the heapify operation on the minimum heap
//     	//{
//     	//	MinHeapify(minLimit);
//     	//}
//     	
// 		System.out.println("The updated Heap is:");
// 		printHeap();
// 
//     }
        public static void Build()
    {
      int max=((heapCurrentLength/2)-1);
      
      for (; max>=0; max--) // performs the heapify operation on the maximum heap
      {
            MaxHeapify(max);
      }
      
      int min=((heapCurrentLength/2)-1);
      for (; min>=0; min--) // performs the heapify operation on the minimum heap
      {
            MinHeapify(min);
      }
      
            System.out.println("The updated structure is:");

            for (int i=0; i<HEIGHT; i++) // prints the structure at the end of the operation (according to the assignment request)
            {
                  if (i==0)
                  {
                        System.out.print("This line represents the maximum heap: ");
                  }
                  if (i==2)
                  {
                        System.out.print("This line represents the minimum heap: ");

                  }
                  for (int j=0; j<heapCurrentLength; j++)
                  {
                        System.out.print(" ");
                        System.out.print(heap[i][j]);    
                  }
                  System.out.println("");
            }

    }
	/**
	 * printHeap function prints the heap
	 * returns void
	**/
   public static void printHeap()
   {
   		for (int i=0; i<HEIGHT; i=i+2)
		{
			if (i==0)
			{
				System.out.print("The maximum heap is:  ");
				for (int j=0; j<heapCurrentLength; j++)
				{
					System.out.print(" ");
					System.out.print(heap[i][j]);	
				}
				System.out.println(" ");
			}
			if (i==2)
			{
				System.out.print("The minimum heap is:  ");
				for (int j=0; j<heapCurrentLength; j++)
				{
					System.out.print(" ");
					System.out.print(heap[i][j]);	
				}
				System.out.println(" ");

			}
		}
	}
   
/**
    * Maximum heapify function is suppose to arrange the structure to be a valid maximum heap O(n)
    * @param index, the index on the maximum heap to work on
   **/
      
    public static void MaxHeapify(int index)
    {
    	int left = ((2 * index)+1); // the index of the left son
    	int right = ((2 * index)+2); // the index of the right son
    	int largest; // will save the largest value, and will be updated every step
    	int tempVal; // saves the value during the exchange between values
    	int tempPos; // saves the position value during the exchange between values
    	int indexPosTemp; // saves the position of the index during the exchange
    	

	    	if ((left <= heapCurrentLength) && (heap[maxVector][left] > heap[maxVector][index])) // checks who is higher, the left son or the parent, if the left son exists 
	    	{
	    		largest = left; // saves the index to the largest if the left son is higher
	    	}

	    else
	    	{
	    		largest = index; // saves the index to the largest if the index (parent) is higher
	    	}
    	
    	if (heapCurrentLength > right)
    	{
	    	if ((right <= heapCurrentLength) && (heap[maxVector][right] > heap[maxVector][largest])) // checks who is higher, the right son or the largest so far, if the right son exists 
	    	{
	    		largest = right;
	    	}
    	}
	    if (largest != index && heapCurrentLength>left) // if the largest in this sub heap is not the parent and there is a son
	    	{
	    		tempVal = heap[maxVector][largest];
	    		tempPos = heap[ref2MinHeap][largest];
	    		indexPosTemp = heap[ref2MinHeap][index]; // saves the position of this value in the minimum heap 
	    		heap[maxVector][largest] = heap[maxVector][index]; // copies the index to the largest place
	    		heap[ref2MinHeap][largest] = heap[ref2MinHeap][index]; // copies the reference to minimum heap
	    		heap[ref2MaxHeap][indexPosTemp] = largest; // updates the minimum heap about the switch
	    		heap[maxVector][index] = tempVal; // copies the value into the new position
	    		heap[ref2MinHeap][index] = tempPos; // copies the reference into the new position
	    		heap[ref2MaxHeap][tempPos] = index; // updates the minimum heap about the switch
	    	   	MaxHeapify(largest);
	    	}
    	}
 
      
    /**
     * Minimum heapify function is suppose to arrange the structure to be a valid minimum heap O(n)
     * @param index, the index on the minimum heap to work on
    **/
    
    public static void MinHeapify(int index)
    {
    	int left = ((2 * index)+1); // the index of the left son
    	int right = ((2 * index)+2); // the index of the right son
    	int smallest; // will save the largest value, and will be updated every step
    	int tempVal; // saves the value during the exchange between values
    	int tempPos; // saves the position value during the exchange between values
    	int indexPosTemp; // saves the position of the index during the exchange
    	
	    	if ((left <= heapCurrentLength) && (heap[minVector][left] < heap[minVector][index])) // checks who is lower, the left son or the parent, if the left son exists 
	    	{
	    		smallest = left; // saves the index to the smallest if the left son is lower
	    	}
	    else
	    	{
	    		smallest = index; // saves the index to the smallest if the index (parent) is lower
	    	}
    	
    	if (heapCurrentLength > right)
    	{
	    	if ((right <= heapCurrentLength) && (heap[minVector][right] < heap[minVector][smallest])) // checks who is lower, the right son or the smallest so far, if the right sun exists 
	    	{
	    		smallest = right;
	    	}
    	}
	    if (smallest != index && heapCurrentLength>left) // if the largest in this sub heap is not the parent and there is a son
	    	{
	    		tempVal = heap[minVector][smallest];
	    		tempPos = heap[ref2MaxHeap][smallest];
	    		indexPosTemp = heap[ref2MaxHeap][index]; // saves the position of this value in the maximum heap 
	    		heap[minVector][smallest] = heap[minVector][index]; // copies the index to the largest place
	    		heap[ref2MaxHeap][smallest] = heap[ref2MaxHeap][index]; // copies the reference to maximum heap
	    		heap[ref2MinHeap][indexPosTemp] = smallest; // updates the maximum heap about the switch
	    		heap[minVector][index] = tempVal; // copies the value into the new position
	    		heap[ref2MaxHeap][index] = tempPos; // copies the reference into the new position
	    		heap[ref2MinHeap][tempPos] = index; // updates the maximum heap about the switch
	    	   	MinHeapify(smallest);
	    	}
    	}
    
    
    /**
     * prints the maximum value in the Heap O(1)
    **/ 
    public static void FindMax()
		{
    	System.out.println ("The max value is: " +heap[0][0]);	
    	System.out.println("The Heap is:");
		printHeap();
		}
    
    /**
     * finds the minimum value in the Heap
	 * returns void
    **/
    public static void FindMin()
		{
    	System.out.println ("The minimum value is: "+heap[2][0]);
		System.out.println("The Heap is:");
		printHeap();
		}
    
    /**
     * inserts a new value into the Heap O(lgn)
     * @ param int x: value to insert into the heap
	 * return void
    **/
      public static void Insert(int x)
    {
		int max = heapCurrentLength; // in order to work on the value on another variable (saved before the enlargement)
		int min = heapCurrentLength; // in order to work on the value on another variable (saved before the enlargement)

    	if (heapCurrentLength >=100) // protection from exception from the structure
    	{
    		System.out.println("It's impossible to insert anymore numbers, the structure is full!");
    		System.out.println("in order to insert another number, please delete the max or min value");
    	}
    	else{
    		heapCurrentLength = heapCurrentLength+1; // enlargement of the structure by one
    		
    		/* at the beginning, insertion into the maximum heap
    		*/
    		
    		int tempMaxRef; // temporary reference
    		while ((max > 0) && (heap [maxVector][Parent(max)]<x))
    		{
    			heap[maxVector][max] = heap[maxVector][Parent(max)]; // copies the parent into the son node
    			heap[ref2MinHeap][max] = heap[ref2MinHeap][Parent(max)]; // copies the reference
    			tempMaxRef = heap[ref2MinHeap][max]; // saves the reference
    			heap[ref2MaxHeap][tempMaxRef] = max; // updates the minimum heap
    			max = Parent(max); // the index now points to his parent
    		}
    		heap[maxVector][max] = x; // the new value inserted to the proper place
    		
    		/* insertion into the minimum heap
    		*/
    		
    		int tempMinRef; // temporary reference
    		while ((min > 0) && (heap [minVector][Parent(min)]>x))
    		{
    			heap[minVector][min] = heap[minVector][Parent(min)]; // copies the parent into the son node
    			heap[ref2MaxHeap][min] = heap[ref2MaxHeap][Parent(min)]; // copies the reference
    			tempMinRef = heap[ref2MaxHeap][min]; // saves the reference
    			heap[ref2MinHeap][tempMinRef] = min; // updates the maximum heap
    			min = Parent(min); // the index now points to his parent
    		}
    		heap[minVector][min] = x; // the new value inserted to the proper place   
    		
    		/* update of the references between the heaps
    		 */
    		
    		heap[ref2MinHeap][max] = min; // updates the reference from the max to the min heap
    		heap[ref2MaxHeap][min] = max; // updates the reference from the min to the max heap
    	}
		System.out.println("The inserted value is: " + x);
		System.out.println("The updated structure is: ");
		printHeap();
    }
    
    /**
	 * This function checks who is the parent value 
     * @param i: represents the index which parent needs to be found
     * @return: the parent
    **/
    
    public static int Parent(int i)
    {
    	//int parentVal = ((i-1)/2);
    	//return parentVal;
		return ((i-1)/2);
    }
    
    /**
     * This function deletes and prints the maximal value in the Heap
	 * return void
    **/
   public static void DeleteMax()
    {
    	if (heapCurrentLength<=1) // if there is only one number left (underflow)
    	{
    		System.out.println("It's impossible to delete anymore numbers, There is only one number left");
    	}
    	else
    	{
    		int max = heap[maxVector][0]; // for printing at the end
    		int tempPosition = heap[ref2MinHeap][0]; // for future use to point the reference and delete the value in the minimum heap
    		heap[maxVector][0] = heap[maxVector][heapCurrentLength-1]; // copies the last value into the first in the heap
    		heap[ref2MinHeap][0] = heap[ref2MinHeap][heapCurrentLength-1]; // copies the last value position reference
    		int refCopied = heap[ref2MinHeap][0];
    		heap[ref2MaxHeap][refCopied] = 0;
    		if (tempPosition == heapCurrentLength-1) // if the deleted max is the last in the minimum heap
    		{
        		heapCurrentLength = (heapCurrentLength-1); // reduction of the structure by one
        		MaxHeapify(0);
        		System.out.println("The maximum value is: "+max); // prints the maximum value
    		}
    		else // if the deleted max is somewhere in the middle of the minimum heap 
    		{
    			int parent = Parent (tempPosition); // saves the parent of the deleted number
    			heap[minVector][tempPosition] = heap[minVector][heapCurrentLength-1]; // copies the last instead of the deleted value
    			int pointerToSwitchedVal = heap[ref2MaxHeap][heapCurrentLength-1]; // saves the reference from the moved last value into the max heap
    			heap[ref2MaxHeap][tempPosition] = heap[ref2MaxHeap][heapCurrentLength-1]; // copies the last instead of the deleted position
        		heap[ref2MinHeap][pointerToSwitchedVal] = tempPosition;
    			heapCurrentLength = (heapCurrentLength-1); // reduction of the structure by one
        		if ((heap[minVector][parent]) > (heap[minVector][tempPosition])) // Switches between the parent and son to organize the heap again
        		{
        			int parentValueForSwitching = (heap[minVector][parent]); // parent value
        			int parentPosForSwitching = (heap[ref2MaxHeap][parent]); // parent position
        			int referenceTempPos = heap[ref2MaxHeap][tempPosition]; // reference to the maximum heap
        			heap[minVector][parent] = heap[minVector][tempPosition]; // copies the son to the parent
        			heap[ref2MaxHeap][parent] = heap[ref2MaxHeap][tempPosition]; // copies the position of the son to the parent
        			heap[ref2MinHeap][referenceTempPos] = parent; // update of the maximum heap
        			heap[minVector][tempPosition] = parentValueForSwitching; //temp into parent
        			heap[ref2MaxHeap][tempPosition] = parentPosForSwitching; // temp position into parent
        			heap[ref2MinHeap][parentPosForSwitching] = tempPosition; // update of the maximum heap

        		}
    			MaxHeapify(0); // heapify to all the heap from the root
        		System.out.println("The maximum value is: "+max); // prints the maximum value
        		System.out.println("The updated structure is: ");

    		}
    		
			printHeap();
    	}
    }
    
    /**
     * This functions deletes and prints the minimal value in the structure
    **/
    
    public static void DeleteMin()
    {
    	if (heapCurrentLength<=1) // if there is only one number left (underflow)
    	{
    		System.out.println("It's impossible to delete anymore numbers, There is only one number left");
    	}
    	else
    	{
    		int min = heap[minVector][0]; // for printing at the end
    		int tempPosition = heap[ref2MaxHeap][0]; // for future use to point the reference and delete the value in the maximum heap
    		heap[minVector][0] = heap[minVector][heapCurrentLength-1]; // copies the last value into the first in the heap
    		heap[ref2MaxHeap][0] = heap[ref2MaxHeap][heapCurrentLength-1]; // copies the last value position reference
    		int refCopied = heap[ref2MaxHeap][0];
    		heap[ref2MinHeap][refCopied] = 0;
    		if (tempPosition == heapCurrentLength-1) // if the deleted min is the last in the maximum heap
    		{
        		heapCurrentLength = (heapCurrentLength-1); // reduction of the structure by one
        		MinHeapify(0);
        		System.out.println("The minimum value is: "+min); // prints the minimum value
        		System.out.println("The updated structure is: ");

    		}
    		else // if the deleted min is somewhere in the middle of the maximum heap 
    		{
    			int parent = Parent (tempPosition); // saves the parent of the deleted number
    			heap[maxVector][tempPosition] = heap[maxVector][heapCurrentLength-1]; // copies the last instead of the deleted value
    			int pointerToSwitchedVal = heap[ref2MinHeap][heapCurrentLength-1]; // saves the reference from the moved last value into the min heap
    			heap[ref2MinHeap][tempPosition] = heap[ref2MinHeap][heapCurrentLength-1]; // copies the last instead of the deleted position
        		heap[ref2MaxHeap][pointerToSwitchedVal] = tempPosition;
    			heapCurrentLength = (heapCurrentLength-1); // reduction of the structure by one
        		if ((heap[maxVector][parent]) < (heap[maxVector][tempPosition])) // Switches between the parent and son to organize the heap again
        		{
        			int parentValueForSwitching = (heap[maxVector][parent]); // parent value
        			int parentPosForSwitching = (heap[ref2MinHeap][parent]); // parent position
        			int referenceTempPos = heap[ref2MinHeap][tempPosition]; // reference to the minimum heap
        			heap[maxVector][parent] = heap[maxVector][tempPosition]; // copies the son to the parent
        			heap[ref2MinHeap][parent] = heap[ref2MinHeap][tempPosition]; // copies the position of the son to the parent
        			heap[ref2MaxHeap][referenceTempPos] = parent; // update of the minimum heap
        			heap[maxVector][tempPosition] = parentValueForSwitching; //temp into parent
        			heap[ref2MinHeap][tempPosition] = parentPosForSwitching; // temp position into parent
        			heap[ref2MaxHeap][parentPosForSwitching] = tempPosition; // update of the minimum heap

        		}
    			MinHeapify(0); // heapify to all the heap from the root
        		System.out.println("The minimum value is: "+min); // prints the minimum value
    		}
    		
		printHeap();
    	}
    
    }
}


