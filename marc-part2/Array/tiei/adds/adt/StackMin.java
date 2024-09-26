package tiei.aads.adt;
/*In terms of space complexity, it should be O(n), using two stacks, one
for storing numerical values and the other for storing the minimum value at
the current stack state, so a total of 2n space is required.*/
/**
 * A class for stacks supporting the findMin operation in THETA(1)
 */
public class StackMin<AnyType extends Comparable<AnyType>> implements StackInterface<AnyType> {

	private ArrayStack<AnyType> stack;
	private ArrayStack<AnyType> minStack;

	public StackMin() {
		stack = new ArrayStack<>();
		minStack = new ArrayStack<>();
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public void push(AnyType item) throws EmptyStackException {
		stack.push(item);
		if(minStack.isEmpty()){
			minStack.push(item);
		}else{
			minStack.push(item.compareTo(minStack.peek()) <= 0 ? item : minStack.peek());
		}
//		if (minStack.isEmpty() || item.compareTo(minStack.peek()) <= 0) {
//			minStack.push(item);
//		}
	}

	@Override
	public AnyType pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		AnyType item = stack.pop();
		minStack.pop();
		return item;
	}

	@Override
	public AnyType peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.peek();
	}

	@Override
	public void clear() {
		stack.clear();
		minStack.clear();
	}

	public AnyType findMin() throws EmptyStackException {
		if (minStack.isEmpty()) {
			throw new EmptyStackException();
		}
		return minStack.peek();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}


	/**
     * A short main for quick testing
     */
	public static void main(String[] args) throws EmptyStackException {
		StackMin<Integer> s = new StackMin<Integer>();
		s.push(3); s.push(1); s.push(2);
		System.out.println(s.findMin());
		s.pop(); s.pop(); s.push(5);
		System.out.println(s.findMin());
		s.push(2); s.push(4); s.push(6);
		System.out.println(s.findMin());
	}
	// expected output
	//
	// 1
	// 3
	// 2
}
