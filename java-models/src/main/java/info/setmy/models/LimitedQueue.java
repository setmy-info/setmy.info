package info.setmy.models;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class LimitedQueue<T extends Object> extends ArrayBlockingQueue<T> {

        public LimitedQueue(int i) {
            super(i);
        }

        @Override
        public boolean add(final T t) {
            if (remainingCapacity() == 0) {
                poll();
            }
            return super.add(t);
        }
    }

