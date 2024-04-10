package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    ListNode<Integer> dummyHead = new ListNode<Integer>(0, L);
    ListNode<Integer> fastPtr = dummyHead;
    for (int i = 0; i < k; i++) {
      fastPtr = fastPtr.next;
    }

    ListNode<Integer> slowPtr = dummyHead;
    while (fastPtr.next != null) {
      slowPtr = slowPtr.next;
      fastPtr = fastPtr.next;
    }

    slowPtr.next = slowPtr.next.next;
    return dummyHead.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
