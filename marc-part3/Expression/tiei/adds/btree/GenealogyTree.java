//
// Advanced Algorithms and Data Structures
// TIEI - Fall 2024
//
package tiei.aads.btree;

//import tiei.aads.adt.*;

import java.util.*;

/**
 * A class for genealogy 
 */
public class GenealogyTree extends BinaryNode<String> {
	
	/**
	 * Build an orphan node of name 'name'
	 * (no father/mother genealogy)
	 */
	public GenealogyTree(String name) {
		super(name);
	}
	
	/**
	 * Build a genealogy node of name 'name'
	 * with 'father' the genealogy of the father
	 * and 'mother' the genealogy of the mother
	 */
	public GenealogyTree(String name,
						 GenealogyTree father,
						 GenealogyTree mother) {
		super(name,father,mother);
	}
	
	//////////////////////
	// ancestors
	
	/**
	 * Return the list of all ancestors from
	 * the this genealogy at level 'level'
	 */
	public List<String> ancestors(int level) {
//		List<String> result = new ArrayList<String>();
//		BinaryNode<String> t = this;
//		List<BinaryNode<String>> queue = new LinkedList<BinaryNode<String>>();
//		queue.add(t);
//		for(int i=0; i<level; i++){
//			while(!queue.isEmpty()) {
//				t = queue.remove(0);
//				if (t.getLeft() != null) {
//					queue.add(t.getLeft());
//				}
//				if (t.getRight() != null) {
//					queue.add(t.getRight());
//				}
//			}
//		}
//		//for循环里面的内容逻辑有问题，每次循环都会循环至队列为空
//		while(!queue.isEmpty()) {
//			t = queue.remove(0);
//			result.add(t.getData());
//		}
//		return result;
		List<String> result = new ArrayList<>();
		Queue<BinaryNode<String>> queue = new LinkedList<>();
		queue.add(this);

		int currentLevel = 0;

		while (!queue.isEmpty() && currentLevel < level) {
			int levelSize = queue.size();
			for (int i = 0; i < levelSize; i++) {
				BinaryNode<String> currentNode = queue.poll();

				if (currentNode.getLeft() != null) {
					queue.add(currentNode.getLeft());
				}
				if (currentNode.getRight() != null) {
					queue.add(currentNode.getRight());
				}
			}
			currentLevel++;
		}

		while (!queue.isEmpty()) {
			BinaryNode<String> node = queue.poll();
			result.add(node.getData());
		}

		return result;
	}
	
	//////////////////////
	// maleAncestors
	
	/**
	 * Return the list of all male ancestors from
	 * the this genealogy at level 'level'
	 */	
	public List<String> maleAncestors(int level) {
//		List<String> result = new ArrayList<String>();
//		BinaryNode<String> t = this;
//		List<BinaryNode<String>> queue = new LinkedList<BinaryNode<String>>();
//		queue.add(t);
//		for(int i=0; i<level - 1; i++){
//			while(!queue.isEmpty()) {
//				t = queue.remove(0);
//				if (t.getLeft() != null) {
//					queue.add(t.getLeft());
//				}
//				if (t.getRight() != null) {
//					queue.add(t.getRight());
//				}
//			}
//		}
//		while(!queue.isEmpty()) {
//			t = queue.remove(0);
//			if(t.getLeft()!= null){
//				result.add(t.getLeft().getData().toString());
//			}
//		}
//		return result;
		List<String> result = new ArrayList<>();
		Queue<BinaryNode<String>> queue = new LinkedList<>();
		queue.add(this);

		int currentLevel = 0;

		while (!queue.isEmpty() && currentLevel < level - 1) {
			int levelSize = queue.size();
			for (int i = 0; i < levelSize; i++) {
				BinaryNode<String> currentNode = queue.poll();

				if (currentNode.getLeft() != null) {
					queue.add(currentNode.getLeft());
				}
				if (currentNode.getRight() != null) {
					queue.add(currentNode.getRight());
				}
			}
			currentLevel++;
		}

		while (!queue.isEmpty()) {
			BinaryNode<String> node = queue.poll();
			if (node.getLeft() != null) {
				result.add(node.getLeft().getData());
			}
		}

		return result;
	}
	
	//////////////////////
	// displayGenerations
	
	/**
	 * Print all the ancestors line by lie, each line
	 * being a generation (i.e. a level in the tree)
	 */
	public void print(List<BinaryNode<String>> queue) {
		for (BinaryNode<String> node : queue) {
			System.out.print(node.getData() + " ");
		}
		System.out.println();  // 打印换行，使输出更清晰
	}

