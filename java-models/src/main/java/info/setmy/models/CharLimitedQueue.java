package info.setmy.models;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class CharLimitedQueue {

    private final char[] array;

    private int remainingCapacity;

    private int position;

    private final int lastPosition;

    public CharLimitedQueue(final int limit) {
        array = new char[limit];
        lastPosition = array.length - 1;
        clear();
    }

    public final void clear() {
        remainingCapacity = array.length;
        position = 0;
    }

    public void add(final char character) {
        if (queueIsFull()) {
            shiftArrayLeft();
            array[lastPosition] = character;
        } else {
            array[position] = character;
            remainingCapacity--;
            position++;
        }
    }

    private void shiftArrayLeft() {
        for (int i = 1; i < array.length; i++) {
            array[i - 1] = array[i];
        }
    }

    private boolean queueIsFull() {
        return remainingCapacity == 0;
    }

    @Override
    public String toString() {
        return new String(getArray());
    }

    public char[] getArray() {
        final int numberToCopy = position;
        final char[] newArray = new char[numberToCopy];
        System.arraycopy(array, 0, newArray, 0, numberToCopy);
        return newArray;
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    public int getPosition() {
        return position;
    }
}
