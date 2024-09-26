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
		// 定义一个整型队列，用于存储文件中读取的整数
		ListQueue<Integer> queue = new ListQueue<>();
		// 使用 Scanner 类来读取文件中的内容
		Scanner scanner = new Scanner(new File(fileName));

		// 循环，只要文件中还有下一个整数，就继续执行
		while (scanner.hasNextInt()) {
			// 读取文件中的下一个整数
			int currentNumber = scanner.nextInt();
			// 当队列不为空，并且队列头部元素加上 n 小于当前读取的整数时，移除队列头部元素
			// 这是为了确保队列中只保留可能与当前整数形成配对的元素
			while (!queue.isEmpty() && queue.peek() + n < currentNumber) {
				queue.poll();
			}
			// 如果队列不为空，并且队列头部元素加上 n 等于当前整数，则打印这对配对
			// 并从队列中移除这个头部元素，因为它已经找到了配对
			if (!queue.isEmpty() && queue.peek() + n == currentNumber) {
				System.out.println("(" + queue.peek() + ", " + currentNumber + ")");
				queue.poll();
			}
			// 将当前整数添加到队列中，以便后续可能形成配对
			queue.offer(currentNumber);
		}

		// 关闭 Scanner 对象，释放资源
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
