package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.ArrayDeque;
import java.util.Deque;
public class IsTreeABst {
  @EpiTest(testDataFile = "is_tree_a_bst.tsv")

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    return isBSTHelperBFS(tree);
    // return isBSTHelper(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean isBSTHelper(BinaryTreeNode<Integer> tree, Integer lower, Integer upper) {
    if (tree == null) {
      return true;
    }

    if (Integer.compare(tree.data, lower) < 0 || Integer.compare(tree.data, upper) > 0) {
      return false;
    }

    return isBSTHelper(tree.left, lower, tree.data) && isBSTHelper(tree.right, tree.data, upper);
  }

  private static class QueueEntry {
    public BinaryTreeNode<Integer> node;
    public Integer lower, upper;

    public QueueEntry(BinaryTreeNode<Integer> node, Integer lower, Integer upper) {
      this.node = node;
      this.lower = lower;
      this.upper = upper;
    }
  }

  private static boolean isBSTHelperBFS(BinaryTreeNode<Integer> tree) {
    Deque<QueueEntry> q = new ArrayDeque<>();
    q.add(new QueueEntry(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));
    while (!q.isEmpty()) {
      QueueEntry entry = q.poll();
      if (entry.node != null) {
        if (Integer.compare(entry.node.data, entry.lower) < 0 || Integer.compare(entry.node.data, entry.upper) > 0) {
          return false;
        }
        q.add(new QueueEntry(entry.node.left, entry.lower, entry.node.data));
        q.add(new QueueEntry(entry.node.right, entry.node.data, entry.upper));
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
