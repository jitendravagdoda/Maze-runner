import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PathCalculator {

    public static String calculate(int height,int width,String t[],boolean mine) {
            // Node for maintaing direction for each number
            Node node = new Node(height, width, t,mine);
            // Stack for storing path
            Stack<Cell> path = new Stack<>();
            // To Display in format
            StringBuilder solution = new StringBuilder();
            // Maze in memory
            String map[][] = node.toBinaryArray();
            // To calculate shorest path
            shortestPath(map, new Cell(node.start_x, node.start_y), new Cell(node.end_x, node.end_y), path);
            //Maintain direction
            int lastrow = node.start_x, lastcol = node.start_y, crow = node.start_x, ccol = node.start_y, count = 0;

           // Poping path from stack to string
            if(path.empty())
               solution.append("No_Path ");

            while (!path.isEmpty()) {

                lastrow = crow;
                lastcol = ccol;

                Cell c = path.pop();
                crow = c.row;
                ccol = c.col;
                //print the current cell

                if (!(crow % 2 == 1 || ccol % 2 == 1)) {
                    // System.out.print(" "+c+" ");
                    if (map[crow][ccol].equals("m")) count++;
                    if (lastrow == crow && lastcol == ccol + 1) {
                        solution.append("'left', ");
                    } else if (lastrow == crow && lastcol + 1 == ccol) {
                        solution.append("'right', ");

                    } else if (lastrow == crow + 1 && lastcol == ccol) {
                        solution.append("'up', ");

                    } else if (lastrow + 1 == crow && lastcol == ccol) {
                        solution.append("'down', ");
                    }
                }
        }
      //  System.out.println("mine " + count);
        drawCanvas(height, width, t, solution.toString());
        return "[ " + solution.toString()+ "]";
    }


    public static class Cell {

        public int row;
        public int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "{" + row + ", " + col + "}";
        }

    }
        public static int shortestPath(String[][] map, Cell start, Cell end,Stack<Cell> path) {
        // initialize distances array filled with infinity
        int[][] distances = new int[map.length][];
        for (int i = 0; i < map.length; i++) {
            distances[i] = new int[map[i].length];
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }

        // the start node should get distance 0
        int distance = 0;
        List<Cell> currentCells = Arrays.asList(start);

        while (distances[end.row][end.col] == Integer.MAX_VALUE
                && !currentCells.isEmpty()) {
            List<Cell> nextCells = new ArrayList<>();

       // loop over all cells added in previous round
       // set their distance
       //    and add their neighbors to the list for next round
            for (Cell cell : currentCells) {
                if (distances[cell.row][cell.col] == Integer.MAX_VALUE
                        && !map[cell.row][cell.col].equals("1")) {
                    distances[cell.row][cell.col] = distance;
                    addNeighbors(cell, nextCells, map.length, map[0].length);
                }
            }

           // prepare for next round
            currentCells = nextCells;
            distance++;
        }

        // find the path
        if (distances[end.row][end.col] < Integer.MAX_VALUE) {
            Cell cell = end;
            path.push(end);
            for (int d = distances[end.row][end.col]-1; d >= 0; d--) {
                cell = getNeighbor(cell, d, distances);
                path.push(cell);
            }
        }
        return distances[end.row][end.col];
    }

    private static void addNeighbors(Cell cell, List<Cell> list,
                                     int maxRow, int maxCol) {
        int[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] d : ds) {
            int row = cell.row + d[0];
            int col = cell.col + d[1];
            if (isValid(row, col, maxRow, maxCol))
                list.add(new Cell(row, col));
        }
    }

    //find the neighbor of a cell having a certain distance from the start
    private static Cell getNeighbor(Cell cell, int distance, int[][] distances) {
        int[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] d : ds) {
            int row = cell.row + d[0];
            int col = cell.col + d[1];
            if (isValid(row, col, distances.length, distances[0].length)
                    && distances[row][col] == distance)
                return new Cell(row, col);
        }
        return null;
    }

    //check if coordinates are inside the maze
    private static boolean isValid(int row, int col, int maxRow, int maxCol) {
        return row >= 0 && row < maxRow && col >= 0 && col < maxCol;
    }


    public static void drawCanvas(int height,int width, String t[],String path)
    {
        Grids application = new Grids(height, width, t,path);
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
}
}





