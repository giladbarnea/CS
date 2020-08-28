#include <stdio.h>

int findpivot(int A[], int first, int last) {
    return last;
}

#define swap(A, B) do { int temp = A;  A = B; B = temp; } while (0)

// Lomuto Partition
int partition(int A[], int first, int last) {
    int pivotIndex = findpivot(A, first, last);

    int pivot = A[pivotIndex];
    int i = first - 1;

    for (int j = first; j != last; j++)
        if ( A[j] <= pivot ) {
            i++;
            swap( A[i], A[j] );
        }

    swap( A[ i + 1 ], A[ last ] );
    return i + 1;
}

#define printArray(A, first, last) \
    do { \
        if ( first <= last ) \
            for (int i = first; i != last + 1; i++) \
                printf("%d ", A[i]); \
        printf("\n"); \
    } while (0) 


void quicksort(int A[], int first, int last) {
    printf("p = %d\tr = %d\n", first+1, last+1);

    if ( first < last ) {
        printArray(A, first, last);
        printf("Call Partition. ");
        int pivot = partition(A, first, last);
        printf("Done Partitioning: the pivot is %d\n", A[pivot]);
        printArray(A, first, last);

        printf("Recurse on ");
        quicksort(A, first, pivot - 1);

        printf("Recurse on ");
        quicksort(A, pivot+1, last);
        printf("The range p = %d\tr = %d is sorted\n", first+1, last+1);
        printArray(A, first, last);
    } else printf("Nothing to do\n");
}

// i is alway relative to first
int select(int A[], int first, int last, int i) {
    printf("p = %d\tr = %d\ti = %d\n", first+1, last+1, i);
    printArray(A, first, last);

    if ( first == last )
        return A[first];

    printf("Call Partition\n");
    int pivotIndex = partition(A, first, last);
    printf("Done Partitioning: the pivot is %d\n", A[pivotIndex]);
    printArray(A, first, last);

    int relativePivotIndex = pivotIndex - first + 1;
    printf("k == %d\n", relativePivotIndex);

    if ( relativePivotIndex == i )
        return A[pivotIndex];  // The pivot is the answer
    else if ( i < relativePivotIndex ) {
        printf("Recurse on ");
        return select(A, first, pivotIndex - 1, i );
    } else {
        printf("Recurse on ");
        return select(A, pivotIndex + 1, last, i - relativePivotIndex );
    }
}

int main(void) {
    int A[] = { 55, 3, 8, 4, 7, 1, 10, 9, 2, 10, 10, 10, 6, 11 };

#if 1
    quicksort(A, 0, sizeof(A)/sizeof(A[0]) - 1);
#else
    // the 10th place is locate in position 9 since the array's are 0 based
    int i = select(A, 0, sizeof(A)/sizeof(A[0]) - 1, 10);
    printf("The 10th order statistic is %d\n", i);
#endif


    return 0;
}
