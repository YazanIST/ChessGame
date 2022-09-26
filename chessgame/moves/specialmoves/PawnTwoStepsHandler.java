package chessgame.moves.specialmoves;

import chessgame.*;
import chessgame.board.*;
import chessgame.chesspieces.*;

// Chain Of Resposipilty
public class PawnTwoStepsHandler extends AbstractSpecialRuleHandler {
    static private PawnTwoStepsHandler soloInstance = new PawnTwoStepsHandler();

    static public PawnTwoStepsHandler getInstance() {
        return soloInstance;
    }
    
    private PawnTwoStepsHandler() {
    }

    public boolean isHandled(Move move, Board board) {
        if (move.getAbsColDif() != 0 || move.getAbsRowDif() != 2) {
            return toNextHandler(move, board);
        }

        if (board.getPiece(move.getFrom()).getCountMoves() != 0) {
            return toNextHandler(move, board);
        }

        if (board.getPiece(move.getFrom()).getColor() == Color.WHITE) {
            if (move.getFrom().getRow() > move.getTo().getRow()) {
                return toNextHandler(move, board);
            }
        } else {
            if (move.getFrom().getRow() < move.getTo().getRow()) {
                return toNextHandler(move, board);
            }
        }

        if (!PathChecker.isPathEmpty(move, board)) {
            return toNextHandler(move, board);
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
