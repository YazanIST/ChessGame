package chessgame.moves;

import chessgame.*;
import chessgame.board.*;

// Strategy Pattern
public class LShapedMove extends MoveType {
    public boolean checkMove(Move move, Board board) {
        if (!checkMoveBase(move, board)) {
            return false;
        }

        if (move.getAbsColDif() == 1 && move.getAbsRowDif() == 2) {
            return true;
        }

        if (move.getAbsColDif() == 2 && move.getAbsRowDif() == 1) {
            return true;
        }

        return false;
    }
}
