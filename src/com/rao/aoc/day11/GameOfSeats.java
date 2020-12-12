package com.rao.aoc.day11;

import com.rao.aoc.utils.InputReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameOfSeats {

  public static final char EMPTY = 'L';
  public static final char OCCUPIED = '#';
  public static final char FLOOR = '.';

  public static void main(String[] args) throws FileNotFoundException {
    final List<String> inputs = InputReader.readInputs("src/com/rao/aoc/day11/inputs.txt")
        .collect(Collectors.toList());
    char[][] grid = getGrid(inputs);
    boolean changed = true;
    do {
      final Map<Boolean, char[][]> iteration = iterate2(grid);
      if (iteration.containsKey(false)) {
        changed = false;
      } else {
        grid = iteration.get(true);
      }
    } while (changed);
    System.out.println(Arrays
        .stream(grid)
        .map(String::valueOf)
        .mapToInt(x->x.replace("L","").replace(".","").length())
        .sum());
  }

  private static Map<Boolean, char[][]> iterate1(final char[][] grid) {
    char[][] newGrid = new char[grid.length][grid[0].length];
    boolean changed = false;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        newGrid[i][j] = grid[i][j];
        if (grid[i][j] != FLOOR) {
          long neighbours = getNeighbourCount(grid, i, j);
          if (grid[i][j] == EMPTY && neighbours == 0) {
            newGrid[i][j] = OCCUPIED;
            changed = true;
          }
          if (grid[i][j] == OCCUPIED && neighbours >= 4) {
            newGrid[i][j] = EMPTY;
            changed = true;
          }
        }
      }
    }
    return Map.of(changed, newGrid);
  }

  private static Map<Boolean, char[][]> iterate2(final char[][] grid) {
    char[][] newGrid = new char[grid.length][grid[0].length];
    boolean changed = false;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        newGrid[i][j] = grid[i][j];
        if (grid[i][j] != FLOOR) {
          int neighbours = getNeighbourCount(grid, i, j);
          if (grid[i][j] == EMPTY && neighbours == 0) {
            newGrid[i][j] = OCCUPIED;
            changed = true;
          }
          if (grid[i][j] == OCCUPIED && neighbours >= 5) {
            newGrid[i][j] = EMPTY;
            changed = true;
          }
        }
      }
    }
    return Map.of(changed, newGrid);
  }

  private static int getNeighbourCount(final char[][] grid, final int i, final int j) {
    int count=0;
    for (int x = j - 1; x >= 0; x--) {
      final char neighbour = getOr0(grid, i, x);
      if(neighbour == OCCUPIED) {
        count++;
        break;
      } else if (neighbour == EMPTY) break;
    }
    for (int x = j + 1; x < grid[i].length; x++) {
      final char neighbour = getOr0(grid, i, x);
      if(neighbour == OCCUPIED) {
        count++;
        break;
      }else if (neighbour == EMPTY) break;
    }
    for (int y = i - 1; y >= 0; y--) {
      final char neighbour = getOr0(grid, y, j);
      if(neighbour == OCCUPIED) {
        count++;
        break;
      }else if (neighbour == EMPTY) break;
    }
    for (int y = i + 1; y < grid.length; y++) {
      final char neighbour = getOr0(grid, y, j);
      if(neighbour == OCCUPIED) {
        count++;
        break;
      }else if (neighbour == EMPTY) break;
    }
    for (int x = j - 1, y = i - 1; x >= 0 && y >= 0; x--, y--) {
      final char neighbour = getOr0(grid, y, x);
      if(neighbour == OCCUPIED) {
        count++;
        break;
      }else if (neighbour == EMPTY) break;
    }
    for (int x = j - 1, y = i + 1; x >= 0 && y < grid.length; x--, y++) {
      final char neighbour = getOr0(grid, y, x);
      if(neighbour == OCCUPIED) {
        count++;
        break;
      }else if (neighbour == EMPTY) break;
    }
    for (int x = j + 1, y = i - 1; x < grid[i].length && y >= 0; x++, y--) {
      final char neighbour = getOr0(grid, y, x);
      if(neighbour == OCCUPIED) {
        count++;
        break;
      }else if (neighbour == EMPTY) break;
    }
    for (int x = j + 1, y = i + 1; x < grid[i].length && y < grid.length; x++, y++) {
      final char neighbour = getOr0(grid, y, x);
      if(neighbour == OCCUPIED) {
        count++;
        break;
      }else if (neighbour == EMPTY) break;
    }
    return count;
  }

  private static int getNeighbourCount1(final char[][] grid, int i, int j) {
    return (int) List.of(
        getOr0(grid, i - 1, j - 1),
        getOr0(grid, i - 1, j),
        getOr0(grid, i - 1, j + 1),

        getOr0(grid, i, j - 1),

        getOr0(grid, i, j + 1),

        getOr0(grid, i + 1, j - 1),
        getOr0(grid, i + 1, j),
        getOr0(grid, i + 1, j + 1)
    ).stream().filter(x -> x == OCCUPIED).count();
  }

  private static char getOr0(final char[][] grid, final int i, final int j) {
    if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
      return 0;
    } else {
      return grid[i][j];
    }
  }


  private static char[][] getGrid(final List<String> inputs) {
    char[][] result = new char[inputs.size()][inputs.get(0).length()];
    int i = 0;
    for (final String input : inputs) {
      result[i++] = input.toCharArray();
    }
    return result;
  }
}
