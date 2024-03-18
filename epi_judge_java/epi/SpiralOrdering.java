package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrdering {
  @EpiTest(testDataFile = "spiral_ordering.tsv")

  public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    return spiralOrderImproved(squareMatrix);
    // List<Integer> ret = new ArrayList<Integer>();
    // for (int offset = 0; offset < Math.ceil(0.5 * squareMatrix.size()); offset++)
    // {
    // addClockwise(offset, squareMatrix, ret);
    // }
    // return ret;
  }

  private static void addClockwise(int offset, List<List<Integer>> matrix, List<Integer> ret) {
    // if matrix length is odd and we are at the center, just add center and return
    if (offset == matrix.size() - offset - 1) {
      ret.add(matrix.get(offset).get(offset));
      return;
    }

    // add items on top
    for (int col = offset; col < matrix.size() - offset - 1; col++) {
      ret.add(matrix.get(offset).get(col));
    }

    // add items on right
    for (int row = offset; row < matrix.size() - offset - 1; row++) {
      ret.add(matrix.get(row).get(matrix.size() - offset - 1));
    }

    // add items on bottom
    for (int col = matrix.size() - offset - 1; col > offset; col--) {
      ret.add(matrix.get(matrix.size() - offset - 1).get(col));
    }

    // add items on left
    for (int row = matrix.size() - offset - 1; row > offset; row--) {
      ret.add(matrix.get(row).get(offset));
    }
  }

  private static List<Integer> spiralOrderImproved(List<List<Integer>> matrix) {
    List<Integer> ret = new ArrayList<Integer>();
    final int[][] SHIFT = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    int dir = 0, x = 0, y = 0;
    for (int i = 0; i < (matrix.size() * matrix.size()); i++) {
      ret.add(matrix.get(x).get(y));
      matrix.get(x).set(y, 0);
      int nextX = x + SHIFT[dir][0];
      int nextY = y + SHIFT[dir][1];
      if (nextX < 0 || nextX >= matrix.size() || nextY < 0 || nextY >= matrix.size()
          || matrix.get(nextX).get(nextY) == 0) {
        dir = (dir + 1) % 4;
        nextX = x + SHIFT[dir][0];
        nextY = y + SHIFT[dir][1];
      }
      x = nextX;
      y = nextY;
    }
    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrdering.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
