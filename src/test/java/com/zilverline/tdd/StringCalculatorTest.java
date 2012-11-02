package com.zilverline.tdd;
import static com.zilverline.tdd.StringCalculator.add;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringCalculatorTest {
   @Test
   public void empty_string_should_be_zero() {
     assertEquals(0, add(""));
   }
   
   @Test
   public void bare_numbers_should_return_itself() {
     assertEquals(1, add("1"));
     assertEquals(7, add("7"));
     assertEquals(42, add("42"));
   }

   @Test
   public void should_add_two_comma_separated_numbers() {
     assertEquals(5, add("2,3"));
     assertEquals(11, add("1,10"));
   }
   
   @Test(expected=NullPointerException.class)
   public void should_fail_on_null() {
     add(null);
   }
   
}