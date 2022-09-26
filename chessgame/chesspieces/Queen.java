package chessgame.chesspieces;

import chessgame.*;
import chessgame.moves.*;

public class Queen extends ChessPiece {
    public Queen(Color color) {
        super(color);
        addAllMoves();
    }

    public Queen(Color color, int countMoves) {
        super(color, countMoves);
        addAllMoves();
    }

    protected void addAllMoves() {
        addMove(new DiagonalMove());
        addMove(new VerticalMove());
        addMove(new HorizontalMove());
    }

    public String toString() {
        return super.toString() + "Q";
    }

    public int hashCode() {
        return 23;
    }

    public Object clone() {
        return new Queen(this.getColor(), this.getCountMoves());
    }
}
