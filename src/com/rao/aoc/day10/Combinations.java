package com.rao.aoc.day10;

import com.rao.aoc.utils.InputReader;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class Combinations {

  public static void main(String[] args) throws FileNotFoundException {
    final List<Integer> inputs = InputReader
        .readInputs("src/com/rao/aoc/day10/inputs.txt")
        .map(Integer::parseInt)
        .sorted()
        .collect(Collectors.toList());
    int max = inputs.get(inputs.size() - 1) + 3;
    inputs.add(0, 0);
    inputs.add(max);

    long[] paths = new long[inputs.size()];
    paths[0] = 1;
    for (int i = 1; i < inputs.size(); i++) {
      for (int j = i - 3; j < i; j++) {
        if (j >= 0 && inputs.get(i) - inputs.get(j) <= 3) {
          paths[i] += paths[j];
        }
      }
    }
    System.out.println(paths[inputs.size()-1]);
  }
}
