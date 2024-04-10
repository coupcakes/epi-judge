package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntAsListAdd {
  @EpiTest(testDataFile = "int_as_list_add.tsv")

  public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1,
      ListNode<Integer> L2) {
    ListNode<Integer> dummyHead = new ListNode<Integer>(0, null);
    ListNode<Integer> iter = dummyHead;
    int carry = 0;
    while (L1 != null || L2 != null || carry != 0) {
      int val = carry + (L1 != null ? L1.data : 0) + (L2 != null ? L2.data : 0);
      L1 = L1 != null ? L1.next : null;
      L2 = L2 != null ? L2.next : null;
      iter.next = new ListNode<Integer>(val % 10, null);
      iter = iter.next;
    }
    return dummyHead.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsListAdd.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
