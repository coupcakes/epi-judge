package epi;

import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RomanToInteger {
  @EpiTest(testDataFile = "roman_to_integer.tsv")

  public static int romanToInteger(String s) {
    Map<Character, Integer> mapper = Map.of(
        'I', 1,
        'V', 5,
        'X', 10,
        'L', 50,
        'C', 100,
        'D', 500,
        'M', 1000);
    int ret = mapper.get(s.charAt(s.length() - 1));
    for (int i = s.length() - 2; i >= 0; i--) {
      if (mapper.get(s.charAt(i)) < mapper.get(s.charAt(i + 1))) {
        ret -= mapper.get(s.charAt(i));
      } else {
        ret += mapper.get(s.charAt(i));
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RomanToInteger.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
