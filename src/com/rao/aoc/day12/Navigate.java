package com.rao.aoc.day12;

import com.rao.aoc.utils.InputReader;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class Navigate {

  public static void main(String[] args) throws FileNotFoundException {

    final List<String> inputs = InputReader.readInputs("src/com/rao/aoc/day12/inputs.txt")
        .collect(Collectors.toList());
    final Coordinates endCoordinates = getEndCoordinates(inputs);
    System.out.println(calculateManhattanDistance(endCoordinates));
  }

  private static long calculateManhattanDistance(final Coordinates coordinates) {
    return Math.abs(coordinates.getX())+Math.abs(coordinates.getY());
  }

  private static Coordinates getEndCoordinates(final List<String> inputs) {
    Coordinates coordinates = new Coordinates(0, 0, Direction.fromVal("E"));
    for (final String input : inputs) {
      String nav = input.substring(0, 1);
      int stepsOrDegrees = Integer.parseInt(input.substring(1));
      switch (nav) {
        case "F":
          move(coordinates, coordinates.getDirection(), stepsOrDegrees);
          break;
        case "R":
          coordinates.setDirection(coordinates.getDirection().turn(nav, stepsOrDegrees));
          break;
        case "L":
          coordinates.setDirection(coordinates.getDirection().turn(nav, stepsOrDegrees));
          break;
        default:
          move(coordinates, Direction.fromVal(nav), stepsOrDegrees);
      }
      System.out.println(coordinates);
    }
    return coordinates;
  }


  private static void move(final Coordinates coordinates, final Direction dir,
                           final int steps) {
    switch (dir) {
      case NORTH:
        coordinates.setY(coordinates.getY() + steps);
        break;
      case SOUTH:
        coordinates.setY(coordinates.getY() - steps);
        break;
      case EAST:
        coordinates.setX(coordinates.getX() + steps);
        break;
      case WEST:
        coordinates.setX(coordinates.getX() - steps);
        break;
    }
  }
}

