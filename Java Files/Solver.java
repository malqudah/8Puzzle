/* *****************************************************************************
 *  Name:    Mohammad Alqudah
 *  NetID:   malqudah
 *  Precept: P05
 *
 *  Description: Solves an n x n puzzle created from the Board.java class.
 * uses a priority queue to keep track of the original board in the form
 * of search nodes; each search node (as a nested class) contains the number
 * of mmoves to reach that point, a board, and the previous board. determines
 * which neighbor of the parent board is added via manhattan priority, and adds
 * these search nodes to a stack where the goal board is the first one added
 * and the intiial board is the final. contains iterable method to iterate
 * through the stack and trace the steps from the initial board to goal
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Solver {


    // instancevariable for the stack
    // instead of saving the minpq as an instance variable to access in
    // the iterable method, save the stack as an instance variable
    // to access in the solver constructor and return it in the iterable method
    private final Stack<Board> stack;

    // instance variable for the move count to the solution
    private final int moveCtTwo;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("initial is null");
        }
        if (!initial.isSolvable()) {
            throw new IllegalArgumentException("Board not solveable");
        }


        // min priority queue to compare search nodes
        MinPQ<SearchNode> pqOne = new MinPQ<SearchNode>();

        // steps for A* search
        // inserts the intiial board, movect and null (bc no prev) into search
        // node
        pqOne.insert(new SearchNode(initial, 0, null));

        // while the min of the pq is not the goal, find the neighbors of min
        // and add them to the priority queue, checking if the grandfather node
        // is the same as a neighbor
        while (!pqOne.min().board.isGoal()) {

            SearchNode minNode = pqOne.min();
            pqOne.delMin();

            // iterates through the neighbors of the minNode board
            for (Board neighbors : minNode.board.neighbors()) {
                if (minNode.prev == null) {
                    pqOne.insert(new SearchNode(neighbors,
                                                minNode.moveCt + 1,
                                                minNode));
                }
                else if (!neighbors.equals(minNode.prev.board)) {
                    pqOne.insert(new SearchNode(neighbors,
                                                minNode.moveCt + 1,
                                                minNode));
                }
            }
        }


        // creates a stack of boards, sets the current search node to the
        // min of the priority queue
        stack = new Stack<Board>();

        SearchNode current = pqOne.min();
        moveCtTwo = current.moveCt;

        // while the prev search node is not null (ie traces from bottom to top)
        // adds the current board to the stack and goes to the prev one
        while (current.prev != null) {
            stack.push(current.board);
            current = current.prev;
        }
        stack.push(initial);
    }

    // a search node is comprised of a board, move count, and a prev node
    // implements comparable to compare pririoties between nodes
    private class SearchNode implements Comparable<SearchNode> {


        // instance variable for the board within a node
        private final Board board;

        // instance variable for a pointer node to prev search node/board
        private final SearchNode prev;

        // used to compare priorities
        private final int manhattPriority;

        // move count of each search node
        private final int moveCt;


        // constructor for SearchNode nested class, initializes the board
        // and moves and prev searchnode
        public SearchNode(Board b, int moves, SearchNode prev) {
            board = b;
            this.prev = prev;
            this.moveCt = moves;
            manhattPriority = moveCt + board.hamming();
        }

        // compares the nodes using the manhattan priorities
        public int compareTo(SearchNode that) {
            return this.manhattPriority - that.manhattPriority;
        }
    }

    // min number of moves to solve initial board
    public int moves() {
        return moveCtTwo;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        return stack;
    }

    // test client (see below)
    public static void main(String[] args) {

        // converts the puzzle from standard in to a n x n array to be used
        // as a board
        int ok = StdIn.readInt();
        int[][] pce;
        pce = new int[ok][ok];
        while (!StdIn.isEmpty()) {
            for (int i = 0; i < ok; i++) {
                for (int j = 0; j < ok; j++) {
                    pce[i][j] = StdIn.readInt();
                }
            }
        }


        // creates board ex from stdin puzzle board
        Board ex = new Board(pce);
        // creates solver from board ex
        Solver exOne = new Solver(ex);

        // for every board in exOne up until the solution, print it
        for (Board b : exOne.solution()) {
            StdOut.println(b);
        }

        // print the final move count
        StdOut.println("Move count: " + exOne.moves());
    }
}
