package game.moveLogic;

import java.util.*;

public class AStar {
    //public static final int DIAGONAL_COST = 14;
    public static final int V_H_COST = 10;

    public static class Cell {
//      TODO: Fix access modifiers
        public int heuristicCost = 0; //Heuristic cost
        public int finalCost = 0; //G+H
        public int x, y;
        Cell parent;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + this.x + ", " + this.y + "]";
        }
    }

    //Blocked cells are just null Cell values in grid
    static Cell[][] grid = new Cell[5][5];

    static PriorityQueue<Cell> open;
    static Queue<Cell> path;

    static boolean closed[][];
    static int startX, startY;
    static int endX, endY;

    public static void setBlocked(int x, int y) {
        grid[x][y] = null;
    }

    public static void setStartCell(int x, int y) {
        startX = x;
        startY = y;
    }

    public static void setEndCell(int x, int y) {
        endX = x;
        endY = y;
    }

    static void checkAndUpdateCost(Cell current, Cell t, int cost) {
        if (t == null || closed[t.x][t.y]) return;
        int t_final_cost = t.heuristicCost + cost;

        boolean inOpen = open.contains(t);
        if (!inOpen || t_final_cost < t.finalCost) {
            t.finalCost = t_final_cost;
            t.parent = current;
            if (!inOpen) {
                open.add(t);
            }
        }
    }

    public static void AStar() {

        //add the start location to open list.
        open.add(grid[startX][startY]);

        Cell current;

        while (true) {
            current = open.poll();
            if (current == null) {
                break;
            }
            closed[current.x][current.y] = true;

            if (current.equals(grid[endX][endY])) {
                return;
            }

            Cell t;

            //REPLACE WITH JUST A CHECK FOR UP DOWN LEFT RIGHT..or a 2x nested for loops not this mess ....
            if (current.x - 1 >= 0) {
                t = grid[current.x - 1][current.y];
                checkAndUpdateCost(current, t, current.finalCost + V_H_COST);

//                if (current.y - 1 >= 0) {
//                    t = grid[current.x - 1][current.y - 1];
//                    checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
//                }
//
//                if (current.y + 1 < grid[0].length) {
//                    t = grid[current.x - 1][current.y + 1];
//                    checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
//                }
            }

            if (current.y - 1 >= 0) {
                t = grid[current.x][current.y - 1];
                checkAndUpdateCost(current, t, current.finalCost + V_H_COST);
            }

            if (current.y + 1 < grid[0].length) {
                t = grid[current.x][current.y + 1];
                checkAndUpdateCost(current, t, current.finalCost + V_H_COST);
            }

            if (current.x + 1 < grid.length) {
                t = grid[current.x + 1][current.y];
                checkAndUpdateCost(current, t, current.finalCost + V_H_COST);

//                if (current.y - 1 >= 0) {
//                    t = grid[current.x + 1][current.y - 1];
//                    checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
//                }
//
//                if (current.y + 1 < grid[0].length) {
//                    t = grid[current.x + 1][current.y + 1];
//                    checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
//                }
            }
        }
    }


    /*
        Params :
        tCase = findPath case No.
        levelX, levelY = Board's dimensions
        startX, startY = start location's x and y coordinates
        endX, endY = end location's x and y coordinates
        int[][] blocked = array containing inaccessible cell coordinates
        */

    //TODO: REMOVE ALL PRINT INFO!
    public static Queue<Cell> findPath(int levelX, int levelY, int startX, int startY, int endX, int endY, int[][] blocked) {
        //Reset
        grid = new Cell[levelX][levelY];
        closed = new boolean[levelX][levelY];
        path = new ArrayDeque<>();
        open = new PriorityQueue<>((Object o1, Object o2) -> {
            Cell c1 = (Cell) o1;
            Cell c2 = (Cell) o2;

            return c1.finalCost < c2.finalCost ? -1 :
                    c1.finalCost > c2.finalCost ? 1 : 0;
        });
        //Set start position
        setStartCell(startX, startY);  //Setting to 0,0 by default. Will be useful for the UI part

        //Set End Location
        setEndCell(endX, endY);

        for (int x = 0; x < levelX; ++x) {
            for (int y = 0; y < levelY; ++y) {
                grid[x][y] = new Cell(x, y);
                grid[x][y].heuristicCost = Math.abs(x - AStar.endX) + Math.abs(y - AStar.endY);
//                  System.out.print(grid[x][y].heuristicCost+" ");
            }
//              System.out.println();
        }
        grid[startX][startY].finalCost = 0;

       /*
        Set blocked cells. Simply set the cell values to null
        for blocked cells.
       */
        for (int x = 0; x <blocked.length; x++) {
            for (int y = 0; y <blocked[x].length ; y++) {
                if (blocked[x][y] != 0) {
                    //TODO fix names to x y everywhere!!!
                    setBlocked(y, x);
                }
            }
        }

        AStar();

        if (closed[AStar.endX][AStar.endY]) {
            Cell current = grid[AStar.endX][AStar.endY];
            path.add(current);
            while (current.parent != null) {
                current = current.parent;
                path.add(current);
            }
            //TODO: USELESS PRINT INFO
            for (Cell cell : path) {
                // System.out.print(" -> " + cell);
            }
            // System.out.println();

        } else {
            // System.out.println("No possible path");
        }
        return path;
    }
}
