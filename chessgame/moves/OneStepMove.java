package chessgame.moves;

import chessgame.*;
import chessgame.board.*;

// Strategy Pattern
public class OneStepMove extends MoveType {
    public boolean checkMove(Move move, Board board) {
        if (!checkMoveBase(move, board)) {
            return false;
        }

        if (move.getAbsColDif() > 1 || move.getAbsRowDif() > 1) {
            return false;
        }

        return true;
    }
}
