package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TreeFromPreorderInorder {
  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")

  public static BinaryTreeNode<Integer> binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
    return helper(preorder, 0, preorder.size(), 0, inorder.size(),
        IntStream.range(0, inorder.size()).boxed().collect(Collectors.toMap(x -> inorder.get(x), x -> x)));
  }

  private static BinaryTreeNode<Integer> helper(List<Integer> preorder, int preorderStart, int preorderEnd,
      int inorderStart, int inorderEnd, Map<Integer, Integer> inorderIdxMap) {
    if (preorderStart >= preorderEnd || inorderStart >= inorderEnd) {
      return null;
    }

    int rootInorderIdx = inorderIdxMap.get(preorder.get(preorderStart));
    int leftSubtreeSize = rootInorderIdx - inorderStart;
    return new BinaryTreeNode<Integer>(
        preorder.get(preorderStart),
        helper(preorder, preorderStart + 1, preorderStart + 1 + leftSubtreeSize, inorderStart, rootInorderIdx,
            inorderIdxMap),
        helper(preorder, preorderStart + 1 + leftSubtreeSize, preorderEnd, rootInorderIdx + 1, inorderEnd,
            inorderIdxMap));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
