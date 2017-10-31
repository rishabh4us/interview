import java.util.*;

public class BinaryTree {
	public static void main(String[] args) throws Exception {
		TreeNode root = getBST();
//		preOrderTraverse(root);
//		preOrder_iterative(root);
//		inOrderTraverse(root);
//		inOrder_iterative(root);
//		postOrderTraverse(root);
//		postOrder_iterative(root);

//		System.out.println(BFS(root, 3));
//		System.out.println(isBalanced(root));
//		System.out.println(isBST(root));
//		System.out.println(BST_LCA(root, new Node(6), new Node(7)).val);
//		printSumPathStartFromAnyNode(root, 6);
//		BinaryTreeNode r = sortedArrayToBST(new int[]{1, 2, 3, 4, 5, 6});
//		inOrderTraverse(r);

//		String s = serializeBinaryTree(root);
//		System.out.println(s);
//		BinaryTreeNode temp = deSerializeBT(s);
//		inOrderTraverse(temp);

//		String s = "4,2,1,3,6,5,7";
//		TreeNode temp = deSerializeBST(s);
//		preOrderTraverse(temp);
//		inOrderTraverse(temp);
//		levelTraverse(root);
//		root = delete_BST(root, 7);
//		root = delete_BST(root, 5);
//		root = delete_BST(root, 4);
//		root = delete_BST(root, 2);
//		root = delete_BST(root, 6);
//		root = delete_BST(root, 1);

//		root = trimBSTByInterval(root, 14, 6);
//		levelTraverse(root);
//		System.out.println(get_1st_bigger(root, 6).val);

//		pathSum(root, 6);
//		System.out.println(path_sum_output);
//		TreeNode ancestor = LCA(root, root.left.left, null);
//		System.out.println(ancestor.val);

		System.out.println(verticalPrint(root));
	}

	/**BST operations, find, insert, delete***/
	static TreeNode find_BST(TreeNode root, int val) {
		if (root == null) {
			return null;
		}
		while (root != null) {
			if ((Integer)root.val == val) {
				return root;
			} else if ((Integer)root.val > val) {
				root = root.left;
			} else {
				root = root.right;
			}
		}
		return null;
	}

	static void insert_BST(TreeNode root, int val) {
		//assume no duplicate
		if (root == null) {
			return;
		}
		if (root.val > val) {
			//go left
			if (root.left == null) {
				root.left = new TreeNode(val);
			} else {
				insert_BST(root.left, val);
			}
		} else {
			//go right
			if (root.right == null) {
				root.right = new TreeNode(val);
			} else {
				insert_BST(root.right, val);
			}
		}
	}

	static TreeNode delete_BST(TreeNode root, int val) {
		if (root == null) {
			return null;
		}
		TreeNode dummy = new TreeNode(0);
		dummy.left = root;
		delete_BST_helper(root, dummy, true, val);
		return dummy.left;
	}

	private static void delete_BST_helper(TreeNode root, TreeNode parent, boolean isLeft, int val) {
		if (root == null) {
			return;
		}

		if (val == root.val) {
			//delete this node
			if (root.left != null && root.right != null) {
				//remove the root, use max value on the left side to replace root
				TreeNode maxFromLeft = findMax_BST(root.left);
				root.val = maxFromLeft.val;
				delete_BST_helper(root.left, root, true, root.val);
			} else if (root.left != null) {
				//only has left
				setChild(parent, root.left, isLeft);
			} else if (root.right != null) {
				//only has right
				setChild(parent, root.right, isLeft);
			} else {
				//node is the leaf
				setChild(parent, null, isLeft);
			}
		} else if (val < root.val) {
			//go to left
			delete_BST_helper(root.left, root, true, val);
		} else {
			//go to right
			delete_BST_helper(root.right, root, false, val);
		}
	}

	private static void setChild(TreeNode parent, TreeNode child, boolean isLeft) {
		if (isLeft) {
			parent.left = child;
		} else {
			parent.right = child;
		}
	}

	private static TreeNode findMax_BST(TreeNode n) {
		if (n == null) {
			return null;
		}

		while (n.right != null) {
			n = n.right;
		}
		return n;
	}

