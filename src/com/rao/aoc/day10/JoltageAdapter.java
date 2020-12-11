package com.rao.aoc.day10;

import com.rao.aoc.utils.InputReader;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JoltageAdapter {

  public static void main(String[] args) throws FileNotFoundException {
    final List<Integer> inputs = InputReader
        .readInputs("src/com/rao/aoc/day10/inputs.txt")
        .map(Integer::parseInt)
        .sorted()
        .collect(Collectors.toList());
    int current = 0;
    Map<Integer, Integer> differences = new HashMap<>();
    differences.put(1, 0);
    differences.put(2, 0);
    differences.put(3, 0);
    for (final Integer input : inputs) {
      System.out.println(input-current);
      incrementDifferences(differences, input - current);
      current = input;
    }
    // device is 3 larger than the max.
    incrementDifferences(differences, 3);
    System.out.println(differences.get(1) * differences.get(3));
  }

  private static void incrementDifferences(final Map<Integer, Integer> differences, final int i) {
    differences.put(i, differences.get(i) + 1);
  }
}
