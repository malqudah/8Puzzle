/* *****************************************************************************
 *  Name: Mohammad Alqudah
 *  NetID: malqudah
 *  Precept: P05
 *
 *  Hours to complete assignment (optional):
 *  6
 **************************************************************************** */

Programming Assignment 4: Slider Puzzle


/* *****************************************************************************
 *  Explain briefly how you represented the Board data type.
 **************************************************************************** */
the board data type was primarily modeled by a n x n 2d array; this was
initialized in the constructor through an n x n array of tiles. i also kept
track of the hamming and manhattan values in the contructor; the hamming
value was kept track of while initializing the board, incrementing the variable
wrong for every value that was not in place. for the manhattan distance, it was
calculated by finding the correct row and the correct column, then subtracting
the current x index and y index from the correct row and col respectively and
taking the abs value;
the correct row and col were calculated by taking first the value at the given
index divided by the length of the array, and the col was calculated by taking
the remainder of this. for the board to be the goal board, the sum of the
manhattan distance and the hamming should be 0, which was checked for in the
isGoal method. to check if boards were equal, i adapted the code from date.java
and applied it accordingly; in addition, used a double for loop to check if
the elements in the two boards were exactly equal. for the neighbors method,
i conducted a series of checks to narrow down whether the board would have 2,3
or 4 neighbors. when this was found, move the blank space up down left or right
respectively, and then add this to a stack. to check if the board was solveable
i used the method that calculated inversions and applied to corresponding
calculations for whether the board was odd dimensional or even dimensional.



/* *****************************************************************************
 *  Explain briefly how you represented a search node
 *  (board + number of moves + previous search node).
 **************************************************************************** */

for the search node nested class, each search node is comprised of a
board, the number of moves needed to reach that board from the initial board,
and a pointer to the board that comes before it. all of these are taken in
 in the constructor, and they are initialized in it. it is in this method where
we compare priorities of different boards using the compare to method, since
our search node nested class implements  comparable search nodes. the compareto
method compares the manhattan priorities of the two boards in question, and the
one with higher priority is selected.





/* *****************************************************************************
 *  Explain briefly how you detected unsolvable puzzles.
 *
 *  What is the order of growth of the running time of your isSolvable()
 *  method in the worst case as function of the board size n? Use big Theta
 *  notation to simplify your answer, e.g., Theta(n log n) or Theta(n^3).
 **************************************************************************** */

Description:
the unsolvable calculation was split between whether the board was even or odd;
regardless, the number of inversions needed to be calculated; essentially when
a number appears before a number that is smaller than it. to do so, i copied
everything into a one dimensional array, and checked for if any elements
after the given element were smaller than it; if so increment the number of
inversions. if the board was even dimensional, it is solveable if and only if
the number of inversions plus the row of the blank(kept track of in constructor)
was odd; else not solvable.
for an odd dimensional board, it is only solvable if the number of inversions
was even; else not solvable.



Order of growth of running time: Theta(n ^ 2)



/* *****************************************************************************
 *  For each of the following instances, give the minimum number of moves to
 *  solve the instance (as reported by your program). Also, give the amount
 *  of time your program takes with both the Hamming and Manhattan priority
 *  functions. If your program can't solve the instance in a reasonable
 *  amount of time (say, 5 minutes) or memory, indicate that instead. Note
 *  that your program may be able to solve puzzle[xx].txt even if it can't
 *  solve puzzle[yy].txt and xx > yy.
 **************************************************************************** */

****** for all of the instances, i gave java 6 gigabytes of memory(of 8) through
java-algs4 -Xmx6g PuzzleChecker ******

                 min number          seconds
     instance     of moves     Hamming     Manhattan
   ------------  ----------   ----------   ----------
   puzzle28.txt     28         0.86             0.03
   puzzle30.txt     30         1.75             0.05
   puzzle32.txt     32    more than 14 minutes  0.89
   puzzle34.txt     34    more than 5 minutes   0.24
   puzzle36.txt     36    more than 5 minutes   1.67
   puzzle38.txt     38    more than 5 minutes   2.05
   puzzle40.txt     40    more than 9 minutes   0.80
   puzzle42.txt     42    more than 5 minutes   3.64



/* *****************************************************************************
 *  If you wanted to solve random 4-by-4 or 5-by-5 puzzles, which
 *  would you prefer: a faster computer (say, 2x as fast), more memory
 *  (say 2x as much), a better priority queue (say, 2x as fast),
 *  or a better priority function (say, one on the order of improvement
 *  from Hamming to Manhattan)? Why?
 **************************************************************************** */

    side note: the timing tests were done on a laptop with 8 gb of ddr3
    memory at 2133mhz, and a four core processor. i attempted to run the tests
    on my windows pc with 16 gb of ddr4 3200mhz memory and a 6 core processor,
    but intellij kept giving me a terminal issue and wouldn't let me run my
    programs there.

    it was VERY clear from the puzzle timing tests that the manhattan priority
    function is radically faster than using the hammig priority function,
    with one instance taking 0.89 seconds with manhattan yet more than 14 min
    with hamming. in addition, allocating more memory when using the manhattan
    priority yielded basically no increase. Because of this, the most reasonable
    choice seems to be a better priority function; when using a worse priority
    function, the program takes much longer. having a better priority queue
    would still rely on the manhattan or hamming priority calculation,
    so even if the prioirity queue was significantly better it would still be
    hindered by the hamming priority. Similarly, more memory and/or faster
    memory wouldn't solve the longer time hamming priority
     takes relative to manhattan. when it comes down to it, it seems that the
     flaw in the design is using the hamming priority instead of the manhattan;
     therefore, developing a better priority function would yield the most
     beneficial results.

    a more efficient algorithm is better than a faster computer
    - more or less paraphrased from what was said in one of the lectures.





/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */



/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */
    prof ibrahim office hours
    lisa office hours
    ryan lab TAs




/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */



/* *****************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */







/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
