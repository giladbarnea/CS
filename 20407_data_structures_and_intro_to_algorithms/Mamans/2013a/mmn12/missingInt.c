#include <stdio.h>
#include <math.h>
#include <stdlib.h>

#define getIthBit(A, bit) ( ( A & ( 0x1 << bit ) ) >> bit )

// len is the number of elements in A, which is one less than n
int missingInt(unsigned int A[], int len ) {
    // The number of entries in B is the maximum number of values which can be
    // represented by ceil(log2( n+1 )) bits
    int bitCount = ceil( log2( len + 1 ) );
    int Bsize = pow( 2, bitCount );
    unsigned int *B = malloc(sizeof(*A) * Bsize );


    // we use B to store both indexes and missing values
    // on the first iteration of the outer loop, B will simply contain the
    // indexes of each element in A and B in order.  On following loops, B's
    // first (Bsize-1)/2 elements will contain the indexes of the remaining
    // elements we want to examine, but any missing values between n and 
    // 2^bitCount will still be availbe in the second half of B.
    for (int i = 0; i != Bsize; ++i)
        B[i] = i;

    unsigned int missingInt = 0;
    for (int i = 0; i != bitCount; ++i) {
        printf("bitCount, Bsize == %d, %d\n", bitCount, Bsize);
        unsigned int oneCount = 0;
        // count the number of one's
        for (int j = 0; j != Bsize; ++j) {
            printf("B[%d] == %d\t", j, B[j]);
            if ( B[j] < len ) {
                // look in A 
                oneCount += getIthBit(A[ B[j] ], i);
            } else if (B[j] > len) {
                // look in B 
                oneCount += getIthBit(B[ B[j] ], i);
            }
        }
        printf("\noneCount == %d\n", oneCount);
        unsigned int evenOrOdd = ( oneCount & 0x1 );
        
        // now record the indexes of the remaining values we want to look at
        for (int j = 0, idx = 0; j != Bsize; ++j) {
            if ( B[j] < len ) {
                // look in A 
                if ( getIthBit(A[ B[j] ], i) == evenOrOdd ) 
                    B[idx++] = B[j];
            } else if (B[j] > len) {
                // look in B 
                if ( getIthBit(B[ B[j] ], i) == evenOrOdd ) 
                    B[idx++] = B[j];
            }
        }
        // adjust Bsize = (Bsize-1)/2
        Bsize = ( Bsize - 1) >> 1;
        // If we're missing a one, add it in
        if ( oneCount != ( Bsize + 1) )
            missingInt |= ( 0x1 << i );
    }
    return missingInt;
}

int main(void) {
    //unsigned int A[] = { 0, 1, 2, 3, 5 };
    //unsigned int A[] = { 3, 5, 2, 1, 0, 4, 6};
    unsigned int A[] = { 3, 5, 2, 1, 0, 4, 6, 7, 9, 8, 11};

    printf("sizeof(A) == %d\n", sizeof(A)/sizeof(A[0]));
    int mi = missingInt(A, sizeof(A)/sizeof(A[0]));

    printf("missing int is %d\n", mi);

    return 0;
}
