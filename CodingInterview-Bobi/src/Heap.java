import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T> {
	private ArrayList<T> array;
	private Comparator<T> comparator;

	Heap(Comparator<T> comparator) {
		this.array = new ArrayList();
		this.comparator = comparator;
	}

	void push(T a) throws  IllegalArgumentException{
		if (a == null) {
			throw new IllegalArgumentException();
		}
		array.add(a);
		bubbleUp(array.size() - 1);
	}

	T pop() throws Exception{
		if (array.isEmpty()) {
			throw new Exception();
		}

		T returnVal = this.array.get(0), lastVal = array.remove(array.size() - 1);
		if (!array.isEmpty()) {
			array.set(0, lastVal);
			bubbleDown(0);
		}
		return returnVal;
	}

	private void bubbleUp(int index) {
		if (index == 0) {
			return;
		}
		int parent = (index - 1) / 2;
		if (comparator.compare(array.get(index), array.get(parent)) < 0) {
			MyUtils.swap(array, index, parent);
			bubbleUp(parent);
		}
	}

	private void bubbleDown(int index) {
		int left = index * 2, right = index * 2 + 1;
		int smallest = index;
		if (left < array.size() && comparator.compare(array.get(smallest), array.get(left)) > 0) {
			smallest = left;
		}

		if (right < array.size() && comparator.compare(array.get(smallest), array.get(right)) > 0) {
			smallest = right;
		}
		if (smallest != index) {
			MyUtils.swap(array, smallest, index);
			bubbleDown(smallest);
		}
	}

	public static void main(String[] args) throws Exception {
		Heap<Integer> h = new Heap(new intComparator());
		h.push(3);
		h.push(2);
		h.push(1);

		System.out.println(h.pop());
		System.out.println(h.pop());
		System.out.println(h.pop());
	}
}

class intComparator implements Comparator<Integer> {
	public int compare(Integer a, Integer b) {
		return a - b;
	}
}