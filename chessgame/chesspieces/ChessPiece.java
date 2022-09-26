package chessgame.chesspieces;

import java.util.ArrayList;

import chessgame.*;
import chessgame.board.*;
import chessgame.moves.*;

abstract public class ChessPiece {
    private Color color;
    private int countMoves = 0;
    ArrayList<MoveType> applicableMoves = new ArrayList<>();

    public ChessPiece(ChessPiece other) {
        if (other == null) {
            color = null;
            applicableMoves = null;
            countMoves = 0;
        } else {
            setColor(color);
            setCountMoves(countMoves);
            this.applicableMoves = new ArrayList<>(other.applicableMoves);
        }
    }

    public ChessPiece(Color color) {
        if (color == null) {
            throw new IllegalArgumentException();
        }
        this.color = color;
    }

    public ChessPiece(Color color, int countMoves) {
        if (color == null) {
            throw new IllegalArgumentException();
        }
        this.color = color;
        this.countMoves = countMoves;
    }

    public boolean checkMove(Move move, Board board) {
        if (move == null || board == null) {
            throw new IllegalArgumentException();
        }
        for (MoveType moveType: applicableMoves) {
            if (moveType.checkMove(move, board)) {
                return true;
            }
        }
        return false;
    }
    
    public void addMove(MoveType moveType) {
        if (moveType == null) {
            throw new IllegalArgumentException();
        }
        applicableMoves.add(moveType);
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException();
        }
        this.color = color;
    }
    
    public int getCountMoves() {
        return countMoves;
    }
    
    public void setCountMoves(int countMoves) {
        this.countMoves = countMoves;
    }
    
    public void increaseMoves() {
        countMoves++;
    }
    
    public String toString() {
        return color.toString().substring(0, 1);
    }
    
    abstract public Object clone();
    abstract public int hashCode();
    abstract protected void addAllMoves();
}
