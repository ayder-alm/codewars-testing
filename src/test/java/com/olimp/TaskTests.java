package com.olimp;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class TaskTests {
    @Test
    public void testRandom() {
        assertEquals(Psychic.guess(), java.lang.Math.random(), 0);
    }
}
