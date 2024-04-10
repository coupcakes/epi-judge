package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsListPalindromic {
  @EpiTest(testDataFile = "is_list_palindromic.tsv")

  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
    ListNode<Integer> slow = L, fast = L;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    ListNode<Integer> firstHalfIter = L;
    ListNode<Integer> secondHalfIter = ReverseList.reverseList(slow);
    while (firstHalfIter != null && secondHalfIter != null) {
      if (!firstHalfIter.data.equals(secondHalfIter.data)) {
        return false;
      }
      firstHalfIter = firstHalfIter.next;
      secondHalfIter = secondHalfIter.next;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
