package com.rao.aoc.day7;

import static java.util.stream.Collectors.toList;

import com.rao.aoc.utils.InputReader;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Day7 {

  public static void main(String[] args) throws FileNotFoundException {
    //prob1();
    prob2();
  }

  public static void prob1() throws FileNotFoundException {
    final List<String> inputs =
        InputReader.readInputs("src/com/rao/aoc/day7/input.txt").collect(toList());
    var data = parseInput(inputs);
    //bags containing gold.
    Queue<String> parents = new ArrayDeque<>();
    parents.add("shiny gold");
    Set<String> answers = new HashSet<>();
    while(!parents.isEmpty()){
      String parent = parents.poll();
      for (final Map.Entry<String, List<String>> bag : data.entrySet()) {
        if (bag.getValue().contains(parent)) {
          answers.add(bag.getKey());
          parents.add(bag.getKey());
        }
      }
    }
    System.out.println(answers.size());
  }

  private static Map<String, List<String>> parseInput(final List<String> inputs) {
    Map<String, List<String>> inputMap = new HashMap<>();
    for (String line : inputs) {
      String colour = line.split(" bags contain ")[0];
      String remaining = line.split(" bags contain ")[1];
      String [] containedBags = remaining.split(", ");
      List<String> containsInfo = new ArrayList<>();
      for(String containedBag: containedBags) {
        if(containedBag.equals("no other bags.")) {
          break;
        }
        String clean = containedBag.split(" bag")[0];
        int count = Integer.parseInt(clean.split(" ")[0]);
        String containsColor = clean.replace(count+" ", "");
        containsInfo.add(containsColor);
      }
      inputMap.put(colour, containsInfo);
    }
    return inputMap;
  }


  public static void prob2() throws FileNotFoundException {
    final List<String> inputs =
        InputReader.readInputs("src/com/rao/aoc/day7/input.txt").collect(toList());
    var data = parseInput2(inputs);
    var ans = getCount(List.of(new Node("shiny gold", 1)), data);
    System.out.println(ans);
  }

  private static int getCount(final List<Node> node,
                               final Map<String, List<Node>> data) {
    if (node == null) {
      return 0;
    }
    return node.stream().mapToInt(n ->
       n.count + n.count*getCount(data.get(n.colour), data)
    ).sum();
  }

  private static Map<String, List<Node>> parseInput2(final List<String> inputs) {
    Map<String, List<Node>> inputMap = new HashMap<>();
    for (String line : inputs) {
      String colour = line.split(" bags contain ")[0];
      String remaining = line.split(" bags contain ")[1];
      String [] containedBags = remaining.split(", ");
      List<Node> containsInfo = new ArrayList<>();
      for(String containedBag: containedBags) {
        if(containedBag.equals("no other bags.")) {
          break;
        }
        String clean = containedBag.split(" bag")[0];
        int count = Integer.parseInt(clean.split(" ")[0]);
        String containsColor = clean.replace(count+" ", "");
        containsInfo.add(new Node(containsColor, count));
      }
      inputMap.put(colour, containsInfo);
    }
    return inputMap;
  }
}
class Node {
  String colour;
  int count;

  public Node(final String colour, final int count) {
    this.colour = colour;
    this.count = count;
  }

  @Override
  public String toString() {
    return colour + count;
  }
}
