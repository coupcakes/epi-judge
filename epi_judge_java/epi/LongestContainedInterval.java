package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class LongestContainedInterval {
  @EpiTest(testDataFile = "longest_contained_interval.tsv")

  public static int longestContainedRange(List<Integer> A) {
    Set<Integer> toProcess = new HashSet<>(A);
    int longestContainedRange = 0;
    while (!toProcess.isEmpty()) {
      Integer val = toProcess.iterator().next();
      toProcess.remove(val);

      int lowerBound = val - 1;
      while (toProcess.contains(lowerBound)) {
        toProcess.remove(lowerBound);
        lowerBound--;
      }

      int upperBound = val + 1;
      while (toProcess.contains(upperBound)) {
        toProcess.remove(upperBound);
        upperBound++;
      }

      longestContainedRange = Math.max(longestContainedRange, upperBound - lowerBound - 1);
    }
    return longestContainedRange;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestContainedInterval.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
