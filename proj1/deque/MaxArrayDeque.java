package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    Comparator<T> com;

    /** Creates a MaxArrayDeque with the given Comparator.
     *
     * @param c the given Comparator.
     */
    public MaxArrayDeque(Comparator<T> c) {
        com = c;
    }

    /** The max item of MaxArrayDeque.
     *
     * @return  the maximum element in the deque as governed by the Comparator com.
     *          If the MaxArrayDeque is empty, simply return null.
     */
    public T max() {
        int maxIndex = 0;
        if (super.size() == 0) {
            return null;
        }
        for (int i = 0; i < super.size(); i++) {
            T maxItem = super.get(maxIndex);
            T currentItem = super.get(i);
            if (com.compare(currentItem, maxItem) > 0) {
                maxIndex = i;
            }
        }
        return super.get(maxIndex);
    }

    /** The max item of MaxArrayDeque.
     *
     * @return  the maximum element in the deque as governed by the parameter Comparator c.
     *          If the MaxArrayDeque is empty, simply return null.
     */
    public T max(Comparator<T> c) {
        int maxIndex = 0;
        if (super.size() == 0) {
            return null;
        }
        for (int i = 0; i < super.size(); i++) {
            T maxItem = super.get(maxIndex);
            T currentItem = super.get(i);
            if (c.compare(currentItem, maxItem) > 0) {
                maxIndex = i;
            }
        }
        return super.get(maxIndex);
    }
}
