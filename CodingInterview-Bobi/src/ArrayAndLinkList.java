public class ArrayAndLinkList {

	public static void main(String[] args) throws Exception {
		ArrayAndLinkList.n = new ListNode(0);
		n.append(1);
		n.append(2);
		n.append(3);
		n.append(4);


//		System.out.println(linkedListToInt(n, false));
//		TreeNode tree = sortedListToBST(n);
//		BinaryTree.inOrderTraverse(tree);

//		ListNode rev = reverse(n);
//		rev.tranverse();

		ListNode rev = reverseBetween(n, 2, 4);
		rev.tranverse();
	}

	static ListNode n;


	static Object KthLast(ListNode start, int k) {
		ListNode r = start, result = start;
		while (k > 1) {
			if (r != null) {
				r = r.next;
			} else {
				return null;
			}
			k--;
		}

		while (r.next != null) {
			r = r.next;
			result = result.next;
		}

		return result.val;
	}

	static void deleteMidlle(ListNode start) {
		if (start == null) {
			return;
		}
		ListNode end = start, middle = start;
		while (end.next != null && end.next.next != null) {
			end = end.next.next;
			middle = middle.next;
		}

		if (middle.next != null) {
			middle.next = middle.next.next;
		}

		start.tranverse();
	}

	static int linkedListToInt(ListNode start, boolean reverse) {
		ListNode r = start;
		int result = 0;

		if (reverse) {
			int unit = 1;
			while (r != null) {
				result += unit * (Integer)r.val;
				unit *= 10;
				r = r.next;
			}
		} else {
			while(r != null) {
				result = result * 10 + (Integer)r.val;
				r = r.next;
			}
		}

		return result;
	}

	static ListNode intToLinkList(int number, boolean reverse) {
		if (number == 0) {
			return new ListNode(0);
		}

		ListNode n = null;
		while (number != 0) {
			int value = number % 10;
			if (n == null) {
				n = new ListNode(value);
			} else {
				if (reverse) {
					n.append(value);
				} else {
					ListNode temp = new ListNode(value);
					temp.next = n;
					n = temp;
				}
			}
			number = (number - value) / 10;
		}

		return n;
	}

	/**
	 * You are given two linked list representing two non-negative numbers.
	 * The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * Output: 7 -> 0 -> 8
	 *
	 * Solution:
	 * Two pointers.
	 */
	static ListNode addTwoNumber(ListNode a, ListNode b) {
		int carrier = 0;
		ListNode result = null;
		while (a != null || b != null) {
			int a_v = 0, b_v = 0;
			if (a != null) {
				a_v = (Integer)a.val;
				a = a.next;
			}

			if (b != null) {
				b_v = (Integer)b.val;
				b = b.next;
			}

			int sum = a_v + b_v + carrier;
			carrier = sum >= 10 ? 1 : 0;
			sum %= 10;
			if (result == null) {
				result = new ListNode(sum);
			} else {
				result.append(sum);
			}
		}
		if (carrier == 1) {
			result.append(carrier);
		}
		return result;
	}

	/**
	 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
	 *
	 * Solution:
	 * Refer to build BST from sorted array.
	 */
	static TreeNode sortedListToBST(ListNode n) {
		if (n == null) {
			return null;
		}
		int len = 0;
		ListNode r = n;
		while (r != null) {
			r = r.next;
			len++;
		}

		return sortedListToBSTHelper(-1, len);
	}

	private static TreeNode sortedListToBSTHelper(int start, int end) {
		if (end - start <= 1) {
			return null;
		}

		int mid = (end - start) / 2 + start;
		TreeNode left = sortedListToBSTHelper(start, mid);
		TreeNode root = new TreeNode((Integer)n.val);
		System.out.println("add" + n.val);
		n = n.next;
		TreeNode right = sortedListToBSTHelper(mid, end);

		root.left = left;
		root.right = right;
		return root;
	}


	/**
	 * reverse a linked list
	 */
	static ListNode reverse(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode dh = new ListNode(0);
		ListNode cur = head, next = head.next;
		dh.next = head;
		while (next != null) {
			cur.next = next.next;
			next.next = dh.next;
			dh.next = next;
			next = cur.next;
		}
		return dh.next;
	}

	/**
	 * revserse a linkedList between m and n. m and n is index, inclusive.
	 */
	static ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null) {
			return null;
		}
		int count = n - m ; //DONT't + 1
		ListNode dh = new ListNode(0);
		dh.next = head;
		ListNode prev = dh;
		while (m > 0) {
			prev = prev.next;
			m--;
		}

		ListNode cur = prev.next, next = prev.next.next;
		while (count > 0 && next != null) {
			cur.next = next.next;
			next.next = prev.next;
			prev.next = next;
			next = cur.next;
			count--;
		}
		return dh.next;
	}

	/**
	 * intervleave two linkedLIst
	 */
	static ListNode interleave(ListNode a, ListNode b) {
		ListNode dh = new ListNode(0);
		ListNode prev = dh;
		boolean isA = true;
		while (a != null && b != null) {
			if (isA) {
				prev.next = a;
				prev = prev.next;
				a = a.next;
				isA = false;
			} else {
				prev.next = b;
				prev = prev.next;
				b = b.next;
				isA = true;
			}
		}
		if (a != null) {
			prev.next = a;
		} else if (b != null) {
			prev.next = b;
		}
		return dh.next;
	}
}

class ListNode {
	int val;
	ListNode next;

	public ListNode(int val) {
		this.val = val;
		next = null;
	}

	void append(int val) {
		ListNode n = new ListNode(val);
		ListNode r = this;
		while (r.next != null) {
			r = r.next;
		}
		r.next = n;
	}

	public void tranverse() {
		ListNode r = this;
		while (r != null) {
			System.out.println(r.val);
			r = r.next;
		}
	}
}

