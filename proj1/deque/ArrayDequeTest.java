package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some array list tests. */
public class ArrayDequeTest {

    @Test
    /** Check if get(int index) is correct.
     *  With resize() works.
     */
    public void getTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            lld1.addLast(i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals("Should have the same value", (long) lld1.get(i), i);
        }
    }

    @Test
    /** Check if resize() works properly in a special case that:
     *  nextLast = 0
     */
    public void specialResizeTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        for (int i = 3; i >= 0; i--) {
            lld1.addFirst(i);
        }
        for (int i = 4; i <= 7; i++) {
            lld1.addLast(i);
        }
        lld1.addLast(8);
        assertEquals("Should have the same value", (long) lld1.get(8), 8);

    }

    @Test
    /** Check if equals(Object o) is correct.
     *  Surely, with resize().
     */
    public void equalsTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        ArrayDeque<Integer> lld2 = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++) {
            lld1.addLast(i);
        }
        for (int i = 0; i < 1000; i++) {
            lld2.addLast(i);
        }
        assertTrue("Two LinkedListDeque should be equal.", lld1.equals(lld2));
        lld2.removeFirst();
        assertFalse("Now the two lists shouldn't be equal", lld1.equals(lld2));
    }

    @Test
    /** Check if resize() is correct when it comes to decrease memory boxes.    */
    public void resizeDecreaseTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        for(int i = 0; i < 17; i++) {
            lld1.addFirst(i);
        }
        for(int i = 0; i < 5; i++) {
            lld1.removeFirst();
            lld1.removeLast();
        }
        assertEquals("Should have the same value", (long) lld1.get(0), 11);
        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    /** Check if size() and isEmpty() are correct.  */
    public void addIsEmptySizeTest() {

        ArrayDeque<String> lld1 = new ArrayDeque<>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

    }
    @Test
    /** Check if get(int index) is correct with strange index.  */
    public void strangeIndexTest() {
        ArrayDeque<Integer> ald1 = new ArrayDeque<>();
        ald1.addLast(1);
        ald1.addLast(2);
        ald1.addLast(3);
        for (int i = 8; i <= 10; i++) {
            assertNull(ald1.get(i));
        }
    }


    @Test
    /** Using randomized check to confirm that the lists are compatible with each other.    */
    public void randomizedTest() {
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        ArrayDeque<Integer> A = new ArrayDeque<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 7);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                A.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size1 = L.size();
                int size2 = A.size();
                assertEquals(size1, size2);

            } else if (operationNumber == 2) {
                // get
                int randVal = StdRandom.uniform(0, 100);
                assertEquals(L.get(randVal), A.get(randVal));
            } else if (operationNumber == 3) {
                // removeLast
                assertEquals(L.removeLast(), A.removeLast());
            } else if (operationNumber == 4) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                L.addFirst(randVal);
                A.addFirst(randVal);
            } else if (operationNumber == 5) {
                // removeFirst
                assertEquals(L.removeFirst(), A.removeFirst());
            } else if (operationNumber == 6) {
                assertEquals(L.isEmpty(), A.isEmpty());
            }
        }
    }
}
