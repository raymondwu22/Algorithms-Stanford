/*
 * The Karatsuba algorithm is a multiplication algorithm developed by Anatolii Alexeevitch Karatsuba in 1960.
 * It operates in O(n^log2(3)) time (~ O(n^1.585)), with n being the number of digits of the numbers we are multiplying
 * together.
 * Standard grade-school multiplication operates in O(n^2) time. Karatsuba's method is asymptotically much faster.
 * Normally, you can choose any base you want, but we will be using base 10 in this algorithm with m varying depending
 * on the length of the inputs.
 * Specific details are included with an example in the comments before the actual method.
 */

public class Karatsuba {
    public static long karatsuba(long first, long second) {
        int n = Math.max(String.valueOf(first).length(), String.valueOf(second).length());
        // base case with single digit multiplication
        if (n < 2) return first * second;
        if (n % 2 == 1) n++;
        // compute a, b, c, d for recursive case
        long a = (long) (first / Math.pow(10, n/2));
        long b = (long) (first %  Math.pow(10, n/2));
        long c = (long) (second / Math.pow(10, n/2));
        long d = (long) (second %  Math.pow(10, n/2));
        // recursively compute AC, BD, ABCD with Gauss's trick:
        long ac = karatsuba(a, c);
        long bd = karatsuba(b, d);
        long abcd = karatsuba(a + b, c + d);

        return (long) ((Math.pow(10, n) * ac) + ((Math.pow(10, n/2)) * (abcd - ac - bd)) + bd);
    }

    public static void main(String[] args) {
        System.out.println(karatsuba(5678, 1234));
    }
}