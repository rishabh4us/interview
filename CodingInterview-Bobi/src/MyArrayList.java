import java.util.*;

public class MyArrayList<T> {

	public static void main(String[] args) {
//		MyArrayList<Integer> aList = new MyArrayList<>();
//		for (int i = 0; i < 10; i++) {
//			aList.insert(i, i);
//		}
//		aList.remove(7);
//		aList.traverse();
//		Iterator<Integer> iter = aList.iterator();
//		while(iter.hasNext()) {
//			System.out.println(iter.next());
//		}

//		ArrayList<Integer> list = new ArrayList(Arrays.asList(1,2,3,4));
//		SkipIterator<Integer> skipIterator = new SkipIterator(list.iterator());
//		while (skipIterator.hasNext()) {
//			System.out.println(skipIterator.next());
//		}
//		skipIterator.next();

		ArrayList<Integer> a = new ArrayList(Arrays.asList(1,2,3,4)), b = new ArrayList(Arrays.asList(5,6,7,8));
		ArrayList<Iterator<Integer>> list = new ArrayList(Arrays.asList(a.iterator(), b.iterator()));
		RotateIterator<Integer> rotateIterator = new RotateIterator(list);
		while (rotateIterator.hasNext()) {
			System.out.println(rotateIterator.next());
		}
		rotateIterator.next();
	}

	private T[] myStore;
	private int actSize;

	public MyArrayList() {
		myStore = (T[])new Object[5];
	}

	public T get(int index) {
		if (index >= 0 && index < actSize) {
			return myStore[index];
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	public void add(T obj) {
		if (myStore.length == actSize) {
			increaseListSize();
		}
		myStore[actSize++] = obj;
	}

	public void insert(int index, T a) {
		if (index >= 0 && index <= actSize) {
			if (myStore.length == actSize) {
				increaseListSize();
			}
			System.arraycopy(myStore, index, myStore, index + 1, actSize - index);
			actSize++;
			myStore[index] = a;
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	public Object remove(int index) {
		if (index >= 0 && index < actSize) {
			T obj = myStore[index];
			while (index < actSize - 1) {
				myStore[index] = myStore[++index];
			}
			// Now index = acSize - 1
			myStore[index] = null;
			actSize--;
			return obj;
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	public int size() {
		return actSize;
	}

	private void increaseListSize() {
		T[] temp = (T[])new Object[myStore.length * 2];
		System.arraycopy(myStore, 0, temp, 0, myStore.length);
		myStore = temp;
	}

	public void traverse() {
		for (int i = 0; i < actSize; i++) {
			System.out.print(myStore[i]);
		}
	}

	public T peek() {
		if (actSize > 0) {
			return myStore[0];
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	public Iterator iterator() {
		return new MyIterator(this);
	}

	private class MyIterator implements Iterator<T> {
		private final MyArrayList myArray;
		private int cur;

		MyIterator(MyArrayList array) {
			myArray = array;
			cur = -1;
		}

		public boolean hasNext(){
			return cur + 1 < myArray.size();
		}

		public T next() {
			return (T) myArray.get(++cur);
		}

		public void remove() {
			if (cur < 0) {
				throw new NoSuchElementException();
			} else {
				myArray.remove(cur);
			}
		}
	}
}



class SkipIterator<T> implements Iterator<T> {
	Iterator<T> iter;

	SkipIterator(Iterator<T> iter) {
		this.iter = iter;
	}

	public boolean hasNext() {
		return iter.hasNext();
	}

	public T next() {
		T val = iter.next(); //this will throw an Exception if there's no additional element
		if (iter.hasNext()) {
			iter.next();
		}
		return val;
	}

	public void remove() {
	}
}

class RotateIterator<T> implements Iterator<T> {
	List<Iterator<T>> list;
	int cur, size;

	RotateIterator(List<Iterator<T>> lists) throws IllegalArgumentException{
		if (lists == null || lists.size() == 0) {
			throw new IllegalArgumentException();
		}
		this.list = lists;
		cur = 0;
		size = lists.size();
	}

	public boolean hasNext() {
		for (int i = 0; i < size; i++) {
			if (list.get(cur).hasNext()) {
				return true;
			}
			cur = (cur + 1) % size;
 		}
		return false;
	}

	public T next() {
		if (hasNext()) {
			T val = list.get(cur).next();
			cur = (cur + 1) % size;
			return val;
		} else {
			return list.get(cur).next(); //this will throw an exception in running
		}
	}

	public void remove() {
	}
}