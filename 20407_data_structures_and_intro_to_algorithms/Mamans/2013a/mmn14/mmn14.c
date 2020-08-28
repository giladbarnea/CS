#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <sys/time.h>

#define printArray(A, first, last, reps) \
    do { \
        if ( first <= last ) \
            for (int i = first; i != last + 1; i++) \
                printf("%d ", A[i]/reps); \
        printf("\n"); \
    } while (0) 

#define swap(A, B) do { int temp = A;  A = B; B = temp; } while (0)

/* Varialbes for collecting key compare and swap counts */
int *heapCmps, *lomutoCmps, *hoareCmps;
int kIdx;

/* Randomized Select and Quicksort Implementations */
int randPivot( int first,  int last) {
    return first + (rand()%(last - first + 1));
}

// Lomuto LomutoPartition
int LomutoPartition(int A[], int first, int last) {
    int pivot = A[last];
    int i = first - 1;

    for (int j = first; j != last; j++) {
        lomutoCmps[kIdx]++;
        if ( A[j] <= pivot ) {
            i++;
            swap( A[i], A[j] );
        }
    }

    return i + 1;
}

int HoarePartition(int *A, int first, int last) {
    int pivot = A[last--];
    while (first <= last) { // Move bounds inward until they meet
        for (;A[first] < pivot; hoareCmps[kIdx]++ ) first++;
        for (;(last >= first) && (A[last] >= pivot); hoareCmps[kIdx]++) last--;
        if (last > first) swap(A[first], A[last]); // Swap out-of-place values
    }
    return first;            // Return first position in last partition
}

// Use a kind of strategy design pattern to test the different partition
// types.
typedef int (*partition_fp)(int *, int, int);
partition_fp partition;

void quicksort(int A[], int first, int last) {

    if ( first < last ) {
#ifdef RANDPIVOT 
        // randomize the pivot
        int randIdx = randPivot(first, last);
        swap(A[randIdx], A[last]);
#endif
        int pivot = partition(A, first, last);
        swap(A[pivot], A[last]);

        quicksort(A, first, pivot - 1);

        quicksort(A, pivot+1, last);
    }
}

// i is alway relative to first
int Select(int A[], int first, int last, int i) {

    if ( first == last )
        return A[first];

    // randomize the pivot
    int randIdx = randPivot(first, last);
    swap(A[randIdx], A[last]);

    int pivotIndex = partition(A, first, last);
    swap(A[pivotIndex], A[last]);

    int relativePivotIndex = pivotIndex - first + 1;

    if ( relativePivotIndex == i )
        return A[pivotIndex];  // The pivot is the answer
    else if ( i < relativePivotIndex ) {
        return Select(A, first, pivotIndex - 1, i );
    } else {
        return Select(A, pivotIndex + 1, last, i - relativePivotIndex );
    }
}
/* End Randomized Select and Quicksort Implementations */

/* Min-Heap Implementation */
#define Parent(i) (i/2)
// these have to be adjusted for 0 based arrays
#define Left(i) (2*(i+1)-1)
#define Right(i) (2*(i+1))

void minHeapify(int *A, int heapSize, int i) {
    int l = Left(i);
    int r = Right(i);
    int smallest;

    if ( l < heapSize && ++heapCmps[kIdx] && A[l] < A[i] )
        smallest = l;
    else
        smallest = i;

    if ( r < heapSize && ++heapCmps[kIdx] && A[r] < A[smallest] )
        smallest = r;

    if ( smallest != i ) {
        swap(A[i], A[smallest]);
        minHeapify(A, heapSize, smallest);
    }
}

void buildMinHeap(int *A, int heapLen) {
    for (int i = (heapLen-1) / 2; i != -1; i-- ) {
        minHeapify(A, heapLen, i);
    }
}

// returns a pointer to a newly allocated array containing the k smallest
// elements in sorted order
int *extractMink(int *A, int heapSize, int k) {
    buildMinHeap(A, heapSize);

    int *Bk = malloc(sizeof(int)*k);

    for (int i = 0; i != k; i++) {
        Bk[i] = A[0];
        A[0] = A[heapSize-1];
        minHeapify(A, --heapSize, 0);
    }
    return Bk;
}
/* End Min-Heap Implementation */

