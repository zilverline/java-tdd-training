package com.zilverline.tdd;
public class StringCalculator {

  public static int add(String input) {
    if (input.isEmpty()) {
      return 0;
    } else {
      return parseAndAddNumbers(separateNumbers(input));
    }
  }

  private static int parseAndAddNumbers(String[] separatedNumbers) {
    int sum = 0;
    for (String number: separatedNumbers) {
      sum += Integer.valueOf(number);
    }
    return sum;
  }

  private static String[] separateNumbers(String input) {
    return input.split(",");
  }

}
