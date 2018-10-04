class Node {
	private int info;
	private Node link;

	public Node(int info, Node link) {
		this.info = info;
		this.link = link;
	}

	public Node(int info) {
		this(info, null);
	}

	public Node() {
		this(0, null);
	}

	public int getInfo() {
		return this.info;
	}

	public Node getLink() {
		return this.link;
	}

	public void setInfo(int info) {
		this.info = info;
	}

	public void setLink(Node link) {
		this.link = link;
	}

	public String toString() {
		return "" + this.info;
	}
}

public class E5_ListNum {

	/**
	 * @param args the command line arguments
	 */
	private Node head = null;

	public E5_ListNum() {
	}

	public void addNode(int newInfo) {
		Node newNode = new Node(newInfo);
		newNode.setLink(head);
		head = newNode;
	}

	public void add(int newInfo) {
		Node newNode = new Node(newInfo);
		Node trav1, trav2;
		trav1 = trav2 = head;
		while (trav1 != null) { // traverse to the tail
			trav2 = trav1;
			trav1 = trav1.getLink();
		}
		if (trav1 != trav2)
			trav2.setLink(newNode);
		else
			head = newNode;

	}

	public void sorted(int newInfo) {
		Node newNode = new Node(newInfo);
		Node trav1, trav2;
		trav1 = trav2 = head;
		while (trav1 != null && trav1.getInfo() < newNode.getInfo()) { // find a proper position
			trav2 = trav1;
			trav1 = trav1.getLink();
		}
		newNode.setLink(trav1);
		if (trav1 != trav2)
			trav2.setLink(newNode);
		else
			head = newNode;
	}

	public String toString() {
		Node trav = head;
		String str = "";
		while (trav != null) {
			str = str.concat(trav.toString());
			// str=str.concat(""+trav.getInfo());
			trav = trav.getLink();
			if (trav != null)
				str = str.concat(" | ");
		}
		return str;
	}

	public void deleteHead() {
		Node delNode;
		delNode = head;
		head = head.getLink();
	}

	public void deletedTail() {
		Node trav1, trav2;
		trav1 = trav2 = head;
		while (trav1 != null && trav1.getLink() != null) { // traverse to the tail
			trav2 = trav1;
			trav1 = trav1.getLink();
		}
		Node delNode = trav1; // node to be deleted
		if (trav2 != null) {
			trav2.setLink(null); // reset link to null
			if (delNode == head)
				head = null; // if delete node at the Head
		}
	}
	
	public void search(int number) {
		int count = 1;
		Node trav = head;
		while(trav != null && trav.getInfo()!= number) {
			trav = trav.getLink();
			++count;
		}
		if(trav != null) {
			System.out.println("This data found at : "+ count+" in the link listed.");
		}
		else
			System.out.println("Not found.");
	}

	public int length() {
		int length = 0;
		Node trav = head;
		while (trav != null) {
			length++;
			trav = trav.getLink();
		}
		return length;
	}

	
	public void deletedAt(int pos) {
		if(pos == 1) {
			this.deleteHead();
		}else if(pos ==this.length()) {
			this.deletedTail();
		}else {
			int count = 1;
			Node delNode= head,trav = head;
			while(count != pos) {
				trav = delNode;
				delNode = head.getLink();
				count++;
			}
			trav.setLink(trav.getLink().getLink()) ;
			
		}
	}
	public static void main(String[] args) {
		E5_ListNum listNum1 = new E5_ListNum();

		System.out.println(listNum1);
		listNum1.sorted(8);
		listNum1.sorted(7);
		listNum1.sorted(5);
		listNum1.sorted(15);
		listNum1.search(5);
		System.out.println(listNum1.length());
		System.out.println(listNum1);
		listNum1.deletedAt(2);
		System.out.println(listNum1);
		listNum1.deletedTail();
		System.out.println(listNum1);
		listNum1.deleteHead();
		System.out.println(listNum1);
	}
}