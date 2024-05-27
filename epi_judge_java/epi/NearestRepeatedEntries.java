package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestRepeatedEntries {
  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")

  public static int findNearestRepetition(List<String> paragraph) {
    Map<String, Integer> strToLatestIdx = new HashMap<String, Integer>();
    int minDistance = Integer.MAX_VALUE;
    for (int i = 0; i < paragraph.size(); i++) {
      String currWord = paragraph.get(i);
      if (strToLatestIdx.containsKey(currWord)) {
        minDistance = Math.min(minDistance, i - strToLatestIdx.get(currWord));
      }
      strToLatestIdx.put(currWord, i);
    }
    return minDistance != Integer.MAX_VALUE ? minDistance : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NearestRepeatedEntries.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
