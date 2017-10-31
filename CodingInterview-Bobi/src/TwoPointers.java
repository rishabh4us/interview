import java.util.*;

public class TwoPointers {
	public static void main(String[] args) {
//		Node n = getLinkedList();
//		n = removeNthFromEnd(n, 4);
//		n = reverseBetween(n, 1, 3);
//		n = rotateList(n, 2);
//		n = swapPairs(n);
//		n.tranverse();

//		int[] a = {1, 2, 2, 0, 0, 1};
//
//		ArrayList<Integer> aList =  new ArrayList<>(Arrays.asList(ArrayUtils.toObject(a)));

//		System.out.println(Arrays.toString(twoSum(aList, 9)));
//		System.out.println(Arrays.toString(threeSum(aList, 3)));
//		System.out.println(threeSumCloset(aList, 9));
//		System.out.println(fourSum(aList, 10));
//		sortColors(aList);
//		System.out.println(aList);
//		aList = new ArrayList<>(Arrays.asList(0,1,0,2,1,0,1,3,2,1,2,1));
//		System.out.println(totalTrap(aList));
//		System.out.println(maxSubContainer(aList));

//		System.out.println(addBinary("11", "1"));

//		Node a = new Node(2), b = new Node(5);
//		a.append(4);
//		a.append(3);
//		b.append(6);
//		b.append(4);
//		Node n = addTwoNumber(a, b);
//		n.tranverse();

//		int[] a = {1, 2, 3, 4, 5, 0, 0, 0, 0, 0};
//		mergeSortedArray(a, new int[]{6, 7, 8}, 5);
//		System.out.println(Arrays.toString(a));

//		Node a = new Node(1), b = new Node(4);
//		a.append(5);
//		a.append(10);
//		b.append(7);
//		b.append(9);
//		Node n = mergeSortedLinedList(a, b);
//		n.tranverse();

//		System.out.println(multiplyString_1("123", "10"));
//		System.out.println(multiplyString_2("100000", "1000000000000000000000000000000"));

		ListNode a = new ListNode(1);
		a.append(4);
		a.append(3);
		a.append(2);
		a.append(5);
		a.append(2);
		partitionList(a, 0);
		a.tranverse();
	}



	static ListNode removeNthFromEnd(ListNode start, int n) {
		if (start == null || n < 0) {
			return null;
		}

		ListNode r = start, end = start;
		for (int i = 0; i < n; i++) {
			if (end.next == null) {
				return null;
			} else {
				end = end.next;
			}
		}

		while (end.next != null) {
			end = end.next;
			r = r.next;
		}

		//Remove, check is start or not
		if (start == r) {
			return start.next;
		} else {
			ListNode prev = start;
			while (prev.next != r) {
				prev = prev.next;
			}

			prev.next = prev.next.next;

			return start;
		}
	}

	/**
	 * continuous put the last one to the beginning.
	 */
	static ListNode reverseBetween(ListNode start, int m, int n) {
		ListNode prev_start = new ListNode(-1);
		ListNode prev_m = prev_start;
		prev_m.next = start;

		for (int i = 0; i < m; i++) {
			prev_m = prev_m.next;
		}

		ListNode head = prev_m.next;
		for ( ; m < n; m++) {
			ListNode temp = head.next;
			head.next = temp.next;
			temp.next = prev_m.next;
			prev_m.next = temp;
		}
		return prev_start.next;
	}

	/**
	 * Given a list, rotate the list to the right by k places, where k is non-negative.
	 * For example:
	 * Given 1->2->3->4->5->NULL and k = 2,
	 * return 4->5->1->2->3->NULL.
	 *
	 * Solution:
	 * Go to end, connnect the end to the head, get the len of linklist.
	 * Then go (len - k) steps, cut. If k is bigger than n, go len - k % len steps.
	 */
	static ListNode rotateList(ListNode start, int k) {
		if (start == null || k <= 0) {
			return start;
		}

		ListNode r = start;
		int len = 1;
		while (r.next != null) {
			r = r.next;
			len++;
		}

		r.next = start;
		for (int i = 0; i < (len - k); i++) {
			r = r.next;
		}

		ListNode newStart = r.next;
		r.next = null;

		return newStart;
	}

