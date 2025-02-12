package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] content;
    private int size;
    private int capacity;
    private int nextFirst;
    private int nextLast;

    /** Create a iterator into ME.
     *
     * @return  The iterator from the interface.
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    /** My own iterator.
     *  When iterator() is called, what is actually called is my own iterator.
     */
    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;
        ArrayDequeIterator() {
            wizPos = (nextFirst + 1) % capacity;
        }
        @Override
        public boolean hasNext() {
            return (wizPos != nextLast);
        }
        @Override
        public T next() {
            T item = content[wizPos];
            wizPos = (wizPos + 1) % capacity;
            return item;
        }
    }
    /** Create a ArrayDeque.    */
    public ArrayDeque() {
        capacity = 8;
        size = 0;
        content = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
    }
    /** Most tricky part: resize the array. */
    private void resize() {
        if (size == capacity) {
            int newCapacity = capacity * 2;
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(content, 0, newArray, 0, nextFirst + 1);
            System.arraycopy(content, nextLast, newArray,
                    nextLast + capacity, (capacity - nextLast) % capacity);
            nextLast = nextFirst + 1;
            nextFirst = nextLast + capacity - 1;
            capacity = newCapacity;
            content = newArray;
        } else {
            int newCapacity = capacity / 2;
            T[] newArray = (T[]) new Object[newCapacity];
            for (int i = 0; i < capacity; i += 1) {
                if (content[i] != null) {
                    int newIndex = i % newCapacity;
                    newArray[newIndex] = content[i];
                }
            }
            nextFirst = nextFirst % newCapacity;
            nextLast = nextLast % newCapacity;
            capacity = newCapacity;
            content = newArray;
        }
    }

    /** Add first item and make nextFirst increase.
     *
     * @param item The content of the new element.
     */
    @Override
    public void addFirst(T item) {
        //  before the addition.
        if (size == capacity) {
            resize();
        }
        size += 1;
        content[nextFirst] = item;
        nextFirst = (nextFirst + capacity - 1) % capacity;
    }

    /** Add last item and make nextLast decrease.
     *
     * @param item The content of the new element.
     */
    @Override
    public void addLast(T item) {
        //  before the addition.
        if (size == capacity) {
            resize();
        }
        size += 1;
        content[nextLast] = item;
        nextLast = (nextLast + 1) % capacity;
    }

    /** Returns the number of items in the deque.   */
    @Override
    public int size() {
        return size;
    }

    /** Prints item in the deque from first to last, separated by space.
     *  Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        for (T x : this) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        size -= 1;
        //  before the removal action.
        if (capacity >= 16 && size * 4 < capacity) {
            resize();
        }
        nextFirst = (nextFirst + 1) % capacity;
        T item = content[nextFirst];
        content[nextFirst] = null;
        return item;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        size -= 1;
        //  before the removal action.
        if (capacity >= 16 && size * 4 < capacity) {
            resize();
        }
        nextLast = (nextLast + capacity - 1) % capacity;
        T item = content[nextLast];
        content[nextLast] = null;
        return item;
    }

    /** Gets the item at the given index,
     *  where 0 is the front, 1 is the next item, and so forth.
     *  If no such item exists, returns null.
     *
     * @param index
     * @return  The item at the given index.
     */
    @Override
    public T get(int index) {
        if (index >= capacity) {
            return null;
        }
        int trueIndex = (nextFirst + 1 + index) % capacity;
        return content[trueIndex];
    }

    /** Returns whether the parameter o is equal to the Deque.
     *  o is considered equal if it is a Deque,
     *  and if it contains the same contents in the same order.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Deque) {
            Deque<T> oList = (Deque<T>) o;
            //  check lists are of the same size.
            if (oList.size() != this.size()) {
                return false;
            }
            // check that all of MY items are in the other list and have the same sequence.
            for (int i = 0; i < this.size(); i += 1) {
                if (!this.get(i).equals(get(i))) {
                    return false;
                }
            }

            return true;
        }
        //  o is not an ArrayDeque.
        return false;
    }

}
