// https://introcs.cs.princeton.edu/java/97data/FFT.java.html
import java.util.Arrays;
/******************************************************************************
 *  Compilation:  javac FFT.java
 *  Execution:    java FFT n
 *  Dependencies: Complex.java
 *
 *  Compute the FFT and inverse FFT of a length n complex sequence
 *  using the radix 2 Cooley-Tukey algorithm.

 *  Bare bones implementation that runs in O(n log n) time. Our goal
 *  is to optimize the clarity of the code, rather than performance.
 *
 *  Limitations
 *  -----------
 *   -  assumes n is a power of 2
 *
 *   -  not the most memory efficient algorithm (because it uses
 *      an object type for representing complex numbers and because
 *      it re-allocates memory for the subarray, instead of doing
 *      in-place or reusing a single temporary array)
 *  
 *  For an in-place radix 2 Cooley-Tukey FFT, see
 *  https://introcs.cs.princeton.edu/java/97data/InplaceFFT.java.html
 *
 ******************************************************************************/

import java.util.Arrays;
public class fft 
{

    private static int newLevel(int oldLevel)
    {
        if (oldLevel > 0)
            return oldLevel + 1;
        else
            return oldLevel - 1;
    }
    
    
    static final Complex plusI = new Complex(0, 1);
    static final Complex minusI = new Complex(0, -1);
    // compute the FFT of x[], assuming its length is a power of 2
    public static Complex[] fft(Complex[] x, int level) 
    {
        int n = x.length;

        // base case
        switch(level)
        {
            case 1 :
            System.out.println("Calling FFT(" +
                Arrays.toString(x) + ",W^1 = i)");
            break;
            case -1 :
            System.out.println("Calling FFT(" +
                Arrays.toString(x) + ",W^-1 = -i)");
            break;
            case 2 :
            System.out.println("\tCalling FFT(" +
                Arrays.toString(x) + ",W^2 = -1)");
            break;
            case -2 :
            System.out.println("\tCalling FFT(" +
                Arrays.toString(x) + ",W^-2 = -1)");
            break;
            case 3 :
            System.out.println("\t\tCalling FFT( " +
                Arrays.toString(x) + ",W^4=1), returning (" + x[0] + ")");
            return new Complex[] { x[0] };
            case -3 :
            System.out.println("\t\tCalling FFT( " +
                Arrays.toString(x) + ",W^-4=1), returning (" + x[0] + ")");
            return new Complex[] { x[0] };
        }

        // fft of even terms
        Complex[] even = new Complex[n/2];
        for (int k = 0; k < n/2; k++) {
            even[k] = x[2*k];
        }
        Complex[] q = fft(even, newLevel(level));

        // fft of odd terms
        Complex[] odd  = new Complex[n/2];;  // reuse the array
        for (int k = 0; k < n/2; k++) {
            odd[k] = x[2*k + 1];
        }
        Complex[] r = fft(odd, newLevel(level));

        // combine
        Complex[] y = new Complex[n];
        // for (int k = 0; k < n/2; k++) {
            // double kth = -2 * k * Math.PI / n;
            // Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            // y[k]       = q[k].plus(wk.times(r[k]));
            // y[k + n/2] = q[k].minus(wk.times(r[k]));
        // }
        
        switch (level)
        {
            case 1 :
            y[0] = q[0].plus(r[0]);
            y[1] = q[1].plus(r[1].times(plusI));
            y[2] = q[0].minus(r[0]);
            y[3] = q[1].plus(r[1].times(minusI));
            System.out.println("\tCalculate \n\t\t" +
                "f(1) = " + q[0] + " + W^0 * (" + r[0] + ") = " + q[0].plus(r[0]) + "\n\t\t" +
                "f(i) = " + q[1] + " + W^1 * (" + r[1] + ") = " + q[1].plus(r[1].times(plusI)) + "\n\t\t" +
                "f(-1) = "+ q[0] + " + W^2 * (" + r[0] + ") = " + q[0].minus(r[0]) + "\n\t\t" + 
                "f(-i) = " + q[1] + " + W^3 * (" + r[1] + ") = " + q[1].plus(r[1].times(minusI)));
            // for (int i = 0; i < n; i++)
                // y[i] = y[i].conjugate();
            System.out.println("\tReturn " + Arrays.toString(y));
            break;
            
            case -1 :
            y[0] = q[0].plus(r[0]);
            y[1] = q[1].plus(r[1].times(minusI));
            y[2] = q[0].minus(r[0]);
            y[3] = q[1].minus(r[1].times(minusI));
            System.out.println("\tCalculate \n\t\t" +
                "f(1) = " + q[0] + " + W^0 * (" + r[0] + ") = " + q[0].plus(r[0]) + "\n\t\t" +
                "f(i) = " + q[1] + " + W^-1 * (" + r[1] + ") = " + q[1].plus(r[1].times(minusI)) + "\n\t\t" +
                "f(-1) = "+ q[0] + " + W^-2 * (" + r[0] + ") = " + q[0].minus(r[0]) + "\n\t\t" + 
                "f(-i) = " + q[1] + " + W^-3 * (" + r[1] + ") = " + q[1].minus(r[1].times(minusI)));
            System.out.println("\tReturn " + Arrays.toString(y));
            break;

            case 2 :
            y[0] = q[0].plus(r[0]);
            y[1] = q[0].minus(r[0]);
            System.out.println("\t\tReturn (" +
                q[0] + " + 1 * (" + r[0] + ")," +
                q[0] + " + W^2 * (" + r[0] + ") = " + Arrays.toString(y));
            break;
            case -2 :
            y[0] = q[0].plus(r[0]);
            y[1] = q[0].minus(r[0]);
            System.out.println("\t\tReturn (" +
                q[0] + " + 1 * (" + r[0] + ")," +
                q[0] + " + W^-2 * (" + r[0] + ") = " + Arrays.toString(y));
            break;
        }
        return y;
    }

