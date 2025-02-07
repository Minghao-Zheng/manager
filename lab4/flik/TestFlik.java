package flik;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;

public class TestFlik {
    /** First test with number 10.  */

    @Test
    public void Testsmallpos() {
        boolean sameornot = Flik.isSameNumber(10, 10);
        Assert.assertTrue(sameornot);
    }

    /** Second test with number 10086 and 10085.  */

    @Test
    public void Testlargepos() {
        boolean service = Flik.isSameNumber(10086, 10085);
        Assert.assertFalse(service);
    }

    /** Test with 1314520.  */
    @Test
    public void Testlargepos2() {
        boolean l = Flik.isSameNumber(1314520, 1314520);
        Assert.assertTrue(l);
    }

    /** Test with 0.    */
    @Test
    public void Testzero() {
        boolean o = Flik.isSameNumber(0, 0);
        Assert.assertTrue(o);
    }

    /** Test with negative numbers. */
    @Test
    public void Testneg() {
        boolean n = Flik.isSameNumber(-543, -543);
        Assert.assertTrue("Last:", n);
    }
}
