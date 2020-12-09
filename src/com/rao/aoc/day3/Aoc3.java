package com.rao.aoc.day3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Aoc3 {

  public static void main(String[] args) throws FileNotFoundException {
    //problem1();
    problem2();
  }

  private static void problem2() throws FileNotFoundException {
    List<Step> routes = List.of(
        new Step(1,1),
        new Step(3,1),
        new Step(5,1),
        new Step(7,1),
        new Step(1,2)
    );
    long product = 1;
    for (Step route: routes){
      int index = 0;
      int count = 0;
      boolean skipRow = false;
      List<String> lines = readInputs("./src/com/rao/aoc/day3/aoc3.txt").collect(Collectors.toList());
      for (String line: lines) {
        if (route.down == 2) {
          if (skipRow) {
            skipRow = false;
            continue;
          } else {
            skipRow = true;
          }
        }
        if (line.charAt(index) == '#') {
          count++;
        }
        index = increment(index, 31, route.right);
      }
      System.out.println(count);
      product = product * count;
    }
    System.out.println(product);
  }

  private static void problem1() throws FileNotFoundException {
    int index = 0;
    int count = 0;
    List<String> lines = readInputs("/src/com/rao/aoc/aoc3.txt").collect(Collectors.toList());
    for (String line: lines) {
      System.out.println(line+" "+index+ " "+(line.charAt(index) == '#'));
      if (line.charAt(index) == '#') {
        count++;
      }
      index = increment(index, 31);
    }
    System.out.println(count);
  }

  private static int increment(int index, int rollback) {
    int newIndex = index + 3;
    if (newIndex > (rollback - 1)){
      newIndex = newIndex - (rollback);
    }
    return newIndex;
  }
  private static int increment(int index, int rollback, int incBy) {
    int newIndex = index + incBy;
    if (newIndex > (rollback - 1)){
      newIndex = newIndex - (rollback);
    }
    return newIndex;
  }
  private static Stream<String> readInputs(final String file) throws FileNotFoundException {
    BufferedReader br = new BufferedReader(new FileReader(file));
    return br.lines();
  }
}

class Step {
  int right;
  int down;

  public Step(final int right, final int down) {
    this.right = right;
    this.down = down;
  }
}
