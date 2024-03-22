package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
  @EpiTest(testDataFile = "pascal_triangle.tsv")

  public static List<List<Integer>> generatePascalTriangle(int numRows) {
    List<List<Integer>> ret = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
      List<Integer> currRow = new ArrayList<>();
      for (int j = 0; j <= i; j++) {
        currRow.add(j > 0 && j < i ? ret.get(i - 1).get(j) + ret.get(i - 1).get(j - 1) : 1);
      }
      ret.add(currRow);
    }
    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PascalTriangle.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
