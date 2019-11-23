package com.olimp;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class TaskTests {
    @Test
    public void testBagel() {
        Bagel bagel = BagelSolver.getBagel();
        assertEquals(bagel.getValue() == 4, java.lang.Boolean.TRUE);
    }
}
