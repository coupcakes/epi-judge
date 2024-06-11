package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class AddingCredits {

  public static class ClientsCreditsInfo {
    private int offset = 0;
    private Map<String, Integer> clientToCredits = new HashMap<>();
    private NavigableMap<Integer, Set<String>> creditToClients = new TreeMap<>();

    public void insert(String clientID, int c) {
      remove(clientID);
      clientToCredits.put(clientID, c - offset);

      creditToClients.putIfAbsent(c - offset, new HashSet<>());
      creditToClients.get(c - offset).add(clientID);
    }

    public boolean remove(String clientID) {
      if (clientToCredits.containsKey(clientID)) {
        Integer creditAmt = clientToCredits.get(clientID);
        creditToClients.get(creditAmt).remove(clientID);
        if (creditToClients.get(creditAmt).isEmpty()) {
          creditToClients.remove(creditAmt);
        }

        clientToCredits.remove(clientID);
        return true;
      }
      return false;
    }

    public int lookup(String clientID) {
      return clientToCredits.containsKey(clientID) ? clientToCredits.get(clientID) + offset : -1;
    }

    public void addAll(int C) {
      this.offset += C;
    }

    public String max() {
      return clientToCredits.isEmpty() ? "" : creditToClients.lastEntry().getValue().iterator().next();
    }

    @Override
    public String toString() {
      return "{clientToCredit: " + clientToCredits + "}";
    }
  }

  @EpiUserType(ctorParams = { String.class, String.class, int.class })
  public static class Operation {
    public String op;
    public String sArg;
    public int iArg;

    public Operation(String op, String sArg, int iArg) {
      this.op = op;
      this.sArg = sArg;
      this.iArg = iArg;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Operation operation = (Operation) o;

      if (iArg != operation.iArg) {
        return false;
      }
      if (!op.equals(operation.op)) {
        return false;
      }
      return sArg.equals(operation.sArg);
    }

    @Override
    public int hashCode() {
      int result = op.hashCode();
      result = 31 * result + sArg.hashCode();
      result = 31 * result + iArg;
      return result;
    }

    @Override
    public String toString() {
      return String.format("%s(%s, %d)", op, sArg, iArg);
    }
  }

  @EpiTest(testDataFile = "adding_credits.tsv")
  public static void ClientsCreditsInfoTester(List<Operation> ops)
      throws TestFailure {
    ClientsCreditsInfo cr = new ClientsCreditsInfo();
    int opIdx = 0;
    for (Operation x : ops) {
      String sArg = x.sArg;
      int iArg = x.iArg;
      int result;
      switch (x.op) {
        case "ClientsCreditsInfo":
          break;
        case "remove":
          result = cr.remove(sArg) ? 1 : 0;
          if (result != iArg) {
            throw new TestFailure()
                .withProperty(TestFailure.PropertyName.STATE, cr)
                .withProperty(TestFailure.PropertyName.COMMAND, x)
                .withMismatchInfo(opIdx, iArg, result);
          }
          break;
        case "insert":
          cr.insert(sArg, iArg);
          break;
        case "add_all":
          cr.addAll(iArg);
          break;
        case "lookup":
          result = cr.lookup(sArg);
          if (result != iArg) {
            throw new TestFailure()
                .withProperty(TestFailure.PropertyName.STATE, cr)
                .withProperty(TestFailure.PropertyName.COMMAND, x)
                .withMismatchInfo(opIdx, iArg, result);
          }
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AddingCredits.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
