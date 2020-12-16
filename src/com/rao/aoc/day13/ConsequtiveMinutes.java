package com.rao.aoc.day13;

import com.rao.aoc.utils.InputReader;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsequtiveMinutes {

  public static void main(String[] args) throws FileNotFoundException {
    var inputs =
        InputReader.readInputs("src/com/rao/aoc/day13/inputs.txt").collect(Collectors.toList());
    final List<String> schudules =
        Arrays.stream(inputs.get(1).split(",")).collect(Collectors.toList());
    int counter = 0;
    Map<Integer, Integer> scheduleDelay = new LinkedHashMap<>();
    for (final String schedule : schudules) {
      if (!schedule.equals("x")) {
        scheduleDelay.put(Integer.parseInt(schedule), counter);
      }
      counter++;
    }
    //trigger warning:
    boolean allgood;
    for (long i = 0; i < Long.MAX_VALUE; i++) {
      long pointer;
      allgood = true;
      for (final Map.Entry<Integer, Integer> schedule : scheduleDelay.entrySet()) {
        pointer = i + schedule.getValue();
        if (!departsNow(pointer, schedule.getKey())) {
          allgood = false;
          break;
        }
      }
      if (allgood) {
        System.out.println(i);
        break;
      }
    }
  }

  private static boolean departsNow(final long timestamp, final long sched) {
    return timestamp % sched==0;
  }
}
