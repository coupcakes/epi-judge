package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SearchInBst {

  public static BstNode<Integer> searchBST(BstNode<Integer> tree, int key) {
    if (tree == null || tree.data == key) {
      return tree;
    }
    return tree.data > key ? searchBST(tree.left, key) : searchBST(tree.right, key);
  }

  public static BstNode<Integer> searchBSTIterative(BstNode<Integer> tree, int key) {
    BstNode<Integer> curr = tree;
    while (curr != null) {
      if (curr.data == key) {
        return curr;
      }
      curr = curr.data > key ? curr.left : curr.right;
    }

    return curr;
  }

  @EpiTest(testDataFile = "search_in_bst.tsv")
  public static int searchBSTWrapper(BstNode<Integer> tree, int key) {
    BstNode<Integer> result = searchBST(tree, key);
    return result != null ? result.data : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
