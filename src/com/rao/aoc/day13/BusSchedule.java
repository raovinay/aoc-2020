package com.rao.aoc.day13;

import com.rao.aoc.utils.InputReader;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class BusSchedule {

  public static void main(String[] args) throws FileNotFoundException {
    final var inputs = InputReader.readInputs("src/com/rao/aoc/day13/inputs.txt")
        .collect(Collectors.toList());
    var timestamp = Integer.parseInt(inputs.get(0));
    var ans =
        Arrays.stream(inputs.get(1).split(",")).filter(x -> !x.equals("x"))
            .map(Integer::parseInt)
            .min(Comparator.comparingInt(x -> nextSched(timestamp, x)))
            .map(x -> (nextSched(timestamp, x) - timestamp) * x).orElse(0);
    System.out.println(ans);
  }

  private static int nextSched(final int timestamp, final int sched) {
    return ((timestamp / sched) + 1) * sched;
  }


}
