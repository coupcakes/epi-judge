package epi;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsAnonymousLetterConstructible {
  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

  public static boolean isLetterConstructibleFromMagazine(String letterText,
      String magazineText) {
    Map<Character, Long> letterFreq = letterText.chars().mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    for (char c : magazineText.toCharArray()) {
      if (letterFreq.containsKey(c)) {
        letterFreq.put(c, letterFreq.get(c) - 1);
        if (letterFreq.remove(c, 0L)) {
          letterFreq.remove(c);
          if (letterFreq.isEmpty()) {
            break;
          }
        }
      }
    }
    return letterFreq.isEmpty();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
