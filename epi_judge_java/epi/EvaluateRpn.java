package epi;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.function.ToIntBiFunction;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class EvaluateRpn {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
    Deque<Integer> intermediateRes = new ArrayDeque<Integer>();
    final String DELIMINATOR = ",";
    final Map<String, ToIntBiFunction<Integer, Integer>> OPS = Map.of(
        "+", (x, y) -> x + y,
        "-", (x, y) -> x - y,
        "*", (x, y) -> x * y,
        "/", (x, y) -> x / y);
    for (String token : expression.split(DELIMINATOR)) {
      if (OPS.get(token) != null) {
        intermediateRes
            .addFirst(OPS.get(token).applyAsInt(intermediateRes.removeFirst(), intermediateRes.removeFirst()));
      } else {
        intermediateRes.addFirst(Integer.parseInt(token));
      }
    }
    return intermediateRes.removeFirst();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
