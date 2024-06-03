package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarRendering {
  @EpiUserType(ctorParams = { int.class, int.class })

  public static class Event {
    public int start, finish;

    public Event(int start, int finish) {
      this.start = start;
      this.finish = finish;
    }
  }

  private static class Endpoint {
    public int time;
    public boolean isStart;

    Endpoint(int time, boolean isStart) {
      this.time = time;
      this.isStart = isStart;
    }
  }

  @EpiTest(testDataFile = "calendar_rendering.tsv")

  public static int findMaxSimultaneousEvents(List<Event> A) {
    List<Endpoint> E = A.stream().map(e -> List.of(new Endpoint(e.start, true), new Endpoint(e.finish, false)))
        .flatMap(List::stream)
        .collect(Collectors.toList());

    E.sort((a, b) -> {
      if (a.time != b.time) {
        return Integer.compare(a.time, b.time);
      }
      return a.isStart && !b.isStart ? -1 : !a.isStart && b.isStart ? 1 : 0;
    });

    int maxSimultaneousEvents = 0, numSimultaneousEvents = 0;
    for (Endpoint e : E) {
      if (e.isStart) {
        numSimultaneousEvents++;
        maxSimultaneousEvents = Math.max(maxSimultaneousEvents, numSimultaneousEvents);
      } else {
        numSimultaneousEvents--;
      }
    }
    return maxSimultaneousEvents;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CalendarRendering.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
