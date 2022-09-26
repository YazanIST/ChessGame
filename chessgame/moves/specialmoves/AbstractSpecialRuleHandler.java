package chessgame.moves.specialmoves;

import chessgame.*;
import chessgame.board.*;
import chessgame.chesspieces.*;
import chessgame.moves.*;

// Chain Of Responsipilty Pattern
public abstract class AbstractSpecialRuleHandler {
    private AbstractSpecialRuleHandler nextHandler = null;

    public void setNext(AbstractSpecialRuleHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public boolean toNextHandler(Move move, Board board) {
        if (nextHandler == null) {
            return false;
        }
        return nextHandler.handleRule(move, board);
    }
    
    public boolean handleRule(Move move, Board board) {
        if (!MoveType.checkMoveBase(move, board)) {
            return toNextHandler(move, board);
        }

        if (!isApplicablePiece(board.getPiece(move.getFrom()))) {
            return toNextHandler(move, board);
        }
        
        if (isHandled(move, board)) {
            return true;
        }
        
        return toNextHandler(move, board);
    }

    public abstract boolean isHandled(Move move, Board board);
    
    public abstract boolean isApplicablePiece(ChessPiece piece);
}
