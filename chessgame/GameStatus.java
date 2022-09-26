package chessgame;

public enum GameStatus {
    DRAW {
        public String toString() {
            return "Game ended with draw";
        }
    },
    WHITE_WIN {
        public String toString() {
            return "White player won!";
        }
    },
    BLACK_WIN {
        public String toString() {
            return "Black player won!";
        }
    },
    ONGOING {
        public String toString() {
            return "Game is still going..";
        }
    }
}
