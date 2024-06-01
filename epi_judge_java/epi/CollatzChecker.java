package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.HashSet;
import java.util.Set;
public class CollatzChecker {
  @EpiTest(testDataFile = "collatz_checker.tsv")

  public static boolean testCollatzConjecture(int n) {
    Set<Long> verifiedNumbers = new HashSet<Long>();
    for (int i = 3; i <= n; i += 2) {
      Set<Long> seq = new HashSet<>();
      long testI = i;
      while (testI >= i) {
        if (!seq.add(testI)) {
          return false;
        }

        if ((testI % 2) != 0) {
          if (!verifiedNumbers.add(testI)) {
            break;
          }

          long nextTestI = (testI * 3) + 1;
          if (nextTestI <= testI) {
            throw new ArithmeticException("CollatzCheckerParallel seq overflow for " + i);
          }
          testI = nextTestI;
        } else {
          testI /= 2;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CollatzChecker.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
