package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class TwoSortedArraysMerge {

  public static void mergeTwoSortedArrays(List<Integer> A, int m,
                                          List<Integer> B, int n) {
    int aPtr = m - 1, bPtr = n - 1, writeIdx = n + m - 1;
    while (aPtr >= 0 && bPtr >= 0) {
      A.set(writeIdx--, A.get(aPtr) > B.get(bPtr) ? A.get(aPtr--) : B.get(bPtr--));
    }

    while (bPtr >= 0) {
      A.set(writeIdx--, B.get(bPtr--));
    }
  }
  @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
  public static List<Integer>
  mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
    mergeTwoSortedArrays(A, m, B, n);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TwoSortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
