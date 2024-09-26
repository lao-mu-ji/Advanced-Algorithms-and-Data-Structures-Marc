package tiei.aads.adt;

import tiei.aads.util.TestClass;

import java.util.Scanner;

/**
 * A class for interactive testing list-based queues
 */
public class TestQueue extends TestClass<TestQueue> {

	private QueueInterface<String> queue;
	private Scanner input;
	
	public TestQueue() {
		queue = new ListQueue<String>();
		input = new Scanner(System.in);
	}
	
	public void queueType() {
		while ( true ) {
			System.out.print("choose queue type ('1' for ListQueue, '2' for StackQueue): ");
			String q = input.nextLine();
			if (q.equals("1")) {
				queue = new ListQueue<>();
				break;
			}
//			if (q.equals("2")) {
//				queue = new StackQueue<>();
//				break;
//			}
		}
	}
	
	public void isEmpty() {
		if ( queue.isEmpty() )
			System.out.println("the queue is empty");
		else
			System.out.println("the queue is not empty");
	}
	
	public void size() {
		System.out.println("the size of the queue is " + queue.size());
	}
	
	public void peek() {
		try {
			System.out.println(queue.peek());
		}
		catch ( EmptyQueueException eqe ) {
			System.out.println("oops! the queue is empty!");
		}
	}
	
	public void offer() {
		System.out.print("next string to enqueue ? ");
		String s = input.next();
		queue.offer(s);
	}
	
	public void poll() {
		try {
			System.out.println(queue.poll());
		}
		catch ( EmptyQueueException eqe ) {
			System.out.println("oops! the queue is empty!");
		}		
	}
	
	public void showQueue() {
		System.out.println(queue);
	}
	
    public static void main(String[] args) {
    	TestQueue test = new TestQueue();
        test.tester();
    }
}