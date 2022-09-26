package chessgame.chesspieces;

import chessgame.*;
import chessgame.moves.*;

public class King extends ChessPiece {
    public King(Color color) {
        super(color);
        addAllMoves();
    }

    public King(Color color, int countMoves) {
        super(color, countMoves);
        addAllMoves();
    }

    protected void addAllMoves() {
        addMove(new OneStepMove());
    }

    public String toString() {
        return super.toString() + "K";
    }

    public int hashCode() {
        return 13;
    }

    public Object clone() {
        return new King(this.getColor(), this.getCountMoves());
    }
}
