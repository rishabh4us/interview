import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyUtils {

	public static void main(String[] args) {
//		System.out.println(stringToInt("123"));
		System.out.println(intToString(100001233));
	}

	static int stringToInt(String a) {
		char[] x = a.toCharArray();
		int result = 0;
		for (char digit : x) {
			result *= 10;
			result += digit - '0';
		}
		return result;
	}

	static String intToString(int a) {
		if (a == 0) {
			return "0";
		}

		StringBuilder s = new StringBuilder();
		while (a != 0) {
			int digit = a % 10;
			s.insert(0, digit);
			a = a - digit;
			a /= 10;
		}
		return s.toString();
	}

	static int binarySearch(List<Integer> a, int target) {
		if (a == null || a.size() == 0 || target > a.get(a.size() -1 )) {
			return -1;
		}
		int start = -1, end = a.size();
		while (end - start > 1) {
			int mid = (end - start) / 2 + start;
			if (a.get(mid) == target) {
				return mid;
			}

			if (mid >= target) {
				end = mid;
			} else {
				start = mid;
			}
		}
		return -1;
	}

	static void swap(Object[] a, int left, int right) {
		Object temp = a[left];
		a[left] = a[right];
		a[right] = temp;
	}

	static void swap(int[] a, int left, int right) {
		int temp = a[left];
		a[left] = a[right];
		a[right] = temp;
	}

	static void swap(List a, int left, int right) {
		if (left == right) {
			return;
		}

		if (a.get(0) instanceof Integer) {
			// use XOR 3 times to swap
			a.set(left, (Integer)a.get(left) ^ (Integer)a.get(right));
			a.set(right, (Integer)a.get(left) ^ (Integer)a.get(right));
			a.set(left, (Integer)a.get(left) ^ (Integer)a.get(right));
		} else {
			Object temp = a.get(left);
			a.set(left, a.get(right));
			a.set(right, temp);
		}
	}

	static void reverse(List a, int start, int end) {
		while (start < end) {
			swap(a, start++, end--);
		}
	}

	static void reverse(int[] a) {
		int start = 0, end = a.length - 1;
		while (start < end) {
			a[start] = a[start] ^ a[end];
			a[end] = a[start] ^ a[end];
			a[start] = a[start] ^ a[end];
			start++;
			end--;
		}
	}

	static ListNode getLinkedList() {
		ListNode start = new ListNode(0);
		for (int i = 1; i < 5; i++) {
			start.append(i);
		}
		return start;
	}

	static ArrayList deepClone(ArrayList<ArrayList<Object>> a) {
		ArrayList<ArrayList<Object>> result = new ArrayList<>();
		for (ArrayList<Object> objects : a) {
			result.add((ArrayList<Object>) objects.clone());
		}
		return result;
	}

	static int sum(int[] a) {
		int sum = 0;
		for (int i : a) {
			sum += i;
		}
		return sum;
	}
}

class MyComparator implements Comparator<Object> {

	@Override
	public int compare(Object a, Object b) {
		if (a instanceof Integer) {
			return (Integer)a - (Integer)b;
		} else if (a instanceof  Character) {
			return (Character)a - (Character)b;
		} else {
			return 1;
		}
	}
}