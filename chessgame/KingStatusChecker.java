package chessgame;

import chessgame.board.*;
import chessgame.moves.specialmoves.*;

public class KingStatusChecker {
    static private BoardPositions allPositions = new BoardPositions();
    static private SpecialRuleHandler specialRuleHandler = new SpecialRuleHandler();
    
    static public KingStatus getKingStatus(Board board, Color color) {
        if (isInStalemate(board, color)) {
            return KingStatus.STALEMATE;
        } else if (isInCheckmate(board, color)) {
            return KingStatus.CHECKMATE;
        } else if (isInCheck(board, color)) {
            return KingStatus.IN_CHECK;
        } else {
            return KingStatus.SAFE;
        }
    }

    static private boolean isInCheck(Board board, Color color) {
        for (Position currentPosition: allPositions) {
            if (board.getPiece(currentPosition) == null
            || board.getPiece(currentPosition).getColor() == color) {
                continue;
            }
            Move newMove = new Move(currentPosition, board.getKingPosition(color));
            if (board.getPiece(currentPosition).checkMove(newMove, board)
                || specialRuleHandler.handle(newMove, board)) {
                return true;
            }
        }
        return false;
    }

    static private boolean isInCheckmate(Board board, Color color) {
        if (!isInCheck(board, color)) {
            return false;
        } else {
            return tryAllMoves(board, color);
        }
    }

    static private boolean isInStalemate(Board board, Color color) {
        if (isInCheck(board, color)) {
            return false;
        } else {
            return tryAllMoves(board, color);
        }
    }

    static private boolean tryAllMoves(Board board, Color color) {
        boolean ret = true;

        for (Position firstPosition: allPositions) {
            for (Position secondPosition: allPositions) {
                if (firstPosition.equals(secondPosition)) continue;
                if (board.getPiece(firstPosition) == null) continue;
                if (board.getPiece(firstPosition).getColor() != color) continue;
                Move newMove = new Move(firstPosition, secondPosition);
                if (board.getPiece(firstPosition).checkMove(newMove, board)
                    || specialRuleHandler.handle(newMove, board)) {
                        board.applyMove(newMove);
                        ret &= isInCheck(board, color);
                        board.revertLastMove();
                }
                if (!ret) {
                    break;
                }
            }
            if (!ret) {
                break;
            }
        }

        return ret;
    }
}
