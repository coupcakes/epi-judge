package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;

public class KLargestInHeap {
  public static class HeapEntry {
    public Integer index;
    public Integer value;

    public HeapEntry(Integer index, Integer value) {
      this.index = index;
      this.value = value;
    }
  }

  @EpiTest(testDataFile = "k_largest_in_heap.tsv")

  public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {
    if (k <= 0) {
      return Collections.emptyList();
    }

    List<Integer> ret = new ArrayList<Integer>();
    PriorityQueue<HeapEntry> maxHeap = new PriorityQueue<HeapEntry>(16,
        (h1, h2) -> Integer.compare(h2.value, h1.value));
    maxHeap.add(new HeapEntry(0, A.get(0)));
    for (int i = 0; i < k; i++) {
      HeapEntry heapEntry = maxHeap.remove();
      ret.add(heapEntry.value);

      Integer leftChildIdx = heapEntry.index * 2 + 1;
      if (leftChildIdx < A.size()) {
        maxHeap.add(new HeapEntry(leftChildIdx, A.get(leftChildIdx)));
      }

      Integer rightChildIdx = heapEntry.index * 2 + 2;
      if (rightChildIdx < A.size()) {
        maxHeap.add(new HeapEntry(rightChildIdx, A.get(rightChildIdx)));
      }
    }
    return ret;
  }

  @EpiTestComparator
  public static boolean comp(List<Integer> expected, List<Integer> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestInHeap.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