    // display an array of Complex numbers to standard output
    public static void main(String[] args) { 
        System.out.println("==========================================================");
        int n = 4; // Integer.parseInt(args[0]);
        Complex[] x1 = new Complex[n];

//         for (int i = 0; i < n; i++)
//             x1[i] = new Complex(-5 + (int)(Math.random() * 11), 0);
//         x1[0] = new Complex(-1, 0);
//         x1[1] = new Complex(2, 0);
//         x1[2] = new Complex(5, 0);
//         x1[3] = new Complex(-4, 0);
        x1[0] = new Complex(-1, 0);
        x1[1] = new Complex(2, 0);
        x1[2] = new Complex(0, 0);
        x1[3] = new Complex(0, 0);
//         x1[0] = new Complex(4, 0);
//         x1[1] = new Complex(3, 0);
//         x1[2] = new Complex(0, 0);
//         x1[3] = new Complex(0, 0);

        // FFT of original data
        Complex[] y1 = fft(x1, 1);
        System.out.println("=== FFT1 RESULT : " + Arrays.toString(y1) + "\n");

        Complex[] x2 = new Complex[n];
//         for (int i = 0; i < n; i++)
//             x2[i] = new Complex(-5 + (int)(Math.random() * 11), 0);
//         x2[0] = new Complex(1, 0);
        x2[0] = new Complex(1, 0);
        x2[1] = new Complex(3, 0);
        x2[2] = new Complex(0, 0);
        x2[3] = new Complex(0, 0);

        // FFT of original data
        Complex[] y2 = fft(x2, 1);
        System.out.println("=== FFT2 RESULT : " + Arrays.toString(y2) + "\n");

        // Build multiplication result
        Complex[] y = new Complex[n];
        for (int i = 0; i < n; i++)
            y[i] = y1[i].times(y2[i]);    
        System.out.println("=== MULTIPLICATION RESULT : " + Arrays.toString(y));
        
        // take inverse FFT
        Complex[] z = fft(y, -1);
        System.out.println("=== INVFFT RESULT : " + Arrays.toString(z));
    }
}
