package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MatrixRotation {

  public static void rotateMatrix(List<List<Integer>> squareMatrix) {
    final int matrixEndIdx = squareMatrix.size() - 1;
    for (int layer = 0; layer < squareMatrix.size() / 2; layer++) {
      for (int i = layer; i < matrixEndIdx - layer; i++) {
        // save values
        int leftVal = squareMatrix.get(matrixEndIdx - i).get(layer);
        int bottomVal = squareMatrix.get(matrixEndIdx - layer).get(matrixEndIdx - i);
        int rightVal = squareMatrix.get(i).get(matrixEndIdx - layer);
        int topVal = squareMatrix.get(layer).get(i);

        // left -> top
        squareMatrix.get(layer).set(i, leftVal);

        // bottom -> left
        squareMatrix.get(matrixEndIdx - i).set(layer, bottomVal);

        // right -> bottom
        squareMatrix.get(matrixEndIdx - layer).set(matrixEndIdx - i, rightVal);

        // top -> right
        squareMatrix.get(i).set(matrixEndIdx - layer, topVal);
      }
    }
    return;
  }

  @EpiTest(testDataFile = "matrix_rotation.tsv")
  public static List<List<Integer>> rotateMatrixWrapper(List<List<Integer>> squareMatrix) {
    rotateMatrix(squareMatrix);
    return squareMatrix;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixRotation.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
