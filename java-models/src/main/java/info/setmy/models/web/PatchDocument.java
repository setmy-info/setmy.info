package info.setmy.models.web;

import java.util.ArrayList;

/**
 * PATCH request by https://tools.ietf.org/html/rfc6902
 *
 * @param <T> type of key
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class PatchDocument<T extends Object> extends ArrayList<Patch> {

    private final T key;

    public PatchDocument(final T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }
}
