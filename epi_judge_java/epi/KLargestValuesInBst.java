package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
public class KLargestValuesInBst {
  @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")

  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
    return findKLargestIterative(tree, k);
    // List<Integer> res = new ArrayList<>();
    // findKLargestHelper(tree, k, res);
    // return res;
  }

  private static void findKLargestHelper(BstNode<Integer> node, int k, List<Integer> res) {
    if (node != null && res.size() < k) {
      findKLargestHelper(node.right, k, res);
      if (res.size() < k) {
        res.add(node.data);
        findKLargestHelper(node.left, k, res);
      }
    }
  }

  private static List<Integer> findKLargestIterative(BstNode<Integer> node, int k) {
    List<Integer> res = new ArrayList<>();
    Deque<BstNode<Integer>> stack = new ArrayDeque<>();
    BstNode<Integer> curr = node;
    while (curr != null || res.size() < k) {
      while (curr != null) {
        stack.push(curr);
        curr = curr.right;
      }
      if (res.size() < k) {
        BstNode<Integer> popped = stack.pop();
        res.add(popped.data);
        curr = popped.left;
      }
    }
    return res;
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
            .runFromAnnotations(args, "KLargestValuesInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
