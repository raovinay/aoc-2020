package com.rao.aoc.day8;

import com.rao.aoc.utils.InputReader;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day8 {
  final Set<Instruction> completed = new HashSet<>();
  int acc = 0;
  int lastIdx = 0;
  List<Instruction> inputs = null;
  public static void main(String[] args) throws FileNotFoundException {
    Day8 d = new Day8();
    d.inputs = InputReader.readInputs("src/com/rao/aoc/day8/input.txt")
        .map(Instruction::new)
        .collect(Collectors.toList());
    d.process(d.inputs.get(0), 0);
    System.out.println(d.acc);
    System.out.println(d.lastIdx);
  }

  public void process(final Instruction instruction, final int idx) {
    if(completed.contains(instruction) || idx == 625) return;
    completed.add(instruction);
    int nextIdx = idx+1;
    switch (instruction.getOperation()) {
      case "nop": break;
      case "acc": acc+=instruction.getSteps();break;
      case "jmp": nextIdx=idx+instruction.getSteps();break;
    }
    lastIdx = idx;
    process(inputs.get(nextIdx), nextIdx);
  }
}
