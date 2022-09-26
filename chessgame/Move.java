package chessgame;

public class Move {
    private Position from, to;

    public Move(Position from, Position to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException();
        }
        setFrom(from);
        setTo(to);
    }

    public Position getFrom() {
        return from;
    }

    public void setFrom(Position from) {
        if (from == null) {
            throw new NullPointerException();
        }
        this.from = from;
    }

    public Position getTo() {
        return to;
    }

    public void setTo(Position to) {
        if (to == null) {
            throw new NullPointerException();
        }
        this.to = to;
    }

    public boolean isValid() {
        return from.isValid() && to.isValid() && !getFrom().equals(getTo());
    }

    public int getAbsColDif() {
        return Math.abs(getFrom().getCol() - getTo().getCol());
    }

    public int getAbsRowDif() {
        return Math.abs(getFrom().getRow() - getTo().getRow());
    }

    public void invertMove() {
        Position temp = new Position(getFrom());
        setFrom(getTo());
        setTo(temp);
    }

    public String toString() {
        return "from " + from.toString() + " to " + to.toString();
    }
}