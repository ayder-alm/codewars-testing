package com.olimp;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class TaskTests {
        @Test
        public void mainTest() {
            int a = 2 + 2;
            int b = 4;
            assertEquals(true, a == b);
        }
}
