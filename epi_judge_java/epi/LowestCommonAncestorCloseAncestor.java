package epi;

import java.util.HashSet;
import java.util.Set;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class LowestCommonAncestorCloseAncestor {

  public static BinaryTree<Integer> lca(BinaryTree<Integer> node0,
      BinaryTree<Integer> node1) {
    Set<BinaryTree<Integer>> nodesVisited = new HashSet<BinaryTree<Integer>>();
    while (node0 != null || node1 != null) {
      if (node0 != null) {
        if (!nodesVisited.add(node0)) {
          return node0;
        }
        node0 = node0.parent;
      }

      if (node1 != null) {
        if (!nodesVisited.add(node1)) {
          return node1;
        }
        node1 = node1.parent;
      }
    }
    throw new IllegalArgumentException("node0 and node1 are not from the same tree.");
  }

  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
      Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> lca(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorCloseAncestor.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
