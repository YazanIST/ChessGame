package chessgame.chesspieces;

import chessgame.*;
import chessgame.moves.*;

public class Knight extends ChessPiece {
    public Knight(Color color) {
        super(color);
        addAllMoves();
    }

    public Knight(Color color, int countMoves) {
        super(color, countMoves);
        addAllMoves();
    }

    protected void addAllMoves() {
        addMove(new LShapedMove());
    }

    public String toString() {
        return super.toString() + "N";
    }

    public int hashCode() {
        return 17;
    }

    public Object clone() {
        return new Knight(this.getColor(), this.getCountMoves());
    }
}
