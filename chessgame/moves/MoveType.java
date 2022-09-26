package chessgame.moves;

import chessgame.*;
import chessgame.board.*;

// Strategy Pattern
abstract public class MoveType {
    static public boolean checkMoveBase(Move move, Board board) {
        if (move == null || board == null) {
            throw new IllegalArgumentException();
        }
        
        if (!move.isValid()) {
            return false;
        }
        
        if (board.getPiece(move.getFrom()) == null) {
            return false;
        }
        
        if(board.getPiece(move.getTo()) != null
        && board.getPiece(move.getFrom()).getColor()
        == board.getPiece(move.getTo()).getColor()) {
            return false;
        }
        
        return true;
    }

    abstract public boolean checkMove(Move move, Board board);
}
