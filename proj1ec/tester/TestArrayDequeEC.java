package tester;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;
import student.StudentArrayDeque;

public class TestArrayDequeEC {

    @Test
    /** Check if StudentArrayDeque is correct.
     *  By comparing it with ArrayDequeSolution.
     */
    public void correctnessTest() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        int N = 5000;
        String message = "";
        for (int i = 0; i < N; i++) {
            int randomInteger = StdRandom.uniform(0,4);
            if (randomInteger == 0) {
                //  addFirst
                Integer randVal = StdRandom.uniform(0, 100);
                sad.addFirst(randVal);
                ads.addFirst(randVal);
                message += "addFirst(" + randVal.toString() + ")\n";
            } else if (randomInteger == 1) {
                //  addLast
                Integer randVal = StdRandom.uniform(0, 100);
                sad.addLast(randVal);
                ads.addLast(randVal);
                message += "addLast(" + randVal.toString() + ")\n";
            }  else if (randomInteger == 2) {
                //  removeFirst
                if (ads.size() == 0) {
                    continue;
                }
                Integer expected = ads.removeFirst();
                Integer actual = sad.removeFirst();
                message += "removeFirst()\n";
                assertEquals(message,expected, actual);
            } else if (randomInteger == 3) {
                //  removeLast
                if (ads.size() == 0) {
                    continue;
                }
                Integer expected = ads.removeLast();
                Integer actual = sad.removeLast();
                message += "removeLast()\n";
                assertEquals(message, expected, actual);
            }
        }
    }
}
