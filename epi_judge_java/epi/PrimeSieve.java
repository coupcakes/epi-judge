package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimeSieve {
  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
    List<Integer> res = new ArrayList<Integer>();
    for (int i = 2; i <= n; i++) {
      boolean isPrime = true;
      for (int j = 2; j <= Math.sqrt(i); j++) {
        if ((i % j) == 0) {
          isPrime = false;
          break;
        }
      }
      if (isPrime) {
        res.add(i);
      }
    }
    return res;
  }

  private static List<Integer> generatePrimesV2(int n) {
    List<Boolean> isPrime = new ArrayList<Boolean>(Collections.nCopies(n + 1, true));
    List<Integer> res = new ArrayList<Integer>();
    isPrime.set(0, false);
    isPrime.set(1, false);
    for (int i = 2; i <= n; i++) {
      if (isPrime.get(i)) {
        res.add(i);
      }
      for (int j = i * 2; j <= n; j += i) {
        isPrime.set(j, false);
      }
    }
    return res;
  }

  private static List<Integer> generatePrimesV3(int n) {
    if (n < 2) {
      return Collections.emptyList();
    }
    final int size = (int) Math.floor((n - 3) * 0.5) + 1;
    List<Boolean> isPrime = new ArrayList<Boolean>(Collections.nCopies(size, true));
    List<Integer> res = new ArrayList<Integer>();
    res.add(2);
    for (long i = 0; i < size; i++) {
      if (isPrime.get((int) i)) {
        // the number the current index represents
        int p = (int) ((2 * i) + 3);
        res.add(p);
        // sieve starting from p^2 = (2i + 3)(2i + 3) = 4i^2 + 12i + 9
        // the index where p^2 is
        // 4i^2 + 12i + 9 = 2i + 3
        // 4i^2 + 12i + 6 = 2i
        // 2i^2 + 6i + 3 = i
        for (long j = (2 * i * i) + 6 * i + 3; j < size; j += p) {
          isPrime.set((int) j, false);
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