	static TreeNode getBST() {
		TreeNode root = new TreeNode(4);
		root.addLeft(2);
		root.addRight(6);
		root.left.addLeft(1);
		root.left.addRight(3);
		root.right.addLeft(5);
		root.right.addRight(7);

		return root;
	}

	static void preOrderTraverse(TreeNode n) {
		if (n != null) {
			System.out.println(n.val);
			preOrderTraverse(n.left);
			preOrderTraverse(n.right);
		}
	}

	static void preOrder_iterative(TreeNode root) {
		LinkedList<TreeNode> s = new LinkedList();
		while (s.size() != 0 || root != null) {
			if (root != null) {
				System.out.println(root.val);
				if (root.right != null) {
					s.push(root.right);
				}
				root = root.left;
			} else {
				root = s.pop();
			}
		}
	}

	static void inOrderTraverse(TreeNode n) {
		if (n != null) {
			inOrderTraverse(n.left);
			System.out.println(n.val);
			inOrderTraverse(n.right);
		}
	}

	static void inOrder_iterative(TreeNode root) {
		LinkedList<TreeNode> s = new LinkedList();
		while (s.size() != 0 || root != null) {
			if (root != null) {
				s.push(root);
				root = root.left;
			} else {
				root = s.pop();
				System.out.println(root.val);
				root = root.right;
			}
		}
	}

	static void postOrderTraverse(TreeNode n) {
		if (n != null) {
			postOrderTraverse(n.left);
			postOrderTraverse(n.right);
			System.out.println(n.val);
		}
	}

	static void postOrder_iterative(TreeNode root) {
		LinkedList<TreeNode> s = new LinkedList(), output = new LinkedList();
		s.push(root);
		while (s.size() != 0) {
			TreeNode n = s.pop();
			output.push(n);
			if (n.left != null) {
				s.push(n.left);
			}
			if (n.right != null) {
				s.push(n.right);
			}
		}
		while(!output.isEmpty()) {
			System.out.println(output.pop().val);
		}
	}

	/**
	 * BFS tranverse by horizontal levels
	 */
	static void horizontalPrint(TreeNode root) {
		LinkedList<TreeNode> q = new LinkedList();
		q.offer(root);
		while (q.size() > 0) {
			TreeNode n = q.poll();
			if (n == null) {
				System.out.print(" #");
			} else {
				System.out.print(" " + n.val);
				q.offer(n.left);
				q.offer(n.right);
			}
		}
	}

	/**
	 * Tranverse by vertical level
	 */
	static ArrayList<ArrayList<Integer>> verticalPrint(TreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList();
		if (root == null) {
			return res;
		}
		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap(Collections.reverseOrder());
		verticalPrintHelper(root, 0, map);
		res.addAll(map.values());
		return res;
	}

	/**
	 * Preorder, put node into corresponding map's arraylist.
	 * Use preorder because we can tranverse in top-down order
	 */
	private static void verticalPrintHelper(TreeNode root, int offset, TreeMap<Integer, ArrayList<Integer>> map) {
		if (map.containsKey(offset)) {
			map.get(offset).add(root.val);
		} else {
			ArrayList<Integer> list = new ArrayList();
			list.add(root.val);
			map.put(offset, list);
		}
		if (root.left != null) {
			verticalPrintHelper(root.left, offset + 1, map);
		}
		if (root.right != null) {
			verticalPrintHelper(root.right, offset - 1, map);
		}
	}



	static Object BFS(TreeNode n, Object data) {
		if (n == null) {
			return null;
		}

		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.add(n);
		while (!queue.isEmpty()) {
			TreeNode current = queue.removeFirst();
			if (current.val == data) {
				return data;
			} else {
				if (current.left != null) {
					queue.add(current.left);
				}

				if (current.right != null) {
					queue.add(current.right);
				}
			}
		}
		return null;
	}

	static Object DFS(TreeNode n, Object data) {
		if (n == null) {
			return null;
		}
		if (n.val == data) {
			return data;
		}
		Object lResult = DFS(n.left, data);
		if (lResult != null) {
			return lResult;
		}

		Object rResult = DFS(n.left, data);
		if (rResult != null) {
			return rResult;
		}

		return null;
	}

