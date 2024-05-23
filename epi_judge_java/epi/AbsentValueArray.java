package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.BitSet;
import java.util.List;

public class AbsentValueArray {

  public static int findMissingElement(Iterable<Integer> stream) {
    final int NUM_BUCKETS = 1 << 16;
    int[] counts = new int[NUM_BUCKETS];
    java.util.Iterator<Integer> s = stream.iterator();
    while (s.hasNext()) {
      int prefix = s.next() >>> 16;
      counts[prefix]++;
    }

    final int BUCKET_SIZE = 1 << 16;
    int candidatePrefix = 0;
    for (int i = 0; i < NUM_BUCKETS; i++) {
      if (counts[i] < BUCKET_SIZE) {
        candidatePrefix = counts[i];
        break;
      }
    }

    BitSet candidates = new BitSet(BUCKET_SIZE);
    s = stream.iterator();
    while (s.hasNext()) {
      int x = s.next();
      int prefix = x >>> 16;
      if (prefix == candidatePrefix) {
        int lsb = ((1 << 16) - 1) & x;
        candidates.set(lsb);
      }
    }

    for (int i = 0; i < BUCKET_SIZE; i++) {
      if (!candidates.get(i)) {
        return (candidatePrefix << 16) | i;
      }
    }
    return 0;
  }

  @EpiTest(testDataFile = "absent_value_array.tsv")
  public static void findMissingElementWrapper(List<Integer> stream)
      throws Exception {
    try {
      int res = findMissingElement(stream);
      if (stream.stream().filter(a -> a.equals(res)).findFirst().isPresent()) {
        throw new TestFailure(String.valueOf(res) + " appears in stream");
      }
    } catch (IllegalArgumentException e) {
      throw new TestFailure("Unexpected no missing element exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AbsentValueArray.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
