package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RangeLookupInBst {
  @EpiUserType(ctorParams = { int.class, int.class })

  public static class Interval {
    public int left, right;

    public Interval(int left, int right) {
      this.left = left;
      this.right = right;
    }
  }

  @EpiTest(testDataFile = "range_lookup_in_bst.tsv")

  public static List<Integer> rangeLookupInBst(BstNode<Integer> tree,
      Interval interval) {
    List<Integer> res = new ArrayList<>();
    return rangeLookupHelper(res, interval, tree);
  }

  private static List<Integer> rangeLookupHelper(List<Integer> res, Interval interval, BstNode<Integer> tree) {
    if (tree == null) {
      return res;
    }

    if (interval.left <= tree.data && tree.data <= interval.right) {
      rangeLookupHelper(res, interval, tree.left);
      res.add(tree.data);
      rangeLookupHelper(res, interval, tree.right);
    } else if (interval.right < tree.data) {
      rangeLookupHelper(res, interval, tree.left);
    } else {
      rangeLookupHelper(res, interval, tree.right);
    }

    return res;
  }

  public static void rangeLookupInBstHelper(BstNode<Integer> tree,
      Interval interval,
      List<Integer> result) {
    if (tree == null) {
      return;
    }
    if (interval.left <= tree.data && tree.data <= interval.right) {
      // tree.data lies in the interval.
      rangeLookupInBstHelper(tree.left, interval, result);
      result.add(tree.data);
      rangeLookupInBstHelper(tree.right, interval, result);
    } else if (interval.left > tree.data) {
      rangeLookupInBstHelper(tree.right, interval, result);
    } else { // interval.right >= tree.data
      rangeLookupInBstHelper(tree.left, interval, result);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RangeLookupInBst.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
