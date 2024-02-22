package info.setmy.linguistics;

import info.setmy.linguistics.models.token.EventType;
import info.setmy.linguistics.models.token.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static info.setmy.linguistics.models.token.EventType.PARSING_BEGIN;
import static info.setmy.linguistics.models.token.EventType.PARSING_END;

public class Parser {

    private final Map<EventType, List<EventHandler>> eventHandlers = new HashMap<>();
    private final EventDecider eventDecider = new EventDecider();

    public List<Token> parse(final String content) {
        final ParseTraversal traversal = new ParseTraversal(content.toCharArray());
        handle(PARSING_BEGIN, traversal);
        for (char character : traversal.getCharacters()) {
            traversal.setCurrentToken(character);
            detectTokenTypeChangeAsEvents(traversal).forEach(eventType -> handle(eventType, traversal));
            traversal.incrementIndex();
        }
        handle(PARSING_END, traversal);
        return traversal.getParsedTokens();
    }

    private List<EventType> detectTokenTypeChangeAsEvents(final ParseTraversal traversal) {
        return eventDecider.decide(traversal);
    }

    public Parser addEventHandler(final EventHandler eventHandler) {
        for (EventType eventType : EventType.values()) {
            addEventHandler(eventType, eventHandler);
        }
        return this;
    }

    public Parser addEventHandler(final EventType eventType, final EventHandler eventHandler) {
        final List<EventHandler> eventHandlersList = getEventHandlersList(eventType);
        eventHandlersList.add(eventHandler);
        return this;
    }

    private Parser handle(final EventType eventType, final ParseTraversal traversal) {
        getEventHandlersList(eventType).forEach(eventHandler -> {
            eventHandler.hande(eventType, traversal);
        });
        return this;
    }

    private List<EventHandler> getEventHandlersList(final EventType eventType) {
        List<EventHandler> list = eventHandlers.get(eventType);
        if (list == null) {
            list = new ArrayList<>();
            eventHandlers.put(eventType, list);
        }
        return list;
    }

}
