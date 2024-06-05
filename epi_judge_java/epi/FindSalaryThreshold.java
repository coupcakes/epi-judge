package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Collections;
import java.util.List;
public class FindSalaryThreshold {
  @EpiTest(testDataFile = "find_salary_threshold.tsv")

  public static double findSalaryCap(int targetPayroll,
                                     List<Integer> currentSalaries) {
    Collections.sort(currentSalaries);
    double unadjustedSalarySum = 0;
    for (int i = 0; i < currentSalaries.size(); i++) {
      int adjustedSalariesNum = currentSalaries.size() - i;
      double adjustedSalarySum = adjustedSalariesNum * currentSalaries.get(i);
      if (unadjustedSalarySum + adjustedSalarySum >= targetPayroll) {
        return (targetPayroll - unadjustedSalarySum) / adjustedSalariesNum;
      }
      unadjustedSalarySum += currentSalaries.get(i);
    }
    return -1.0;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "FindSalaryThreshold.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
