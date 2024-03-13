package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class ApplyPermutation {
  public static void applyPermutation(List<Integer> perm, List<Integer> A) {
    for (int i = 0; i < A.size(); i++) {
      int nextIndex = i;
      while (perm.get(nextIndex) != nextIndex) {
        int nextNextIndex = perm.get(nextIndex);
        Collections.swap(perm, nextIndex, nextNextIndex);
        Collections.swap(A, nextIndex, nextNextIndex);
      }
    }
    return;
  }

  public static void applyPermutation1(List<Integer> perm, List<Integer> A) {
    for (int i = 0; i < A.size(); i++) {
      int next = i;
      while (perm.get(next) >= 0) {
        Collections.swap(A, i, perm.get(next));
        int temp = perm.get(next);
        perm.set(next, perm.get(next) - perm.size());
        next = temp;
      }
    }

    for (int i = 0; i < perm.size(); i++) {
      perm.set(i, perm.get(i) + perm.size());
    }
  }

  public static void applyPermutation2(List<Integer> perm, List<Integer> A) {
    for (int i = 0; i < A.size(); i++) {
      int j = perm.get(i);
      boolean isMin = true;

      while (j != i) {
        if (j < i) {
          isMin = false;
          break;
        }
        j = perm.get(j);
      }

      if (isMin) {
        cyclicPermutation(i, perm, A);
      }
    }
  }

  private static void cyclicPermutation(int start, List<Integer> perm, List<Integer> A) {
    int i = start;
    int temp = A.get(start);
    do {
      int nextI = perm.get(i);
      int nextTemp = A.get(nextI);
      A.set(nextI, temp);
      i = nextI;
      temp = nextTemp;
    } while (i != start);
  }

  @EpiTest(testDataFile = "apply_permutation.tsv")
  public static List<Integer> applyPermutationWrapper(List<Integer> perm,
      List<Integer> A) {
    applyPermutation(perm, A);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ApplyPermutation.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
