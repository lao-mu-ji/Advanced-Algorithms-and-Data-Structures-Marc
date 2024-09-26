//
// Advanced Algorithms and Data Structures
// TIEI - Fall 2024
//
package tiei.aads.btree;

import java.util.*;

/**
 * A class for simple binary nodes
 */
public class BinaryNode<AnyType> {
		
	private AnyType data;
	private BinaryNode<AnyType> left, right;

	public AnyType getData() {
		return data;
	}

	public void setData(AnyType data) {
		this.data = data;
	}

	public BinaryNode<AnyType> getLeft() {
		return left;
	}

	public void setLeft(BinaryNode<AnyType> left) {
		this.left = left;
	}

	public BinaryNode<AnyType> getRight() {
		return right;
	}

	public void setRight(BinaryNode<AnyType> right) {
		this.right = right;
	}

	//////////////// constructors
	
	/**
	 * Build a binary node which is
	 * a leaf holding the value 'data'
	 */
	public BinaryNode(AnyType data) {
		this(data,null,null);
	}

	/**
	 * Build a binary node holding the value 'data' with
	 * 'left' as the left sub-tree and 'right' as the right sub-tree
	 */
	public BinaryNode(AnyType data, BinaryNode<AnyType> left, BinaryNode<AnyType> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	//////////////// accessors
	
	public AnyType data() {
		return data;
	}
	
	public BinaryNode<AnyType> left() {
		return left;
	}
	
	public BinaryNode<AnyType> right() {
		return right;
	}
	
	//////////////// utility methods
	
	public boolean isLeaf() {
		return left == null && right == null;
	}
	
	//////////////// the example of the height: 
	//////////////// apply the same scheme to the other methods
	
	public int height() {
		return height(this);
	}
	
	private int height(BinaryNode<AnyType> t) {
		if ( t == null )
			return -1;
		return 1 + Math.max(height(t.left), height(t.right));
	}

	//////////////// methods you have to complete ////////////////
	
	//////////////// lowness ////////////////
	// The lowness of a BN is the length of a
	// shortest path from the root to a leaf
	
	public int lowness() {
		return lowness(this);
	}

	public int lowness(BinaryNode<AnyType> t) {
		if(t == null){
			return -1;
		}
		return 1 + Math.min(lowness(t.left), lowness(t.right));
	}
	//需要计算出每个节点的lowness所以时间复杂度是O(n)
	
	//////////////// size ////////////////
	// The size of a BN is its number of
	// non null nodes
	
	public int size() {
		return size(this);
	}
	
	private int size(BinaryNode<AnyType> t) {
		if(t == null){
			return 0;
		}
		return 1 + size(t.left) + size(t.right);
	}
	//size方法会递归的访问树中的每一个节点，在最坏情况下，除叶子节点以外都只有左子树或右子树
	//这样size会便利所有节点，时间复杂度为O(n)
	
	//////////////// leaves ////////////////
	// The leaves method returns the number
	// of leaves of the BN
	
	public int leaves() {
		return leaves(this);
	}
	
	private int leaves(BinaryNode<AnyType> t) {
		if(t == null){
			return 0;
		}
		else if(t.left == null && t.right == null){
			return 1;
		}
		return leaves(t.left) + leaves(t.right);
	}
	//在最坏情况下O（n）
	
	//////////////// isomorphic ////////////////
	// Two BN are isomorphic if they have exactly
	// the same structure, no matter the data they
	// store
	
	public boolean isomorphic(BinaryNode<AnyType> t) {
		return isomorphic(this,t);
	}
	
	private boolean isomorphic(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2) {
		return t1 == null && t2 == null ||
				(t1 != null && t2 != null &&
				t1.data.equals(t2.data) &&
				isomorphic(t1.left,t2.left) &&
				isomorphic(t1.right,t2.right));
	}

	//////////////// balanced1 ////////////////
	// A BN is said balanced if for each node
	// (including the root node) the absolute
	// value of the difference between the height
	// of the left node and the height of the
	// right node is at most 1
	
	// First version: you are to use the height method


	public boolean balanced1() {
		return balanced1(this);
	}
	
	private boolean balanced1(BinaryNode<AnyType> t) {
		if(t == null){
			return true;
		}
		int leftHeight = height(t.left);
		int rightHeight = height(t.right);
		if(Math.abs(leftHeight - rightHeight) > 1){
			return false;
		}
		return balanced1(t.left) && balanced1(t.right);
	}
	//在最坏情况下，对于每个节点，他都需要遍历其子树来计算左右子树的高度，这一步时间复杂度为O(n)
	//而一共有n个节点，所以时间复杂度应为O(n*n)

	//////////////// balanced2 ////////////////
	// In this version, the complexity must be O(n)
	// where n is the size of the BN (the number of
	// non null nodes)

	public boolean balanced2() {
		return balanced2(this).balanced;
	}

	private BalancedResult balanced2(BinaryNode<AnyType> t) {
		if (t == null) return new BalancedResult(true, -1);
		BalancedResult left = balanced2(t.left);
		BalancedResult right = balanced2(t.right);
		boolean isBalanced = left.balanced && right.balanced && Math.abs(left.height - right.height) <= 1;
		int height = 1 + Math.max(left.height, right.height);
		return new BalancedResult(isBalanced, height);
	}

	private static class BalancedResult {
		boolean balanced;
		int height;

		BalancedResult(boolean balanced, int height) {
			this.balanced = balanced;
			this.height = height;
		}
	}

	
	
	//////////////// shapely1 /////////////////
	// A BN is said to be shapely if its height
	// is less or equal to the double of its lowness

	// First version: you are to use the height and the lowness methods
	
	public boolean shapely1() {
		return shapely1(this);
	}
	
	private boolean shapely1(BinaryNode<AnyType> t) {
		if(t == null){
			return true;
		}
		int height = height(t);
		int lowness = lowness(t);
		if(height > 2 * lowness){
			return false;
		}
		return shapely1(t.left) && shapely1(t.right);
	}
	//类似，O（n^2）
	
	//////////////// shapely2 ////////////////
	// In this version, the complexity must be O(n)
	// where n is the size of the BN (the number of
	// non null nodes)

	public boolean shapely2() {
		ShapelyResult result = shapely2(this);
		return result.shapely && result.height <= 2 * result.lowness;
	}

	private ShapelyResult shapely2(BinaryNode<AnyType> t) {
		if (t == null) return new ShapelyResult(true, 0, 0);
		ShapelyResult left = shapely2(t.left);
		ShapelyResult right = shapely2(t.right);
		boolean isShapely = left.shapely && right.shapely && Math.max(left.height, right.height) <= 2 * Math.min(left.lowness, right.lowness);
		int height = 1 + Math.max(left.height, right.height);
		int lowness = 1 + Math.min(left.lowness, right.lowness);
		return new ShapelyResult(isShapely, height, lowness);
	}

	private static class ShapelyResult {
		boolean shapely;
		int height;
		int lowness;

		ShapelyResult(boolean shapely, int height, int lowness) {
			this.shapely = shapely;
			this.height = height;
			this.lowness = lowness;
		}
	}

	//////////////////////////
	
	/**
	 * Display the BN in (ascii) 2D
	 */
    public void display() {
    	display(this,"","");
    }

    private void display(BinaryNode<AnyType> t, String r, String p) {
        if ( t == null ) {
            System.out.println(r);
        }
        else {
            String rs = t.data.toString();
            System.out.println(r + rs);
            if ( t.left != null || t.right != null ) {
                String rr = p + '|' + makeString('_',rs.length()) + ' ';
                display(t.right,rr, p + '|' + makeString(' ',rs.length() + 1));
                System.out.println(p + '|');
                display(t.left,rr, p + makeString(' ',rs.length() + 2));
            }
        }
    }

    private String makeString(char c, int k) {
        String s = "";
        for ( int i = 0; i < k; i++ ) {
            s += c;
        }
        return s;
    }
    
    ////////////////////////////////////
    
    /**
     * Build a BN of strings from it's linear prefix representation
     * "root left right". We use the '$' sign to mark leaves and/or
     * null subtree:
     * - X$ means that X is a leaf
     * - $  is the empty tree
     */
    public static BinaryNode<String> read(String inputString) {
    	Scanner input = new Scanner(inputString);
    	return read(input);
    }
    
    private static BinaryNode<String> read(Scanner input) {
    	if ( ! input.hasNext() )
    		return null;
    	String s = input.next();
    	if ( s.equals("$") )
    		return null;
    	if ( s.endsWith("$") )
    		return new BinaryNode<String>(s.substring(0,s.length()-1));
    	return new BinaryNode<String>(s,read(input),read(input));
    }

    /**
     * A short main for quick testing
     */
	public static void main(String[] args) {
		BinaryNode<String> t = read("A B D X 1 10$ 20$ $ Y$ E V$ W$ C F$ G$");
		t.display();
		if ( t.shapely2() )
			System.out.println("shapely");
		else
			System.out.println("not shapely");
	}
}
