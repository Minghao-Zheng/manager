package deque;

import java.util.Iterator;

/** Create a double-ended linked list.  */
public class LinkedListDeque<T> implements Iterable<T>{
    /** A naked linked list inside our DLList. */
    private static class SubNode<T> {
        public T content;
        public SubNode<T> next;
        public SubNode<T> pre;

        /** Create a SubNode.
         *
         * @param c The content of SubNode.
         * @param p The present node of SubNode.
         * @param n The next node of SubNode.
         */
        public SubNode(T c, SubNode<T> p, SubNode<T> n) {
            content = c;
            next = n;
            pre = p;
        }
    }
    /** A private sentinel node and a private size. */
    private SubNode<T> sentinel;
    private int size;

    /** Returns a iterator into ME. */
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    /** My own iterator.
     *  When iterator() is called, what is actually called is my own iterator.
     */
    private class LinkedListDequeIterator implements Iterator<T> {
        private SubNode<T> node;
        public LinkedListDequeIterator() {
            node = sentinel.next;
        }
        public boolean hasNext() {
            return (node.next != sentinel);
        }
        public T next() {
            T returnItem = node.content;
            node = node.next;
            return returnItem;
        }
    }

    /** Create a LinkedListDeque.   */
    public LinkedListDeque() {
        size = 0;
        sentinel = new SubNode(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
    }

    /** Add the first item.
     *
     * @param item  The content of the new node.
     */
    public void addFirst(T item) {
        sentinel.next = new SubNode<>(item, sentinel, sentinel.next);
        sentinel.next.next.pre = sentinel.next;
        size += 1;
    }

    /** Add the last item.
     *
     * @param item  The same as addFirst.
     */
    public void addLast(T item) {
        sentinel.pre = new SubNode<>(item, sentinel.pre, sentinel);
        sentinel.pre.pre.next = sentinel.pre;
        size += 1;
    }

    /** Check if the deque is empty.
     *
     * @return  True if the deque is empty and False if not.
     * */
    public boolean isEmpty() {
        return (size == 0);
    }

    /** Returns the number of items in the deque.   */
    public int size() {
        return size;
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     */
    public T removeFirst() {
        size -= 1;
        SubNode<T> f = sentinel.next;
        if (f == sentinel) {
            return null;
        }
        T item = f.content;
        sentinel.next = f.next;
        f.next.pre = sentinel;
        f = null;
        return item;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.
     */
    public T removeLast() {
        size -= 1;
        SubNode<T> f = sentinel.pre;
        if (f == sentinel) {
            return null;
        }
        T item = f.content;
        sentinel.pre = f.pre;
        f.pre.next = sentinel;
        f = null;
        return item;
    }

    /** Prints item in the deque from first to last, separated by space.
     *  Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        SubNode<T> node;
        for (node = sentinel.next ; node.next != sentinel; node = node.next) {
            System.out.print(node.content + " ");
        }
        if (node != sentinel) {
            System.out.print(node.content);
        }
        System.out.println();
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *  If no such item exists, returns null.
     *
     * @param index
     * @return  The item at the given index.
     */
    public T get(int index) {
        Iterator<T> seer = this.iterator();
        for (int i = 0; i < index; i += 1) {
            if (!seer.hasNext()) {
                return null;
            } else {
                seer.next();
            }
        }
        return seer.next();
    }

    /** Same as get, but uses recursion.
     *
     * @param index
     * @return  The item at the given index.
     */
    public T getRecursive(int index) {
        SubNode<T> node = sentinel.next;
        return myGetRecursive(index, node);
    }

    /** My helper method for recursive getRecursive method.
     *
     * @param index Required offset from the current node.
     * @param node  The current node.
     * @return  The item we need.
     */
    private T myGetRecursive(int index, SubNode<T> node) {
        if (node == sentinel) {
            return null;
        } else if (index == 0) {
            return node.content;
        } else {
            return myGetRecursive(index - 1, node.next);
        }
    }

    /** Returns whether or not the parameter o is equal to the Deque.
     *  o is considered equal if it is a Deque and if it contains the same contents in the same order.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof LinkedListDeque) {
            LinkedListDeque<T> oList = (LinkedListDeque<T>) o;
            SubNode<T> node = oList.sentinel.next;
            // check lists are of the same size.
            if (oList.size != this.size) {
                return false;
            }
            // check that all of MY items are in the other list and have the same sequence.
            for (T x : this) {
                if (node.content != x) {
                    return false;
                }
                node = node.next;
            }

            return true;
        }
        // o is not a LinkedListDeque.
        return false;
    }
}
