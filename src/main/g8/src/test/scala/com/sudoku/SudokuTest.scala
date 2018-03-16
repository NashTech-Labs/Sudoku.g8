package com.sudoku

class SudokuTest extends FunSuite {

  val obj = new Sudoku

  val grid = Vector(Vector(0, 0, 4, 8, 0, 0, 0, 1, 7), Vector(6, 7, 0, 9, 0, 0, 0, 0, 0), Vector(5, 0, 8, 0, 3, 0, 0, 0, 4),
    Vector(3, 0, 0, 7, 4, 0, 1, 0, 0), Vector(0, 6, 9, 0, 0, 0, 7, 8, 0), Vector(0, 0, 1, 0, 6, 9, 0, 0, 5),
    Vector(1, 0, 0, 0, 8, 0, 3, 0, 6), Vector(0, 0, 0, 0, 0, 6, 0, 9, 1), Vector(2, 4, 0, 0, 0, 1, 5, 0, 0))


  val vall = obj.sudokuSolver(grid)
  test("Checking for Sudoko") {
    assert(vall.==(true))
  }

  val grid1 = Vector(Vector(0, 0, 4, 8, 0, 0, 0, 1, 7), Vector(6, 7, 0, 9, 0, 0, 0, 0, 0), Vector(5, 0, 8, 0, 3, 0, 0, 0, 4),
    Vector(3, 0, 0, 7, 4, 0, 1, 0, 0), Vector(0, 6, 9, 0, 0, 0, 7, 8, 0), Vector(0, 0, 1, 0, 6, 9, 0, 0, 5),
    Vector(1, 0, 0, 0, 8, 0, 3, 0, 6), Vector(0, 0, 0, 0, 0, 6, 0, 9, 1), Vector(2, 3, 0, 0, 0, 1, 6, 0, 0))

  val vall1 = obj.sudokuSolver(grid1)
  test("Checking for Sudoko test 2") {
    assert(vall1.==(false))
  }

}
