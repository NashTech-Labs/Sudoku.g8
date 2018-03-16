package com.sudoku

class Sudoku {
  /** Takes a partially filled-in grid and attempts to assign
    * values to all unassigned locations in such a way to meet
    * the requirements for Sudoku solution (non-duplication
    * across rows, columns, and boxes) */
  def sudokuSolver(grid: Vector[Vector[Int]]): Boolean = {

    def solver(grid: Vector[Vector[Int]], row: Int, col: Int, prevVal: Int = 0): Boolean = {
      if (row == 9) {
        println(grid.map(_.mkString(" ")).mkString("\n"))
        true
      }
      else if (col == 9) {
        solver(grid, row + 1, 0)
      }
      else {
        val (row, col) = findUnassignedLocation(grid)
        if (row == -1 && col == -1) {
          true
        }
        else {
          val num = whatToAssign(grid, row, col, prevVal)
          if (num == -1) {
            false
          } else {
            val updatedGrid = grid.updated(row, grid(row).updated(col, num))
            if (solver(updatedGrid, row, col + 1)) {
              true
            } else {
              solver(grid, row, col, updatedGrid(row)(col))
            }
          }
        }
      }
    }

    solver(grid, 0, 0)
  }

  private def whatToAssign(grid: Vector[Vector[Int]], row: Int, col: Int, prevVal: Int): Int = {

    val num = List.range(prevVal + 1, 10)

    def assign(grid: Vector[Vector[Int]], num: List[Int], row: Int, col: Int): Int = {
      if (num.isEmpty)
        -1
      else if (isSafeToAssign(grid, row, col, num.head))
        num.head
      else
        assign(grid, num.tail, row, col)
    }

    assign(grid, num, row, col)
  }

  /** Searches the grid to find an entry that is still unassigned. If
    * found, the reference parameters row, col will be set the location
    * that is unassigned, and true is returned. If no unassigned entries
    * remain, false is returned. */
  private def findUnassignedLocation(grid: Vector[Vector[Int]]): (Int, Int) = {

    def find(grid: Vector[Vector[Int]], row: Int, col: Int): (Int, Int) = {
      if (grid(row)(col) == 0)
        (row, col)
      else {
        if (row == 8 && col == 8) (-1, -1) else if (col == 8) find(grid, row + 1, 0) else find(grid, row, col + 1)
      }
    }

    find(grid, 0, 0)
  }

  /** Returns a boolean which indicates whether it will be legal to assign
    * num to the given row,col location
    */
  private def isSafeToAssign(grid: Vector[Vector[Int]], row: Int, col: Int, num: Int): Boolean = {
    if (usedInRow(grid, row, num) || usedInCol(grid, col, num) || usedInBox(grid, row - row % 3, col - col % 3, num))
      false
    else true
  }

  /** Returns a boolean which indicates whether any assigned entry
    * in the specified row matches the given number.
    */
  private def usedInRow(grid: Vector[Vector[Int]], row: Int, num: Int): Boolean = if (grid(row).contains(num)) true else false

  /** Returns a boolean which indicates whether any assigned entry
    * in the specified column matches the given number.
    */
  private def usedInCol(grid: Vector[Vector[Int]], col: Int, num: Int): Boolean = {
    val colVector = grid.map(_ (col))
    if (colVector.contains(num)) true else false
  }

  /** Returns a boolean which indicates whether any assigned entry
    * within the specified 3x3 box matches the given number. */
  private def usedInBox(grid: Vector[Vector[Int]], row: Int, col: Int, num: Int): Boolean = {

    def conflict(grid: Vector[Vector[Int]], row: Int, col: Int, num: Int, i: Int, j: Int): Boolean = {
      if (j == 3) {
        false
      } else if (i == 3) {
        conflict(grid, row, col, num, 0, j + 1)
      } else {
        if (grid(row + i)(col + j) == num) {
          true
        } else {
          conflict(grid, row, col, num, i + 1, j)
        }
      }
    }

    conflict(grid, row, col, num, 0, 0)
  }

}
