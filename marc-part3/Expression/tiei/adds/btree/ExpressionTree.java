//
// Advanced Algorithms and Data Structures
// TIEI - Fall 2024
//
package tiei.aads.btree;

import java.util.*;

/**
 * A class for arithmetic expression tree
 */
public class ExpressionTree extends BinaryNode<String> {
		
	/**
	 * Build a leaf expression (a number)
	 */
	public ExpressionTree(String x) {
		super(x);
	}
	
	/**
	 * Build a tree expression 'left op right'
	 * where 'op' is "+", "*", "/" or "^"
	 */
	public ExpressionTree(String op, ExpressionTree left, ExpressionTree right) {
		super(op,left,right);
	}
	
	/**
	 * Return the value of the expression
	 */
	public double exe(String data, double x, double y){
		if(data.equals("+")){
			return x + y;
		}
		else if(data.equals("-")){
			return x - y;
		}
		else if(data.equals("*")){
			return x * y;
		}
		else if(data.equals("/")){
			return x / y;
		}
		else if(data.equals("^")){
			return Math.pow(x, y);
		}
		throw new IllegalArgumentException("Invalid operator: " + data);
	}
//	public double exe(String data, double x, double y){
//		if(data == "+"){
//			return x + y;
//		}
//		else if(data == "-"){
//			return x - y;
//		}
//		else if(data == "*"){
//			return x * y;
//		}
//		else if(data == "/"){
//			return x / y;
//		}
//		return Math.pow(x, y);
//	}
//在Java中，字符串比较应该使用equals方法而不是==操作符。这是因为==比较的是两个对象的引用是否相同，而不是它们的内容是否相同。
//字符串字面量（如"+"、"-"、"*"、"/"）在编译时被存储在字符串常量池中，
//而通过new String()或者字符串连接操作创建的字符串则存储在堆中，即使是内容相同的字符串，它们的引用也是不同的。
	public double eval(BinaryNode<String> t) throws NumberFormatException {
		if (t == null) return 0; // Handle null case if necessary

		String data = t.getData();
		if (data.equals("+") || data.equals("-") || data.equals("*") || data.equals("/") || data.equals("^")) {
			double leftVal = eval(t.getLeft());
			double rightVal = eval(t.getRight());
			return exe(data, leftVal, rightVal);
		} else {
			return Double.parseDouble(data);
		}
	}
//	public double eval(BinaryNode t) throws NumberFormatException {
//		if(t.getData() == "+" || getData() == "-" || getData() == "*" || getData() == "/" || getData() == "^"){
//			return exe(t.getData().toString(), eval(t.getLeft()), eval(t.getRight()));
//		}
//		return Double.parseDouble(getData());
//	}



	public double eval() throws NumberFormatException{
		return eval(this);
	}
	
    ////////////////////////////////////
    
	/**
	 * Return an expression tree whose linear form
	 * is given as the string 'inputstring'
	 */
    public static ExpressionTree read(String inputString) {
    	Scanner input = new Scanner(inputString);
    	return read(input);
    }
    
    private static ExpressionTree read(Scanner input) {
    	if ( ! input.hasNext() )
    		return null;
    	String s = input.next();
    	if ( s.equals("$") )
    		return null;
    	if ( s.endsWith("$") )
    		return new ExpressionTree(s.substring(0,s.length()-1));
    	return new ExpressionTree(s,read(input),read(input));
    }
	
    /**
     * A short main for quick testing
     */
	public static void main(String[] args) {
		ExpressionTree e = read("- * 2$ 5$ ^ 3$ 2$");
		e.display();
		System.out.println(e.eval());

	}
}
