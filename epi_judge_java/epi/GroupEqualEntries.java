package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GroupEqualEntries {
  @EpiUserType(ctorParams = { Integer.class, String.class })

  public static class Person {
    public Integer age;
    public String name;

    public Person(Integer k, String n) {
      age = k;
      name = n;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;

      Person person = (Person) o;

      if (!age.equals(person.age))
        return false;
      return name.equals(person.name);
    }

    @Override
    public int hashCode() {
      int result = age.hashCode();
      result = 31 * result + name.hashCode();
      return result;
    }
  }

  public static void groupByAge(List<Person> people) {
    Map<Integer, Integer> ageToCount = new HashMap<Integer, Integer>();
    for (Person p : people) {
      ageToCount.put(p.age, ageToCount.getOrDefault(p.age, 0) + 1);
    }

    Map<Integer, Integer> ageToOffset = new HashMap<Integer, Integer>();
    int offset = 0;
    for (Map.Entry<Integer, Integer> entry : ageToCount.entrySet()) {
      ageToOffset.put(entry.getKey(), offset);
      offset += entry.getValue();
    }

    while (!ageToOffset.isEmpty()) {
      Map.Entry<Integer, Integer> fromPerson = ageToOffset.entrySet().iterator().next();
      Integer ageOfFromPerson = people.get(fromPerson.getValue()).age;
      Integer idxToMoveFromPerson = ageToOffset.get(ageOfFromPerson);
      // after swap, person at idxToMoveFromPerson is in correct spot
      Collections.swap(people, idxToMoveFromPerson, fromPerson.getValue());
      Integer count = ageToCount.get(ageOfFromPerson) - 1;
      ageToCount.put(ageOfFromPerson, count);
      if (count > 0) {
        ageToOffset.put(ageOfFromPerson, idxToMoveFromPerson + 1);
      } else {
        ageToOffset.remove(ageOfFromPerson);
      }
    }
    return;
  }

  private static Map<Person, Integer> buildMultiset(List<Person> people) {
    Map<Person, Integer> m = new HashMap<>();
    for (Person p : people) {
      m.put(p, m.getOrDefault(p, 0) + 1);
    }
    return m;
  }

  @EpiTest(testDataFile = "group_equal_entries.tsv")
  public static void groupByAgeWrapper(TimedExecutor executor,
      List<Person> people) throws Exception {
    if (people.isEmpty()) {
      return;
    }
    Map<Person, Integer> values = buildMultiset(people);

    executor.run(() -> groupByAge(people));

    Map<Person, Integer> newValues = buildMultiset(people);
    if (!values.equals(newValues)) {
      throw new TestFailure("Entry set changed");
    }

    Set<Integer> ages = new HashSet<>();
    int lastAge = people.get(0).age;
    for (Person p : people) {
      if (ages.contains(p.age)) {
        throw new TestFailure("Entries are not grouped by age");
      }
      if (p.age != lastAge) {
        ages.add(lastAge);
        lastAge = p.age;
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "GroupEqualEntries.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
