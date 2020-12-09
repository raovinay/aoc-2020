package com.rao.aoc.day8;

import java.util.Objects;

public class Instruction {
  private String operation;
  private int steps;
  public Instruction(String input) {
    this.operation=input.split(" ")[0];
    this.steps=Integer.parseInt(input.split(" ")[1]);
  }
  public Instruction(Instruction input) {
    this.operation=input.operation;
    this.steps=input.steps;
  }

  public void setOperation(final String operation) {
    this.operation = operation;
  }

  public String getOperation() {
    return operation;
  }

  public int getSteps() {
    return steps;
  }
}
