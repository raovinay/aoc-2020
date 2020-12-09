package com.rao.aoc.day9;

import com.rao.aoc.utils.InputReader;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day9P2 {

  public static void main(String[] args) throws FileNotFoundException {
    final List<Long> input = InputReader.readInputs("src/com/rao/aoc/day9/input.txt")
        .map(Long::parseLong)
        .collect(Collectors.toList());
    long weakness = 15353384;
    for (int i = 0; i < input.size(); i++) {
      long sum = 0;
      for (int j = i; j < input.size(); j++) {
        sum+=input.get(j);
        if(sum==weakness){
          System.out.println(input.get(i)+" "+input.get(j));
          List<Long> weaknessList = input.subList(i,j);
          System.out.println(weaknessList.size());
          Collections.sort(weaknessList);
          System.out.println("Ans = "+(weaknessList.get(0)+weaknessList.get(weaknessList.size()-1)));
        }
      }
    }
  }
}
