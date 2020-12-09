package com.rao.aoc.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.stream.Stream;

public class InputReader {
  public static Stream<String> readInputs(final String file) throws FileNotFoundException {
    BufferedReader br = new BufferedReader(new FileReader(file));
    return br.lines();
  }
  public static Stream<String> readInputs(final String file, final String delimiter)
      throws FileNotFoundException {
    Scanner scanner = new Scanner(new FileInputStream(file));
    scanner.useDelimiter(delimiter);
    return scanner.tokens();
  }
}
