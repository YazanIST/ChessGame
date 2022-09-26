package chessgame.moves.specialmoves;

import chessgame.*;
import chessgame.board.*;
import chessgame.chesspieces.*;

// Chain Of Resposipilty
public class PawnCaptureHandler extends AbstractSpecialRuleHandler {
    static private PawnCaptureHandler soloInstance = new PawnCaptureHandler();

    static public PawnCaptureHandler getInstance() {
        return soloInstance;
    }
    
    private PawnCaptureHandler() {
    }

    public boolean isHandled(Move move, Board board) {
        if (move.getAbsColDif() != 1 || move.getAbsRowDif() != 1) {
            return false;
        }

        if (board.getPiece(move.getTo()) == null) {
            return false;
        }

        if (board.getPiece(move.getTo()).getColor() == board.getPiece(move.getFrom()).getColor()) {
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

        return true;
    }

    public boolean isApplicablePiece(ChessPiece piece) {
        if (piece == null) {
            return false;
        }

        return piece instanceof Pawn;
    }
}
