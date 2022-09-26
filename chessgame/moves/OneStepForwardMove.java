package chessgame.moves;

import chessgame.*;
import chessgame.board.*;

// Strategy Pattern
public class OneStepForwardMove extends MoveType {
    public boolean checkMove(Move move, Board board) {
        if (!checkMoveBase(move, board)) {
            return false;
        }

        if (move.getAbsColDif() != 0 || move.getAbsRowDif() != 1) {
            return false;
        }

        if (board.getPiece(move.getFrom()).getColor() == Color.WHITE) {
            if (move.getFrom().getRow() > move.getTo().getRow()) {
                return false;
            }
        } else {
            if (move.getFrom().getRow() < move.getTo().getRow()) {
                return false;
            }
        }
        
        if (board.getPiece(move.getTo()) != null) {
            return false;
        }
        
        return true;
    }
}
