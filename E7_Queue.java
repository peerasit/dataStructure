import java.util.Random;

class Node{
	private Object info = null;
	private Node link = null;
	
	public Node(Object info,Node link) {
		this.info = info;
		this.link = link;
	}
	public Node(Object info) {
		this(info,null);
	}
	public Node() {
		this(null,null);
	}
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
	public Node getLink() {
		return link;
	}
	public void setLink(Node link) {
		this.link = link;
	}
}

class QueueLinkedList {
	private Node queueFront  = null;
	private Node queueRear = null;
	private int count = 0;
	
	public void initializeQueue() {
		queueFront = null;
		queueRear = null;
		count = 0;
	}
	
	public boolean isEmptyQueue() {
		return (queueFront == null);
	}
	public boolean isFullQueue() {
		return false;
	}
	public Object front() throws Exception{
		if(isEmptyQueue())
			throw new Exception("QueueUnderflow");
		return queueFront.getInfo();	
	}
	public Object back() throws Exception{
		if(isEmptyQueue())
			throw new Exception("QueueUnerflow");
		return queueRear.getInfo();
	}
	public void enQueue(Object newItem) {
		Node newNode = new Node(newItem);
		if(queueFront == null) {
			queueFront = newNode;
			queueRear = newNode;
		}else {
			queueRear.setLink(newNode);
			queueRear = queueRear.getLink();
		}
		count++;
	}
	
	public Object deQueue() throws Exception {
		Object itemInfo;
		if(isEmptyQueue())
			throw new Exception("QueueUnderflow");
		itemInfo = queueFront.getInfo();
		queueFront = queueFront.getLink();
		if(queueFront == null)
			queueRear  = null;
		count--;
		return itemInfo;
			
	}
	public int getSize() {
		return count;
	}
}

public class E7_Queue {
	public static void main(String[] args) throws Exception {
		Random rand = new Random();
		QueueLinkedList even = new QueueLinkedList();
		QueueLinkedList odd = new QueueLinkedList();
		QueueLinkedList even2 = new QueueLinkedList();
		QueueLinkedList odd2 = new QueueLinkedList();
		
		final int n = rand.nextInt(11)+10;
		System.out.println("--- Random numbers ["+n+"] ---");
		for(int i = 0;i<n;i++) {
			int number = rand.nextInt(101);
			System.out.print(number+" ");
			if(number%2 == 0) {
				even.enQueue(number);
				even2.enQueue(number);
			}else {
				odd.enQueue(number);
				odd2.enQueue(number);
			}
		}
		
		System.out.println();
		System.out.println("--- Even Queue ["+even.getSize()+"] ---");
		System.out.print("[");
		while(even.getSize()>0) {
			System.out.print(" "+even.deQueue()+" ");
		}
		
		System.out.println("]");
		
		System.out.println("--- Odd Queue ["+odd.getSize()+"] ---");
		System.out.print("[");
		while(odd.getSize()>0) {
			System.out.print(" "+odd.deQueue()+" ");
		}
		System.out.println("]");
		
		int round = 0;
		int winOdd=0,winEven=0;
		while(even2.getSize()>0 && odd2.getSize() > 0) {
			Object evenNum = even2.deQueue();
			Object oddNum = odd2.deQueue();
			if((int)evenNum > (int)oddNum) {
				odd2.enQueue(oddNum);
				++winEven;
			}else {
				even2.enQueue(evenNum);
				++winOdd;
			}
			++round;
		}
		if(even2.getSize() == 0) {
			System.out.println("===> Winner is Even!");
		}else if(odd2.getSize() == 0) {
			System.out.println("===> Winner is Odd!");
		}
		
		System.out.print("Round="+round+", Win=(E:"+winEven+",O:"+winOdd+"), ");
		if(even2.getSize() == 0) {
			System.out.println("Odd Left="+odd2.getSize());
		}else if(odd2.getSize() == 0) {
			System.out.println("Even Left="+even2.getSize());
		}
		
		
	}
}