	/**
	 * You are given a string, S, and a list of words, L, that are all of the same length.
	 * Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.
	 *
	 * For example, given:
	 * S: "barfoothefoobarman"
	 * L: ["foo", "bar"]
	 * You should return the indices: [0,9] (order does not matter).
	 *
	 * Solution:
	 * Put all word in hashmap, because word cout appear multiple times, so key :word,value : count.
	 * Loop through string, for each substring = s[i:i+len], get all words in substring, check each word is in map or not.
	 * If in map, count--. If not in map, break. Check map is empty in the end.
	 */
	static ArrayList<Integer> findSubString(String s, String[] wordList) {
		if (s == null || wordList == null) {
			return null;
		}

		ArrayList<Integer> list = new ArrayList<>();
		HashMap<String, Integer> wordTable = new HashMap<>();
		for (String word : wordList) {
			int count = wordTable.containsKey(word) ? wordTable.get(word) : 0;
			wordTable.put(word, ++count);
		}

		int wordLen = wordList[0].length(), allLen = wordList.length * wordList[0].length();
		for (int i = 0; i < s.length() - allLen; i++) {
			String subStr = s.substring(i, allLen);
			HashMap<String, Integer> temp = (HashMap<String, Integer>) wordTable.clone();
			while (true) {
				String word = subStr.substring(0, wordLen);

				if (! temp.containsKey(word) || temp.get(word) == 0) {
					break;
				} else {
					temp.put(word, temp.get(word) - 1);
				}

				subStr = subStr.substring(wordLen);
				if (subStr.isEmpty()) {
					list.add(i);
				}
			}
		}
		return list;
	}

	/**
	 * Given a linked list, swap every two toVerticeList nodes and return its head.
	 * For example,
	 * Given 1->2->3->4, you should return the list as 2->1->4->3.
	 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
	 *
	 * Solution:
	 * Like the rever in [m, n], p->c->n to p->n->c. Then, p = p.next, c = c.next, n = c.next;
	 */
	static ListNode swapPairs(ListNode start) {
		if (start == null || start.next == null) {
			return start;
		}

		ListNode p = new ListNode(0);
		p.next = start;
		ListNode c = start, n = start.next;
		start = start.next; // because the start is swapped
		while (n != null) {
			//swap
			p.next = n;
			c.next = n.next;
			n.next = c;

			//move on
			p = c;
			c = c.next;
			n = c.next;
		}
		return start;
	}

	/**
	 * Given an array of integers, find two numbers such that they add up to a specific target number.
	 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
	 * Please note that your returned answers (both index1 and index2) are not zero-based.
	 *
	 * You may assume that each input would have exactly one solution.
	 * Input: numbers={2, 7, 11, 15}, target=9
	 * Output: index1=1, index2=2
	 *
	 * Solution:
	 * Indexmap, time is O(n) but this need O(n) space and require all the elem is UNIQUE.
	 */
	static int[] twoSum(ArrayList<Integer> a, int target) {
		HashMap<Integer, Integer> indexMap = new HashMap<>();
		for (int i = 0; i < a.size(); i++) {
			indexMap.put(a.get(i), i);
		}

		int[] index = new int[2];
		for (int i = 0; i < a.size(); i++) {
			index[0] = i;
			int remain = target - a.get(i);

			if (indexMap.containsKey(remain)) {
				index[1] = indexMap.get(remain);
				return index;
			}
		}
		return null;
	}

	/**
	 * Only return true or false.
	 * In this case, we can sort. Use two pointers.
	 *
	 * Time is O(nLog(n)), space is O(1)
	 */
	static boolean hasTwoSUm(ArrayList<Integer> a, int target) {
		Collections.sort(a);
		int start = 0, end = a.size() - 1;

		while (start < end) {
			int sum = a.get(start) + a.get(end);
			if (sum == target) {
				return true;
			} else if (sum > target) {
				end--;
			} else {
				start++;
			}
		}
		return false;
	}

	/**
	 * Given an array S of n integers and a target, are there elements a, b, c in S such that a + b + c = target?
	 * Find all unique triplets in the array which gives the sum of zero.
	 *
	 * Note:
	 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
	 * The solution set must not contain duplicate triplets.
	 * For example, given array S = [-1,0,1,2,-1,-4], target = 3, return [1,2,3]
	 *
	 * Solution:
	 * For each one, do two sum.
	 */
	static int[] threeSum(ArrayList<Integer> a, int target) {
		if (a == null || a.size() == 0) {
			return null;
		}

		HashMap<Integer, Integer> indexMap = new HashMap<>();
		for (int i = 0; i < a.size(); i++) {
			indexMap.put(a.get(i), i);
		}

		int[] index = new int[3];
		for (int i = 0; i < a.size(); i++) {
			int remain_2 = target - a.get(i);
			index[0] = i;

			for (int j = i + 1; j < a.size(); j++) {
				index[1] = j;
				int remain_1 = remain_2 - a.get(j);
				if (indexMap.containsKey(remain_1)) {
					index[2] = indexMap.get(remain_1);
					return index;
				}
			}
		}
		return null;
	}

