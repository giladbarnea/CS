
/**
 * Author: Ron Sne
 * i.d: 305840571
 * cell phone: 050-4751814
 * Maman - 14
 * This is the structure class 
 * contains all the possible operations that can be performed on the structure 
 **/

public class Structure {

	private static int _struct[][];
	private static int _structLength=0;
	private static final int HEIGHT = 4; // the fixed height of the structure
	private static final int LENGTH = 100; // the maximum length of the structure
	private static final int MAXVAL = 0; // the line that contains the values of the maximum heap
	private static final int MAXPOS = 1; // the line that contains the reference to the minimum heap
	private static final int MINVAL = 2; // the line that contains the values of the minimum heap
	private static final int MINPOS = 3; // the line that contains the reference to the maximum heap
	int temptemptemp = _structLength;

	
	/**
	 * The contractor
	 * @param tempArray, the temporary array that contains the numbers 
	 * @param n, the temporary array length (occupied length)
	 */
	
	public  Structure (int [] tempArray, int n) 
    {
      _structLength = n;
      _struct = new int [HEIGHT] [LENGTH]; // builds an array of ints (100 X 4)

      for ( int i=0; i<_structLength; i++) // works only with the occupied array cells
      {
    	  _struct [MAXVAL] [i] = tempArray[i];
      	  _struct [MINVAL] [i] = tempArray[i];
      	  _struct [MAXPOS] [i] = i;
      	  _struct [MINPOS] [i] = i;
      }
      Build();  // starts the Building operation
     }
	
	
	/**
	 * Build function
	 * Builds the structure based on minimum heap and maximum heap that are connected between them O(n)
	**/
	
