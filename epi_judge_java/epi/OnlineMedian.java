package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class OnlineMedian {
  public static List<Double> onlineMedian(Iterator<Integer> sequence) {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(16, Collections.reverseOrder());
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    List<Double> ret = new ArrayList<Double>();
    while (sequence.hasNext()) {
      minHeap.add(sequence.next());
      maxHeap.add(minHeap.remove());
      if (maxHeap.size() > minHeap.size()) {
        minHeap.add(maxHeap.remove());
      }
      ret.add(minHeap.size() == maxHeap.size() ? (minHeap.peek() + maxHeap.peek()) * 0.5 : minHeap.peek());
    }
    return ret;
  }

  @EpiTest(testDataFile = "online_median.tsv")
  public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
    return onlineMedian(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "OnlineMedian.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
