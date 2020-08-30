/* *****************************************************************************
 *  Name:    Mohammad Alqudah
 *  NetID:   malqudah
 *  Precept: P05
 *
 *  Description: models an n x n board of an n slider puzzle.
 * calculates the hamming distance of the board (all the numbers that are out
 * of place/not in row major order), the manhattan distance (the sum of the
 * vertical and horizontal distance each number needs to move to reach its
 * proper place), and the neighbors of a board (2, 3 or 4 depending on the
 * location of the blank tile, and adds these neighbors to a stack to be
 * iterated across through a for each loop). also calculates if a board
 * is solvable via checking the number of inversions of a given board, and
 * doing the respective calculations (see readme) depending on if the board
 * was even or odd dimensional.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Board {


    // board in 2d array
    private final int[][] board;

    // hamming variable
    private final int wrongTwo;

    // manhattan distance variable
    private final int manhattDistance;

    // length of grid
    private final int length;

    // row in which the 0/blank is contained
    private int rowBlank;

    // col in which the 0/blank is contained
    private int colBlank;


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        length = tiles.length;
        board = new int[length][length];
        int positionOne = 1;
        int wrong = 0;
        // initializes board to be the tiles array
        // also computes hamming while copying the elements over
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                board[i][j] = tiles[i][j];
                if (board[i][j] == 0) {
                    rowBlank = i;
                    colBlank = j;
                }
                if (board[i][j] != positionOne && board[i][j] != 0) {
                    wrong++;
                }
                positionOne++;
            }
        }
        wrongTwo = wrong;


        int positionTwo = 1;
        int manhattDistanceTwo = 0;

        // checks the manhattan distance of each element and adds them to
        // variable manhattDistance
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (board[i][j] == positionTwo || board[i][j] == 0) {
                    positionTwo++;
                    continue;
                }
                int correctRow = (board[i][j] - 1) / length;
                int correctCol = (board[i][j] - 1) % length;
                int xDis = Math.abs(correctRow - i);
                int yDis = Math.abs(correctCol - j);
                positionTwo++;
                manhattDistanceTwo += (xDis + yDis);
            }
        }
        manhattDistance = manhattDistanceTwo;
    }

    // string representation of this board
    public String toString() {
        StringBuilder s1 = new StringBuilder();
        s1.append(length);
        s1.append("\n");
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                s1.append(String.format("%2d ", board[i][j]));

            }
            s1.append("\n");
        }
        return s1.toString();
    }

    // tile at (row, col) or 0 if blank
    public int tileAt(int row, int col) {
        if (row < 0 || row > length - 1) {
            throw new IllegalArgumentException("Invalid Row or Column");
        }
        else if (col < 0 || col > length - 1) {
            throw new IllegalArgumentException("Invalid Row or Column");
        }
        return board[row][col];
    }


    // board size n
    public int size() {
        return length;
    }

    // number of tiles out of place/in wrong position
    public int hamming() {
        return wrongTwo;
    }

    // sum of Manhattan distances between tiles and goal/vertical + horizontal
    // distances
    public int manhattan() {
        return manhattDistance;
    }

    // is this board the goal board?
    public boolean isGoal() {
        if (wrongTwo + manhattDistance == 0) {
            return true;
        }
        return false;
    }

    /* @citation half copied and half
    adapted from: https://algs4.cs.princeton.edu/12oop/Date.java.html
    Accessed 03/01/2020.
    */

    // does this board equal y?
    public boolean equals(Object y) {

        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) {
            return false;
        }
        Board that = (Board) y;
        if (this.size() != that.size()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (this.board[i][j] != that.board[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }


    // all neighboring boards
    public Iterable<Board> neighbors() {

        Stack<Board> stack = new Stack<Board>();

        // the comments within the first if statement essentially apply to
        // the other ones; same logic used
        // switch with above
        if (rowBlank != 0) {
            // creates a copy of the board, and saves the value of the blank
            int[][] newArray = copy(board);
            int blank = newArray[rowBlank][colBlank];
            int oldVal = newArray[rowBlank - 1][colBlank];
            newArray[rowBlank - 1][colBlank] = blank;
            newArray[rowBlank][colBlank] = oldVal;
            Board neighbor = new Board(newArray);
            stack.push(neighbor);
        }

        // switch with below
        if (rowBlank != length - 1) {
            int[][] newArray = copy(board);
            int blank = newArray[rowBlank][colBlank];
            int oldVal = newArray[rowBlank + 1][colBlank];
            newArray[rowBlank + 1][colBlank] = blank;
            newArray[rowBlank][colBlank] = oldVal;
            Board neighbor = new Board(newArray);
            stack.push(neighbor);
        }

        // switch with left
        if (colBlank != 0) {
            int[][] newArray = copy(board);
            int blank = newArray[rowBlank][colBlank];
            int oldVal = newArray[rowBlank][colBlank - 1];
            newArray[rowBlank][colBlank - 1] = blank;
            newArray[rowBlank][colBlank] = oldVal;
            Board neighbor = new Board(newArray);
            stack.push(neighbor);
        }

        // switch with right
        if (colBlank != length - 1) {
            int[][] newArray = copy(board);
            int blank = newArray[rowBlank][colBlank];
            int oldVal = newArray[rowBlank][colBlank + 1];
            newArray[rowBlank][colBlank + 1] = blank;
            newArray[rowBlank][colBlank] = oldVal;
            Board neighbor = new Board(newArray);
            stack.push(neighbor);
        }

        return stack;
    }

    // helper method to copy board array
    private int[][] copy(int[][] a1) {

        int[][] copied = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                copied[i][j] = a1[i][j];
            }
        }
        return copied;
    }


    // is this board solvable?
    public boolean isSolvable() {

        int inversions = 0;
        int counter = 0;
        int[] oneD = new int[length * length];
        // converts the board to a oneD array to help with inversion calculation
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                oneD[counter] = board[i][j];
                counter++;
            }
        }
        // calculates the number of inversions
        for (int i = 0; i < oneD.length; i++) {
            for (int j = i; j < oneD.length; j++) {
                if (oneD[i] == 0 || oneD[j] == 0) {
                    continue;
                }
                if (oneD[i] > oneD[j]) {
                    inversions++;
                }
            }
        }


        // if the board is even dimensional
        if (length % 2 == 0) {
            if ((inversions + rowBlank) % 2 != 0) {
                return true;
            }
        }

        // if the board is off dimensional
        if (length % 2 != 0) {
            if (inversions % 2 == 0) {
                return true;
            }
        }
        return false;
    }

    // unit testing (required)
    public static void main(String[] args) {

        // unsolvable test
        int[][] test = new int[2][2];
        test[0][0] = 1;
        test[0][1] = 0;
        test[1][0] = 2;
        test[1][1] = 3;

        int[][] testTwo = new int[3][3];
        testTwo[0][0] = 1;
        testTwo[0][1] = 0;
        testTwo[1][0] = 2;
        testTwo[1][1] = 3;
        testTwo[2][2] = 3;
        testTwo[1][2] = 3;
        testTwo[2][1] = 3;


        // creates test boards and calls the methods in the class
        Board b1 = new Board(test);
        Board b2 = new Board(testTwo);
        StdOut.println("b1 is solvable: " + b1.isSolvable());
        StdOut.println("b1 hamming: " + b1.hamming());
        StdOut.println("b1 manhattan distance: " + b1.manhattan());
        StdOut.println("b1 size: " + b1.size());
        StdOut.println("is b1 the goal: " + b1.isGoal());
        StdOut.println("tile at 1, 1: " + b1.tileAt(1, 1));
        StdOut.println("neighbors are: ");
        StdOut.println(b1.neighbors().toString());
        StdOut.println("is b1 equal to b2: " + b1.equals(b2));
    }
}
