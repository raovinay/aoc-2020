package com.rao.aoc.day9;

import com.rao.aoc.utils.InputReader;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 {

  public static void main(String[] args) throws FileNotFoundException {
    int preambleLength = 25;
    final List<Long> input = InputReader.readInputs("src/com/rao/aoc/day9/input.txt")
        .map(Long::parseLong)
        .collect(Collectors.toList());
    for (int i = preambleLength; i < input.size(); i++) {
      if(!isValid(input.get(i), input.subList(i-preambleLength, i))){
        System.out.println(input.get(i));
      }
    }
  }

  private static boolean isValid(final Long curr, final List<Long> preamble) {
    for (int i = 0; i < preamble.size(); i++) {
      for (int j = 1; j < preamble.size(); j++) {
        if (preamble.get(i)+preamble.get(j) == curr) return true;
      }
    }
    return false;
  }
}
