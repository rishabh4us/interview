public class DoubleLinkedList {
	DoubleNode anchor;
	int length;

	public DoubleLinkedList () {
		//Anchor never point to self
		anchor = new DoubleNode(null);
		length = 0;
	}

	public void append(Object data) {
		DoubleNode n = new DoubleNode(data);
		DoubleNode tail = length == 0 ? anchor : anchor.prev;

		tail.next = n;
		n.prev = tail;
		n.next = anchor;
		anchor.prev = n;

		length++;
	}

	public void traverse() {
		DoubleNode r = anchor.next;
		while (r != null && r != anchor) {
			System.out.println(r.data.toString());
			r = r.next;
		}
	}
}

class DoubleNode {
	Object data = null;
	DoubleNode prev = null;
	DoubleNode next = null;

	DoubleNode(Object data) {
		this.data = data;
	}
}

