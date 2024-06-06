package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class BstFromPreorder {
  private static int rootIdx;
  
  @EpiTest(testDataFile = "bst_from_preorder.tsv")


  public static BstNode<Integer>
  rebuildBSTFromPreorder(List<Integer> preorderSequence) {
    rootIdx = 0;
    return bstHelper(preorderSequence, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static BstNode<Integer> bstHelper(List<Integer> preorderSeq, int lower, int upper) {
    if (rootIdx == preorderSeq.size()) {
      return null;
    }

    int rootVal = preorderSeq.get(rootIdx);
    if (rootVal < lower || rootVal > upper) {
      return null;
    }

    rootIdx++;
    BstNode<Integer> leftSubtree = bstHelper(preorderSeq, lower, rootVal);
    BstNode<Integer> rightSubtree = bstHelper(preorderSeq, rootVal, upper);
    return new BstNode<>(rootVal, leftSubtree, rightSubtree);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromPreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
