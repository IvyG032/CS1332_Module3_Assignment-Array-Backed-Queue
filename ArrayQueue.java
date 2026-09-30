import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayQueue.
 *
 * @author Zichao Guan
 * @version 1.0
 */
public class ArrayQueue<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    public static final int INITIAL_CAPACITY = 9;

    /*
     * Do not change the access modifiers of these variables.
     */
    private T[] backingArray;
    private int front;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     *
     * Initializes the backing array to the INITIAL_CAPACITY,
     * and sets front and size to their starting values.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        front = 0;
        size = 0;
    }

    /**
     * Adds the data to the back of the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow the array to double its current length. Don't forget to unwrap
     * the elements so that the front is reset to index 0.
     *
     * Must be O(1) amortized.
     *
     * @param data the data to add to the back of the queue
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void enqueue(T data) {
        // check for null data
        if (data == null) {
            throw new IllegalArgumentException("Error: Data cannot be null");
        }

        // resize
        if (size == backingArray.length) {
            T[] newBackingArray = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < size; i ++) {
                newBackingArray[i] = backingArray[(front + i) % backingArray.length];
            }
            backingArray = newBackingArray;
            front = 0;
        }

        // enqueue new data
        backingArray[(front + size) % backingArray.length] = data;
        size ++;
    }

    /**
     * Removes and returns the data from the front of the queue.
     *
     * Do not shrink the backing array.
     *
     * Replace any spots that you dequeue from with null.
     *
     * If the queue becomes empty as a result of this call, do not explicitly
     * reset front to 0.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        // check if array is empty
        if (size == 0) {
            throw new NoSuchElementException("Error: The array is empty");
        }

        // dequeue and return data
        T data = backingArray[front];
        backingArray[front] = null;
        front = (front + 1) % backingArray.length;
        size --;
        return data;
    }

    /**
     * Returns the data from the front of the queue without removing it.
     *
     * Must be O(1).
     *
     * @return the data located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T peek() {
        if (size == 0) {
            throw new NoSuchElementException("Error: The array is empty");
        }
        return backingArray[front];
    }

    /**
     * Returns the backing array of the queue.
     *
     * DO NOT MODIFY THIS METHOD.
     *
     * @return the backing array of the queue
     */
    public T[] getBackingArray() {
        return backingArray;
    }

    /**
     * Returns the front index of the queue.
     *
     * DO NOT MODIFY THIS METHOD.
     *
     * @return the front index of the queue
     */
    public int getFront() {
        return front;
    }

    /**
     * Returns the size of the queue.
     *
     * DO NOT MODIFY THIS METHOD.
     *
     * @return the size of the queue
     */
    public int size() {
        return size;
    }
}