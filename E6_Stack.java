import java.util.*;
class Node {
	private Object info = null;
	private Node link = null;

	public Node(Object info, Node link) {
		this.info = info;
		this.link = link;
	}

	public Node(Object info) {
		this(info, null);
	}

	public Node() {
		this(null, null);
	}

	public Object getInfo() {
		return this.info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public Node getLink() {
		return this.link;
	}

	public void setLink(Node node) {
		this.link = node;
	}
}

public class E6_Stack {
	private int stackSize = 0;
	private Node stackTop = null;

	public E6_Stack() {
		stackTop = null;
	}

	public void initializeStack() {
		stackTop = null;
	}

	public boolean isEmptyStack() {
		return (stackTop == null);
	}

	public boolean isFullStack() {
		return false;
	}
	
	public void push(Object newItem) {
		Node newNode = new Node(newItem);
		newNode.setLink(stackTop);
		stackTop = newNode;
		stackSize++;
	}
	
	public Object peek() throws Exception{
		if(isEmptyStack())
			throw new Exception("StackUnderflow");
		return stackTop.getInfo();
	}
	
	public Object pop() throws Exception{
		Object itemInfo;
		if(isEmptyStack())
			throw new Exception("StackUnderflow");
		itemInfo = stackTop.getInfo();
		stackTop = stackTop.getLink();
		stackSize--;
		return itemInfo;
	}
	
	public int getSize() {
		return stackSize;
	}
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		E6_Stack even = new E6_Stack();
		E6_Stack odd = new E6_Stack();
		E6_Stack newEven = new E6_Stack();
		E6_Stack newOdd = new E6_Stack();
		final int n = rand.nextInt(11)+10;
		System.out.println("--- Random Numbers  ["+n+"] ---");
		
		for(int i = 0;i<n;i++) {
			int ranNum = rand.nextInt(101);
			System.out.print(ranNum+" ");
			if(ranNum%2 == 0) {
				even.push(ranNum);
			}else {
				odd.push(ranNum);
			}
		}
		System.out.println();
		System.out.println("--- Even Stack ["+even.getSize()+"] ---");
		int countEven = 0,countOdd = 0;
		while(!even.isEmptyStack()) {
			Object num = even.peek();
			newEven.push(num);
			System.out.print(num+" ");
			even.pop();
		}
		System.out.println("\n--- Odd Stack ["+odd.getSize()+"] ---");
		while(!odd.isEmptyStack()) {
			Object num = odd.peek();
			newOdd.push(num);
			System.out.print(num+" ");
			odd.pop();
		}
		//System.out.println(newEven.getSize());
		//System.out.println(newOdd.getSize());
		System.out.println();
		while(true) {
			if(newEven.isEmptyStack()) {
				System.out.println("===> Even is the winner!");
				break;
			}
			else if(newOdd.isEmptyStack()) {
				System.out.println("===> Odd is the winner!");
				break;
			}
			Object e = newEven.peek(),o = newOdd.peek();
			if((int)e > (int)o) {
				newEven.pop();
			}else {
				newOdd.pop();
			}
			
		}
		//System.out.println(newEven.getSize());
		//System.out.println(newOdd.getSize());
		
	}
}