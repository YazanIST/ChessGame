package chessgame;

import java.util.HashMap;

import chessgame.board.*;

// Visitor Pattern Variation
public class GameStatusChecker {
    private HashMap<Integer, Integer> boardsFreq = new HashMap<Integer, Integer>();
    private int checksCount = 0;

    public GameStatus getGameStatus(Board board, Color currentPlayer) {
        if (board == null || currentPlayer == null) {
            throw new IllegalArgumentException();
        }

        if (checkWhiteWin(board)) {
            return GameStatus.WHITE_WIN;
        } else if (checkBlackWin(board)) {
            return GameStatus.BLACK_WIN;
        } else if (checkDraw(board, currentPlayer)) {
            return GameStatus.DRAW;
        } else {
            return GameStatus.ONGOING;
        }
    }

    private boolean checkWhiteWin(Board board) {
        return KingStatusChecker.getKingStatus(board, Color.BLACK) == KingStatus.CHECKMATE;
    }

    private boolean checkBlackWin(Board board) {
        return KingStatusChecker.getKingStatus(board, Color.WHITE) == KingStatus.CHECKMATE;
    }

    private boolean checkDraw(Board board, Color currentPlayer) {
        if (checksCount >= 100) {
            return true;
        }
        
        if (boardsFreq.containsKey(board.hashCode()) && boardsFreq.get(board.hashCode()) >= 3) {
            return true;
        }
        
        if (board.getBlackPiecesCount() == 1 && board.getWhitePiecesCount() == 1) {
            return true;
        }
        
        if (KingStatusChecker.getKingStatus(board, currentPlayer) == KingStatus.STALEMATE) {
            return true;
        }

        return false;
    }

    public void increaseBoardFreq(Board board) {
        if (board == null) {
            throw new IllegalArgumentException();
        }
        int newFreq = 1;
        checksCount++;

        if (boardsFreq.containsKey(board.hashCode())) {
            newFreq += boardsFreq.get(board.hashCode());
        }
        
        boardsFreq.put(board.hashCode(), newFreq);
    }
}
