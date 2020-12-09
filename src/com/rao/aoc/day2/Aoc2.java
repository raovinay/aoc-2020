package com.rao.aoc.day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Stream;

public class Aoc2 {

  public static void main(String[] args) throws FileNotFoundException {
    System.out.println(readInputs("./src/com/rao/aoc/day2/aoc2.txt")
        .map(x -> x.split(" "))
        .filter(x -> {
          String[] range = x[0].split("-");
          int min = Integer.parseInt(range[0]);
          int max = Integer.parseInt(range[1]);
          char validiationChar = x[1].charAt(0);
          String password = x[2];
          boolean A= password.charAt(min-1) == validiationChar;
          boolean B= password.charAt(max-1) == validiationChar;
          return (A && !B) || (!A && B);
        }).count());

  }

  private static Stream<String> readInputs(final String file) throws FileNotFoundException {
    BufferedReader br = new BufferedReader(new FileReader(file));
    return br.lines();
  }

  static List<String> input;
}
