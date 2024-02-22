package info.setmy.linguistics;

import info.setmy.linguistics.models.token.EventType;

public class ParsingHandler implements EventHandler {

    @Override
    public void hande(final EventType eventType, final ParseTraversal traversal) {
        switch (eventType) {
            case CHARACTER:
                doHandleCharacter(traversal);
                break;
            case START_WORD:
                doStartWord(traversal);
                break;
            case END_WORD:
                doEndWord(traversal);
                break;
            default:
        }
    }


    private void doStartWord(final ParseTraversal traversal) {
    }

    private void doEndWord(final ParseTraversal traversal) {
        traversal.getTextTokenBuilder().append(traversal.getCurrentToken().getValue());
        traversal.finishParsingWord();
    }


    private void doHandleCharacter(final ParseTraversal traversal) {
        if(traversal.getCurrentToken().isNotWhiteCharSingleToken()) {
            traversal.getTextTokenBuilder().append(traversal.getCurrentToken().getValue());
        }
    }
}
