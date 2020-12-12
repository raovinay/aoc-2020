package com.rao.aoc.day12;

class Coordinates {

  private int x;
  private int y;
  private Direction direction;

  public Coordinates(final int x, final int y, final Direction direction) {
    this.x = x;
    this.y = y;
    this.direction = direction;
  }

  public int getX() {
    return x;
  }

  public void setX(final int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(final int y) {
    this.y = y;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(final Direction direction) {
    this.direction = direction;
  }

  @Override
  public String toString() {
    return "Coordinates{" +
           "x=" + x +
           ", y=" + y +
           ", direction=" + direction +
           '}';
  }
}
