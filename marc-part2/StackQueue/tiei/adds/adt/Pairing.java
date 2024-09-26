package tiei.aads.adt;

import java.io.*;
import java.util.*;

/**
 * A class to find pairs (x,y) of integers inside a file
 * matching y = x + n for a given n.
 */
public class Pairing {
	
	/**
	 * Display all the pairs (x,y), x and y  in the file, such that y = x + n
	 * The file 'fileName' contains an entirely increasing (strict) sequence of integers
	 */
	public static void showPairs(int n, String fileName) throws FileNotFoundException, EmptyQueueException {
		StackQueue<Integer> queue = new StackQueue<>();
		Scanner scanner = new Scanner(new File(fileName));

		while (scanner.hasNextInt()) {
			int currentNumber = scanner.nextInt();
			while (!queue.isEmpty() && queue.peek() + n < currentNumber) {
				queue.poll();
			}
			if (!queue.isEmpty() && queue.peek() + n == currentNumber) {
				System.out.println("(" + queue.peek() + ", " + currentNumber + ")");
				queue.poll();
			}
			queue.offer(currentNumber);
		}

		scanner.close();
	}
	
    /**
     * A short main for quick testing
     */
	public static void main(String[] args) throws FileNotFoundException, EmptyQueueException {
		// put the right path here
		showPairs(1273,"C:\\Users\\YQzhou\\Desktop\\Pairing\\tiei\\adds\\adt\\big-file.txt");
	}

}
