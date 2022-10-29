package de.htwg.se.Chess
/** Game State
  *    0    1    2    3    4    5    6    7
  * /----+----+----+----+----+----+----+----\
  * | T1 | S1 | L1 | D1 | k1 | L1 | S1 | T1 | 0
  * |----+----+----+----+----+----+----+----|
  * | B1 | B1 | B1 | B1 | B1 | B1 | B1 | B1 | 1
  * |----+----+----+----+----+----+----+----|
  * |    |    |    |    |    |    |    |    | 2
  * |----+----+----+----+----+----+----+----|
  * |    |    |    |    |    |    |    |    | 3
  * |----+----+----+----+----+----+----+----|
  * |    |    |    |    |    |    |    |    | 4
  * |----+----+----+----+----+----+----+----|
  * |    |    |    |    |    |    |    |    | 5
  * |----+----+----+----+----+----+----+----|
  * | B2 | B2 | B2 | B2 | B2 | B2 | B2 | B2 | 6
  * |----+----+----+----+----+----+----+----|
  * | T2 | S2 | L2 | D2 | K2 | L2 | S2 | T2 | 7
  * \----+----+----+----+----+----+----+----/
  *
  */
class Game {
    val state = Array.ofDim[String](8, 8) // create a array of Dim 8x8
    for (i <- 0 to 7; j <- 0 to 7)
        state(i)(j) = "  "

    def newGame() : Array[Array[String]] =
        // Initialize New Game
        // Player One
        state(0)(0) = "T1"
        state(1)(0) = "S1"
        state(2)(0) = "L1"
        state(3)(0) = "D1"
        state(4)(0) = "K1"
        state(5)(0) = "L1"
        state(6)(0) = "S1"
        state(7)(0) = "T1"

        state(0)(1) = "B1"
        state(1)(1) = "B1"
        state(2)(1) = "B1"
        state(3)(1) = "B1"
        state(4)(1) = "B1"
        state(5)(1) = "B1"
        state(6)(1) = "B1"
        state(7)(1) = "B1"

        // Player Two
        state(0)(7) = "T2"
        state(1)(7) = "S2"
        state(2)(7) = "L2"
        state(3)(7) = "D2"
        state(4)(7) = "K2"
        state(5)(7) = "L2"
        state(6)(7) = "S2"
        state(7)(7) = "T2"

        state(0)(6) = "B2"
        state(1)(6) = "B2"
        state(2)(6) = "B2"
        state(3)(6) = "B2"
        state(4)(6) = "B2"
        state(5)(6) = "B2"
        state(6)(6) = "B2"
        state(7)(6) = "B1"

        return state

    def move(x_position_old: Int, y_position_old: Int, x_position_new: Int, y_position_new: Int) =
        val figure = this.state(x_position_old)(y_position_old)
        println(figure)

        state(x_position_old)(y_position_old) = "  ";
        state(x_position_new)(y_position_new) = figure


    /** Field
     * Playfield
     *  8x8 Grid
     * /---+---+---+---+---+---+---+---\
     * |###|###|###|###|###|###|###|###|
     * |---+---+---+---+---+---+---+---|
     * |###|###|###|###|###|###|###|###|
     * |---+---+---+---+---+---+---+---|
     * |   |   |   |   |   |   |   |   |
     * |---+---+---+---+---+---+---+---|
     * |   |   |   |   |   |   |   |   |
     * |---+---+---+---+---+---+---+---|
     * |   |   |   |   |   |   |   |   |
     * |---+---+---+---+---+---+---+---|
     * |   |   |   |   |   |   |   |   |
     * |---+---+---+---+---+---+---+---|
     * |###|###|###|###|###|###|###|###|
     * |---+---+---+---+---+---+---+---|
     * |###|###|###|###|###|###|###|###|
     * \---+---+---+---+---+---+---+---/
     * 1x1 Grid
     * /---\
     * |   |
     * \---/
     * 2x2 Grid
     * /---+---\
     * |   |   |
     * |---+---|
     * |   |   |
     * \---+---/
    */
    val eol = sys.props("line.separator")

    def first_last_row(x_size:Int = 8, first:String = "/", last:String = "\\") = first + ("-" * 4 + "+") * (x_size - 1) + ("-" * 4) + last

    def border_row(x_size:Int = 8) = ("+" + "-" * 4) * x_size + "+"

    def cell_row(x_size:Int = 8, row_array: Array[Array[String]], row:Int): String =
        val str1 = " |"
        val str2 = " | "
        var str3 = "| " // TODO: var should be val but how can this be solved ?
        for (i <- 0 to (x_size - 1))
            str3 = str3.concat(row_array(i)(row))
            if (i != (x_size - 1))
                str3 = str3.concat(str2)
            else
                str3 = str3.concat(str1)
        return str3


    def field(x_size:Int = 8, y_size:Int = 8): String =
        var tmp = cell_row(x_size, state, 0).concat(eol)
        tmp = tmp.concat(border_row(x_size)).concat(eol)

        for (i <- 1 to (x_size - 1))
            tmp = tmp.concat(cell_row(x_size, state, i)).concat(eol)
            if (i != (x_size - 1))
                tmp = tmp.concat(border_row(x_size)).concat(eol)

        return first_last_row(x_size).concat(eol) + tmp + first_last_row(x_size, "\\", "/")

}

