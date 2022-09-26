package chessgame;

import java.util.ArrayList;

public class MovesRecorder {
    ArrayList<Move> moves = new ArrayList<>();

    public void addMove(Move newMove){
        moves.add(newMove);
    }

    public void undoLastMove() {
        if (moves.size() < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        moves.remove(moves.size() - 1);
    }

    public void displayMoves() {
        for (Move move: moves) {
            System.out.println(move);
        }
    }
}
