package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TestUtils;
import epi.test_framework.TimedExecutor;

import java.util.List;

public class BstFromSortedArray {

  public static BstNode<Integer> buildMinHeightBSTFromSortedArray(List<Integer> A) {
    return buildBalancedBST(A, 0, A.size() - 1);
  }

  private static BstNode<Integer> buildBalancedBST(List<Integer> A, int low, int high) {
    if (low > high) {
      return null;
    }

    int mid = low + (high - low) / 2;
    BstNode<Integer> node = new BstNode<>(A.get(mid));
    node.left = buildBalancedBST(A, low, mid - 1);
    node.right = buildBalancedBST(A, mid + 1, high);
    return node;
  }

  @EpiTest(testDataFile = "bst_from_sorted_array.tsv")
  public static int buildMinHeightBSTFromSortedArrayWrapper(TimedExecutor executor,
      List<Integer> A) throws Exception {
    BstNode<Integer> result = executor.run(() -> buildMinHeightBSTFromSortedArray(A));

    List<Integer> inorder = BinaryTreeUtils.generateInorder(result);

    TestUtils.assertAllValuesPresent(A, inorder);
    BinaryTreeUtils.assertTreeIsBst(result);
    return BinaryTreeUtils.binaryTreeHeight(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromSortedArray.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
