package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SmallestSubarrayCoveringSet {

  // Represent subarray by starting and ending indices, inclusive.
  private static class Subarray {
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph,
      Set<String> keywords) {
    LinkedHashMap<String, Integer> dict = new LinkedHashMap<String, Integer>(keywords.size(), 1, true);
    for (String keyword : keywords) {
      dict.put(keyword, null);
    }
    int numKeywordsSeenSoFar = 0;
    Subarray res = new Subarray(-1, -1);
    for (int i = 0; i < paragraph.size(); i++) {
      String currString = paragraph.get(i);
      if (dict.containsKey(currString)) {
        if (dict.get(currString) == null) {
          numKeywordsSeenSoFar++;
        }
        dict.put(currString, i);
        if (numKeywordsSeenSoFar == keywords.size()) {
          if ((res.start == -1 && res.end == -1) || (i - getFirstOccurrence(dict) < res.end - res.start)) {
            res.start = getFirstOccurrence(dict);
            res.end = i;
          }
        }
      }
    }

    return res;
    // Map<String, Long> keywordsToCover = keywords.stream()
    // .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    // Subarray res = new Subarray(-1, -1);
    // int remainingToCover = keywords.size();
    // for (int left = 0, right = 0; right < paragraph.size(); right++) {
    // String currStr = paragraph.get(right);
    // if (keywordsToCover.containsKey(currStr) && keywordsToCover.put(currStr,
    // keywordsToCover.get(currStr) - 1) == 1) {
    // remainingToCover--;
    // }

    // while (remainingToCover == 0) {
    // if ((res.start == -1 && res.end == -1) || (right - left < res.end -
    // res.start)) {
    // res.start = left;
    // res.end = right;
    // }

    // String currLeftStr = paragraph.get(left);
    // if (keywordsToCover.containsKey(currLeftStr)
    // && keywordsToCover.put(currLeftStr, keywordsToCover.get(currLeftStr) + 1) ==
    // 0) {
    // remainingToCover++;
    // }
    // left++;
    // }
    // }

    // return res;
  }

  private static Integer getFirstOccurrence(LinkedHashMap<String, Integer> dict) {
    Integer idx = null;
    for (Map.Entry<String, Integer> entry : dict.entrySet()) {
      idx = entry.getValue();
      break;
    }
    return idx;
  }

  @EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
  public static int findSmallestSubarrayCoveringSetWrapper(
      TimedExecutor executor, List<String> paragraph, Set<String> keywords)
      throws Exception {
    Set<String> copy = new HashSet<>(keywords);

    Subarray result = executor.run(
        () -> findSmallestSubarrayCoveringSet(paragraph, keywords));

    if (result.start < 0 || result.start >= paragraph.size() ||
        result.end < 0 || result.end >= paragraph.size() ||
        result.start > result.end)
      throw new TestFailure("Index out of range");

    for (int i = result.start; i <= result.end; i++) {
      copy.remove(paragraph.get(i));
    }

    if (!copy.isEmpty()) {
      throw new TestFailure("Not all keywords are in the range");
    }
    return result.end - result.start + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SmallestSubarrayCoveringSet.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
