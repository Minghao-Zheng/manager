package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;


/** Performs some basic Array list tests with some added functions. */
public class MaxArrayDequeTest {
    /** Create a private class which implements Comparator<Integer>.    */
    private static class MyIntegerComparator implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }
    /** Create a method to access MyIntegerComparator.  */
    public static Comparator<Integer> getMyIntegerComparator() {
        return new MyIntegerComparator();
    }


    @Test
    /** Test the Comparator<Integer> is correct.    */
    public void integerTest() {
        Comparator<Integer> ic = MaxArrayDequeTest.getMyIntegerComparator();
        MaxArrayDeque<Integer> mad1 = new MaxArrayDeque<>(ic);
        for (int i = 0; i < 16; i++) {
            mad1.addLast(i % 8);
        }
        int max = mad1.max();
        assertEquals(max, 7);
    }

    /** Create a static class with its own Comparable and Comparator.   */
    public static class Dog implements Comparable<Dog> {
        private int size;
        public Dog(int s) {
            size = s;
        }
        public int dogSize() {
            return size;
        }
        public int compareTo(Dog o) {
            return this.size - o.size;
        }
        private static class SizeComparator implements Comparator<Dog> {
            public int compare(Dog o1, Dog o2) {
                return o1.compareTo(o2);
            }
        }
        public static Comparator<Dog> getSizeComparator() {
            return new SizeComparator();
        }
    }

    @Test
    /** Check if max method works fine for dogs.    */
    public void dogTest() {
        Comparator<Dog> cd = Dog.getSizeComparator();
        MaxArrayDeque<Dog> mad1 = new MaxArrayDeque<>(cd);
        for (int i = 0; i < 8; i++) {
            Dog d = new Dog(i);
            mad1.addLast(d);
        }
        Dog maxDog = mad1.max(cd);
        assertEquals(maxDog.dogSize(), 7);
    }
}
