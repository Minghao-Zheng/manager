/** Class that prints the Collatz sequence starting from a given number.
 *  Minghao Zheng's work
 */
public class Collatz {

    /** if n is an even number, the next number is n/2.
     *  else, the next number is 3*n+1. */
    public static int nextNumber(int n) {
        if (n % 2 == 0) {
            return n / 2;
        } else {
            return 3 * n + 1;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");
        while (n != 1) {
            n = nextNumber(n);
            System.out.print(n + " ");
        }
        System.out.println();
    }
}

