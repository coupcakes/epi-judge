package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SumRootToLeaf {
  @EpiTest(testDataFile = "sum_root_to_leaf.tsv")

  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
    return sumRootToLeafHelper(tree, 0);
  }

  private static int sumRootToLeafHelper(BinaryTreeNode<Integer> node, int sum) {
    if (node == null) {
      return 0;
    }

    int partialSum = (sum * 2) + node.data;
    if (node.left == null && node.right == null) {
      return partialSum;
    }

    return sumRootToLeafHelper(node.left, partialSum) + sumRootToLeafHelper(node.right, partialSum);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SumRootToLeaf.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
