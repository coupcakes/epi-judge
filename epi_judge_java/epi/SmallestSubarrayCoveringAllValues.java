package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmallestSubarrayCoveringAllValues {

  public static class Subarray {
    // Represent subarray by starting and ending indices, inclusive.
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  public static Subarray findSmallestSequentiallyCoveringSubset(List<String> paragraph,
      List<String> keywords) {
    Map<String, Integer> keywordToIdx = new HashMap<String, Integer>();
    List<Integer> latestOccurrence = new ArrayList<Integer>();
    List<Integer> shortestSubarrayLen = new ArrayList<Integer>();

    // initialize
    for (int i = 0; i < keywords.size(); i++) {
      keywordToIdx.put(keywords.get(i), i);
      latestOccurrence.add(null);
      shortestSubarrayLen.add(Integer.MAX_VALUE);
    }

    int shortestLen = Integer.MAX_VALUE;
    Subarray ret = new Subarray(-1, -1);
    for (int i = 0; i < paragraph.size(); i++) {
      Integer idx = keywordToIdx.get(paragraph.get(i));
      if (idx != null) {
        if (idx == 0) {
          shortestSubarrayLen.set(0, 1);
        } else if (shortestSubarrayLen.get(idx - 1) != Integer.MAX_VALUE) {
          shortestSubarrayLen.set(idx, shortestSubarrayLen.get(idx - 1) + (i - latestOccurrence.get(idx - 1)));
        }
        latestOccurrence.set(idx, i);

        if (idx == keywords.size() - 1 && shortestSubarrayLen.get(idx) < shortestLen) {
          shortestLen = shortestSubarrayLen.get(idx);
          ret.start = i - shortestSubarrayLen.get(idx) + 1;
          ret.end = i;
        }
      }
    }
    return ret;
  }

  @EpiTest(testDataFile = "smallest_subarray_covering_all_values.tsv")
  public static int findSmallestSequentiallyCoveringSubsetWrapper(
      TimedExecutor executor, List<String> paragraph, List<String> keywords)
      throws Exception {
    Subarray result = executor.run(
        () -> findSmallestSequentiallyCoveringSubset(paragraph, keywords));

    int kwIdx = 0;
    if (result.start < 0) {
      throw new TestFailure("Subarray start index is negative");
    }
    int paraIdx = result.start;

    while (kwIdx < keywords.size()) {
      if (paraIdx >= paragraph.size()) {
        throw new TestFailure("Not all keywords are in the generated subarray");
      }
      if (paraIdx >= paragraph.size()) {
        throw new TestFailure("Subarray end index exceeds array size");
      }
      if (paragraph.get(paraIdx).equals(keywords.get(kwIdx))) {
        kwIdx++;
      }
      paraIdx++;
    }
    return result.end - result.start + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SmallestSubarrayCoveringAllValues.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
