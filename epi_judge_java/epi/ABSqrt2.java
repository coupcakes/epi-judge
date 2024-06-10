package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ABSqrt2 {

  public static class Number {
    public int a, b;
    public double val;

    public Number(int a, int b) {
      this.a = a;
      this.b = b;
      val = a + b * Math.sqrt(2);
    }
  }

  @EpiTest(testDataFile = "a_b_sqrt2.tsv")

  public static List<Double> generateFirstKABSqrt2(int k) {
    List<Number> ret = new ArrayList<>(k);
    ret.add(new Number(0, 0));
    int i = 0, j = 0;
    for (int n = 1; n < k; n++) {
      Number candidate1 = new Number(ret.get(i).a + 1, ret.get(i).b);
      Number candidate2 = new Number(ret.get(j).a, ret.get(j).b + 1);
      ret.add(candidate1.val < candidate2.val ? candidate1 : candidate2);
      if (Double.compare(candidate1.val, ret.get(ret.size() - 1).val) == 0) {
        i++;
      }
      if (Double.compare(candidate2.val, ret.get(ret.size() - 1).val) == 0) {
        j++;
      }
    }
    return ret.stream().map(c -> c.val).collect(Collectors.toList());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ABSqrt2.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
