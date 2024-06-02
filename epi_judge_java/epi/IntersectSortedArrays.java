package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.List;
public class IntersectSortedArrays {
  @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
    List<Integer> res = new ArrayList<>();
    int aPtr = 0, bPtr = 0;
    while (aPtr < A.size() && bPtr < B.size()) {
      if (A.get(aPtr).equals(B.get(bPtr)) && (aPtr == 0 || !A.get(aPtr - 1).equals(A.get(aPtr)))) {
        res.add(A.get(aPtr));
        aPtr++;
        bPtr++;
      } else if (A.get(aPtr) < B.get(bPtr)) {
        aPtr++;
      } else {
        bPtr++;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntersectSortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