	public void displayGenerations() throws EmptyQueueException {
//		BinaryNode<String> t = this;
//		List<BinaryNode<String>> queue = new LinkedList<BinaryNode<String>>();
//		queue.add(t);
////		print(queue);
//
//		while(!queue.isEmpty()) {
//			int size = queue.size();
//			for(int i=0; i<size; i++){
//				print(queue);
//				t = queue.remove(0);
//				if (t.getLeft() != null) {
//					queue.add(t.getLeft());
//				}
//				if (t.getRight() != null) {
//					queue.add(t.getRight());
//				}
//			}
//			if(queue != null){
//				print(queue);
//			}
//		}



		Queue<BinaryNode<String>> queue = new LinkedList<>();
		queue.add(this);

		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			for (int i = 0; i < levelSize; i++) {
				BinaryNode<String> currentNode = queue.poll();
				System.out.print(currentNode.getData() + " ");

				if (currentNode.getLeft() != null) {
					queue.add(currentNode.getLeft());
				}
				if (currentNode.getRight() != null) {
					queue.add(currentNode.getRight());
				}
			}
			System.out.println();
		}
	}
	
    ////////////////////////////////////
    
	/**
	 * Return a genealogy tree whose linear form
	 * is given as the string 'inputstring'
	 */
    public static GenealogyTree read(String inputString) {
    	Scanner input = new Scanner(inputString);
    	return read(input);
    }
    
    private static GenealogyTree read(Scanner input) {
    	if ( ! input.hasNext() )
    		return null;
    	String s = input.next();
    	if ( s.equals("$") )
    		return null;
    	if ( s.endsWith("$") )
    		return new GenealogyTree(s.substring(0,s.length()-1));
    	return new GenealogyTree(s,read(input),read(input));
    }

    /**
     * A main for quick testing
     */
	public static void main(String[] args){
		GenealogyTree g = read("Edward David Carl $ Barbara Anthony$ Anna$ $ Dorothy Craig Bruce Allan$ Amanda$ $ Carol Brian Andrew$ Ann$ Brenda Albert$ Alice$");
		g.display();
		System.out.println();
		
		System.out.print("Ancestors at generation 1 (parents): ");
		for ( String s : g.ancestors(1) )
			System.out.print(s + " ");
		System.out.println();
		
		System.out.print("Ancestors at generation 2 (grand-parents): ");
		for ( String s : g.ancestors(2) )
			System.out.print(s + " ");
		System.out.println();
		
		System.out.print("Ancestors at generation 3: ");
		for ( String s : g.ancestors(3) )
			System.out.print(s + " ");
		System.out.println();
		
		System.out.print("Male ancestors at generation 1 (father): ");
		for ( String s : g.maleAncestors(1) )
			System.out.print(s + " ");
		System.out.println();
		
		System.out.print("Male ancestors at generation 2 (grand-fathers): ");
		for ( String s : g.maleAncestors(2) )
			System.out.print(s + " ");
		System.out.println();
		
		System.out.print("Male ancestors at generation 3: ");
		for ( String s : g.maleAncestors(3) )
			System.out.print(s + " ");
		System.out.println();
		
		System.out.print("Ancestors at generation 10 (empty): ");
		for ( String s : g.ancestors(10) )
			System.out.print(s + " ");
		System.out.println();
		
		System.out.println("All generations:");
		try {
			g.displayGenerations();
		}
		catch ( EmptyQueueException eqe ){}
		System.out.println();
	}
	// expected output
	//
	// Edward
	// |______ Dorothy
	// |       |_______ Carol
	// |       |        |_____ Brenda
	// |       |        |      |______ Alice
	// |       |        |      |
	// |       |        |      |______ Albert
	// |       |        |
	// |       |        |_____ Brian
	// |       |               |_____ Ann
	// |       |               |
	// |       |               |_____ Andrew
	// |       |
	// |       |_______ Craig
	// |                |_____ 
	// |                |
	// |                |_____ Bruce
	// |                       |_____ Amanda
	// |                       |
	// |                       |_____ Allan
	// |
	// |______ David
	//         |_____ 
	//         |
	//        |_____ Carl
	//                |____ Barbara
	//                |     |_______ Anna
	//                |     |
	//                |     |_______ Anthony
	//                |
	//                |____ 
	// 
	// Ancestors at generation 1 (parents): David Dorothy 
	// Ancestors at generation 2 (grand-parents): Carl Craig Carol 
	// Ancestors at generation 3: Barbara Bruce Brian Brenda 
	// Male ancestors at generation 1 (father): David 
	// Male ancestors at generation 2 (grand-fathers): Carl Craig 
	// Male ancestors at generation 3: Bruce Brian 
	// Ancestors at generation 10 (empty): 
	// All generations:
	// Edward 
	// David Dorothy 
	// Carl Craig Carol 
	// Barbara Bruce Brian Brenda 
	// Anthony Anna Allan Amanda Andrew Ann Albert Alice 
}
