package chessgame;

import chessgame.board.Board;

// a class that is supposed to find if the path that 
// the move will take is empty on the given baord
// the taken path must be guaranted to be either vertical, horizontal, or diagonal
public class PathChecker {
    static private final int[] dirVertical   = {+0, +0, -1, +1, -1, -1, +1, +1};
    static private final int[] dirHorizontal = {-1, +1, +0, +0, -1, +1, +1, -1};
    
    static public boolean isPathEmpty(Move path, Board board) {
        if (path == null || board == null) {
            throw new IllegalArgumentException();
        }
        return isPathEmpty(new Position(path.getFrom()), path, board);
    }

    static private boolean isPathEmpty(Position curPosition, Move path, Board board) {
        if (curPosition.equals(path.getTo())) {
            return true;
        }

        if (!curPosition.equals(path.getFrom()) && board.getPiece(curPosition) != null) {
            return false;
        }

        return isPathEmpty(getNextPosition(curPosition, path.getTo()), path, board);
    }

    static private Position getNextPosition(Position curPosition, Position destination) {
        Position ret = null;
        int dis = (int) 1e9;

        for (int i = 0; i < 8; i++) {
            Position toCheck = new Position(curPosition);
            toCheck.setRow(toCheck.getRow() + dirVertical[i]);
            toCheck.setCol(toCheck.getCol() + dirHorizontal[i]);
            if (toCheck.isValid()) {
                if (dis > toCheck.getDistance(destination)) {
                    ret = toCheck;
                    dis = toCheck.getDistance(destination);
                }
            }
        }

        return ret;
    }
}
