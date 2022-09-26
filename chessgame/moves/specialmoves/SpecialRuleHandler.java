package chessgame.moves.specialmoves;

import chessgame.*;
import chessgame.board.*;

// Chain Of Resposipilty Wrapper
public class SpecialRuleHandler {
    AbstractSpecialRuleHandler headHandler = null;
    AbstractSpecialRuleHandler tailHandler = null;

    public SpecialRuleHandler() {
        add(EnPassantHandler.getInstance());
        add(CastlingHandler.getInstance());
        add(PawnCaptureHandler.getInstance());
        add(PawnTwoStepsHandler.getInstance());
    }

    public void add(AbstractSpecialRuleHandler newHandler) {
        if (headHandler == null) {
            headHandler = newHandler;
        } else if (tailHandler == null) {
            tailHandler = newHandler;
            headHandler.setNext(tailHandler);
        } else {
            tailHandler.setNext(newHandler);
            tailHandler = newHandler;
        }
    }

    public boolean handle(Move move, Board board) {
        if (headHandler == null) {
            return false;
        }
        return headHandler.handleRule(move, board);
    }
}
