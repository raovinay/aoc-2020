package com.rao.aoc.day12;

import com.rao.aoc.utils.InputReader;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class MoveWaypoint {

  public static void main(String[] args) throws FileNotFoundException {

    final List<String> inputs = InputReader.readInputs("src/com/rao/aoc/day12/test.txt")
        .collect(Collectors.toList());
    Coordinates waypoint = new Coordinates(10, 1, null);
    Coordinates ship = new Coordinates(0, 0, null);
    final Coordinates endCoordinates = getEndCoordinates(inputs, ship, waypoint);
    System.out.println(calculateManhattanDistance(endCoordinates));
  }

  private static long calculateManhattanDistance(final Coordinates coordinates) {
    return Math.abs(coordinates.getX())+Math.abs(coordinates.getY());
  }

  private static Coordinates getEndCoordinates(final List<String> inputs,
                                               final Coordinates ship,
                                               Coordinates waypoint) {
    for (final String input : inputs) {
      String nav = input.substring(0, 1);
      int stepsOrDegrees = Integer.parseInt(input.substring(1));
      switch (nav) {
        case "F":
          //move ship to the wp step number of times.
          moveShip(ship, waypoint, stepsOrDegrees);
          break;
        case "R":
          waypoint = turn(waypoint, stepsOrDegrees, nav);
          break;
        case "L":
          waypoint = turn(waypoint, stepsOrDegrees, nav);
          break;
        default:
          //move wp direction
          moveWaypoint(waypoint, Direction.fromVal(nav), stepsOrDegrees);
      }
    }
    return ship;
  }

  private static void moveWaypoint(final Coordinates waypoint, final Direction direction,
                                   final int steps) {
    switch (direction){
      case EAST:
        waypoint.setX(waypoint.getX()+steps);break;
      case WEST:
        waypoint.setX(waypoint.getX()-steps);break;
      case NORTH:
        waypoint.setY(waypoint.getY()+steps);break;
      case SOUTH:
        waypoint.setY(waypoint.getY()-steps);break;
    }
  }

  private static Coordinates turn(final Coordinates waypoint, final int degrees, final String nav) {
    Direction xDirection=Direction.EAST;//e10
    Direction yDirection=Direction.NORTH;
    if(waypoint.getX()<0){
      xDirection=Direction.WEST;
    }
    if (waypoint.getY()<0){
      yDirection= Direction.SOUTH;
    }
    Direction newX = xDirection.turn(nav, degrees);//S10
    Direction newY = yDirection.turn(nav, degrees);
    Coordinates newWaypoint = new Coordinates(
        Math.abs(waypoint.getX()),
        Math.abs(waypoint.getY()), waypoint.getDirection());
    if(! (newX.equals(Direction.EAST) || newX.equals(Direction.WEST))){
      //directions flipped
      newWaypoint.setY(waypoint.getX());
      newWaypoint.setX(waypoint.getY());
    }
    if (newX.equals(Direction.SOUTH) || newY.equals(Direction.SOUTH)){
      newWaypoint.setY(newWaypoint.getY() * -1);
    }
    if (newX.equals(Direction.WEST) || newY.equals(Direction.WEST)){
      newWaypoint.setX(newWaypoint.getX() * -1);
    }

    return newWaypoint;
  }


  private static void moveShip(final Coordinates ship, final Coordinates waypoint,
                               final int factor) {
    ship.setX(ship.getX()+(waypoint.getX()*factor));
    ship.setY(ship.getY()+(waypoint.getY()*factor));
  }

}

