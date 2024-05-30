package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Anagrams {
  @EpiTest(testDataFile = "anagrams.tsv")

  public static List<List<String>> findAnagrams(List<String> dictionary) {
    Map<String, List<String>> map = new HashMap<String, List<String>>();
    for (String s : dictionary) {
      String sortedStr = Stream.of(s.split("")).sorted().collect(Collectors.joining());
      map.putIfAbsent(sortedStr, new ArrayList<String>());
      map.get(sortedStr).add(s);
    }
    return map.values().stream().filter(group -> group.size() > 1).collect(Collectors.toList());
  }

  @EpiTestComparator
  public static boolean comp(List<List<String>> expected,
      List<List<String>> result) {
    if (result == null) {
      return false;
    }
    for (List<String> l : expected) {
      Collections.sort(l);
    }
    expected.sort(new LexicographicalListComparator<>());
    for (List<String> l : result) {
      Collections.sort(l);
    }
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Anagrams.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
