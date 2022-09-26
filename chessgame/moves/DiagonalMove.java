package chessgame.moves;

import chessgame.*;
import chessgame.board.*;

// Strategy Pattern
public class DiagonalMove extends MoveType{
    public boolean checkMove(Move move, Board board) {
        if (!checkMoveBase(move, board)) {
            return false;
        }

        if (move.getAbsColDif() != move.getAbsRowDif()) {
            return false;
        }

        return PathChecker.isPathEmpty(move, board);
    }
}
