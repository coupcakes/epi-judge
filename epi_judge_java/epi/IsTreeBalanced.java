package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return true;
    }
    return getHeight(tree, 1) != -1;
  }

  private static int getHeight(BinaryTreeNode<Integer> root, int currHeight) {
    if (root == null) {
      return currHeight;
    }
    int leftHeight = getHeight(root.left, currHeight + 1);
    int rightHeight = getHeight(root.right, currHeight + 1);
    if (Math.abs(leftHeight - rightHeight) > 1) {
      return -1;
    }
    return Math.max(leftHeight, rightHeight);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
