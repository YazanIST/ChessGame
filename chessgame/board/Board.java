package chessgame.board;

import java.util.NoSuchElementException;

import chessgame.*;
import chessgame.chesspieces.*;
import chessgame.moves.specialmoves.CastlingHandler;
import chessgame.moves.specialmoves.EnPassantHandler;

public class Board implements Cloneable {
    // first row is ignored so we can use it in 1-indexing
    private ChessPiece[][] board = new ChessPiece[9][9];
    private Board previousBoard;
    private int whitePiecesCount, blackPiecesCount;
    private Position whiteKingPos, blackKingPos;
    private BoardPositions allPositions = new BoardPositions();

    public Board() {
        initPawns();
        initRooks();
        initKnights();
        initBishops();
        initQueens();
        initKings();
        initNulls();
        setWhitePiecesCount(16);
        setBlackPiecesCount(16);
    }
    
    public int getWhitePiecesCount() {
        return whitePiecesCount;
    }
    
    private void setWhitePiecesCount(int whitePiecesCount) {
        this.whitePiecesCount = whitePiecesCount;
    }
    
    public int getBlackPiecesCount() {
        return blackPiecesCount;
    }
    
    private void setBlackPiecesCount(int blackPiecesCount) {
        this.blackPiecesCount = blackPiecesCount;
    }
    
    private void initPawns() {
        for (int i = 1; i <= 8; i++) {
            add(new Pawn(Color.WHITE), new Position(2, i));
            add(new Pawn(Color.BLACK), new Position(7, i));
        }
    }
    
    private void initRooks() {
        add(new Rook(Color.WHITE), new Position(1, 1));
        add(new Rook(Color.WHITE), new Position(1, 8));
        add(new Rook(Color.BLACK), new Position(8, 1));
        add(new Rook(Color.BLACK), new Position(8, 8));
    }
    
    private void initKnights() {
        add(new Knight(Color.WHITE), new Position(1, 2));
        add(new Knight(Color.WHITE), new Position(1, 7));
        add(new Knight(Color.BLACK), new Position(8, 2));
        add(new Knight(Color.BLACK), new Position(8, 7));
    }
    
    private void initBishops() {
        add(new Bishop(Color.WHITE), new Position(1, 3));
        add(new Bishop(Color.WHITE), new Position(1, 6));
        add(new Bishop(Color.BLACK), new Position(8, 3));
        add(new Bishop(Color.BLACK), new Position(8, 6));
    }
    
    private void initQueens() {
        add(new Queen(Color.WHITE), new Position(1, 4));
        add(new Queen(Color.BLACK), new Position(8, 4));
    }
    
    private void initKings() {
        setWhiteKingPos(new Position(1, 5));
        add(new King(Color.WHITE), whiteKingPos);
        setBlackKingPos(new Position(8, 5));
        add(new King(Color.BLACK), blackKingPos);
    }

    private void initNulls() {
        for (int i = 0; i <= 8; i++) {
            board[i][0] = null;
            board[0][i] = null;
        }
        for (int i = 3; i <= 6; i++) {
            for (int j = 1; j <= 8; j++) {
                board[i][j] = null;
            }
        }
        previousBoard = null;
    }
    
    public ChessPiece getPiece(Position pos) {
        return board[pos.getRow()][pos.getCol()];
    }
    
    public Position getWhiteKingPos() {
        return whiteKingPos;
    }
    
    public void setWhiteKingPos(Position whiteKingPos) {
        this.whiteKingPos = whiteKingPos;
    }
    
    public Position getBlackKingPos() {
        return blackKingPos;
    }
    
    public void setBlackKingPos(Position blackKingPos) {
        this.blackKingPos = blackKingPos;
    }

    public Position getKingPosition(Color color) {
        if (color == Color.WHITE) {
            return getWhiteKingPos();
        } else {
            return getBlackKingPos();
        }
    }

