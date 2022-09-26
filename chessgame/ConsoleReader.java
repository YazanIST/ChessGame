package chessgame;
import java.util.Scanner;

import chessgame.chesspieces.*;

// Singilton Pattern
public class ConsoleReader {
    private static ConsoleReader soloReader = new ConsoleReader();
    Scanner scanner = new Scanner(System.in);

    private ConsoleReader() {
    }

    public static ConsoleReader getReader() {
        return soloReader;
    }

    public String readString(String message) {
        System.out.print(message);
        String ret = scanner.nextLine();
        ret = ret.toLowerCase();
        return ret;
    }

    public String readString() {
        return readString("");
    }
    
    public Position readPosition(String message) {
        System.out.print(message);

        String s = readString();

        if (s.length() != 2) {
            return null;
        }
        if (!Character.isDigit(s.charAt(1))) {
            return null;
        }
        if (!Character.isLetter(s.charAt(0))) {
            return null;
        }

        return new Position(s.charAt(1) - '0', s.charAt(0) - 'a' + 1);
    }

    public Position readPosition() {
        return readPosition("");
    }

    public Move readMove(String message) {
        System.out.print(message);

        String s = readString();

        if (s.length() != 5) {
            return null;
        }

        String from = s.substring(0, 2);
        String to = s.substring(3, 5);
        
        if (s.length() != 5 || !Position.isValidStringPos(from) || !Position.isValidStringPos(to)) {
            return null;
        }
        return new Move(new Position(from), new Position(to));
    }

    public Move readMove() {
        return readMove("");
    }

    public ChessPiece readPiece(Color color, String message) {
        System.out.println(message);

        String s = readString();

        if (s.length() != 1) {
            return null;
        }

        // B Bishop, N Knight, Q Queen, R Rook
        if (s.charAt(0) == 'B') {
            return new Bishop(color);
        } else if (s.charAt(0) == 'N') {
            return new Knight(color);
        } else if (s.charAt(0) == 'Q') {
            return new Queen(color);
        } else if (s.charAt(0) == 'R') {
            return new Rook(color);
        } else {
            return null;
        }
    }

    public ChessPiece readPiece(Color color) {
        return readPiece(color, "");
    }
}