    public static void Build()
    {
    	int max=((_structLength/2)-1);
    	
    	for (; max>=0; max--) // performs the heapify operation on the maximum heap
    	{
    		MaxHeapify(max);
    	}
    	
    	int min=((_structLength/2)-1);
    	for (; min>=0; min--) // performs the heapify operation on the minimum heap
    	{
    		MinHeapify(min);
    	}
    	
		System.out.println("The updated structure is:");

		for (int i=0; i<HEIGHT; i=i+2) // prints the structure at the end of the operation (according to the assignment request)
		{
			if (i==0)
			{
				System.out.print("This line represents the maximum heap: ");
				for (int j=0; j<_structLength; j++)
				{
					System.out.print(" ");
					System.out.print(_struct[i][j]);	
				}
				System.out.println("");
			}
			if (i==2)
			{
				System.out.print("This line represents the minimum heap: ");
				for (int j=0; j<_structLength; j++)
				{
					System.out.print(" ");
					System.out.print(_struct[i][j]);	
				}
				System.out.println("");

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
    	
//    	if (_structLength > left)
//    	{
	    	if ((left <= _structLength) && (_struct[MAXVAL][left] > _struct[MAXVAL][index])) // checks who is higher, the left son or the parent, if the left son exists 
	    	{
	    		largest = left; // saves the index to the largest if the left son is higher
	    	}
//    	}
	    else
	    	{
	    		largest = index; // saves the index to the largest if the index (parent) is higher
	    	}
    	
    	if (_structLength > right)
    	{
	    	if ((right <= _structLength) && (_struct[MAXVAL][right] > _struct[MAXVAL][largest])) // checks who is higher, the right son or the largest so far, if the right son exists 
	    	{
	    		largest = right;
	    	}
    	}
	    if (largest != index && _structLength>left) // if the largest in this sub heap is not the parent and there is a son
	    	{
	    		tempVal = _struct[MAXVAL][largest];
	    		tempPos = _struct[MAXPOS][largest];
	    		indexPosTemp = _struct[MAXPOS][index]; // saves the position of this value in the minimum heap 
	    		_struct[MAXVAL][largest] = _struct[MAXVAL][index]; // copies the index to the largest place
	    		_struct[MAXPOS][largest] = _struct[MAXPOS][index]; // copies the reference to minimum heap
	    		_struct[MINPOS][indexPosTemp] = largest; // updates the minimum heap about the switch
	    		_struct[MAXVAL][index] = tempVal; // copies the value into the new position
	    		_struct[MAXPOS][index] = tempPos; // copies the reference into the new position
	    		_struct[MINPOS][tempPos] = index; // updates the minimum heap about the switch
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
    	
	    	if ((left <= _structLength) && (_struct[MINVAL][left] < _struct[MINVAL][index])) // checks who is lower, the left son or the parent, if the left son exists 
	    	{
	    		smallest = left; // saves the index to the smallest if the left son is lower
	    	}
	    else
	    	{
	    		smallest = index; // saves the index to the smallest if the index (parent) is lower
	    	}
    	
    	if (_structLength > right)
    	{
	    	if ((right <= _structLength) && (_struct[MINVAL][right] < _struct[MINVAL][smallest])) // checks who is lower, the right son or the smallest so far, if the right sun exists 
	    	{
	    		smallest = right;
	    	}
    	}
	    if (smallest != index && _structLength>left) // if the largest in this sub heap is not the parent and there is a son
	    	{
	    		tempVal = _struct[MINVAL][smallest];
	    		tempPos = _struct[MINPOS][smallest];
	    		indexPosTemp = _struct[MINPOS][index]; // saves the position of this value in the maximum heap 
	    		_struct[MINVAL][smallest] = _struct[MINVAL][index]; // copies the index to the largest place
	    		_struct[MINPOS][smallest] = _struct[MINPOS][index]; // copies the reference to maximum heap
	    		_struct[MAXPOS][indexPosTemp] = smallest; // updates the maximum heap about the switch
	    		_struct[MINVAL][index] = tempVal; // copies the value into the new position
	    		_struct[MINPOS][index] = tempPos; // copies the reference into the new position
	    		_struct[MAXPOS][tempPos] = index; // updates the maximum heap about the switch
	    	   	MinHeapify(smallest);
	    	}
    	}
    
    /**
     * prints the maximum value in the structure O(1)
    **/
    
    public static void FindMax()
    {
    	System.out.println ("The maximum value is: "+_struct[MAXVAL][0]);
		
    	System.out.println("The structure is:");

		for (int i=0; i<HEIGHT; i=i+2) // prints the structure at the end of the operation (according to the assignment request)
		{
			if (i==0)
			{
				System.out.print("This line represents the maximum heap: ");
				for (int j=0; j<_structLength; j++)
				{
					System.out.print(" ");
					System.out.print(_struct[i][j]);	
				}
				System.out.println("");
			}
			if (i==2)
			{
				System.out.print("This line represents the minimum heap: ");
				for (int j=0; j<_structLength; j++)
				{
					System.out.print(" ");
					System.out.print(_struct[i][j]);	
				}
				System.out.println("");

			}
			
		}
    }
    
    /**
     * prints the minimum value in the structure O(1)
    **/
  
    public static void FindMin()
    {
    	System.out.println ("The minimum value is: "+_struct[MINVAL][0]);
    	
		System.out.println("The structure is:");

		for (int i=0; i<HEIGHT; i=i+2) // prints the structure at the end of the operation (according to the assignment request)
		{
			if (i==0)
			{
				System.out.print("This line represents the maximum heap: ");
				for (int j=0; j<_structLength; j++)
				{
					System.out.print(" ");
					System.out.print(_struct[i][j]);	
				}
				System.out.println("");
			}
			if (i==2)
			{
				System.out.print("This line represents the minimum heap: ");
				for (int j=0; j<_structLength; j++)
				{
					System.out.print(" ");
					System.out.print(_struct[i][j]);	
				}
				System.out.println("");

			}
			
		}
    }
    
    /**
     * inserts a new value into the structure O(lgn)
     * @param x: represents the new value to be inserted 
    **/
    
    public static void Insert(int x)
    {
		int max = _structLength; // in order to work on the value on another variable (saved before the enlargement)
		int min = _structLength; // in order to work on the value on another variable (saved before the enlargement)

    	if (_structLength >=100) // protection from exception from the structure
    	{
    		System.out.println("It's impossible to insert anymore numbers, the structure is full!");
    		System.out.println("in order to insert another number, please delete the max or min value");
    	}
    	else{
    		_structLength = _structLength+1; // enlargement of the structure by one
    		
    		/* at the beginning, insertion into the maximum heap
    		*/
    		
    		int tempMaxRef; // temporary reference
    		while ((max > 0) && (_struct [MAXVAL][Parent(max)]<x))
    		{
    			_struct[MAXVAL][max] = _struct[MAXVAL][Parent(max)]; // copies the parent into the son node
    			_struct[MAXPOS][max] = _struct[MAXPOS][Parent(max)]; // copies the reference
    			tempMaxRef = _struct[MAXPOS][max]; // saves the reference
    			_struct[MINPOS][tempMaxRef] = max; // updates the minimum heap
    			max = Parent(max); // the index now points to his parent
    		}
    		_struct[MAXVAL][max] = x; // the new value inserted to the proper place
    		
    		/* insertion into the minimum heap
    		*/
    		
    		int tempMinRef; // temporary reference
    		while ((min > 0) && (_struct [MINVAL][Parent(min)]>x))
    		{
    			_struct[MINVAL][min] = _struct[MINVAL][Parent(min)]; // copies the parent into the son node
    			_struct[MINPOS][min] = _struct[MINPOS][Parent(min)]; // copies the reference
    			tempMinRef = _struct[MINPOS][min]; // saves the reference
    			_struct[MAXPOS][tempMinRef] = min; // updates the maximum heap
    			min = Parent(min); // the index now points to his parent
    		}
    		_struct[MINVAL][min] = x; // the new value inserted to the proper place   
    		
    		/* update of the references between the heaps
    		 */
    		
    		_struct[MAXPOS][max] = min; // updates the reference from the max to the min heap
    		_struct[MINPOS][min] = max; // updates the reference from the min to the max heap
    	}
		System.out.println("The inserted value is: " + x);
		System.out.println("The updated structure is: ");

		for (int i=0; i<HEIGHT; i=i+2) // prints the structure at the end of the operation (according to the assignment request)
		{
			if (i==0)
			{
				System.out.print("This line represents the maximum heap: ");
				for (int j=0; j<_structLength; j++)
				{
					System.out.print(" ");
					System.out.print(_struct[i][j]);	
				}
				System.out.println("");
			}
			if (i==2)
			{
				System.out.print("This line represents the minimum heap: ");
				for (int j=0; j<_structLength; j++)
				{
					System.out.print(" ");
					System.out.print(_struct[i][j]);	
				}
				System.out.println("");

			}
			
		}
    }
    
    /**
     * @param i: represents the index which parent needs to be found
     * @return: the parent
    **/
    
    public static int Parent(int i)
    {
    	int roundedDown = ((i-1)/2);
    	return roundedDown;
    }
    
    /**
     * This functions deletes and prints the maximal value in the structure
    **/
   
    public static void DeleteMax()
    {
    	if (_structLength<=1) // if there is only one number left (underflow)
    	{
    		System.out.println("It's impossible to delete anymore numbers, There is only one number left");
    	}
    	else
    	{
    		int max = _struct[MAXVAL][0]; // for printing at the end
    		int tempPosition = _struct[MAXPOS][0]; // for future use to point the reference and delete the value in the minimum heap
    		_struct[MAXVAL][0] = _struct[MAXVAL][_structLength-1]; // copies the last value into the first in the heap
    		_struct[MAXPOS][0] = _struct[MAXPOS][_structLength-1]; // copies the last value position reference
    		int refCopied = _struct[MAXPOS][0];
    		_struct[MINPOS][refCopied] = 0;
    		if (tempPosition == _structLength-1) // if the deleted max is the last in the minimum heap
    		{
        		_structLength = (_structLength-1); // reduction of the structure by one
        		MaxHeapify(0);
        		System.out.println("The maximum value is: "+max); // prints the maximum value
    		}
    		else // if the deleted max is somewhere in the middle of the minimum heap 
    		{
    			int parent = Parent (tempPosition); // saves the parent of the deleted number
    			_struct[MINVAL][tempPosition] = _struct[MINVAL][_structLength-1]; // copies the last instead of the deleted value
    			int pointerToSwitchedVal = _struct[MINPOS][_structLength-1]; // saves the reference from the moved last value into the max heap
    			_struct[MINPOS][tempPosition] = _struct[MINPOS][_structLength-1]; // copies the last instead of the deleted position
        		_struct[MAXPOS][pointerToSwitchedVal] = tempPosition;
    			_structLength = (_structLength-1); // reduction of the structure by one
        		if ((_struct[MINVAL][parent]) > (_struct[MINVAL][tempPosition])) // Switches between the parent and son to organize the heap again
        		{
        			int parentValueForSwitching = (_struct[MINVAL][parent]); // parent value
        			int parentPosForSwitching = (_struct[MINPOS][parent]); // parent position
        			int referenceTempPos = _struct[MINPOS][tempPosition]; // reference to the maximum heap
        			_struct[MINVAL][parent] = _struct[MINVAL][tempPosition]; // copies the son to the parent
        			_struct[MINPOS][parent] = _struct[MINPOS][tempPosition]; // copies the position of the son to the parent
        			_struct[MAXPOS][referenceTempPos] = parent; // update of the maximum heap
        			_struct[MINVAL][tempPosition] = parentValueForSwitching; //temp into parent
        			_struct[MINPOS][tempPosition] = parentPosForSwitching; // temp position into parent
        			_struct[MAXPOS][parentPosForSwitching] = tempPosition; // update of the maximum heap

        		}
    			MaxHeapify(0); // heapify to all the heap from the root
        		System.out.println("The maximum value is: "+max); // prints the maximum value
        		System.out.println("The updated structure is: ");

    		}
    		
    		for (int i=0; i<HEIGHT; i=i+2) // prints the structure at the end of the operation (according to the assignment request)
    		{
    			if (i==0)
    			{
    				System.out.print("This line represents the maximum heap: ");
    				for (int j=0; j<_structLength; j++)
    				{
    					System.out.print(" ");
    					System.out.print(_struct[i][j]);	
    				}
    				System.out.println("");
    			}
    			if (i==2)
    			{
    				System.out.print("This line represents the minimum heap: ");
    				for (int j=0; j<_structLength; j++)
    				{
    					System.out.print(" ");
    					System.out.print(_struct[i][j]);	
    				}
    				System.out.println("");

    			}
    			
    		}
    	}
    }
    
    /**
     * This functions deletes and prints the minimal value in the structure
    **/
    
    public static void DeleteMin()
    {
    	if (_structLength<=1) // if there is only one number left (underflow)
    	{
    		System.out.println("It's impossible to delete anymore numbers, There is only one number left");
    	}
    	else
    	{
    		int min = _struct[MINVAL][0]; // for printing at the end
    		int tempPosition = _struct[MINPOS][0]; // for future use to point the reference and delete the value in the maximum heap
    		_struct[MINVAL][0] = _struct[MINVAL][_structLength-1]; // copies the last value into the first in the heap
    		_struct[MINPOS][0] = _struct[MINPOS][_structLength-1]; // copies the last value position reference
    		int refCopied = _struct[MINPOS][0];
    		_struct[MAXPOS][refCopied] = 0;
    		if (tempPosition == _structLength-1) // if the deleted min is the last in the maximum heap
    		{
        		_structLength = (_structLength-1); // reduction of the structure by one
        		MinHeapify(0);
        		System.out.println("The minimum value is: "+min); // prints the minimum value
        		System.out.println("The updated structure is: ");

    		}
    		else // if the deleted min is somewhere in the middle of the maximum heap 
    		{
    			int parent = Parent (tempPosition); // saves the parent of the deleted number
    			_struct[MAXVAL][tempPosition] = _struct[MAXVAL][_structLength-1]; // copies the last instead of the deleted value
    			int pointerToSwitchedVal = _struct[MAXPOS][_structLength-1]; // saves the reference from the moved last value into the min heap
    			_struct[MAXPOS][tempPosition] = _struct[MAXPOS][_structLength-1]; // copies the last instead of the deleted position
        		_struct[MINPOS][pointerToSwitchedVal] = tempPosition;
    			_structLength = (_structLength-1); // reduction of the structure by one
        		if ((_struct[MAXVAL][parent]) < (_struct[MAXVAL][tempPosition])) // Switches between the parent and son to organize the heap again
        		{
        			int parentValueForSwitching = (_struct[MAXVAL][parent]); // parent value
        			int parentPosForSwitching = (_struct[MAXPOS][parent]); // parent position
        			int referenceTempPos = _struct[MAXPOS][tempPosition]; // reference to the minimum heap
        			_struct[MAXVAL][parent] = _struct[MAXVAL][tempPosition]; // copies the son to the parent
        			_struct[MAXPOS][parent] = _struct[MAXPOS][tempPosition]; // copies the position of the son to the parent
        			_struct[MINPOS][referenceTempPos] = parent; // update of the minimum heap
        			_struct[MAXVAL][tempPosition] = parentValueForSwitching; //temp into parent
        			_struct[MAXPOS][tempPosition] = parentPosForSwitching; // temp position into parent
        			_struct[MINPOS][parentPosForSwitching] = tempPosition; // update of the minimum heap

        		}
    			MinHeapify(0); // heapify to all the heap from the root
        		System.out.println("The minimum value is: "+min); // prints the minimum value
    		}
    		
    		for (int i=0; i<HEIGHT; i=i+2) // prints the structure at the end of the operation (according to the assignment request)
    		{
    			if (i==0)
    			{
    				System.out.print("This line represents the maximum heap: ");
    				for (int j=0; j<_structLength; j++)
    				{
    					System.out.print(" ");
    					System.out.print(_struct[i][j]);	
    				}
    				System.out.println("");
    			}
    			if (i==2)
    			{
    				System.out.print("This line represents the minimum heap: ");
    				for (int j=0; j<_structLength; j++)
    				{
    					System.out.print(" ");
    					System.out.print(_struct[i][j]);	
    				}
    				System.out.println("");

    			}
    			
    		}
    	}
    }
} // end of structure


