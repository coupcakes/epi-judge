package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class SortedArraysMerge {
  private static class ArrayEntry {
    public Integer value;
    public Integer arrayIndex;

    public ArrayEntry(Integer value, Integer arrayIndex) {
      this.value = value;
      this.arrayIndex = arrayIndex;
    }
  }

  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")

  public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
    List<Iterator<Integer>> iters = new ArrayList<Iterator<Integer>>(sortedArrays.size());
    for (List<Integer> lst : sortedArrays) {
      iters.add(lst.iterator());
    }

    PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(sortedArrays.size(),
        (o1, o2) -> Integer.compare(o1.value, o2.value));
    for (int i = 0; i < iters.size(); i++) {
      if (iters.get(i).hasNext()) {
        minHeap.add(new ArrayEntry(iters.get(i).next(), i));
      }
    }

    List<Integer> ret = new ArrayList<Integer>();
    while (!minHeap.isEmpty()) {
      ArrayEntry polledArrayEntry = minHeap.poll();
      ret.add(polledArrayEntry.value);
      if (iters.get(polledArrayEntry.arrayIndex).hasNext()) {
        minHeap.add(new ArrayEntry(iters.get(polledArrayEntry.arrayIndex).next(), polledArrayEntry.arrayIndex));
      }
    }

    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
