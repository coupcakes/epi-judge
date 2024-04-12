package epi;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class DirectoryPathNormalization {
  @EpiTest(testDataFile = "directory_path_normalization.tsv")

  public static String shortestEquivalentPath(String path) {
    Deque<String> stack = new ArrayDeque<String>();
    if (path.startsWith("/")) {
      stack.addFirst("/");
    }
    for (String token : path.split("/")) {
      if (token.equals("..")) {
        if (stack.isEmpty() || stack.peekFirst().equals("..")) {
          stack.addFirst(token);
        } else {
          if (stack.peekFirst().equals("/")) {
            throw new IllegalArgumentException("Path error: trying to go up root " + path);
          }
          stack.removeFirst();
        }
      } else if (!token.equals(".") && !token.isEmpty()) {
        stack.addFirst(token);
      }
    }
    StringBuilder sb = new StringBuilder();
    if (!stack.isEmpty()) {
      Iterator<String> it = stack.descendingIterator();
      String prev = it.next();
      sb.append(prev);
      while (it.hasNext()) {
        if (!prev.equals("/")) {
          sb.append("/");
        }
        prev = it.next();
        sb.append(prev);
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DirectoryPathNormalization.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
