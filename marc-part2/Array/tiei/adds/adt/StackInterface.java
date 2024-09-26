package tiei.aads.adt;

/**
 * A generic interface for the Stack type
 */
public interface StackInterface<AnyType> {
	
	/**
	 * Return the size of the stack
	 */
	public int size();
	
	
	/**
	 * Check if the stack is empty
	 */
	default public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Push the value item onto the stack.
	 */
	public void push(AnyType item) throws EmptyStackException;
	
	/**
	 * Pop the stack and return the value popped
	 * Throw EmptyStackException if the stack is empty
	 */
	public AnyType pop() throws EmptyStackException;
	
	/**
	 * Return the next value to be popped from the stack
	 * Throw EmptyStackException if the stack is empty
	 */	
	public AnyType peek() throws EmptyStackException;

	/**
	 * Empty the stack
	 */
	public void clear();

}
