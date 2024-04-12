package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    List<List<Integer>> res = new ArrayList<>();
    if (tree == null) {
      return res;
    }
    List<BinaryTreeNode<Integer>> nodesToProcess = new LinkedList<BinaryTreeNode<Integer>>();
    nodesToProcess.add(tree);

    while (!nodesToProcess.isEmpty()) {
      res.add(nodesToProcess.stream().map(n -> n.data).toList());
      nodesToProcess = nodesToProcess.stream()
          .map(n -> Arrays.asList(n.left, n.right))
          .flatMap(List::stream)
          .filter(c -> c != null)
          .toList();
    }
    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
