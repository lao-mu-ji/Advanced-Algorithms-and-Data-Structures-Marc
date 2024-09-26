package tiei.aads.adt;

import java.util.*;

/**
 * A class to arrange train configuration
 */
public class TrainManagement {
	
	private String[] from; // the initial ordering
	private String[] to;   // the final ordering
	
	private Stack<String> U; // to hold the cars on track U(nsorted)
	private Stack<String> T; // to hold the cars on track T(emporary)
	private Stack<String> S; // to hold the cars on track S(orted)
	

	
	/**
	 * Build a TrainManagement object
	 * Preconditions:
	 * 'from' and 'to' have the same size N and are
	 * both permutations of each other
	 */
	public TrainManagement(String[] from, String[] to) {
		this.from = from;
		this.to = to;
		U = new Stack<>();
		T = new Stack<>();
		S = new Stack<>();

		for (int i = from.length - 1; i >= 0; i--) {
			U.push(from[i]);
		}
	}

	/**
	 * Output the basic move commands to transfer
	 * the cars from the 'from' order on track U
	 * to the 'to' order on track S
	 */
	public void arrange() throws EmptyStackException {
//		int toIndex = 0;
//
//		while (toIndex < to.length) {
//			if (T.isEmpty() || !T.peek().equals(to[toIndex])) {
//				if (!U.isEmpty()) {
//					String car = U.pop();
//					T.push(car);
//					System.out.println("move car " + car + " from U to T");
//				} else {
//					while (!T.isEmpty() && !T.peek().equals(to[toIndex])) {
//						String car = T.pop();
//						U.push(car);
//						System.out.println("move car " + car + " from T to U");
//					}
//				}
//			} else {
//				String car = T.pop();
//				S.push(car);
//				System.out.println("move car " + car + " from T to S");
//				toIndex++;
//			}
//		}
		int toIndex = 0;
		while(toIndex < to.length){
			if(T.isEmpty() || T.peek() != to[toIndex]){
				if(!U.isEmpty()){
					String car = U.pop();
					T.push(car);
					System.out.println("将"+car+"由U到T");
				}else{
					while(!T.isEmpty() && T.peek() != to[toIndex]){
						String car = T.pop();
						U.push(car);
						System.out.println("将"+car+"由T到U");
					}
				}
			}else{
				String car = T.pop();
				S.push(car);
				System.out.println("将"+car+"由T到S");
				toIndex++;
			}
		}
	}
	
	
    /**
     * A short main for quick testing
     */
	public static void main(String[] args) throws EmptyStackException {
		String[] from = { "33", "11", "44", "22" };
		String[] to   = { "11", "22", "33", "44" };
		TrainManagement tm = new TrainManagement(from,to);
		tm.arrange();
	}
	// expected output
	//
	// move car 33 from U to T
	// move car 11 from U to T
	// move car 11 from T to S
	// move car 44 from U to T
	// move car 22 from U to T
	// move car 22 from T to S
	// move car 44 from T to U
	// move car 33 from T to S
	// move car 44 from U to T
	// move car 44 from T to S
}
