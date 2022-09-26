package chessgame.chesspieces;

import chessgame.*;
import chessgame.moves.*;

public class Pawn extends ChessPiece {
    public Pawn(Color color) {
        super(color);
        addAllMoves();
    }

    public Pawn(Color color, int countMoves) {
        super(color, countMoves);
        addAllMoves();
    }

    protected void addAllMoves() {
        addMove(new OneStepForwardMove());
    }

    public String toString() {
        return super.toString() + "P";
    }

    public int hashCode() {
        return 19;
    }

    public Object clone() {
        return new Pawn(this.getColor(), this.getCountMoves());
    }
}
