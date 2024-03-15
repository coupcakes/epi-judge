package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomSubset {

  // Returns a random k-sized subset of {0, 1, ..., n - 1}.
  public static List<Integer> randomSubset(int n, int k) {
    Map<Integer, Integer> changedEls = new HashMap<Integer, Integer>();
    Random randGen = new Random();
    for (int i = 0; i < k; i++) {
      int randIdx = i + randGen.nextInt(n - i);
      Integer ptr1 = changedEls.get(i);
      Integer ptr2 = changedEls.get(randIdx);
      if (ptr1 == null && ptr2 == null) {
        changedEls.put(i, randIdx);
        changedEls.put(randIdx, i);
      } else if (ptr1 == null && ptr2 != null) {
        changedEls.put(randIdx, i);
        changedEls.put(i, ptr2);
      } else if (ptr1 != null && ptr2 == null) {
        changedEls.put(randIdx, ptr1);
        changedEls.put(i, randIdx);
      } else {
        changedEls.put(i, ptr2);
        changedEls.put(randIdx, ptr1);
      }
    }

    List<Integer> res = new ArrayList<Integer>(k);
    for (int i = 0; i < k; i++) {
      res.add(changedEls.get(i));
    }

    return res;
  }

  private static boolean randomSubsetRunner(TimedExecutor executor, int n,
      int k) throws Exception {
    List<List<Integer>> results = new ArrayList<>();

    executor.run(() -> {
      for (int i = 0; i < 1000000; ++i) {
        results.add(randomSubset(n, k));
      }
    });

    int totalPossibleOutcomes = RandomSequenceChecker.binomialCoefficient(n, k);
    List<Integer> A = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) {
      A.add(i);
    }
    List<List<Integer>> combinations = new ArrayList<>();
    for (int i = 0; i < RandomSequenceChecker.binomialCoefficient(n, k); ++i) {
      combinations.add(RandomSequenceChecker.computeCombinationIdx(A, n, k, i));
    }
    List<Integer> sequence = new ArrayList<>();
    for (List<Integer> result : results) {
      Collections.sort(result);
      sequence.add(combinations.indexOf(result));
    }
    return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
        sequence, totalPossibleOutcomes, 0.01);
  }

  @EpiTest(testDataFile = "random_subset.tsv")
  public static void randomSubsetWrapper(TimedExecutor executor, int n, int k)
      throws Exception {
    RandomSequenceChecker.runFuncWithRetries(
        () -> randomSubsetRunner(executor, n, k));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RandomSubset.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
