package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CircularQueue {

  public static class Queue {
    private int head = 0, tail = 0, numOfItemsinQueue = 0;
    private static final int SCALING_FACTOR = 2;
    private Integer[] entries;

    public Queue(int capacity) {
      this.entries = new Integer[capacity];
    }

    public void enqueue(Integer x) {
      if (numOfItemsinQueue == entries.length) {
        Collections.rotate(Arrays.asList(entries), -head);
        head = 0;
        tail = numOfItemsinQueue;
        entries = Arrays.copyOf(entries, entries.length * SCALING_FACTOR);
      }

      entries[tail] = x;
      tail = (tail + 1) % entries.length;
      numOfItemsinQueue++;
      return;
    }

    public Integer dequeue() {
      Integer val = entries[head];
      head = (head + 1) % entries.length;
      numOfItemsinQueue--;
      return val;
    }

    public int size() {
      return numOfItemsinQueue;
    }

    @Override
    public String toString() {
      return "Queue { " + "head = " + head + ", tail = " + tail + ", entries = " + Arrays.toString(entries) + " }";
    }
  }

  @EpiUserType(ctorParams = { String.class, int.class })
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }

    @Override
    public String toString() {
      return op;
    }
  }

  @EpiTest(testDataFile = "circular_queue.tsv")
  public static void queueTester(List<QueueOp> ops) throws TestFailure {
    Queue q = new Queue(1);
    int opIdx = 0;
    for (QueueOp op : ops) {
      switch (op.op) {
        case "Queue":
          q = new Queue(op.arg);
          break;
        case "enqueue":
          q.enqueue(op.arg);
          break;
        case "dequeue":
          int result = q.dequeue();
          if (result != op.arg) {
            throw new TestFailure()
                .withProperty(TestFailure.PropertyName.STATE, q)
                .withProperty(TestFailure.PropertyName.COMMAND, op)
                .withMismatchInfo(opIdx, op.arg, result);
          }
          break;
        case "size":
          int s = q.size();
          if (s != op.arg) {
            throw new TestFailure()
                .withProperty(TestFailure.PropertyName.STATE, q)
                .withProperty(TestFailure.PropertyName.COMMAND, op)
                .withMismatchInfo(opIdx, op.arg, s);
          }
          break;
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CircularQueue.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
