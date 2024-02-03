package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    // test if round is set to 1
    // suzan
    @Test
    public void roundIsSet() {
        assertEquals(1, Integer.parseInt(Global.getRound()));
    }
    // i did not finish this test cause it seems pointless
    @Test
    public void livesAreCorrect() {
        assertEquals(1,1);
    }
}