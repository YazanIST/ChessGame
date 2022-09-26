package chessgame.moves.specialmoves;

import chessgame.*;
import chessgame.board.*;
import chessgame.chesspieces.*;

// Chain Of Responsipilty Pattern
public class EnPassantHandler extends AbstractSpecialRuleHandler {
    static private EnPassantHandler soloInstance = new EnPassantHandler();

    static public AbstractSpecialRuleHandler getInstance() {
        return soloInstance;
    }
    
    private EnPassantHandler() {
    }

    public Position removedPiecePosition(Move move, Board board) {
        if (board.getPiece(move.getFrom()).getColor() == Color.WHITE) {
            return new Position(move.getFrom().getRow(), move.getTo().getCol());
        } else {
            return new Position(move.getFrom().getRow(), move.getTo().getCol());
        }
    }

    public Position getOldPosition(Move move, Board board) {
        if (board.getPiece(move.getFrom()).getColor() == Color.WHITE) {
            return new Position(move.getFrom().getRow() + 2, move.getTo().getCol());
        } else {
            return new Position(move.getFrom().getRow() - 2, move.getTo().getCol());
        }
    }

    public boolean checkPrevBoard(Position emptyPos, Position filledPosition, Board board) {
        if (board.getPrevBoard() == null) {
            return false;
        }

        if (board.getPrevBoard().getPiece(emptyPos) != null) {
            return false;
        }

        if (board.getPrevBoard().getPiece(filledPosition) == null) {
            return false;
        }

        if (!(board.getPrevBoard().getPiece(filledPosition) instanceof Pawn)) {
            return false;
        }

        return true;
    }

    public boolean isHandled(Move move, Board board) {
        if (move.getAbsColDif() != 1 || move.getAbsRowDif() != 1) {
            return false;
        }

        if (board.getPiece(move.getTo()) != null) {
            return false;
        }

        if (board.getPiece(move.getFrom()).getColor() == Color.WHITE) {
            // up or down
            if (move.getFrom().getRow() > move.getTo().getRow()) {
                return false;
            }
        } else {
            if (move.getFrom().getRow() < move.getTo().getRow()) {
                return false;
            }
        }
        
        // check if to is empty
        if (board.getPiece(removedPiecePosition(move, board)) == null) {
            return false;
        }

        // check it is pawn
        if (!(board.getPiece(removedPiecePosition(move, board)) instanceof Pawn)) {
            return false;
        }

        if (board.getPiece(removedPiecePosition(move, board)).getColor()
            == board.getPiece(move.getFrom()).getColor() ) {
            return false;
        }

        if (!checkPrevBoard(removedPiecePosition(move, board), getOldPosition(move, board), board)) {
            return false;
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
