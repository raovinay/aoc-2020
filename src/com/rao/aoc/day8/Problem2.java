package com.rao.aoc.day8;

import com.rao.aoc.utils.InputReader;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem2 {

  public static void main(String[] args) throws FileNotFoundException {
    final List<Instruction> original =
        InputReader.readInputs("src/com/rao/aoc/day8/input.txt")
            .map(Instruction::new)
            .collect(Collectors.toList());
    Instruction lastInstruction = original.get(original.size()-1);
    for(int i = 0; i< original.size();i++) {
      List<Instruction> newList = null;
      if (original.get(i).getOperation().equals("nop")) {
        newList = original.stream().map(Instruction::new).collect(Collectors.toList());
        newList.get(i).setOperation("jmp");
      }
      else if (original.get(i).getOperation().equals("jmp")) {
        newList = original.stream().map(Instruction::new).collect(Collectors.toList());
        newList.get(i).setOperation("nop");
      } else {
        continue;
      }
      Day8 d = new Day8();
      d.inputs=newList;
      d.process(newList.get(0), 0);
      if(d.lastIdx==624) {
        System.out.println(i);
        System.out.println(d.acc);
      }
    }
  }
}
