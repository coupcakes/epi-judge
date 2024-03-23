package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ConvertBase {
  @EpiTest(testDataFile = "convert_base.tsv")

  public static String convertBase(String numAsString, int b1, int b2) {
    boolean isNegative = numAsString.charAt(0) == '-';
    // convert to base 10
    int numAsInt = 0;
    for (int i = (isNegative ? 1 : 0); i < numAsString.length(); i++) {
      numAsInt = (numAsInt * b1)
          + (Character.isDigit(numAsString.charAt(i)) ? numAsString.charAt(i) - '0' : numAsString.charAt(i) - 'A' + 10);
    }
    return (isNegative ? "-" : "") + (numAsInt == 0 ? "0" : convertToBase(numAsInt, b2));
  }

  private static String convertToBase(int num, int base) {
    if (num == 0) {
      return "";
    }
    int lsd = num % base;
    return convertToBase(num / base, base) + (char) (lsd >= 10 ? ('A' + (lsd - 10)) : '0' + lsd);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
