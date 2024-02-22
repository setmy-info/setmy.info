package info.setmy.linguistics;

import info.setmy.linguistics.models.token.EventType;

public interface EventHandler {

    void hande(final EventType eventType, final ParseTraversal traversal);
}
