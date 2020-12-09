package com.rao.aoc.day6;

import static java.util.stream.Collectors.toList;

import com.rao.aoc.utils.InputReader;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6 {

  public static void main(String[] args) throws FileNotFoundException {
    final Stream<String> input = InputReader.readInputs("src/com/rao/aoc/day6/input.txt",
        "\n\n");
    var out1 = input
        .map(x->x.replace("\n", ""))
        .mapToInt(x -> {
          Set<Character> chars = new HashSet<>();
          for (Character c: x.toCharArray()) {
            chars.add(c);
          }
          return chars.size();
        }).sum();
    System.out.println(out1);

    var out2 = InputReader.readInputs("src/com/rao/aoc/day6/input.txt",
        "\n\n")
        .mapToInt(x->{
          final String[] split = x.split("\n");
          int commonCount = 0;
          Map<Character, Integer> counts = new HashMap<>();
          for (String ans : split) {
            final char[] chars = ans.toCharArray();
            for(Character indiv: chars) {
              if(!counts.containsKey(indiv))
                counts.put(indiv, 1);
              else
                counts.put(indiv, counts.get(indiv)+1);
            }

            for(Map.Entry<Character, Integer> entry: counts.entrySet()) {
              if(entry.getValue() == split.length){
                commonCount++;
              }
            }
          }
          return commonCount;
        })
        .sum();
    System.out.println(out2);
  }
}
