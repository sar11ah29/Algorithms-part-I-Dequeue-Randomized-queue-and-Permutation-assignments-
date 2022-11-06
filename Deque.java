/* *****************************************************************************
 *  Name:              Sarah Modhfar
 *  Coursera User ID:  f35521d656432242756e03e85802eb89
 *  Last modified:     November 5, 2022
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int numberOfItems;

    // construct an empty deque
    public Deque() {

    }

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (this.numberOfItems == 0);
    }

    // return the number of items on the deque
    public int size() {
        return this.numberOfItems;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("Can not Add a null item to the deque!!!? ");
        }
        Node newFirst = new Node();
        newFirst.item = item;
        if (numberOfItems == 0) {
            first = newFirst;
            last = newFirst;
        }
        else {
            newFirst.next = first;
            first.prev = newFirst;
            first = newFirst;
            newFirst.prev = null;
        }
        numberOfItems++;

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException(
                    "Can not Add a null item to the deque!!!? ");
        }
        Node newlast = new Node();
        newlast.item = item;
        if (numberOfItems == 0) {
            first = newlast;
            last = newlast;
        }
        else {
            newlast.prev = last;
            last.next = newlast;
            last = newlast;
            last.next = null;
        }
        numberOfItems++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (numberOfItems == 0) {
            throw new java.util.NoSuchElementException(
                    "The deque is empty, Can not remove an item from empty deque!!!? ");
        }
        Item item = first.item;
        if (numberOfItems == 1) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }
        numberOfItems--;
        return item;
    }

    // remove and return the[
    // item from the back
    public Item removeLast() {
        if (numberOfItems == 0) {
            throw new java.util.NoSuchElementException(
                    "The deque is empty, Can not remove an item from empty deque!!!? ");
        }
        Item item = last.item;

        if (numberOfItems == 1) {
            first = null;
            last = null;
        }
        else {
            last = last.prev;
            last.next = null;

        }
        numberOfItems--;
        return item;

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove method not supported!!!?");
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("");

            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addLast("A");
        deque.addFirst("B");
        deque.addLast("C");
        deque.removeFirst();
        deque.addLast("D");
        deque.removeLast();
        deque.addLast("E");
        deque.addFirst("F");
        deque.addLast("G");
        deque.addFirst("H");
        System.out.println("Deque elements are");
        for (String i : deque) {
            System.out.print(i + " ");
        }
        System.out.print("The size is: " + deque.size());
        System.out.println();

    }
}



