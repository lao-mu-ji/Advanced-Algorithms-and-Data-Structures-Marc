package tiei.aads.adt;

/**
 * An array-based stack class
 */
public class ArrayStack<AnyType> implements StackInterface<AnyType> {

	private static final int DEFAULT_CAPACITY = 500;
	private AnyType[] theArray;
	private int topIndex;

	@SuppressWarnings("unchecked")
	public ArrayStack() {
		theArray = (AnyType[]) new Object[DEFAULT_CAPACITY];
		topIndex = -1;
	}

	@Override
	public int size() {
		return topIndex + 1;
	}

	@Override
	public void push(AnyType item) {
		if (size() == theArray.length) {
			expandCapacity();
		}
		theArray[++topIndex] = item;
	}

	@Override
	public AnyType pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return theArray[topIndex--];
	}

	@Override
	public AnyType peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return theArray[topIndex];
	}

	@Override
	public void clear() {
		topIndex = -1;
	}

	private void expandCapacity() {
		AnyType[] oldArray = theArray;
		theArray = (AnyType[]) new Object[oldArray.length * 2];
		System.arraycopy(oldArray, 0, theArray, 0, oldArray.length);
	}

    /**
     * A short main for quick testing
     */
	public static void main(String[] args) throws EmptyStackException {
		ArrayStack<Integer> stack = new ArrayStack<Integer>();
		for ( int i = 1; i <= 100; i++ )
			stack.push(i);
		int i = 0;
		while ( ! stack.isEmpty() ) {
			if ( i++ % 25 == 0 )
				System.out.println();
			System.out.print(stack.pop() + " ");
		}
			
		System.out.println();
	}
	// expected output:
	//
	// 100 99 98 97 96 95 94 93 92 91 90 89 88 87 86 85 84 83 82 81 80 79 78 77 76 
	// 75 74 73 72 71 70 69 68 67 66 65 64 63 62 61 60 59 58 57 56 55 54 53 52 51 
	// 50 49 48 47 46 45 44 43 42 41 40 39 38 37 36 35 34 33 32 31 30 29 28 27 26 
	// 25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
}
