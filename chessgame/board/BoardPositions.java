package chessgame.board;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.ArrayList;

import chessgame.Position;

public class BoardPositions implements Iterable<Position> {
    private ArrayList<Position> allPositions = new ArrayList<>();

    public BoardPositions() {
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++)  {
                addPosition(row, col);
            }
        }
    }

    public void addPosition(int row, int col) {
        allPositions.add(new Position(row, col));
    }

    public Iterator<Position> iterator() {
        return new PositionsIterator();
    }

    private class PositionsIterator implements Iterator<Position>{
        int cur = 0;

        public boolean hasNext() {
            return cur < allPositions.size();
        }

        public Position next() {
            Position ret = allPositions.get(cur);
            cur++;
            return ret;
        }
    }
}