	static int getHeight(TreeNode n) {
		if (n == null) {
			return 0;
		} else {
			return Math.max(getHeight(n.left), getHeight(n.right)) + 1;
		}
	}

	static boolean isBalanced(TreeNode n) {
		return n != null && Math.abs(getHeight(n.left) - getHeight(n.right)) <= 1;
	}

	static TreeNode constructBSTFromSortedArray(int[] array, int leftIndex, int rightIndex) {
		int length = rightIndex - leftIndex;
		if (length == 1) {
			return new TreeNode(array[leftIndex]);
		} else {
			int midIndex = (rightIndex - leftIndex) / 2;
			TreeNode m = new TreeNode(array[midIndex]);

			// Left side always has
			m.left = constructBSTFromSortedArray(array, leftIndex, midIndex);
			m.right = midIndex == rightIndex ? null : constructBSTFromSortedArray(array, midIndex + 1, rightIndex);

			return m;
		}
	}

	static boolean isBST(TreeNode root) {
		return isBST_helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static boolean isBST_helper(TreeNode root, int min, int max) {
		if (root == null) {
			return true;
		} else if (root.val <= min || root.val >= max) {
			return false;
		} else {
			return isBST_helper(root.left, min, root.val) && isBST_helper(root.right, root.val, max);
		}
	}

	static TreeNode LCA(TreeNode a, TreeNode b) {
		if (a == null || b == null) {
			return null;
		}

		int m = getDepth(a), n = getDepth(b);
		int diff = m - n;
		TreeNode x = diff > 0 ? a : b;
		TreeNode y = diff > 0 ? b : a;
		diff = Math.abs(diff);
		// on the same level now
		while (diff >0) {
			x = x.parent;
			diff--;
		}

		while (x != y) {
			x = x.parent;
			y = y.parent;
		}

		return a;
	}
	private static int getDepth(TreeNode a) {
		int d = 0;
		while (a != null) {
			a = a.parent;
			d++;
		}
		return d;
	}

	static TreeNode BST_LCA(TreeNode root, TreeNode a, TreeNode b) {
		if (root == null) {
			return null;
		}

		if ((root.compareTo(a) >= 0 && root.compareTo(b) <= 0) || (root.compareTo(a) <= 0 && root.compareTo(b) >= 0)) {
			return root;
		} else if ((root.compareTo(a) >= 0 && root.compareTo(b) >= 0)) {
			return BST_LCA(root.left, a, b);
		} else {
			return BST_LCA(root.right, a, b);
		}
	}

	static TreeNode LCA(TreeNode root, TreeNode a, TreeNode b) {
		LCA_Result r = LCA_helper(root, a , b);
		return r.isAncestor ? r.node : null;
	}

	static LCA_Result LCA_helper(TreeNode root, TreeNode a, TreeNode b) {
		if (root == null || a == null || b == null) {
			return new LCA_Result(null, false);
		}

		if (root == a && root == b) {
			return new LCA_Result(root, true);
		}

		LCA_Result left = LCA_helper(root.left, a, b);
		if (left.isAncestor) {
			return left;
		}

		LCA_Result right = LCA_helper(root.right, a, b);
		if (right.isAncestor) {
			return right;
		}

		if (right.node != null && left.node != null) {
			return new LCA_Result(root, true);
		} else if (root == a || root == b) {
			//This is to check the case: a is b's direct parent
			boolean isAncestor = right.node != null || left.node != null;
			return new LCA_Result(root, isAncestor);
		} else {
			return new LCA_Result(left.node != null ? left.node : right.node, false);
		}
	}

	private static class LCA_Result {
		TreeNode node;
		boolean isAncestor;
		LCA_Result(TreeNode node, boolean isAncestor) {
			this.node = node;
			this.isAncestor = isAncestor;
		}
	}

	static boolean matchTree(TreeNode t1, TreeNode t2) {
		if (t1 == t2) {
			return true;
		}

		if (t1 == null || t2 == null || t1.compareTo(t2) != 0) {
			return false;
		}

		return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right);
	}

