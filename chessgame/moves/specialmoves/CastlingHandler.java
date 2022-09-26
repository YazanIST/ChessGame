package chessgame.moves.specialmoves;

import chessgame.*;
import chessgame.board.*;
import chessgame.chesspieces.*;

// Chain Of Responsipilty
public class CastlingHandler extends AbstractSpecialRuleHandler {
    static private CastlingHandler soloInstance = new CastlingHandler();

    static public CastlingHandler getInstance() {
        return soloInstance;
    }
    
    private CastlingHandler() {
    }

    public Position getRockPosition(Move move, Board board) {
        Position ret = new Position(move.getTo());

        if (move.getFrom().getCol() < move.getTo().getCol()) {
            ret.setCol(ret.getCol() + 1);
        } else if (move.getFrom().getCol() > move.getTo().getCol()) {
            ret.setCol(ret.getCol() - 2);
        }

        return ret;
    }

    public Position getNewRockPosition(Move move, Board board) {
        Position ret = new Position(move.getFrom());

        if (move.getFrom().getCol() < move.getTo().getCol()) {
            ret.setCol(ret.getCol() + 1);
        } else if (move.getFrom().getCol() > move.getTo().getCol()) {
            ret.setCol(ret.getCol() - 1);
        }

        return ret;
    }

    public boolean isHandled(Move move, Board board) {
        if (move.getAbsRowDif() != 0 || move.getAbsColDif() != 2) {
            return false;
        }

        if (board.getPiece(move.getFrom()).getCountMoves() != 0) {
            return false;
        }

        Position rockPos = getRockPosition(move, board);
        if (board.getPiece(rockPos) != null && board.getPiece(rockPos) instanceof Rook) {
            Move pathToCheck = new Move(move.getFrom(), rockPos);
            if (PathChecker.isPathEmpty(pathToCheck, board)) {
                return true;
            }
        }

        return false;
    }

    public boolean isApplicablePiece(ChessPiece piece) {
        if (piece == null) {
            return false;
        }

        return piece instanceof King;
    }
}
