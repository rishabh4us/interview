
import java.util.LinkedList;

public class StackQueue {
	public static void main(String[] args) {
//		MinStack s = new MinStack();
//		s.push(1);
//		s.push(3);
//		s.push(-1);
//		System.out.println(s.min());

		TwoStackQueue q = new TwoStackQueue();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
	}

}

class MinStack {
	MinStackNode top;
	int size;

	void push(int data) {
		int lastMin = size == 0? data : top.min;
		MinStackNode n = new MinStackNode(data, lastMin);
		n.next = top;
		top = n;
		size++;
	}

	int pop() {
		if (size > 0) {
			MinStackNode n = top;
			top = top.next;
			return n.data;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	int min() {
		if (size == 0) {
			throw new IndexOutOfBoundsException();
		} else {
			return top.min;
		}
	}

	int peek() {
		if (size == 0) {
			throw new IndexOutOfBoundsException();
		} else {
			return top.data;
		}
	}

}

class MinStackNode {
	int data;
	int min; //In Java, reference size is either 32bit or 64bit, so it's not saving mem if we store min node's reference here.
	MinStackNode next;

	MinStackNode(int data, int lastMin) {
		this.data = data;
		this.min = lastMin > data ? data : lastMin;
	}
}

/**
 * Implement Queue using two stacks
 */
class TwoStackQueue {
	LinkedList<Object> s1, s2;
	int size;

	public TwoStackQueue() {
		s1 = new LinkedList<>();
		s2 = new LinkedList<>();
	}

	public void enqueue(Object data) {
		if (size != 0) {
			moveToS1();
		}
		s1.push(data);
		size++;
	}

	public Object dequeue() {
		if (size == 0) {
			throw new IndexOutOfBoundsException();
		} else {
			moveToS2();
			size--;
			return s2.pop();
		}
	}

	private void moveToS2() {
		while (s1.size() != 0) {
			s2.push(s1.pop());
		}
	}

	private void moveToS1() {
		while (s2.size() != 0) {
			s1.push(s2.pop());
		}
	}
}