	static boolean subTree(TreeNode t1, TreeNode t2) {
		if (t1 == null || t2 == null) {
			return false;
		}

		if (t1.compareTo(t2) == 0 && matchTree(t1, t2)) {
			return true;
		}

		return subTree(t1.left, t2) || subTree(t1.right, t2);
	}

	static void printSumPath(TreeNode root, TreeNode current, int sum, HashMap<TreeNode, Integer> table) {
		if (current == null) {
			return;
		}

		int value = sum - (Integer)current.val;
		if (value == 0) {
			TreeNode n = current;
			while (true) {
				System.out.println(n.val);
				if (n == root) {
					break;
				} else {
					n = n.parent;
				}
			}
		}
		table.put(current, value);
		printSumPath(root, current.left, value, table);
		printSumPath(root, current.right, value, table);
	}

	static void printSumPathStartFromAnyNode(TreeNode root, int sum) {
		if (root == null) {
			return;
		}
		printSumPath(root, root, sum, new HashMap<TreeNode, Integer>());
		printSumPathStartFromAnyNode(root.left, sum);
		printSumPathStartFromAnyNode(root.right, sum);
	}

	/**
	 * Sorted Array to BST.
	 * Start and end is exclusive.
	 *
	 * BST, left parts always less than root, right part always bigger than root.
	 */
	static TreeNode sortedArrayToBST(int[] a) {
		if (a == null) {
			return null;
		}
		return sortedArrayToBSTHelper(a, -1, a.length);
	}

	static TreeNode sortedArrayToBSTHelper(int[] a, int start, int end) {
		if (end - start <= 1) {
			return null;
		}

		int mid = (end - start) / 2 + start;
		TreeNode root = new TreeNode(a[mid]);
		root.left = sortedArrayToBSTHelper(a, start, mid);
		root.right = sortedArrayToBSTHelper(a, mid, end);
		return root;
	}



	/** Serialization **/

	static String serializeBinaryTree(TreeNode root) {
		ArrayList<String> l = new ArrayList<>();
		serializeHelper(root, l);
		return StringManipulation.join(l, ",");
	}

	private static void serializeHelper(TreeNode root, ArrayList<String> l) {
		if (root == null) {
			l.add("#");
			return;
		}
		l.add(String.valueOf(root.val));
		serializeHelper(root.left, l);
		serializeHelper(root.right, l);
	}

	static TreeNode deSerializeBT(String s) {
		if (s == null) {
			return null;
		}
		LinkedList<String> l = new LinkedList();
		Collections.addAll(l, s.split(","));
		return deserializeBTHelper(l);
	}

	private static TreeNode deserializeBTHelper(LinkedList<String> l) {
		if (l.size() == 0) {
			return null;
		}
		String val = l.remove();
		if (val.equals("#")) {
			return null;
		}
		TreeNode root = new TreeNode(Integer.valueOf(val));
		TreeNode left = deserializeBTHelper(l);
		TreeNode right = deserializeBTHelper(l);
		root.left = left;
		root.right = right;
		return root;
	}

