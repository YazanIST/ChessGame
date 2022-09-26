package chessgame.moves;

import chessgame.*;
import chessgame.board.*;

// Strategy Pattern
public class VerticalMove extends MoveType {
    public boolean checkMove(Move move, Board board) {
        if (!checkMoveBase(move, board)) {
            return false;
        }
        
        if (move.getAbsColDif() != 0) {
            return false;
        }

        return PathChecker.isPathEmpty(move, board);
    }
}
