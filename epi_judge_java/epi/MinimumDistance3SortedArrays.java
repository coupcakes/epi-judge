package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class MinimumDistance3SortedArrays {

  public static class ArrayData implements Comparable<ArrayData> {
    public int val;
    public int idx;

    public ArrayData(int idx, int val) {
      this.val = val;
      this.idx = idx;
    }

    @Override
    public int compareTo(ArrayData o) {
      int result = Integer.compare(val, o.val);
      if (result == 0) {
        result = Integer.compare(idx, o.idx);
      }
      return result;
    }
  }

  @EpiTest(testDataFile = "minimum_distance_3_sorted_arrays.tsv")

  public static int findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {
    List<Integer> pointersIntoEachArray = new ArrayList<Integer>(sortedArrays.size());
    for (int i = 0; i < sortedArrays.size(); i++) {
      pointersIntoEachArray.add(0);
    }

    NavigableSet<ArrayData> currentHeads = new TreeSet<>();
    for (int i = 0; i < pointersIntoEachArray.size(); i++) {
      currentHeads.add(new ArrayData(i, sortedArrays.get(i).get(pointersIntoEachArray.get(i))));
    }

    int minDistanceSoFar = Integer.MAX_VALUE;
    int nextMinArrIdx = 0; // just initialize it as first array for now
    while (pointersIntoEachArray.get(nextMinArrIdx) < sortedArrays.get(nextMinArrIdx).size()) {
      minDistanceSoFar = Math.min(minDistanceSoFar, currentHeads.last().val - currentHeads.first().val);
      nextMinArrIdx = currentHeads.first().idx;
      pointersIntoEachArray.set(nextMinArrIdx, pointersIntoEachArray.get(nextMinArrIdx) + 1);
      if (pointersIntoEachArray.get(nextMinArrIdx) >= sortedArrays.get(nextMinArrIdx).size()) {
        break;
      }
      currentHeads.pollFirst(); // removes min
      currentHeads.add(
          new ArrayData(nextMinArrIdx, sortedArrays.get(nextMinArrIdx).get(pointersIntoEachArray.get(nextMinArrIdx))));
    }

    return minDistanceSoFar;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumDistance3SortedArrays.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
