package chessgame.board;

import chessgame.*;

// Visitor Pattern Variation
public class BoardPrinter {
    static public void print(Board board) {
        boolean turn = true;
        System.out.print(' ');
        for (int col = 1; col <= 41; col++) {
            System.out.print('+');
        }
        System.out.println();
        for (int row = 24; row >= 1; row--) {
            System.out.print((row - 1) / 3 + 1);
            System.out.print('+');
            for (int col = 1; col <= 32; col++) {
                if (row % 3 == 2 && col % 4 == 2) {
                    Position curPosition = new Position(row / 3 + 1, col / 4 + 1);
                    if (board.getPiece(curPosition) != null) {
                        System.out.print(board.getPiece(curPosition));
                        col++;
                        continue;
                    }
                }
                if (turn) {
                    System.out.print(' ');
                } else {
                    System.out.print('.');
                }
                if (col % 4 == 0) {
                    System.out.print('+');
                    turn = !turn;
                }
            }
            if (row % 3 == 1) {
                turn = !turn;
                System.out.println();
                System.out.print(' ');
                for (int col = 1; col <= 41; col++) {
                    System.out.print('+');
                }
            }
            System.out.println();
        }
        int col = -1;
        for (int i = 0; i <= 41; i++) {
            if (i == 0 || (i - 1) % 5 == 0) {
                System.out.print(' ');
                col++;
            } else {
                System.out.print((char)('A' + col - 1));
            }
        }
        System.out.println('\n');
    }
}
