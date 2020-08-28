// Author Sagi Rubin

// This class represent d-heap implementation in java

public class DHeap {
    // Array represent heap
    private int[] Heap;
    // Size of heap array - max elements in hap
    private int maxSize;
    // Current heap elements - current heap size
    private int size;
    // Num of max possible childrens of heap node
    private int d;

    // Constructor
    public DHeap(int maxSize, int d)
    {
        this.Heap = new int[maxSize];
        this.maxSize = maxSize;
        this.size = 0;
        this.d = d;
    }

    public int Height()
    {
        //return (int)(Math.log(size) / Math.log(d));
        return (int)Math.ceil((Math.log(size * (d - 1) + 1) / Math.log(d)) - 1);
    }

    /*  This function calculates the parent node index of heap node in the heap array

        @param currNodeIndex - index of current node in heap array
        @return index of parent node in heap array
    */
    public int ParentIndex(int currNodeIndex)
    {
        return ((currNodeIndex-1)/d);
    }

    /*  This function calculates the child node index of heap node in the heap array

        @param currNodeIndex - index of current node in heap array
        @return index of child node in heap array
    */
    public int ChildIndex(int currNodeIndex, int requestedChild)
    {
        return ((d * currNodeIndex) + requestedChild + 1);
    }

    /*  This function get index representing from where need to start arranging the max heap

        @param indexToHeapifyFrom - initial index to start heapifing from
        @return nothing
    */
    private void maxHeapify(int indexToHeapifyFrom)
    {
        int maxElementIndex = indexToHeapifyFrom;

        // Find index of max element of current node childrens
        for (int childNumber = 0; childNumber < this.d; childNumber++)
        {
            int childIndexInHeapArray = ChildIndex(indexToHeapifyFrom, childNumber);

            // Update value of max element index to current child index if he's larger than value in maxElementIndex
            if (childIndexInHeapArray < size && Heap[childIndexInHeapArray] > Heap[maxElementIndex])
            {
                maxElementIndex = childIndexInHeapArray;
            }
        }

        // Swap if max element is in different index
        if (maxElementIndex != indexToHeapifyFrom)
        {
            Swap(maxElementIndex, indexToHeapifyFrom);

            maxHeapify(maxElementIndex);
        }
    }

    /*  This function get max element from heap - remove him and keep heap maintenance with heapify

        @return max element in haep
    */
    public int ExtractMax()
    {
        int maxElement;

        maxElement = Heap[0];

        // Set last element at heap in first index
        Heap[0] = Heap[size - 1];

        size--;

        maxHeapify(0);

        return maxElement;
    }

    /*  This function update key of node in specific parameter index

        @param index - index of key need to update
        @param key   - key to update to
        @return nothing
    */
    public void increaseKey(int index, int key)
    {
        if (!indexInHeapArrayRange(index) || key < Heap[index])
        {
            return;
        }

        Heap[index] = key;

        // Find the new index of the increases key
        while (index > 0 && Heap[ParentIndex(index)] < key)
        {
            Swap(index, ParentIndex(index));

            index = ParentIndex(index);
        }
    }

    /*  This function insert new element to heap

        @param key   - element to be inserted
        @return nothing
    */
    public void insert(int key)
    {
        this.size++;
        Heap[size - 1] = Integer.MIN_VALUE;

        increaseKey(size - 1, key);
    }

    @Override
    public String toString()
    {
        StringBuilder sbTree = new StringBuilder();
        StringBuilder sbLevels = new StringBuilder();

        int indexOfFirstLevelElement = 0;
        int heapHeight = Height() + 1;

        for (int level = 1; level <= heapHeight; level++)
        {
            int maxElementsInLevel = (int)Math.pow(d, level - 1);

            int indent = (int)Math.pow(d, heapHeight - level) - 1;
            int spacing = (int)Math.pow(d, heapHeight - (level - 1)) - 1;

            sbLevels.append("Level " + level + ": ");

            sbTree.append(printWhitespaces(indent));

            int elementsPrintedFromCurrenLevel = 0;
            for (int elementIndexInLevel = indexOfFirstLevelElement;
                 elementIndexInLevel < size &&
                 elementsPrintedFromCurrenLevel < maxElementsInLevel;
                 elementIndexInLevel++)
            {
                sbTree.append(Heap[elementIndexInLevel]);
                sbLevels.append(Heap[elementIndexInLevel] + ", ");

                sbTree.append(printWhitespaces(spacing));

                elementsPrintedFromCurrenLevel++;
            }

            indexOfFirstLevelElement = ChildIndex(indexOfFirstLevelElement, 0);

            sbTree.append("\n");
            sbLevels.append("\n");
        }

        return sbTree.toString() + "\n\n" + sbLevels.toString();
    }

    private static String printWhitespaces(int count) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++)
            sb.append(" ");

        return sb.toString();
    }

    /*  This function swaps 2 elements in heap in specific indexes

        @param firstElementIndex  - index of first element need to be swapped
        @param secondElementIndex - index of second element need to be swapped
        @return nothing
    */
    private void Swap(int firstElementIndex, int secondElementIndex)
    {
        // Check if index out of range
        if (!indexInHeapArrayRange(firstElementIndex) || !indexInHeapArrayRange(secondElementIndex))
        {
            return;
        }

        int temp = Heap[firstElementIndex];
        Heap[firstElementIndex] = Heap[secondElementIndex];
        Heap[secondElementIndex] = temp;
    }

    /*  This function check if index in range of array reperesenting the heap

        @param indexToCheck  - index need to check
        @return if the index is in array valid range or out of range
    */
    private boolean indexInHeapArrayRange(int indexToCheck)
    {
        return (indexToCheck >= 0 && indexToCheck <= maxSize);
    }
}