	/**
	 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
	 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
	 *
	 * For example, given array S = {-1 2 1 -4}, and target = 1.
	 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
	 *
	 * Solution:
	 * We return sum, not index here, so we can sort and use two pointer.
	 * Just keep min with abs(remain - summ)
	 */
	static int threeSumCloset(ArrayList<Integer> a, int target) {
		Collections.sort(a);

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < a.size(); i++) {
			int remain = target - a.get(i);
			int start = i + 1, end = a.size() - 1;
			while (start < end) {
				int sum = a.get(start) + a.get(end);
				int diff = remain - sum;
				if (diff == 0) {
					return target;
				} else {
					min = min > Math.abs(diff) ? Math.abs(diff) : min;
				}

				if (sum > remain) {
					end--;
				} else {
					start++;
				}
			}
		}
		return target - min;
	}

	/**
	 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
	 * Find all unique quadruplets in the array which gives the sum of target.
	 *
	 * Note:
	 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
	 * The solution set must not contain duplicate quadruplets.
	 *
	 * Example:
	 * given array S = {1 0 -1 0 -2 2}, and target = 0.

	 * A solution set is:
	 * (-1,  0, 0, 1)
	 * (-2, -1, 1, 2)
	 * (-2,  0, 0, 2)
	 *
	 * Solution:
	 * Like 3 sum, still sort first, then use two pointer from start and end, check sum each time.
	 * Time : O(n^3), space:O(n) because we need to path_sum_output the result list.
	 */
	static ArrayList<ArrayList<Integer>> fourSum(ArrayList<Integer> a, int target) {
		if (a == null) {
			return null;
		}
		Collections.sort(a);

		HashSet<ArrayList<Integer>> hashSet = new HashSet<>();
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();

		for (int i = 0; i < a.size(); i++) {
			for (int j = i + 1; j < a.size(); j++) {
				int start = j + 1, end = a.size() - 1;
				while (start < end) {
					int sum = a.get(i) + a.get(j) + a.get(start) + a.get(end);

					if (sum == target) {
						ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(a.get(i), a.get(j), a.get(start), a.get(end)));
						if (!hashSet.contains(temp)) {
							result.add(temp);
							hashSet.add(temp);
						}
						// Unlike 3sum, we don't return here.
						start++;
						end--;
					} else if (sum < target) {
						start++;
					} else {
						end--;
					}
				}
			}
		}
		return result;
	}

	/**
	 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
	 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
	 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
	 * Return Max volume

	 * Note: You may not slant the container.
	 *
	 * Solution:
	 * Two pointer, one from start, one from end. Lower height determine the containing volume.
	 * So move lower one to the center, and keep max value.
	 */
	static int maxVolume(ArrayList<Integer> height) {
		if (height == null) {
			return 0;
		}

		int max = Integer.MIN_VALUE;
		int start = 0, end = height.size() - 1;
		while (start < end) {
			int volume = (end - start) * Math.min(height.get(start), height.get(end));
			max = volume > max ? volume : max;
			if (height.get(start) > height.get(end)) {
				end--;
			} else {
				start++;
			}
		}
		return max;
	}

	/**
	 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are toVerticeList.
	 * With the colors in the order red, white and blue.
	 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
	 *
	 * Follow up:
	 * A rather straight forward solution is a two-pass algorithm using counting sort.
	 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
	 * Could you come up with an one-pass algorithm using only constant space?
	 *
	 * Solution:
	 * 1.Count and write new array, O(n) space.
	 * 2.Bubble sort, O(1) space.
	 * 3.Optimal, use 3 pointers. Becuae we only has 3 colors here, one pointer from start to write 0, one from end to write 2, one for loop, check this elem.
	 * O(n) time, O(1) space.
	 */
	static void sortColors(ArrayList<Integer> colors) {
		int start = 0, end = colors.size() - 1;
		int i = 0;

		while (i <= end) {
			if (colors.get(i) == 0) {
				MyUtils.swap(colors, start++, i++);
			} else if (colors.get(i) == 2) {
				MyUtils.swap(colors, i, end--);
			} else {
				i++;
			}
		}
	}


	/**
	 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
	 * For example,
	 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
	 *
	 * Solution:
	 * For each node, the volume is min(max_left, max_right)
	 * So set maxL is the max_left height for each i, same for maxR.
	 * We loop twice, first to get maxL, second get maxR and compute volume for each i at the same time.
	 */
	static int totalTrap(ArrayList<Integer> a) {
		if (a == null || a.size() < 2) {
			return 0;
		}

		int[] maxL = new int[a.size()], maxR = new int[a.size()];
		maxL[0] = a.get(0);
		maxR[a.size() - 1] = 0;
		// maxL including itself
		for (int i = 1; i < a.size(); i++) {
			maxL[i] = Math.max(maxL[i - 1], a.get(i));
		}

		int total = 0;
		for (int i = a.size() - 2; i > 0 ; i--) {
			maxR[i] = Math.max(maxR[i + 1], a.get(i + 1));
			int volume = Math.min(maxL[i], maxR[i]) - a.get(i);
			if (volume > 0) {
				total += volume;
			}
		}
		return total;
	}

	/**
	 * Follow up:
	 * 如果要求最大的subcontainer的话, 就先这么走一遍, 求得每个i的volume. [0,0,1,0,1,2,1,0,0,1,0,0]
	 * 对这个数组找出最大的连续和. 走一遍, keep max, 遇到0就清零.
	 *
	 * Time: O(n), space:O(n)
	 */
	static int maxSubContainer(ArrayList<Integer> a) {
		if (a == null || a.size() < 2) {
			return 0;
		}

		int[] maxL = new int[a.size()], maxR = new int[a.size()];
		maxL[0] = a.get(0);
		maxR[a.size() - 1] = 0;
		// maxL including itself
		for (int i = 1; i < a.size(); i++) {
			maxL[i] = Math.max(maxL[i - 1], a.get(i));
		}

		int[] volume = new int[a.size()];
		for (int i = a.size() - 2; i > 0 ; i--) {
			maxR[i] = Math.max(maxR[i + 1], a.get(i + 1));
			int temp = Math.min(maxL[i], maxR[i]) - a.get(i);
			if (temp > 0) {
				volume[i] = temp;
			}
		}

		int max = 0, sum = 0;
		for (int v : volume) {
			if (v == 0) {
				sum = 0;
			} else {
				sum += v;
				max = Math.max(sum, max);
			}
		}
		return max;
	}

	/**
	 * Given two binary strings, return their sum (also a binary string).
	 * For example,
	 * a = "11"
	 * b = "1"
	 * Return "100".
	 *
	 * Solution:
	 * Two poiter, scan from back, and keep a carrier.
	 * Here use a little trick to reduce some looping code.
	 */
	static String addBinary(String a, String b) {
		char[] x = a.toCharArray(), y = b.toCharArray();
		int i = 0;
		int carrier = 0;

		StringBuilder output = new StringBuilder();
		while (i < Math.max(x.length, y.length)) {
			int x_i = i < x.length ? x[x.length - 1 - i] - '0' : 0;
			int y_i = i < y.length ? y[y.length - 1 - i] - '0' : 0;
			i++;
			int sum = x_i + y_i + carrier;
			int result = sum % 2;
			carrier = sum >= 2 ? 1 : 0;
			output.insert(0, result);
		}

		if (carrier == 1) {
			output.insert(0, carrier);
		}
		return output.toString();
	}


	/**
	 * You are given two linked list representing two non-negative numbers.
	 * The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * Output: 7 -> 0 -> 8
	 *
	 * Solution:
	 * Similar to addBinary, and this time, it's already in reverse order
	 */
	static ListNode addTwoNumber(ListNode a, ListNode b) {
		ListNode output = null;
		int carrier = 0;

		while (a != null || b != null) {
			int a_v = a == null ? 0 : (Integer)a.val;
			int b_v = b == null ? 0 : (Integer)b.val;
			int sum = a_v + b_v + carrier;
			int result = sum % 10;
			carrier = sum >= 10 ? 1 : 0;
			if (output == null) {
				output = new ListNode(result);
			}  else {
				output.append(result);
			}
			a = a == null ? null : a.next;
			b = b == null ? null : b.next;
		}
		return output;
	}


	/**
	 * Merger sorted array
	 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
	 * Note:
	 * You may assume that A has enough space to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.
	 *
	 * Solution:
	 * Two pointers, merge from back of a to head of a. From m+n-1 to 0.
	 */
	static void mergeSortedArray(int[] a, int[] b, int m) {
		int n = b.length;
		int i = m + n - 1;
		m--;
		n--;
		while (i >= 0) {
			if (m < 0) {
				a[i--] = b[n--];
			} else if (n < 0) {
				a[i--] = a[m--];
			} else {
				if (a[m] >= b[n]) {
					a[i--] = a[m--];
				} else {
					a[i--] = b[n--];
				}
			}
		}
	}

	/**
	 * Merge two sorted linked list and return it as a new list.
	 * The new list should be made by splicing together the nodes of the first two list.
	 *
	 * Example:
	 * 1->2->3, 4->5->6, return 1->2->3->4->5->6
	 *
	 * Solution:
	 * Two pointer
	 */
	static ListNode mergeSortedLinedList(ListNode a, ListNode b) {
		ListNode p = new ListNode(0);
		ListNode start = p;
		while (a != null || b != null) {
			int av = a == null ? Integer.MAX_VALUE : (Integer)a.val;
			int bv = b == null ? Integer.MAX_VALUE : (Integer)b.val;
			if (av < bv) {
				p.next = a;
				a = a.next;
			} else {
				p.next = b;
				b = b.next;
			}
			p = p.next;
		}
		return start.next;
	}




	/**
	 * Given two numbers represented as strings, return multiplication of the numbers as a string.
	 * Note: The numbers can be arbitrarily large and are non-negative.
	 *
	 * Example:
	 * "12", "20", return "240"
	 *
	 * Solution:
	 * 1. turn String into int, multiply, then convert back to string.
	 * O(n) time, O(1) space, but will overflow when number is really big.
	 *
	 * 2. Similart to addTwoNumber, process one by one, take the carrier.
	 */
	static String multiplyString_1(String a, String b) {
		int x = MyUtils.stringToInt(a), y = MyUtils.stringToInt(b);
		return MyUtils.intToString(x * y);
	}

	/**
	 * BIG NUMBER MULTIPLY
	 *
	 * Reverse string, loop from the one -> ten -> hunderd
	 * Keep the sum array to track the sum of each digit.
	 *
	 * Then convert to string.
	 */
	static String multiplyString_2(String a, String b) {
		a = new StringBuilder(a).reverse().toString();
		b = new StringBuilder(b).reverse().toString();

		// This is key, a.len + b.len is big enough to hold their multiplication.,
		int[] sum = new int[a.length() + b.length()];
		for (int i = 0; i < a.length(); i++) {
			int x = a.charAt(i) - '0';
			for (int j = 0; j < b.length(); j++) {
				int y = b.charAt(j) - '0';
				sum[i + j] += x * y;
			}
		}

		StringBuilder result = new StringBuilder();
		int carrier = 0;
		for (int v : sum) {
			int s = v + carrier;
			int digit = s % 10;
			carrier = s / 10;
			result.insert(0, digit);
		}
		//don't worry about carrier here, the sum array is long enough to digest carrier
		//Trim 0
		while (result.length() > 1 && result.charAt(0) == '0') {
			result.deleteCharAt(0);
		}
		return result.toString();
	}

	/**
	 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
	 * You should preserve the original relative order of the nodes in each of the two partitions.
	 *
	 * For example,
	 * Given 1->4->3->2->5->2 and x = 3,
	 * return 1->2->2->4->3->5.
	 *
	 * Solution:
	 * 从左往右扫描，找到第一个大于X的指针，然后再该指针左边，不断插入小于X的元素.
	 *
	 * 插入的时候, n是runner, p是插入位置的前面的node, h是n作为runner前面的node, temp是n后面的node.
	 */
	static ListNode partitionList(ListNode n, int target) {
		if (n == null) {
			return null;
		}

		ListNode p = new ListNode(0);
		p.next = n;
		ListNode start = p.next;
		while (n != null) {
			if ((Integer)n.val < target) {
				n = n.next;
				p = p.next;
			} else {
				break;
			}
		}
		ListNode h = p;
		while (n != null) {
			if ((Integer)n.val < target) {
				ListNode temp = n.next;
				n.next = p.next;
				p.next = n;
				p = p.next;
				h.next = temp;
				n = temp;
			} else {
				h = h.next;
				n = n.next;
			}
		}

		return start.next;
	}

	/**
	 * One dimension, line (a,b), line (c,d)
	 * check two lines is intersected or not
	 */
	static boolean isTwoLineIntersect(int a, int b, int c, int d) {
		return (a<=d) && (b>=c);
	}
}
