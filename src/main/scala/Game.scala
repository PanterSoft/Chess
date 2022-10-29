package de.htwg.se.Chess

import scala.compiletime.ops.int

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
        state(1)(0) = "J1"
        state(2)(0) = "R1"
        state(3)(0) = "Q1"
        state(4)(0) = "K1"
        state(5)(0) = "R1"
        state(6)(0) = "J1"
        state(7)(0) = "T1"

        state(0)(1) = "F1"
        state(1)(1) = "F1"
        state(2)(1) = "F1"
        state(3)(1) = "F1"
        state(4)(1) = "F1"
        state(5)(1) = "F1"
        state(6)(1) = "F1"
        state(7)(1) = "F1"

        // Player Two
        state(0)(7) = "T2"
        state(1)(7) = "J2"
        state(2)(7) = "R2"
        state(3)(7) = "Q2"
        state(4)(7) = "K2"
        state(5)(7) = "R2"
        state(6)(7) = "J2"
        state(7)(7) = "T2"

        state(0)(6) = "F2"
        state(1)(6) = "F2"
        state(2)(6) = "F2"
        state(3)(6) = "F2"
        state(4)(6) = "F2"
        state(5)(6) = "F2"
        state(6)(6) = "F2"
        state(7)(6) = "F1"

        return state

    def move(x_position_old: Int, y_position_old: Int, x_position_new: Int, y_position_new: Int): Boolean =
        val figure = this.state(x_position_old)(y_position_old)

        if (check_move(x_position_old, y_position_old, x_position_new, y_position_new))
            state(x_position_old)(y_position_old) = "  ";
            state(x_position_new)(y_position_new) = figure
            return true
        else
            println("Invalid Move")
            return false

    /** Allowed Moves:
     * K: 1 field in x or y
     * Q: X field in x or y or xy
     * R: X fields in xy
     * J: 3,1 fields in xy (like L pattern)
     * T: X fields in x or y
     * F: 2 fields in x when in Base pos. else only 1 in x
     *
     * Allowed Attack Moves
     * K: same as above
     * Q: same as above
     * R: same as above
     * J: same as above
     * T: same as above
     * F: 1 Field in xy but only forward
    */

    def check_move(x_position_now: Int, y_position_now: Int, x_position_new: Int, y_position_new: Int): Boolean =

        val figure = this.state(x_position_now)(y_position_now)
        val figure_split = figure.splitAt(1)
        val figure_type = figure_split(0)

        val x_diff = Math.abs(x_position_now - x_position_new)
        val y_diff = Math.abs(y_position_now - y_position_new)

        if (allowed_field(x_position_now, y_position_now, x_position_new, y_position_new) == false)
            return false
        // Check if Move from Figure is allowed on an empty board
        figure_type match
            case "K" => if (x_diff == 0 && y_diff == 1 || x_diff == 1 && y_diff == 0) return true// King
            case "Q" => if (x_diff == y_diff || x_diff == 0 && y_diff > 1 || x_diff > 1 && y_diff == 0) return true // Queen
            case "R" => if (x_diff == y_diff && x_diff >= 1) return true // Runner
            case "J" => if (x_diff == 3 && y_diff == 1 || x_diff == 1 && y_diff == 3) return true // Jumper
            case "T" => if (x_diff == 0 && y_diff >= 1 || x_diff >= 1 && y_diff == 0) return true // Tower
            case "F" => if(Bauer(x_position_now, y_position_now, x_position_new, y_position_new)) return true // Farmer
        return false

    def get_player(x_position: Int, y_position: Int): String =
        return this.state(x_position)(y_position).splitAt(1)(1)

    def different_player(x_position_now: Int, y_position_now: Int, x_position_new: Int, y_position_new: Int): Boolean =

        if(get_player(x_position_new, y_position_new) != get_player(x_position_now, y_position_now) && get_player(x_position_new, y_position_new) != "  ")
            return true
        return false

    def allowed_field(x_position_now: Int, y_position_now: Int, x_position_new: Int, y_position_new: Int): Boolean =
        if (get_player(x_position_now, y_position_now) != get_player(x_position_new, y_position_new))
            return true
        return false

    def Bauer(x_position_now: Int, y_position_now: Int, x_position_new: Int, y_position_new: Int): Boolean =

        val x_diff = Math.abs(x_position_now - x_position_new)
        val y_diff = Math.abs(y_position_now - y_position_new)

        if (x_diff == 0 && y_diff == 1 && get_player(x_position_now, y_position_now) == "1" && y_position_now < y_position_new || x_diff == 0 && y_diff == 1 && get_player(x_position_now, y_position_now) == "2" && y_position_now > y_position_new) // Normal Move (1 Field)
            return true
        else if (x_diff == 0 && y_diff == 2 && get_player(x_position_now, y_position_now) == "1" && y_position_now < y_position_new && y_position_now == 1 || x_diff == 0 && y_diff == 2 && get_player(x_position_now, y_position_now) == "2" && y_position_now > y_position_new && y_position_now == 6) // First Move (2 Fields)
            return true
        else if (x_diff == 1 && y_diff == 1 && different_player(x_position_now, y_position_now, x_position_new, y_position_new) && get_player(x_position_now, y_position_now) == "1" && y_position_now < y_position_new || x_diff == 1 && y_diff == 1 && different_player(x_position_now, y_position_now, x_position_new, y_position_new) && get_player(x_position_now, y_position_now) == "2" && y_position_now > y_position_new ) // Attack Move
            return true
        else
            return false


    /** Playboard
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

    def numbering_row(x_size: Int, y_size: Int): String =
        var str = " "
        for (i <- 0 to (x_size - 1))
            str = str + "  " + i
            if (i != (x_size - 1))
                str = str + "  "
        return str


    def board(x_size:Int = 8, y_size:Int = 8): String =
        var tmp = cell_row(x_size, state, 0) + " 0" + eol
        tmp = tmp + border_row(x_size) + eol

        for (i <- 1 to (x_size - 1))
            tmp = tmp + cell_row(x_size, state, i) + " "+ i.toString() + eol
            if (i != (x_size - 1))
                tmp = tmp + border_row(x_size) + eol

        return numbering_row(x_size, y_size) + eol + first_last_row(x_size) + eol + tmp + first_last_row(x_size, "\\", "/")

}

