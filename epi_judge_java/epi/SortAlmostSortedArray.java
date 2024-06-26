package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class SortAlmostSortedArray {

  public static List<Integer> sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    for (int i = 0; i < k && sequence.hasNext(); i++) {
      minHeap.add(sequence.next());
    }

    List<Integer> ret = new ArrayList<Integer>();
    while (sequence.hasNext()) {
      ret.add(minHeap.remove());
      minHeap.add(sequence.next());
    }

    while (!minHeap.isEmpty()) {
      ret.add(minHeap.remove());
    }
    return ret;
  }

  @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
  public static List<Integer> sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
    return sortApproximatelySortedData(sequence.iterator(), k);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortAlmostSortedArray.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
