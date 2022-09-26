package chessgame.chesspieces;

import chessgame.*;
import chessgame.moves.*;

public class Rook extends ChessPiece {
    public Rook(Color color) {
        super(color);
        addAllMoves();
    }

    public Rook(Color color, int countMoves) {
        super(color, countMoves);
        addAllMoves();
    }

    protected void addAllMoves() {
        addMove(new VerticalMove());
        addMove(new HorizontalMove());
    }

    public String toString() {
        return super.toString() + "R";
    }

    public int hashCode() {
        return 29;
    }

    public Object clone() {
        return new Rook(this.getColor(), this.getCountMoves());
    }
}
