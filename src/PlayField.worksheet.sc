class Game {
    val state = Array.ofDim[String](8, 8)  // create a Array of Dim 8x8

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

        return state

    def move(x_position_old: Int, y_position_old: Int, x_position_new: Int, y_position_new: Int) =
        val figure = this.state(x_position_old)(y_position_old)
        println(figure)

        state(x_position_old)(y_position_old) = null;
        state(x_position_new)(y_position_new) = figure
}
def calculateCellValue(i: Int, j: Int) =
            if (i == 0 || j == 0 || i == 9 || j == 9)  '#' else 'x'

val temp = Array.tabulate(10,10)(calculateCellValue)