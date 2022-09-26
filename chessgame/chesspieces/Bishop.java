package chessgame.chesspieces;

import chessgame.*;
import chessgame.moves.*;

public class Bishop extends ChessPiece{
    public Bishop(Color color) {
        super(color);
        addAllMoves();
    }
    public Bishop(Color color, int countMoves) {
        super(color, countMoves);
        addAllMoves();
    }
    
    protected void addAllMoves() {
        addMove(new DiagonalMove());
    }

    // decorator pattern inspired
    public String toString() {
        return super.toString() + "B";
    }

    public int hashCode() {
        return 11;
    }

    public Object clone() {
        return new Bishop(this.getColor(), this.getCountMoves());
    }
}
