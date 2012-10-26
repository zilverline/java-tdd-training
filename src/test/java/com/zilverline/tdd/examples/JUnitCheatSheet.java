package com.zilverline.tdd.examples;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class JUnitCheatSheet {

    // A new instance of a test object is created every time a test is run, so all instance variables are fresh!
    private String myTestString;

    // Methods annotated with @Before are run before _every_ test. Used to initialize instance variables etc.
    @Before
    public void setUp() {
        myTestString = "hello";
    }

    // Methods annotated with @After are run after _every_ test. Used to clean up. Usually not needed!
    @After
    public void tearDown() {
    }

    // Methods annotated with @BeforeClass are run before any test and only once per class. Method must be static!
    @BeforeClass
    public static void setUpClass() {
        // Useful for slow stuff. But avoid if you can!
    }

    // Methods annotated with @AfterClass are run after all tests and only once per class. Method must be static!
    @AfterClass
    public static void tearDownClass() {
    }

    // Methods annotated with @Test declare a test. Each test is run independently in a fresh instance of the test class.
    @Test
    public void my_test_string_should_say_hello() {
        // Assertions are static methods on the org.junit.Assert class.
        org.junit.Assert.assertEquals("string should be equal to hello", "hello", myTestString);

        // Usually imported for direct access.
        assertEquals("string should be equal to hello", // First parameter is a human readable explanation (optional)
                "hello",                                // The expected value
                myTestString);                          // The actual (computed) value

        // There are many other assertion methods
        assertTrue("true should really be true", true);
        assertFalse(false);
        assertNull(null);
        assertNotNull(myTestString);

        // assertEquals uses the .equals methods, which is "broken" in Java.
        assertArrayEquals(new int[] { 1, 2, 3 }, new int[] { 1, 2, 3});

        // Sometimes you need to test for reference equality (== vs equals)
        assertSame(myTestString, myTestString);
        assertNotSame(new Object(), new Object());

        // You can try to test that exceptions occur:
        try {
            aMethodThatMustThrowAnException();
            fail("RuntimeException expected");
        } catch (RuntimeException expected) {
        }
    }

    @Test(expected=RuntimeException.class)
    public void another_way_to_test_for_exceptions() {
        aMethodThatMustThrowAnException();
    }

    @Ignore
    @Test
    public void ignored_test_are_not_run() {
        assertTrue(false);
    }

    private void aMethodThatMustThrowAnException() {
        throw new RuntimeException("test");
    }
}
