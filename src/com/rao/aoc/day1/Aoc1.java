package com.rao.aoc.day1;

import com.rao.aoc.utils.InputReader;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Aoc1 {

  public static void main(String[] args) throws FileNotFoundException {
    //bruteForce();
    timeOptimizedFirst();
  }

  private static void timeOptimizedFirst() throws FileNotFoundException {
    int total = 2020;
    Set<Integer> inputSet = InputReader
        .readInputs("./src/com/rao/aoc/day1/inputs.txt")
        .map(Integer::parseInt)
        .collect(Collectors.toSet());
    inputSet.forEach(first -> {
      final int second = total - first;
      if (inputSet.contains(second)) {
        System.out.println(first * second);
      }
    });
  }

  private static void bruteForce(List<Integer> input) {
    int total = 2020;
    for (int i : input) {
      if (input.contains(total - i)) {
        System.out.println(i * (total - i));
        break;
      }
    }

    for (int i = 0; i < input.size(); i++) {
      for (int j = 1; j < input.size(); j++) {
        var val1 = input.get(i);
        var val2 = input.get(j);
        if (input.contains(total - (val1 + val2))) {
          System.out.println(val1 * val2 * (total - (val1 + val2)));
          break;
        }
      }
    }
  }
}