    public ChessPiece getKing(Color color) {
        if (color == Color.WHITE) {
            return getPiece(getWhiteKingPos());
        } else {
            return getPiece(getBlackKingPos());
        }
    }

    public int getPiecesCount(Color color) {
        if (color == Color.WHITE) {
            return getWhitePiecesCount();
        } else {
            return getBlackPiecesCount();
        }
    }

    public void remove(Position pos) {
        if (!pos.isValid()) {
            return;
        }
        board[pos.getRow()][pos.getCol()] = null;
    }

    public void add(ChessPiece piece, Position pos) {
        if (!pos.isValid()) {
            return;
            }
        board[pos.getRow()][pos.getCol()] = piece;
    }

    public void applyMove(Move move) {
        boolean isCastling = CastlingHandler.getInstance().isHandled(move, this);
        boolean isEnPassent = EnPassantHandler.getInstance().isHandled(move, this);
        Move otherMove = null;
        Position toRemove = null;

        if (isCastling) {
            otherMove = new Move(CastlingHandler.getInstance().getRockPosition(move, this),
                                CastlingHandler.getInstance().getNewRockPosition(move, this));
        }
        if (isEnPassent) {
            toRemove = ((EnPassantHandler) EnPassantHandler.getInstance()).removedPiecePosition(move, this);
        }

        previousBoard = (Board) this.clone();

        if (move == null || getPiece(move.getFrom()) == null) {
            throw new NullPointerException();
        }

        if (getPiece(move.getFrom()) instanceof King) {
            if (getPiece(move.getFrom()).getColor() == Color.WHITE) {
                whiteKingPos = move.getTo();
            } else {
                blackKingPos = move.getTo();
            }
        }
        
        add(getPiece(move.getFrom()), move.getTo());
        getPiece(move.getTo()).increaseMoves();
        remove(move.getFrom());

        if (isCastling) {
            add(getPiece(otherMove.getFrom()), otherMove.getTo());
            getPiece(otherMove.getTo()).increaseMoves();
            remove(otherMove.getFrom());
        }
        if (isEnPassent) {
            remove(toRemove);
        }
    }
    
    public boolean replacePiece(Position pos, ChessPiece chessPiece){
        if (getPiece(pos) == null) {
            return false;
        }
        
        chessPiece.setCountMoves(getPiece(pos).getCountMoves());
        add(chessPiece, pos);
        
        return true;
    }
    
    public int hashCode() {
        int ret = 0;
        for (Position pos: allPositions) {
            if (getPiece(pos) != null) {
                ret = ModularArithmetic.add(ret,
                ModularArithmetic.power(getPiece(pos).hashCode(), pos.hashCode()));
            }
        }
        return ret;
    }

    public Object clone() {
        Board ret = new Board();
        ret.blackKingPos = new Position(blackKingPos);
        ret.whiteKingPos = new Position(whiteKingPos);
        ret.whitePiecesCount = whitePiecesCount;
        ret.blackPiecesCount = blackPiecesCount;
        ret.previousBoard = previousBoard;
        ret.board = new ChessPiece[9][9];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (board[i][j] != null) {
                    ret.board[i][j] = (ChessPiece) board[i][j].clone();
                } else {
                    ret.board[i][j] = null;
                }
            }
        }
        return ret;
    }

    public void revertLastMove() {
        this.blackKingPos = new Position(previousBoard.blackKingPos);
        this.whiteKingPos = new Position(previousBoard.whiteKingPos);
        this.whitePiecesCount = previousBoard.whitePiecesCount;
        this.blackPiecesCount = previousBoard.blackPiecesCount;
        this.board = previousBoard.board.clone();
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (previousBoard.board[i][j] != null) {
                    this.board[i][j] = (ChessPiece) previousBoard.board[i][j].clone();
                } else {
                    this.board[i][j] = null;
                }
            }
        }
        this.previousBoard = previousBoard.previousBoard;
    }

    public Board getPrevBoard() {
        if (previousBoard == null) {
            throw new NoSuchElementException();
        }
        return previousBoard;
    }
}
