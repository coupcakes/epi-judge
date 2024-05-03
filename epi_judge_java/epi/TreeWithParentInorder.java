package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class TreeWithParentInorder {
  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
    List<Integer> ret = new ArrayList<Integer>();
    BinaryTree<Integer> prev = null, curr = tree;

    while (curr != null) {
      BinaryTree<Integer> next;
      if (curr.parent == prev) {
        if (curr.left != null) {
          next = curr.left;
        } else {
          ret.add(curr.data);
          next = curr.right != null ? curr.right : curr.parent;
        }
      } else if (curr.left == prev) {
        ret.add(curr.data);
        next = curr.right != null ? curr.right : curr.parent;
      } else {
        next = curr.parent;
      }
      prev = curr;
      curr = next;
    }
    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
