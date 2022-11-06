/* *****************************************************************************
 *  Name:              Sarah Modhfar
 *  Coursera User ID:  f35521d656432242756e03e85802eb89
 *  Last modified:     November 5, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int queueSize;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        queueSize = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (queueSize == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return queueSize;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("Can not Add a null item to the deque!!!? ");
        }
        if (queueSize == queue.length) {
            Item[] resizeQueue = (Item[]) new Object[queue.length * 2];
            for (int i = 0; i < queue.length; i++) {
                resizeQueue[i] = queue[i];
            }
            queue = resizeQueue;
        }
        queue[queueSize++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (queueSize == 0) {
            throw new NoSuchElementException("The RandomizeQueue is empty!!!? ");
        }
        int seed = StdRandom.uniformInt(0, queueSize);
        Item item = queue[seed];
        queueSize--;
        queue[seed] = queue[queueSize];
        queue[queueSize] = null;
        if (queue.length > 4 && queueSize < queue.length / 4) {
            Item[] resizeQueue = (Item[]) new Object[queue.length / 2];
            for (int i = 0; i < queue.length; i++) {
                resizeQueue[i] = queue[i];
            }
            queue = resizeQueue;
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (queueSize == 0) {
            throw new NoSuchElementException("The RandomizeQueue is empty!!!? ");
        }
        return queue[StdRandom.uniformInt(0, queueSize)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new IndependentIterator(queue, queueSize);

    }

    private class IndependentIterator implements Iterator<Item> {
        private Item[] iteratorQueue;
        private int index = 0;

        public IndependentIterator(Item[] queue, int size) {
            iteratorQueue = (Item[]) new Object[size];
            for (int i = 0; i < iteratorQueue.length; i++) {
                iteratorQueue[i] = queue[i];
            }
            for (int j = 1; j < iteratorQueue.length; j++) {
                int swap = StdRandom.uniformInt(j + 1);
                Item swapItem = iteratorQueue[j];
                iteratorQueue[j] = iteratorQueue[swap];
                iteratorQueue[swap] = swapItem;
            }
        }

        @Override
        public boolean hasNext() {
            return (index < iteratorQueue.length);
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
            Item item = iteratorQueue[index];
            index++;
            return item;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("A");
        randomizedQueue.enqueue("B");
        randomizedQueue.enqueue("C");
        randomizedQueue.dequeue();
        randomizedQueue.enqueue("D");
        randomizedQueue.dequeue();
        randomizedQueue.enqueue("E");
        randomizedQueue.enqueue("F");
        randomizedQueue.enqueue("G");
        randomizedQueue.enqueue("H");
        for (String i : randomizedQueue) {
            System.out.print(i + " ");
        }
        System.out.print("The size is: " + randomizedQueue.size());
        System.out.println();
    }
}
