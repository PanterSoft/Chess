package de.htwg.se.Chess.model

import scala.collection.immutable.VectorMap

import com.google.inject.Inject

 /** empty game board
     *  8x8 Grid
     * /----+----+----+----+----+----+----+----\
     * | A1 | B1 | C1 | D1 | E1 | F1 | G1 | H1 |
     * |----+----+----+----+----+----+----+----|
     * | A2 | B2 | C2 | D2 | E2 | F2 | G2 | H2 |
     * |----+----+----+----+----+----+----+----|
     * | A3 | B3 | C3 | D3 | E3 | F3 | G3 | H3 |
     * |----+----+----+----+----+----+----+----|
     * | A4 | B4 | C4 | D4 | E4 | F4 | G4 | H4 |
     * |----+----+----+----+----+----+----+----|
     * | A5 | B5 | C5 | D5 | E5 | F5 | G5 | H5 |
     * |----+----+----+----+----+----+----+----|
     * | A6 | B6 | C6 | D6 | E6 | F6 | G6 | H6 |
     * |----+----+----+----+----+----+----+----|
     * | A7 | B7 | C7 | D7 | E7 | F7 | G7 | H7 |
     * |----+----+----+----+----+----+----+----|
     * | A8 | B8 | C8 | D8 | E8 | F8 | G8 | H8 |
     * \----+----+----+----+----+----+----+----/
*/



