package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TreeInorder {

  private static class NodeAndState {
    public BinaryTreeNode<Integer> node;
    public Boolean leftSubtreeTraversed;

    public NodeAndState(BinaryTreeNode<Integer> node,
        Boolean leftSubtreeTraversed) {
      this.node = node;
      this.leftSubtreeTraversed = leftSubtreeTraversed;
    }
  }

  @EpiTest(testDataFile = "tree_inorder.tsv")
  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
    List<Integer> res = new ArrayList<Integer>();
    Deque<NodeAndState> stack = new ArrayDeque<NodeAndState>();
    stack.addFirst(new NodeAndState(tree, false));

    while (!stack.isEmpty()) {
      NodeAndState nodeAndState = stack.removeFirst();
      if (nodeAndState.node != null) {
        if (nodeAndState.leftSubtreeTraversed) {
          res.add(nodeAndState.node.data);
        } else {
          stack.addFirst(new NodeAndState(nodeAndState.node.right, false));
          stack.addFirst(new NodeAndState(nodeAndState.node, true));
          stack.addFirst(new NodeAndState(nodeAndState.node.left, false));
        }
      }
    }

    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
