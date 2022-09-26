package chessgame;

import chessgame.board.*;
import chessgame.chesspieces.*;
import chessgame.moves.specialmoves.*;

// Facade Pattern Variation
public class ChessGame {
    private Board board = new Board();
    private Color currentPlayer = Color.WHITE;
    private String[] playersNames = new String[2];
    private MovesRecorder movesRecorder = new MovesRecorder();
    private GameStatusChecker statusChecker = new GameStatusChecker();
    private SpecialRuleHandler specialRuleHandler = new SpecialRuleHandler();

    private void alternatePlayer() {
        if (currentPlayer == Color.WHITE) {
            currentPlayer = Color.BLACK;
        } else {
            currentPlayer = Color.WHITE;
        }
    }
    
    private Move getMove() {
        Move ret = null;
        while (ret == null || board.getPiece(ret.getFrom()) == null
                || board.getPiece(ret.getFrom()).getColor() != currentPlayer
                || (!board.getPiece(ret.getFrom()).checkMove(ret, board)
                    && !specialRuleHandler.handle(ret, board))) {
            ret = ConsoleReader.getReader().readMove(
                    "Enter next move (" + currentPlayer + "): move ");
        }
        return ret;
    }

    private void promotePawn(Position pos) {
        ChessPiece newPiece = null;
        while (newPiece == null) {
            newPiece = ConsoleReader.getReader().readPiece(currentPlayer,
                "Pawn promotion for " + currentPlayer + "player, pick your new piece\n" + 
                "'B' -> Bishop, 'N' -> Knight, 'Q' -> Queen, 'R' -> Rock"
            );
        }
        board.replacePiece(pos, newPiece);
    }

    public void start() {
        playersNames[0] = ConsoleReader.getReader().readString(
            "Enter the white player name: "
        );
        playersNames[1] = ConsoleReader.getReader().readString(
            "Enter the black player name: "
        );

        boolean print = true;
        while (statusChecker.getGameStatus(board, currentPlayer) == GameStatus.ONGOING) {
            if (print) {
                BoardPrinter.print(board);
                if (KingStatusChecker.getKingStatus(board, currentPlayer) == KingStatus.IN_CHECK) {
                    System.out.println("King is in Check");
                }
            }


            Move currentMove = getMove();
            board.applyMove(currentMove);
            System.out.println();
            
            if ((currentMove.getTo().getRow() == 8 || currentMove.getTo().getRow() == 1)
            && board.getPiece(currentMove.getTo()) instanceof Pawn) {
                promotePawn(currentMove.getTo());
            }
            
            if (KingStatusChecker.getKingStatus(board, currentPlayer) == KingStatus.IN_CHECK) {
                board.revertLastMove();
                print = false;
                continue;
            }

            print = true;
            movesRecorder.addMove(currentMove);
            statusChecker.increaseBoardFreq(board);
            alternatePlayer();
        }

        System.out.println(statusChecker.getGameStatus(board, currentPlayer));
    }
}
