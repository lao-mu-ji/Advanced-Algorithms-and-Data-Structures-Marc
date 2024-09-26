package tiei.aads.adt;

/**
 * A generic interface for the Queue type
 */
public interface QueueInterface<AnyType> {
	
	/**
	 * Return the number of elements
	 * currently in the queue
	 */
	public int size();
	
	
	default public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Add item in the queue
	 */
	public void offer(AnyType item);
	
	/**
	 * Remove and return the next element to
	 * be dequeued.
	 * If the queue is empty throws EmptyQueueException
	 */
	public AnyType poll() throws EmptyQueueException;
	
	/**
	 * Return the next value to be dequeued.
	 * If the queue is empty throws EmptyQueueException
	 */
	public AnyType peek() throws EmptyQueueException;

}
