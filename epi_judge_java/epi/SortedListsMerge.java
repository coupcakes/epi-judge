package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  // @include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
      ListNode<Integer> L2) {
    ListNode<Integer> dummy = new ListNode<Integer>(0, null);
    ListNode<Integer> curr = dummy;
    while (L1 != null && L2 != null) {
      if (L1.data <= L2.data) {
        curr.next = L1;
        L1 = L1.next;
      } else {
        curr.next = L2;
        L2 = L2.next;
      }
      curr = curr.next;
    }
    curr.next = L1 != null ? L1 : L2;

    return dummy.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