	/**
	 * If input string has "#" to indicate empty child, then the deserializeBT code is working here.
	 * deSerialize BST, intput is in pre-order, BUT without "#".
	 * Like 4-2-1-3-6-5-7
	 */
	static TreeNode deSerializeBST(String s) {
		if (s == null) {
			return null;
		}
		LinkedList<String> l = new LinkedList();
		Collections.addAll(l, s.split(","));
		return deserializeBSTHelper(l, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static TreeNode deserializeBSTHelper(LinkedList<String> l, int min, int max) {
		if (l.size() == 0) {
			return null;
		}
		int val = Integer.valueOf(l.get(0));
		if (val <= max && val >= min) {
			TreeNode root = new TreeNode(Integer.valueOf(l.remove()));
			TreeNode left = deserializeBSTHelper(l, min, val);
			TreeNode right = deserializeBSTHelper(l, val, max);
			root.left = left;
			root.right = right;
			return root;
		} else {
			return null;
		}
	}

	/**
	 * Facebook question.
	 * Give BST and interval, return a bst that all node in that interval
	 */
	static TreeNode trimBSTByInterval(TreeNode root, int min, int max) {
		if (root == null || min > max) {
			return null;
		} else if (root.val > max) {
			//left
			return trimBSTByInterval(root.left, min, max);
		} else if (root.val < min) {
			//right
			return trimBSTByInterval(root.right, min, max);
		} else {
			root.left = trimBSTByInterval(root.left, min, max);
			root.right = trimBSTByInterval(root.right, min, max);
			return root;
		}
	}

	/**
	 * Give a BST.
	 * Return the first node which is bigger than node.
	 *
	 * Solution:
	 * Find this node. If in this tree, return it's rightChild's leftmost.
	 * If not, return the current root.
	 */
	static TreeNode get_1st_bigger(TreeNode root, int target) {
		if (root == null) {
			return null;
		}
		TreeNode node = findNode(root, target);
		if (node == null) {
			return null;
		} else if (node.val == target) {
			return get_right_child_left_most(node);
		} else {
			return node;
		}
	}

	/**
	 * get_1st_bigger 's pathSum_helper function.
	 * return null, means all the node is smaller than target.
	 * return node, if node.val == target, FOUND!
	 * else: node is the 1st bigger than target.
	 */
	private static TreeNode findNode(TreeNode root, int target) {
		if (root == null) {
			throw new IllegalArgumentException();
		}
		while (root != null) {
			if (root.val == target) {
				return root;
			} else if (target > root.val) {
				root = root.right;
			} else {
				if (root.left != null && target <= root.left.val) {
					root = root.left;
				} else {
					return root;
				}
			}
		}
		return null;
	}

	private static TreeNode get_right_child_left_most(TreeNode root) {
		if (root.right == null) {
			return null;
		}
		root = root.right;
		while (root.left != null) {
			root = root.left;
		}
		return root;
	}

	/**
	 * Path sum, return all the path in the tree that sum up to a certain num
	 * For further optimize, set up a new class include arraylist and its sum. To avoid sum computation.
	 */
	static ArrayList<ArrayList<Integer>> path_sum_output;
	static ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
		path_sum_output = new ArrayList();
		if (root == null) {
			return path_sum_output;
		}
		pathSum_helper(root, sum);
		return path_sum_output;
	}

	private static ArrayList<ArrayList<Integer>> pathSum_helper(TreeNode root, int sum) {
		ArrayList<ArrayList<Integer>> result = new ArrayList();
		if (root.left != null) {
			result.addAll(pathSum_helper(root.left, sum));
		}
		if (root.right != null) {
			result.addAll(pathSum_helper(root.right, sum));
		}

		result.add(new ArrayList()); //add self
		Iterator<ArrayList<Integer>> iter = result.iterator();
		while (iter.hasNext()) {
			ArrayList<Integer> list = iter.next();
			list.add(root.val);
			int s = sum(list);
			if (s == sum) {
				path_sum_output.add(list);
				iter.remove();
			} else if (s > sum) {
				iter.remove();
			}
		}
		return result;
	}

	private static int sum(ArrayList<Integer> list)  {
		int n = 0;
		for (Integer i : list) {
			n += i;
		}
		return n;
	}

	/**
	 * Inorder successor. Assume has parent link. Input root is already visited
	 * if root has right, return left most of right.
	 * else go to parent if root is the right of parent, return parent.
	 */
	static TreeNode inOrderSuccessor(TreeNode root) {
		if (root == null) {
			return null;
		}
		if (root.right != null) {
			root = root.right;
			while (root.left != null) {
				root = root.left;
			}
			return root;
		} else {
			while (root.parent != null && root.parent.right == root) {
				root = root.parent;
			}
			return root.parent;
		}
	}
}


class TreeNode implements Comparable<TreeNode>{
	TreeNode parent, left, right;
	int val;

	TreeNode(int val) {
		this.val = val;
	}

	void addLeft(int data) {
		TreeNode n = new TreeNode(data);
		n.parent = this;
		this.left = n;
	}

	void addRight(int data) {
		TreeNode n = new TreeNode(data);
		n.parent = this;
		this.right = n;
	}

	@Override
	public int compareTo(TreeNode to) {
		if (to == null) {
			return 1;
		} else {
			return this.val - to.val;
		}
	}
}