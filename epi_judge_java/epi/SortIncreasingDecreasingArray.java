package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortIncreasingDecreasingArray {
  @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")

  public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {
    List<List<Integer>> sortedLists = new ArrayList<List<Integer>>();
    int startIdx = 0;
    SubarrayType subarrayType = SubarrayType.INCREASING;
    for (int i = 1; i <= A.size(); i++) {
      if (i == A.size() || (A.get(i - 1) >= A.get(i) && subarrayType == SubarrayType.INCREASING)
          || (A.get(i - 1) < A.get(i) && subarrayType == SubarrayType.DECREASING)) {
        List<Integer> subList = A.subList(startIdx, i);
        if (subarrayType == SubarrayType.DECREASING) {
          Collections.reverse(subList);
        }
        sortedLists.add(subList);
        startIdx = i;
        subarrayType = subarrayType == SubarrayType.INCREASING ? SubarrayType.DECREASING : SubarrayType.INCREASING;
      }
    }
    return SortedArraysMerge.mergeSortedArrays(sortedLists);
  }

  private static enum SubarrayType {
    INCREASING, DECREASING
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortIncreasingDecreasingArray.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
