package tiei.aads.adt;

import java.util.*;
import java.io.*;

/**
 * A class to compute the span of stock values
 */
public class StockSpan {
	
	private Scanner input;
	private PrintStream output;
	
	public StockSpan(Scanner input, PrintStream output) {
		this.input = input;
		this.output = output;
	}
	
	/**
	 * Simple computeSpan static function
	 * working on arrays
	 */
	public static int[] computeSpan(int[] input) {
		int n = input.length;
		int[] spans = new int[n];
		spans[0] = 1;

		for (int i = 1; i < n; i++) {
			spans[i] = 1;
			for (int j = i - 1; (j >= 0) && (input[i] >= input[j]); j--) {
				spans[i]++;
			}
		}
		return spans;
	}

	/**
	 * Compute the span of the input values
	 * using a stack.
	 * Complexity: THETA(n) where is n is the 
	 * number of values to compute the span of
	 */
	public void computeSpan() {
		Stack<Integer[]> stack = new Stack<>();
		int index = 0;
		while (input.hasNextInt()) {
			int price = input.nextInt();

			while(!stack.empty()&&stack.peek()[0] <= price){
				stack.pop();
			}

			int span = stack.empty() ? 1 : index - stack.peek()[1];

//			stack.push(price);
			output.println("value: " + price + " span: " + span);
			stack.push(new Integer[]{price, index++});
		}
	}




	public static void main(String[] args) throws FileNotFoundException {
//		int[] a = {100, 80, 60, 70, 60, 75, 85};
//		int[] b = computeSpan(a);
//		System.out.println(Arrays.toString(b));
		
		Scanner input = new Scanner(new File("C:\\Users\\YQzhou\\Desktop\\Stock\\tiei\\aads\\adt\\stock.txt"));
		StockSpan ss = new StockSpan(input,System.out);
		ss.computeSpan();

	}
//  expected output:
//
//	value: 100 span: 1
//	value:  95 span: 1
//	value:  90 span: 1
//	value:  85 span: 1
//	value:  80 span: 1
//	value:  75 span: 1
//	value:  70 span: 1
//	value:  93 span: 6
//	value:  87 span: 1
//	value:  82 span: 1
//	value:  77 span: 1
//	value:  72 span: 1
//	value:  94 span: 11
//	value:  91 span: 1
//	value:  88 span: 1
//	value:  85 span: 1
//	value:  82 span: 1
//	value:  83 span: 2
//	value:  86 span: 4
//	value:  89 span: 6
//	value:  92 span: 8
//	value:  95 span: 21
//	value:  97 span: 22
//	value:  99 span: 23
//	value: 101 span: 25
//	value: 103 span: 26
}
