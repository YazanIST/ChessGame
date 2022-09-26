package chessgame;

public class Position {
    private int row, col;

    public Position(int row, int col) {
        setRow(row);
        setCol(col);
    }

    public Position(String s) {
        if (isValidStringPos(s)) {
            setRow(s.charAt(1) - '0');
            setCol(s.charAt(0) - 'a' + 1);
        }
    }
    
    public Position(Position pos) {
        if (pos == null) {
            throw new NullPointerException();
        }
        setRow(pos.getRow());
        setCol(pos.getCol());
    }

    static public boolean isValidStringPos(String s) {
        return s.length() == 2 && Character.isDigit(s.charAt(1))
                && Character.isLetter(s.charAt(0));
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isValid() {
        return row >= 1 && row <= 8 && col >= 1 && col <= 8;
    }

    public String toString() {
        return "" + (char)(col + 'a' - 1) + row;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || !(obj instanceof Position)) {
            return false;
        }

        Position other = (Position) obj;
        if (this.row == other.row && this.col == other.col) {
            return true;
        }

        return false;
    }

    public int getDistance(Position other) {
        if (other == null) {
            throw new NullPointerException();
        }

        return Math.abs(other.getCol() - this.getCol()) + Math.abs(other.getRow() - this.getRow());
    }

    public int hashCode() {
        return (row - 1) * 8 + col;
    }
}
