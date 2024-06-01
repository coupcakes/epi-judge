package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
public class StringDecompositionsIntoDictionaryWords {
  @EpiTest(testDataFile = "string_decompositions_into_dictionary_words.tsv")

  public static List<Integer> findAllSubstrings(String s, List<String> words) {
    Map<String, Long> wordFreq = words.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    int unitSize = words.get(0).length();
    List<Integer> res = new ArrayList<Integer>();

    for (int i = 0; i + (unitSize * words.size()) <= s.length(); i++) {
      if (hasAllWords(s, wordFreq, i, words.size(), unitSize)) {
        res.add(i);
      }
    }
    return res;
  }

  private static boolean hasAllWords(String sentence, Map<String, Long> wordFreq, int startIdx, int numWords, int unitSize) {
    Map<String, Integer> currStrFreq = new HashMap<String, Integer>();
    for (int i = 0; i < numWords; i++) {
      String currWord = sentence.substring(startIdx + (i * unitSize), startIdx + ((i + 1) * unitSize));
      if (wordFreq.get(currWord) == null) {
        return false;
      }
      currStrFreq.put(currWord, currStrFreq.getOrDefault(currWord, 0) + 1);
      if (currStrFreq.get(currWord) > wordFreq.get(currWord)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
                    .runFromAnnotations(
                        args, "StringDecompositionsIntoDictionaryWords.java",
                        new Object() {}.getClass().getEnclosingClass())
                    .ordinal());
  }
}
