package de.htwg.se.Chess

enum Figure(Figure_As_String: String):
    override def toString(): String = Figure_As_String
    case K1 extends Figure("K1")
    case Q1 extends Figure("Q1")
    case B1 extends Figure("B1")
    case k1 extends Figure("k1")
    case R1 extends Figure("R1")

    case P1 extends Figure("P1")

    case K2 extends Figure("K2")
    case Q2 extends Figure("Q2")
    case B2 extends Figure("B2")
    case k2 extends Figure("k2")
    case R2 extends Figure("R2")

    case P2 extends Figure("P2")

    case Empty extends Figure("  ")