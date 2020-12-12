package com.rao.aoc.day12;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

import java.util.Map;

enum Direction {
  NORTH("N"), SOUTH("S"), EAST("E"), WEST("W");

  private String val;
  private static final Map<String, Direction> LOOKUP =
      stream(values()).collect(toMap(Direction::getVal, x -> x));

  private String getVal() {
    return val;
  }

  Direction(String val) {
    this.val = val;
  }

  public static Direction fromVal(String val) {
    return LOOKUP.get(val);
  }

  public Direction turn(String nav, int angle) {
    Direction newDirection = this;
    if (nav.equals("R")) {
      for (int i = 0; i < angle / 90; i++) {
        newDirection = newDirection.next();
      }
    }
    if (nav.equals("L")) {
      for (int i = 0; i < angle / 90; i++) {
        newDirection = newDirection.prev();
      }
    }
    return newDirection;
  }

  private Direction next() {
    switch (this) {
      case EAST:
        return SOUTH;
      case SOUTH:
        return WEST;
      case WEST:
        return NORTH;
      case NORTH:
        return EAST;
      default:
        throw new RuntimeException("Wrong direction");
    }
  }

  private Direction prev() {
    switch (this) {
      case EAST:
        return NORTH;
      case NORTH:
        return WEST;
      case WEST:
        return SOUTH;
      case SOUTH:
        return EAST;
      default:
        throw new RuntimeException("Wrong direction");
    }
  }
}
