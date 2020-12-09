package com.rao.aoc.day5;

import com.rao.aoc.utils.InputReader;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5 {

  public static void main(String[] args) throws FileNotFoundException {
    final Stream<String> input = InputReader.readInputs("src/com/rao/aoc/day5/input.txt");
    input
        .map(s -> s.replace('F', '0').replace('B', '1').replace('L', '0').replace('R', '1'))
        .mapToInt(s -> Integer.parseInt(s, 2))
        .max().getAsInt();

    //System.out.println(asInt);

  }
}