case class Board @Inject() (var board: VectorMap[String, String]) extends BoardInterface {
    override def move(pos_now: String, pos_new: String): Board =
        val figure = board.get(pos_now)

        if (match_pattern(figure) != "Invalid" && check_move(board, pos_now, pos_new))
            Board(Board(board.updated(pos_now, "  ")).board.updated(pos_new, match_pattern(figure)))
        else
            Board(board)


    def match_pattern(option: Option[String]) = option match {
        case Some(s) => (s)
        case None => ("Invalid")
    }

    def match_pattern(option: Option[Int]) = option match {
        case Some(s) => (s)
        case None => (0)
    }

    override def game_finished(game_map: VectorMap[String, String]): Int =
        if (!game_map.values.exists(_ == "K2"))
            return 1
        else if (!game_map.values.exists(_ == "K1"))
            return 2
        else
            return 0

    def check_move(board: VectorMap[String, String], pos_now: String, pos_new: String): Boolean =

        if (empty_field(pos_now)) // Check if Position now is empty
            return false

        // Check if Move from Figure is allowed on an empty board
        get_figure_type(pos_now) match
            case "K" => if (king(pos_now, pos_new)) return true// King
            case "Q" => if (queen(pos_now, pos_new)) return true // Queen
            case "B" => if (bishop(pos_now, pos_new)) return true // Bishop
            case "k" => if (knight(pos_now, pos_new)) return true // Knight
            case "R" => if (rook(pos_now, pos_new)) return true // Rook
            case "P" => if (pawn(pos_now, pos_new)) return true // Pawn

        return false

    def get_player(pos: String): String =
        val pos_tmp = match_pattern(board.get(pos))

        if (pos_tmp != "Invalid")
            return pos_tmp.splitAt(1)(1)

        return "Invalid"

    def get_figure_type(pos: String): String =
        val figure = match_pattern(board.get(pos))
        return figure.splitAt(1)(0) // Figure or Invalid

    def different_player(pos_one: String, pos_two: String): Boolean =
        if(get_player(pos_one) != get_player(pos_two) && get_player(pos_one) != "Invalid" && get_player(pos_two) != "Invalid")
            return true
        return false

    def empty_field(pos: String): Boolean =
        val pos_tmp = match_pattern(board.get(pos))
        if ( pos_tmp != "Invalid" && pos_tmp == "  ")
            return true
        return false

    def forward_move(pos_now: String, pos_new: String): Boolean =
        val y_now = pos_now.splitAt(1)(1)
        val y_new = pos_new.splitAt(1)(1)

        if (get_player(pos_now) == "1" && y_now < y_new || get_player(pos_now) == "2" && y_now > y_new)
            return true

        return false

    def x_diff(pos_now: String, pos_new: String): Int =
        val x_map = Map("A"->1, "B"->2, "C"->3, "D"->4, "E"->5, "F"->6, "G"->7, "H"->8)

        val x_now = match_pattern(x_map.get(pos_now.splitAt(1)(0)))
        val x_new = match_pattern(x_map.get(pos_new.splitAt(1)(0)))

        return Math.abs(x_now - x_new)

    def y_diff(pos_now: String, pos_new: String): Int =
        val y_now = pos_now.splitAt(1)(1).toInt
        val y_new = pos_new.splitAt(1)(1).toInt

        return Math.abs(y_now - y_new)

    def xy_equal(pos_now: String, pos_new: String): Boolean =
        if (x_diff(pos_now, pos_new) != y_diff(pos_now, pos_new))
            return false
        return true

    def x_or_y(pos_now: String, pos_new: String): Boolean =
        if (x_diff(pos_now, pos_new) == 0 && y_diff(pos_now, pos_new) >= 0 || x_diff(pos_now, pos_new) >= 0 && y_diff(pos_now, pos_new) == 0)
            return true
        return false

    def x_y_maxlength(pos_now: String, pos_new: String): Int =
        if (x_diff(pos_now, pos_new) > y_diff(pos_now, pos_new))
            return x_diff(pos_now, pos_new)
        return y_diff(pos_now, pos_new)


    def check_x(pos_now: String, x: String): Boolean =
        if (pos_now.splitAt(1)(0) == x)
            true
        else
            false

    def king(pos_now: String, pos_new: String): Boolean =
        if (different_player(pos_now, pos_new) && xy_equal(pos_now, pos_new) == false && x_y_maxlength(pos_now, pos_new) == 1)
            return true
        return false

    def queen(pos_now: String, pos_new: String): Boolean =

        if (different_player(pos_now, pos_new) && xy_equal(pos_now, pos_new) || x_or_y(pos_now, pos_new))
            return true
        return false

    def bishop(pos_now: String, pos_new: String): Boolean =

        if(different_player(pos_now, pos_new) && xy_equal(pos_now, pos_new))
            return true
        return false

    def knight(pos_now: String, pos_new: String): Boolean =
        if(different_player(pos_now, pos_new) && (x_diff(pos_now, pos_new) == 2 && y_diff(pos_now, pos_new) == 1 || x_diff(pos_now, pos_new) == 1 && y_diff(pos_now, pos_new) == 2))
            return true
        return false

    def rook(pos_now: String, pos_new: String): Boolean =
        if(different_player(pos_now, pos_new) && x_or_y(pos_now, pos_new))
            return true
        return false

    def pawn(pos_now: String, pos_new: String): Boolean =
        if ((get_player(pos_now) == "1" || get_player(pos_now) == "2") && (y_diff(pos_now, pos_new) == 1 || (y_diff(pos_now, pos_new) == 2)) && x_or_y(pos_now, pos_new) && forward_move(pos_now, pos_new)) // Move (1 or 2 Field)
            return true
        else if (x_diff(pos_now, pos_new) == 1 && y_diff(pos_now, pos_new) ==   1 && (different_player(pos_now, pos_new) && get_player(pos_now) == "1" && forward_move(pos_now, pos_new) && empty_field(pos_new) != true || different_player(pos_now, pos_new) && get_player(pos_now) == "2" && forward_move(pos_now, pos_new) && empty_field(pos_new) != true)) // Attack Move
            return true
        return false



    val eol = sys.props("line.separator")

    def top_row(): String =
        return "   A    B    C    D    E    F    G    H  " + eol

    def first_last_row(x_size:Int = 8, first:String = "/", last:String = "\\")= first + ("-" * 4 + "+") * (x_size - 1) + ("-" * 4) + last + eol

    def border_row(x_size:Int = 8) = ("+" + "-" * 4) * x_size + "+" + eol

    override def board_to_string(): String =
        val map_values = board.values.mkString("| ", " | ", " |").toString()
        val cell_array = map_values.grouped(40).toArray

        return top_row() + first_last_row(8) + cell_array(0) + "| 1\n" + border_row() + cell_array(1) + "| 2\n" + border_row() + cell_array(2) + "| 3\n" + border_row() + cell_array(3) + "| 4\n" + border_row() + cell_array(4) + "| 5\n" + border_row() + cell_array(5) + "| 6\n" + border_row() + cell_array(6) + "| 7\n" + border_row() + cell_array(7) + "| 8\n" + first_last_row(8, "\\", "/")

}
object Board {
    def apply(board: VectorMap[String, String]) : Board = new Board(board)
    def apply(): Board = new Board(VectorMap("A1"->"R1", "B1"-> "k1", "C1"->"B1", "D1"->"Q1", "E1"->"K1", "F1"->"B1", "G1"->"k1", "H1"->"R1", "A2"->"P1", "B2"->"P1", "C2"->"P1", "D2"->"P1", "E2"->"P1", "F2"->"P1", "G2"->"P1", "H2"->"P1", "A3"->"  ", "B3"-> "  ", "C3"->"  ", "D3"->"  ", "E3"->"  ", "F3"->"  ", "G3"->"  ", "H3"->"  ", "A4"->"  ", "B4"->"  ", "C4"->"  ", "D4"->"  ", "E4"->"  ", "F4"->"  ", "G4"->"  ", "H4"->"  ", "A5"->"  ", "B5"-> "  ", "C5"->"  ", "D5"->"  ", "E5"->"  ", "F5"->"  ", "G5"->"  ", "H5"->"  ", "A6"->"  ", "B6"->"  ", "C6"->"  ", "D6"->"  ", "E6"->"  ", "F6"->"  ", "G6"->"  ", "H6"->"  ", "A7"->"P2", "B7"->"P2", "C7"->"P2", "D7"->"P2", "E7"->"P2", "F7"->"P2", "G7"->"P2", "H7"->"P2", "A8"->"R2", "B8"->"k2", "C8"->"B2", "D8"->"Q2", "E8"->"K2", "F8"->"B2", "G8"->"k2", "H8"->"R2"))
}
