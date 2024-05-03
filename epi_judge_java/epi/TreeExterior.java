package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeExterior {

  public static List<BinaryTreeNode<Integer>> exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
    List<BinaryTreeNode<Integer>> ret = new ArrayList<BinaryTreeNode<Integer>>();
    if (tree == null) {
      return ret;
    }
    ret.add(tree);
    addLeftBoundary(tree.left, ret);
    addLeaves(tree.left, ret);
    addLeaves(tree.right, ret);
    addRightBoundary(tree.right, ret);
    return ret;
  }

  private static void addLeftBoundary(BinaryTreeNode<Integer> node, List<BinaryTreeNode<Integer>> ret) {
    if (node == null || (node.left == null && node.right == null)) {
      return;
    }
    ret.add(node);
    if (node.left != null) {
      addLeftBoundary(node.left, ret);
    } else {
      addLeftBoundary(node.right, ret);
    }
  }

  private static void addRightBoundary(BinaryTreeNode<Integer> node, List<BinaryTreeNode<Integer>> ret) {
    if (node == null || (node.left == null && node.right == null)) {
      return;
    }
    if (node.right != null) {
      addRightBoundary(node.right, ret);
    } else {
      addRightBoundary(node.left, ret);
    }
    ret.add(node);
  }

  private static void addLeaves(BinaryTreeNode<Integer> node, List<BinaryTreeNode<Integer>> ret) {
    if (node == null) {
      return;
    }
    if (node.left == null && node.right == null) {
      ret.add(node);
      return;
    }
    addLeaves(node.left, ret);
    addLeaves(node.right, ret);
  }

  private static List<Integer> createOutputList(List<BinaryTreeNode<Integer>> L)
      throws TestFailure {
    if (L.contains(null)) {
      throw new TestFailure("Resulting list contains null");
    }
    List<Integer> output = new ArrayList<>();
    for (BinaryTreeNode<Integer> l : L) {
      output.add(l.data);
    }
    return output;
  }

  @EpiTest(testDataFile = "tree_exterior.tsv")
  public static List<Integer> exteriorBinaryTreeWrapper(TimedExecutor executor,
      BinaryTreeNode<Integer> tree) throws Exception {
    List<BinaryTreeNode<Integer>> result = executor.run(() -> exteriorBinaryTree(tree));

    return createOutputList(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeExterior.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
