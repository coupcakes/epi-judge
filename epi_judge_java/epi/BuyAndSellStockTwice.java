package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BuyAndSellStockTwice {
  @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
  public static double buyAndSellStockTwice(List<Double> prices) {
    double minPrice1 = -prices.get(0);
    double maxProfit1 = 0;
    double minPrice2 = -prices.get(0);
    double maxProfit2 = 0;
    for (int i = 1; i < prices.size(); i++) {
      minPrice1 = Math.max(minPrice1, -prices.get(i));
      maxProfit1 = Math.max(maxProfit1, prices.get(i) + minPrice1);
      minPrice2 = Math.max(minPrice2, maxProfit1 - prices.get(i));
      maxProfit2 = Math.max(maxProfit2, prices.get(i) + minPrice2);
    }
    return maxProfit2;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
