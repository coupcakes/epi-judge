package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class DescendantAndAncestorInBst {

  public static boolean pairIncludesAncestorAndDescendantOfM(BstNode<Integer> possibleAncOrDesc0,
      BstNode<Integer> possibleAncOrDesc1,
      BstNode<Integer> middle) {
    BstNode<Integer> pointer0 = possibleAncOrDesc0, pointer1 = possibleAncOrDesc1;

    while (pointer0 != possibleAncOrDesc1 && pointer0 != middle && pointer1 != possibleAncOrDesc0 && pointer1 != middle
        && (pointer0 != null || pointer1 != null)) {
      if (pointer0 != null) {
        pointer0 = middle.data < pointer0.data ? pointer0.left : pointer0.right;
      }

      if (pointer1 != null) {
        pointer1 = middle.data < pointer1.data ? pointer1.left : pointer1.right;
      }
    }

    if (pointer0 == possibleAncOrDesc1 || pointer1 == possibleAncOrDesc0
        || (pointer0 != middle && pointer1 != middle)) {
      return false;
    }

    return searchForTarget(middle, pointer0 == middle ? possibleAncOrDesc1 : possibleAncOrDesc0);
  }

  private static boolean searchForTarget(BstNode<Integer> from, BstNode<Integer> target) {
    while (from != null && from != target) {
      from = target.data < from.data ? from.left : from.right;
    }

    return from == target;
  }

  @EpiTest(testDataFile = "descendant_and_ancestor_in_bst.tsv")
  public static boolean pairIncludesAncestorAndDescendantOfMWrapper(
      TimedExecutor executor, BstNode<Integer> tree, int possibleAncOrDesc0,
      int possibleAncOrDesc1, int middle) throws Exception {
    final BstNode<Integer> candidate0 = BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc0);
    final BstNode<Integer> candidate1 = BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc1);
    final BstNode<Integer> middleNode = BinaryTreeUtils.mustFindNode(tree, middle);

    return executor.run(() -> pairIncludesAncestorAndDescendantOfM(
        candidate0, candidate1, middleNode));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DescendantAndAncestorInBst.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
