package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class StackWithMax {
  private static class ElementWithCachedMax {
    public Integer value;
    public Integer max;

    public ElementWithCachedMax(Integer value, Integer max) {
      this.value = value;
      this.max = max;
    }
  }

  public static class Stack {
    private Deque<ElementWithCachedMax> deque = new LinkedList<ElementWithCachedMax>();

    public boolean empty() {
      return deque.isEmpty();
    }

    public Integer max() {
      if (empty()) {
        throw new IllegalStateException("max(): stack is empty");
      }
      return deque.peek().max;
    }

    public Integer pop() {
      if (empty()) {
        throw new IllegalStateException("pop(): stack is empty");
      }
      return deque.removeFirst().max;
    }

    public void push(Integer x) {
      deque.addFirst(new ElementWithCachedMax(x, Math.max(x, empty() ? x : max())));
    }
  }

  @EpiUserType(ctorParams = { String.class, int.class })
  public static class StackOp {
    public String op;
    public int arg;

    public StackOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testDataFile = "stack_with_max.tsv")
  public static void stackTester(List<StackOp> ops) throws TestFailure {
    try {
      Stack s = new Stack();
      int result;
      for (StackOp op : ops) {
        switch (op.op) {
          case "Stack":
            s = new Stack();
            break;
          case "push":
            s.push(op.arg);
            break;
          case "pop":
            result = s.pop();
            if (result != op.arg) {
              throw new TestFailure("Pop: expected " + String.valueOf(op.arg) +
                  ", got " + String.valueOf(result));
            }
            break;
          case "max":
            result = s.max();
            if (result != op.arg) {
              throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                  ", got " + String.valueOf(result));
            }
            break;
          case "empty":
            result = s.empty() ? 1 : 0;
            if (result != op.arg) {
              throw new TestFailure("Empty: expected " + String.valueOf(op.arg) +
                  ", got " + String.valueOf(s));
            }
            break;
          default:
            throw new RuntimeException("Unsupported stack operation: " + op.op);
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StackWithMax.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