// This is part of a command line interface for volume testing
void runTests(  int nReps,  int kReps, int n ) {
    heapCmps = malloc(sizeof(int) * kReps);
    lomutoCmps = malloc(sizeof(int) * kReps);
    hoareCmps = malloc(sizeof(int) * kReps);
    int count = 0;

    for (int j = 0; j != kReps; j++)
        heapCmps[j] = lomutoCmps[j] = hoareCmps[j] = 0;

    for (  int i = 0; i != nReps; i++ ) {
        kIdx = 0;
        int *T, *A, *B, *Bk, *C;

        T = malloc(n * sizeof(int));
        A = malloc(n * sizeof(int));
        B = malloc(n * sizeof(int));
        C = malloc(n * sizeof(int));
        int j = n; 
        while ( j-- )
#ifdef UNIQUE
            T[j] = (int)rand();
#else
            T[j] = (int)rand()%1000;
#endif

#if 0
        // count inversions
        for ( int x = 0; x != n; x++ )
            for ( int y = x+1; y != n; y++ )
                if ( T[x] > T[y] )
                    count++;
        printf("Inversion Count == %d\n", count);
#endif
        for (  int k = 4; k!=(n<<2) && kIdx != kReps; kIdx++, k<<=2 ) {
            j = n;
            while( j-- )
                A[j] = B[j] = C[j] = T[j];

            partition = LomutoPartition;
            Select(A, 0, n-1, k);
            quicksort(A, 0, k-1);

            partition = HoarePartition;
            Select(C, 0, n-1, k);
            quicksort(C, 0, k-1);

            Bk = extractMink(B, n, k);

#if 0
            // make sure all the algorithms are giving the same output.
            if ( memcmp(A, Bk, sizeof(int)*k) != 0 || memcmp(Bk, C, sizeof(int)*k) != 0 ) {
                printArray(A, 0, k-1,1);
                printArray(Bk, 0, k-1,1);
                printArray(C, 0, k-1,1);
            }
#endif
        }
#if 1        
        // count distinct values - assumes you've sorted the whole array
        count++;
        for ( j = 1; j != n; j++ )
            if ( A[j-1] != A[j] )
                count++;
#endif

        free(A);
        free(B);
        free(Bk);
    }
    printf("n = %d\nk =", n);
    for ( int k = 4, j=0; j!=kReps && k!=(n<<2); j++, k<<=2 )
        printf("\t%d", k);
    printf("\nHeap\t");
    printArray(heapCmps, 0, kReps-1, nReps);
    printf("Lomuto\t");
    printArray(lomutoCmps, 0, kReps-1, nReps);
    printf("Hoare\t");
    printArray(hoareCmps, 0, kReps-1, nReps);
    printf("\n");

    printf("Avg. Distinct values == %d\n", count/nReps);

    free(heapCmps);
    free(lomutoCmps);
    free(hoareCmps);
}

int main(int argc, char **argv) {
     int n, k, nReps, kReps;
    char choice;

    struct timeval t1;
    gettimeofday(&t1, NULL);
    // 10 millionth prime number
    srand( t1.tv_usec * t1.tv_sec * 179424673 );

    if ( argc == 4 ) {
        runTests(atoi(argv[1]), atoi(argv[2]), atoi(argv[3]));
        return 0;
    } else {
        // we're not using these so just use them to store the stats
        heapCmps = &nReps;
        lomutoCmps = &kReps;
    }

    while(1) {
        printf("Please enter n and k (space between): ");

        if ( scanf("%d %d", &n, &k) != 2 ) {
            perror("scanf: ");
            return 1;
        }
        printf("\nPlease choose\na - for random numbers betrween 0 and 999, b - to give your own numbers: ");

        if ( scanf(" %c", &choice) != 1 ) {
            perror("scanf: ");
            return 1;
        }
        int *A = malloc(n * sizeof(int));
        int *B = malloc(n * sizeof(int));
        int *Bk;
        int i;

        heapCmps[0] = lomutoCmps[0] = 0;

        if ( choice == 'a' ) {
            i = n;
            while ( i-- )
#ifdef UNIQUE
                A[i] = B[i] = ( int)rand();
#else
                A[i] = B[i] = ( int)rand()%1000;
#endif

        } else {
            i = 0;
            while ( i != n ) {
                scanf("%d", &A[i]);
                B[i] = A[i];
                i++;
            }
        }
        kIdx=0;
        partition = LomutoPartition;
        Select(A, 0, n-1, k);
        quicksort(A, 0, k-1);

        printArray(A, 0, k-1, 1);

        Bk = extractMink(B, n, k);

        if ( memcmp(A, Bk, sizeof(int)*k) != 0 )
            printArray(Bk, 0, k-1, 1);

        printf("Heap Key Compares\t%d\n", heapCmps[0]); 
        printf("Lomuto Key Compares\t%d\n", lomutoCmps[0]); 

        free(A);
        free(B);
        free(Bk);
        printf("Press cntl-c to exit\n");
    }

    return 0;
}
