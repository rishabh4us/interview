import java.util.*;

public class SortSearch {
	public static void main(String[] args) {
//		int[] a = {1, 3, 0, 2, 4,5,5};
//		quickSort(a, 0, a.length - 1);
//		mergeSort(a, 0, a.length - 1);
//		countSort(a);
//		heapSort(a);
//		System.out.println(Arrays.toString(a));
//		System.out.println(kSelection(a, 6, 0, a.length - 1));
//		System.out.println(partition(a, 0, a.length-1, 1));

//		ArrayList<ArrayList<Integer>> kWayList = new ArrayList<>();
//		kWayList.add(new ArrayList<>(Arrays.asList(1,2,3)));
//		kWayList.add(new ArrayList<>(Arrays.asList(4,5,6)));
//		System.out.println(kWayMerge(kWayList));

//		ArrayList<ArrayList<Integer>> allPairs = new ArrayList<>();
//		allPairs.add(new ArrayList<>(Arrays.asList(1,3)));
//		allPairs.add(new ArrayList<>(Arrays.asList(2,6)));
//		allPairs.add(new ArrayList<>(Arrays.asList(8,10)));
//		allPairs.add(new ArrayList<>(Arrays.asList(15,18)));
//		System.out.println(mergeIntervals(allPairs));

//		int[][] matrix = {{1, 3, 5, 7}, {10, 11,16, 20}, {23, 30, 40, 60}};
//		System.out.println(searchMatrix(matrix, 60));

//		System.out.println(Arrays.toString(searchRange(new int[]{1, 3, 4, 8, 8, 10}, 8)));

//		System.out.println(searchRotated_I(new int[]{1,3,}, 1));
//		System.out.println(searchRotated_II(new int[]{1, 1, 3, 1}, 3));

//		System.out.println(divide_helper(Integer.MAX_VALUE, 1));

//		System.out.println(pow(2, 9));

//		System.out.println(sqrt(2147483647));
//		int[] a = {2}, b = {1};
//		System.out.println(findMedianInsortedArrays(a, b));

//		int[] a = {1,2,2,2, 2, 3,4};
//		System.out.println(Arrays.toString(searchInterval(a, 5)));

//		int[][] intervals = {{1,2}, {2,3}, {1,4}, {2,3}};
//		System.out.println(max_meeting_room(intervals));
//		System.out.println(meeting_room_arrangement(intervals));

//		char[][] grid = {{'a', 'b', 'c'}, {'b', 'b', 'b'}, {'c', 'b', 'a'}};
//		System.out.println(wordSearch(grid, "abc"));
//		int[] a = {2,1,-1, 4, 3};
//		move_slot(a);
//		sort_odd_bigger_than_neighbor(a);
//		System.out.println(Arrays.toString(a));


//		Interval[] list = {new Interval(0,1), new Interval(2,3), new Interval(3,4), new Interval(5,8), new Interval(10,11), new Interval(9,10), new Interval(13,15)};
//		System.out.println(get_loggin_time(list, 0,19));

//		String[] list = {"123", "456", "213", "321", "654"};
//		sortAnagram(list);
//		System.out.println(Arrays.toString(list));
//		String[] list = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
//		System.out.println(search_string_array_with_empty(list, "ball"));

//		ArrayList<Integer> a = new ArrayList(Arrays.asList(0, -1, 1));
//		System.out.println(shuffle(a));

//		int[][] m = {{1,1,0}, {1,1,0}, {1,1,0}};
//		System.out.println(bulp_game(m));

//		int[] a = {6,7,1,2,3,4,5};
//		System.out.println(find_rotate_first_index(a));

//		int[][] m = {{1,2,3}, {5,4,7}, {10,6,11}};
//		System.out.println(k_small_in_sorted_matrix(m, 7));

//		ArrayList<Interval> list = new ArrayList(Arrays.asList(new Interval(0,1), new Interval(2,3), new Interval(3,4), new Interval(5,8), new Interval(10,11), new Interval(9,10), new Interval(13,15)));
//		System.out.println(hasConflict(list, new Interval(11,12)));

//		LinkedList<Integer> list = new LinkedList(Arrays.asList(1,2,3,4,5,6));
//		System.out.println(yp_loop_remove(list, 2));

		int[] a = {0,2,1,1,1,1,0,0,0,2};
		sortColors(a);
		System.out.println(Arrays.toString(a));

//		ListNode a = new ListNode(1), b = new ListNode(5);
//		a.append(2);
//		a.append(3);
//		a.append(4);
//		b.append(6);
//		ListNode x = interleave(a, b);
//		x.tranverse();

//		int[] a = {0,1,2,3,4,4,3,2,1,0};
//		countSort(a, 5);
//		System.out.println(Arrays.toString(a));
	}
	/**
	 * Algorithm:
	 * 1. If the array contains only one element or zero elements then the array is sorted.
	 * 2. Select an element from the array, like the middle one. This element is called the "pivot element".
	 * 3. All elements which are smaller than the pivot element are moved to left and all elements which are larger move to the right.
	 * 4. Sort both arrays by recursively applying Quicksort to them.
	 *
	 * Quicksort can be implemented to sort "in-place".
	 *
	 * start and end is includsive
	 */
	static void quickSort(int[] a, int start, int end) {
		if (a.length <= 1) {
			return;
		}
		if (start < end) {
			//take a pivot
			int pivotIndex = partition(a, start, end);
			quickSort(a, start, pivotIndex-1);
			quickSort(a, pivotIndex+1, end);
		}
	}

	/**
	 * start and end is inclusive
	 */
	static void mergeSort(int[] a, int start, int end) {
		if (start >= end) {
			return;
		}

		int mid = (end - start) / 2 + start;

		mergeSort(a, start, mid);
		mergeSort(a, mid + 1, end);

		//merge, need extra space
		int i = mid, j = end, k = end - start;
		int[] temp = new int[end - start + 1];
		while (i >= start && j >= mid + 1) {
			if (a[i] > a[j]) {
				temp[k--] = a[i--];
			} else {
				temp[k--] = a[j--];
			}
		}

		while (i >= start) {
			temp[k--] = a[i--];
		}

		while (j >= mid + 1) {
			temp[k--] = a[j--];
		}

		System.arraycopy(temp, 0, a, start, temp.length);
	}

	/**
	 * Count sort.
	 * Time: O(n), space: O(n)
	 */
	static void countSort(int[] a) {
		int b[] = new int[a.length];
		int max = a[0], min = a[0];
		for(int i : a) {
			max = i > max ? i : max;
			min = i > min ? min : i;
		}
		//这里count的大小是要排序的数组中，元素大小的极值差+1
		int[] c = new int[max - min + 1];
		for(int v : a) {
			c[v - min] += 1;
		}

		//这里的c数组里面, i是第i大的元素的位置.
		for(int i = 1; i < c.length; i++) {
			c[i] += c[i-1];
		}

		for(int i = a.length-1; i >= 0; i--) {
			b[--c[a[i] - min]] = a[i];//按存取的方式取出c的元素
		}
		System.arraycopy(b, 0, a, 0, a.length);
	}

	/**
	 * Facebook interview.
	 * 3 color is 0, 1, 2. And maintain the relative order within each color
	 * using partition's idea, swap
	 */
	static void sortColors(int[] a) {
		if (a == null || a.length <= 1) {
			return;
		}
		int start = 0, end = a.length - 1;
		int i = 0;

		while (i <= end) { //MUST be i <= end, because end index is used to write
			if (a[i] == 0) {
				if (i > start) {
					MyUtils.swap(a, i, start++);
				} else {
					//i == start
					i++;
					start++;
				}
			} else if (a[i] == 2) {
				if (i < end) {
					MyUtils.swap(a, i, end--);
				} else {
					i++;
					end--;
				}
			} else {
				i++;
			}
		}
	}

	/**
	 * Count sort.
	 * In array a, at most contains [0, k) number
	 */
	static void countSort(int[] a, int k) {
		if (a == null || k <= 0) {
			return;
		}
		int[] count = new int[k];
		for (int i : a) {
			count[i]++;
		}
		for (int i = 0, write = 0; i < k; i++) {
			while(count[i] > 0) {
				a[write] = i;
				write++;
				count[i]--;
			}
		}
	}

	/**
	 * heap sort
	 *
	 * Time: O(nLog(n)), space:O(n)
	 */
	static void heapSort(int[] a) {
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		for (int v : a) {
			heap.add(v);
		}
		for (int i = 0; i < a.length; i++) {
			a[i] = heap.poll();
		}
	}


	/*************** Merge ****************/

	/**
	 * Get the k_th elem
	 * Like quick sort.
	 */
	static int kSelection(int[] a, int k, int start, int end) {
		int pivot_index = partition(a, start, end);

		// check pivot index, k is still k. Because array size doesn't change
		if (pivot_index == k) {
			return a[k];
		} else if (pivot_index < k) {
			return kSelection(a, k, pivot_index + 1, end);
		} else {
			return kSelection(a, k, start, pivot_index - 1);
		}
	}

	//This is the correct partition algo!!!!
	//Idea is like the remove dup in array
	//Two pointers, one for loop, one for write
	private static int partition(int[] a, int start, int end) {
		int pivotIndex = (end - start) / 2 + start;
		int value = a[pivotIndex];
		MyUtils.swap(a, pivotIndex, end);
		int writeIndex = start;
		for (int i=start; i<end; i++) {
			if (a[i] <= value) {
				MyUtils.swap(a, i, writeIndex);
				writeIndex++;
			}
		}
		MyUtils.swap(a, end, writeIndex);
		return writeIndex;
	}

	/**
	 * Merge K sorted list into one sorted list.
	 */
	static ArrayList<Integer> kWayMerge(ArrayList<ArrayList<Integer>> kWayList) {
		ArrayList<Integer> result = new ArrayList<>();
		while (kWayList.size() != 0) {
			int min_v = Integer.MAX_VALUE, min_index = 0;
			for (int i = 0; i < kWayList.size(); i++) {
				if (min_v > kWayList.get(i).get(0)) {
					min_v = kWayList.get(i).get(0);
					min_index = i;
				}
			}
			kWayList.get(min_index).remove(0);
			if (kWayList.get(min_index).size() == 0) {
				kWayList.remove(min_index);
			}
			result.add(min_v);
		}
		return result;
	}

	/**
	 * Given a collection of intervals, merge all overlapping intervals.
     * All pair is sorted.
	 *
	 * For example,
	 * Given [1,3],[2,6],[8,10],[15,18],
	 * return [1,6],[8,10],[15,18].
	 */
	static ArrayList<ArrayList<Integer>> mergeIntervals(ArrayList<ArrayList<Integer>> allPairs) {
		int start = 0;
		for (int i = 1; i < allPairs.size(); i++) {
			if (allPairs.get(i).get(0) < allPairs.get(i - 1).get(1)) {
				allPairs.get(i).set(0, allPairs.get(i - 1).get(0));
				start = i;
			}
		}
		return new ArrayList<>(allPairs.subList(start, allPairs.size()));
	}


	/*************** BFS, DFS ****************/

	static void preOrder(TreeNode root) {
		if (root != null) {
			System.out.println(root.val);
			preOrder(root.left);
			preOrder(root.right);
		}
	}


	static void inOrder(TreeNode root) {
		if (root != null) {
			preOrder(root.left);
			System.out.println(root.val);
			preOrder(root.right);
		}
	}

	static void postOrder(TreeNode root) {
		if (root != null) {
			preOrder(root.left);
			preOrder(root.right);
			System.out.println(root.val);
		}
	}

	static void BFS(TreeNode root, int target) {
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.push(root);

		while (queue.size() != 0) {
			TreeNode node = queue.poll();
			if (node.val == target) {
				System.out.println(root.val);
			}

			if (root.left != null) {
				queue.push(root.left);
			}

			if (root.right != null) {
				queue.push(root.right);
			}
		}
	}


	/*************** Binary Search ****************/


	/**
	 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
	 * Integers in each row are sorted from left to right.
	 * The first integer of each row is greater than the last integer of the previous row.
	 * For example,
	 *
	 * Consider the following matrix:
	 * [
	 * [1,   3,  5,  7],
	 * [10, 11, 16, 20],
	 * [23, 30, 34, 50]
	 * ]
	 *
	 * Given target = 3, return true.
	 */
	static boolean searchMatrix(int[][] matrix, int target) {
		int colLength = matrix[0].length;

		for (int[] row : matrix) {
			if (row[colLength - 1] >= target) {
				//Binary search
				int start = -1, end = colLength;
				while (end - start > 1) {
					int mid = (end - start) / 2 + start;
					if (row[mid] == target) {
						return true;
					} else if (row[mid] > target) {
						end = mid;
					} else {
						start = mid;
					}
				}
				return false;
			}
		}
		return false;
	}


	/**
	 * Given a sorted array of integers, find the starting and ending position of a given target value.
	 * If the target is not found in the array, return [-1, -1].
	 *
	 * For example,
	 * Given [5, 7, 7, 8, 8, 10] and target value 8,
	 * return [3, 4].
	 *
	 * Solution:
	 * Recursive Binary search, once found the target, go to left and right to get the boundary.
	 * REMEMBER, start and end is always exclusive.
	 */
	static int[] searchRange(int[] a, int target) {
		int[] min_max = {-1, -1};
		searchRangeHelper(a, target, 0, a.length, min_max);
		return min_max;
	}

	private static void searchRangeHelper(int[] a, int target, int start, int end, int[] min_max) {
		if (end - start <= 1) {
			return;
		}
		int mid = (end - start) / 2 + start;
		if (a[mid] == target) {
			if (min_max[0] == -1) {
				// not init
				min_max[0] = min_max[1] = mid;
				searchRangeHelper(a, target, start, mid, min_max);
				searchRangeHelper(a, target, mid, end, min_max);
			} else {
				min_max[0] = Math.min(min_max[0], mid);
				min_max[1] = Math.max(min_max[1], mid);
			}

		} else if (a[mid] < target) {
			searchRangeHelper(a, target, mid, end, min_max);
		} else {
			searchRangeHelper(a, target, start, mid, min_max);
		}
	}


	/**
	 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
	 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	 * You are given a target value to search. If found in the array return its index, otherwise return -1.
	 * You may assume no duplicate exists in the array.
	 *
	 * Solution:
	 * Binary search, compare start with mid make sure go to the left or right.
	 */
	static int searchRotated_I(int[] a, int target) {
		int start = -1, end = a.length;
		while (end - start > 1) {
			int mid = (end - start) / 2 + start;
			if (a[mid] == target) {
				return mid;
			}

			if (a[mid] >= a[start + 1]) {
				//left continuous
				if (target < a[mid] && target >= a[start + 1]) {
					//left
					end = mid;
				} else {
					start = mid;
				}
			} else {
				//right continous
				if (target > a[mid] && target <= a[end - 1]) {
					//right
					start = mid;
				} else {
					end = mid;
				}
			}
		}
		return -1;
	}

	/**
	 * Follow up for "Search in Rotated Sorted Array":
	 * return True or false.
	 * Example:[1,3,1,1], 3. return true.
	 */
	static boolean searchRotated_II(int[] a, int target) {
		int start = -1, end = a.length;
		while (end - start > 1) {
			int mid = (end - start) / 2 + start;
			if (a[mid] == target) {
				return true;
			}
			if (a[mid] > a[start + 1]) {
				// left continuous
				if (target < a[mid] && target >= a[start + 1]) {
					//left
					end = mid;
				} else {
					start = mid;
				}
			} else if (a[mid] < a[start + 1]) {
				//right continuous
				if (target > a[mid] && a[end - 1] >= target) {
					//right
					start = mid;
				} else {
					end = mid;
				}
			} else {
				//a[start] = a[mid]
				start++;
			}

		}
		return false;
	}

	/**
	 * There are two sorted arrays A and B of size m and n respectively.
	 * Find the median of the two sorted arrays.
	 * The overall run time complexity should be O(log (m+n)).
	 *
	 * Solution:
	 * 1) 2-way merge, count to median. O((n + m)/2)
	 * 2) binary search O(log(n + m)), equal to find Kth number in sorted array.
	 */
	static double findMedianInsortedArrays(int[] a, int[] b) {
		int totalLength = a.length + b.length;
		if (totalLength % 2 == 1) {
			return findKth(a, b, totalLength / 2);
		} else {
			return (findKth(a, b, totalLength / 2 - 1) + findKth(a, b, totalLength / 2)) / 2;
		}
	}

	static double findKth(int[] a, int[] b, int k) {
		int i = 0, j = 0;
		int min = Integer.MAX_VALUE;
		while (k >= 0) {
			if (j == b.length || (i < a.length && a[i] < b[j])) {
				min = a[i];
				i++;
			} else {
				min = b[j];
				j++;
			}
			k--;
		}
		return min;
	}


	/**
	 * Divide two integers without using multiplication, division and mod operator.
	 *
	 * Solution:
	 * 1.Use minus, 每次减去的值是上次减去的值的两倍. count计数也是两倍, 如果本次减去的值大于被减数了, 就从original divsor从新开始.
	 * 2.Use Bit.
	 */
	static int divide(int dividend, int divisor) {
		if (dividend == 0) {
			return 0;
		}
		if (divisor == 1) {
			return dividend;
		}
		if (divisor == 0) {
			throw new ArithmeticException();
		}

		boolean isPos = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
		long absdDividend = dividend < 0 ? -(long)dividend : (long)dividend;
		long absDivisor = divisor < 0 ? -(long)divisor : (long)divisor;
		int result = divideHelper(absdDividend, absDivisor);
		return isPos ? result : 0 - result;
	}

	static int divideHelper(long dividend, long divisor) {
		long remain = dividend, minus = divisor;
		int count = 0;
		int unit = 1;
		while (remain >= divisor) {
			if (remain >= minus) {
				remain -= minus;
				count += unit;
				unit += unit;
				minus += minus;
			} else {
				minus = divisor;
				unit = 1;
			}
		}
		return count;
	}

	/**
	 * Implement pow(x, n)
	 *
	 * Solution:
	 * Binary split.
	 * x ^ n = x ^ (n/2) * x ^ (n/2)
	 */
	static double pow(double x, int n) {
		return n < 0 ? 1.0 / powHelper(x, -n) : powHelper(x, n);
	}

	static private double powHelper(double x, int n) {
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return x;
		}

		double result = powHelper(x, n / 2);
		if (n % 2 == 0) {
			return result * result;
		} else {
			return result * result * x;
		}
	}

	/**
	 * Implement int sqrt(int x).
	 * Compute and return the square root of x.
	 *
	 * Solution:
	 * Binary search, to pass the test, convert all the input to long.
	 */
	static int sqrt(int x) {
		if (x == 1 || x == 0) {
			return x;
		}

		long long_x = (long)x;
		long start = 1, end = long_x + 1;
		while (end - start > 1) {
			long mid = (end - start) / 2 + start;
			long v = mid * mid;
			if (v == long_x) {
				return (int)mid;
			} else if (v > long_x) {
				end = mid;
			} else {
				start = mid;
			}
		}
		return (int)((end - start) / 2 + start);
	}

	/**
	 * given a sorted array, and a duplicate target, return the start index and endIndex
	 * example, [1,2,2,2,3,4], target=2, return [1, 3]; target=5, return [-1,-1]
	 */
	static int[] searchInterval(int[] a, int target) {
		int[] result = {-1, -1};
		int start = binarySearchStart(a, target);
		if (start == -1) {
			return result;
		} else {
			result[0] = start;
			result[1] = binarySearchEnd(a, target);
			return result;
		}
	}

	//search Start index for target in a. if no, return -1
	static int binarySearchStart(int[] a, int target) {
		int start = -1, end = a.length;
		while (end - start > 1) {
			int mid = (end - start) / 2 + start;
			if (target <= a[mid]) {
				end = mid;
			} else {
				start = mid;
			}
		}
		return end < a.length && a[end] == target ? end : -1;
	}

	static int binarySearchEnd(int[] a, int target) {
		int start = -1, end = a.length;
		while (end - start > 1) {
			int mid = (end - start) / 2 + start;
			if (target >= a[mid]) {
				start = mid;
			} else {
				end = mid;
			}
		}
		return start > -1 && a[start] == target ? start : -1;
	}

	/**
	 * Facebook interview Question
	 * Meeting room interval problem.
	 * Solution: warp the start point and end point.
	 * sort them, end come before than start.
	 * then loop through, count.
	 */
	static int max_meeting_room(int[][] intervals) {
		ArrayList<IntervalTime> sortedList = new ArrayList();
		for (int[] interval : intervals) {
			sortedList.add(new IntervalTime(interval[0], true));
			sortedList.add(new IntervalTime(interval[1], false));
		}

		Collections.sort(sortedList);
		int count = 0, max = 0;
		for (IntervalTime time : sortedList) {
			if (time.isStart) {
				count++;
				max = Math.max(count, max);
			} else {
				count--;
			}
		}
		return max;
	}

	/**
	 * Facebook interview Question
	 * Follow up, return the meeting room arrangement
	 */
	static ArrayList<ArrayList<IntervalTime[]>> meeting_room_arrangement(int[][] intervals) {
		ArrayList<IntervalTime> sortedList = new ArrayList();
		HashMap<IntervalTime, IntervalTime> map = new HashMap();
		ArrayList<ArrayList<IntervalTime[]>> result = new ArrayList();

		for (int[] interval : intervals) {
			IntervalTime start = new IntervalTime(interval[0], true), end = new IntervalTime(interval[1], false);
			map.put(start, end);
			sortedList.add(start);
			sortedList.add(end);
		}

		Collections.sort(sortedList);
		int count = 0;
		for (IntervalTime time : sortedList) {
			if (time.isStart) {
				count++;
				IntervalTime[] meeting = {time, map.get(time)};
				map.remove(time);
				if (result.size() < count) {
					ArrayList<IntervalTime[]> newMeetingRoom = new ArrayList();
					newMeetingRoom.add(meeting);
					result.add(newMeetingRoom);
				} else {
					result.get(count-2).add(meeting);
				}
			} else {
				count--;
			}
		}
		return result;
	}

	static private class IntervalTime implements Comparable<IntervalTime>{
		int time;
		boolean isStart;

		IntervalTime(int time, boolean isStart) {
			this.time = time;
			this.isStart = isStart;
		}

		@Override
		public int compareTo(IntervalTime to) {
			int diff = this.time - to.time; //in ascending order
			if (diff != 0) {
				return diff;
			} else {
				//if same, end one come first, reduce meeting room first!!
				return this.isStart ? 1 : -1;
			}
		}
	}


	/**
	 * Give a char grid, and a target, find how many path.
	 */
	static int wordSearch(char[][] grid, String target) {
		int total = 0;
		for (int row=0; row<grid.length; row++) {
			for (int col=0; col<grid[0].length; col++) {
				if (grid[row][col] == target.charAt(0)) {
					boolean[][] visited = new boolean[grid.length][grid[0].length];
					total += wordSearchHelper(grid, visited, row, col, new StringBuilder(target));
				}
			}
		}
		return total;
	}

	private static int wordSearchHelper(char[][] grid, boolean[][] visited, int row, int col, StringBuilder target) {
		target.deleteCharAt(0);
		visited[row][col] = true;
		char c = grid[row][col];
		if (target.length() == 0) {
			target.insert(0, c);
			visited[row][col] = false;
			return 1;
		}

		int total = 0;
		int[][] directions = {{row + 1, col}, {row - 1, col}, {row, col + 1}, {row, col - 1}};
		for (int[] d : directions) {
			if (isLegal(grid, visited, d[0], d[1], target.charAt(0))) {
				total += wordSearchHelper(grid, visited, d[0], d[1], target);
			}
		}
		target.insert(0, c);
		visited[row][col] = false;
		return total;
	}

	private static boolean isLegal(char[][] grid, boolean[][] visited, int row, int col, char c) {
		if (row >=0 && row < grid.length && col >= 0 && col < grid[0].length && !visited[row][col] && grid[row][col] == c) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * given array {4,3,-1,0,1} -1 means the slot
	 * return array {0,1,-1,3,4} that slot in the right position. with minium steps
	 * rules: You can only move the number to the slot.
	 */
	static void move_slot(int[] a) {
		//find the slot and the missing value
		HashSet<Integer> set = new HashSet();
		HashMap<Integer, Integer> map = new HashMap();
		for(int i = 0; i<a.length; i++) {
			set.add(i);
		}

		int slotIndex = 0;
		for (int i = 0; i < a.length; i++) {
			int num = a[i];
			if (num == -1) {
				slotIndex = i;
			} else {
				set.remove(num);
				map.put(num, i);
			}
		}

		int missing = set.iterator().next();
		while (true) {
			if (slotIndex != missing) {
				a[slotIndex] = slotIndex; //put the right num
				slotIndex = map.get(slotIndex);
				a[slotIndex] = -1;
			} else {
				//find the incorrect index from end to start
				//if return -1, mean all set.
				int index = move_slot_helper(a);
				if (index == -1) {
					return;
				} else {
					a[slotIndex] = a[index];
					map.put(a[index], slotIndex);
					slotIndex = index;
				}
			}
		}
	}

	private static int move_slot_helper(int[] a) {
		for (int j=a.length-1; j >=0; j--) {
			if (a[j] != -1 && a[j] != j) {
				return j;
			}
		}
		return -1;
	}

	/**
	 * Google interview
	 * merge k stream
	 */
	static void k_sorted_stream_merge(List<IStream> streams, OStream out, Comparator comparator) throws Exception {
		if (streams == null || streams.size() <= 1 || out == null || comparator == null) {
			throw new IllegalArgumentException();
		}
		while (true) {
			List<IStream> more = k_sorted_stream_merge_hasMore(streams);
			if (more.size() == 0) {
				return;
			} else {
				IStream min_stream = more.get(0);
				Object min = min_stream.peek();

				for (IStream s : more) {
					if (comparator.compare(min, s.peek()) > 0) {
						min_stream = s;
						min = s.peek();
					}
				}
				out.append(min_stream.next());
			}
		}
	}

	private static List<IStream> k_sorted_stream_merge_hasMore(List<IStream> streams) {
		ArrayList<IStream> result = new ArrayList();
		for (IStream s : streams) {
			if (s.hasNext()) {
				result.add(s);
			}
		}
		return result;
	}

	static void sort_odd_bigger_than_neighbor(int[] a) {
		if (a == null || a.length <= 1) {
			return;
		}

		for (int i=1; i<a.length; i+=2) {
			if (a[i] > a[i-1] && (i == a.length - 1 || a[i] < a[i+1])) {
				continue;
			} else {
				if (a[i] < a[i-1]) {
					MyUtils.swap(a, i, i-1);
				}
				if (i < a.length - 1 && a[i] < a[i+1]) {
					MyUtils.swap(a, i, i+1);
				}
			}
		}
	}


	/*
	 * Facebook interview Q
	 * Give a list of interval(overlapped) and start time and end time.
	 * return a time that user logged in that time period.
	 */
	static int get_loggin_time(Interval[] list, int start, int end) {
		if (list == null || start >= end) {
			return 0;
		}

		list = merge(list);
		Node<Interval> root = buildBST(list, 0, list.length - 1);
//		return search_interval_BST(root, start, end);
		return search_interval_in_array(list, start, end);
	}

	private static Interval[] merge(Interval[] list) {
		if (list == null || list.length <= 1) {
			return list;
		}
		Arrays.sort(list);
		ArrayList<Interval> res = new ArrayList();
		Interval cur = list[0];
		for (Interval i : list) {
			if (i.end < cur.start) {
				res.add(i);
			} else if (i.start > cur.end) {
				res.add(cur);
				cur = i;
			} else {
				cur.start = Math.min(i.start, cur.start);
				cur.end = Math.max(i.end, cur.end);
			}
		}
		res.add(cur);
		return res.toArray(new Interval[res.size()]);
	}

	private static Node buildBST(Interval[] list, int start, int end) {
		if (start > end) {
			return null;
		}

		int mid = (end - start) / 2 + start;
		Node root = new Node(list[mid]);
		root.left = buildBST(list, start, mid-1);
		root.right = buildBST(list, mid+1, end);
		return root;
	}


	private static int search_interval_BST(Node<Interval> root, int start, int end) {
		if (root == null || start >= end) {
			return 0;
		}
		if (root.val.start > end) {
			return search_interval_BST(root.left, start, end);
		} else if (root.val.end < start) {
			return search_interval_BST(root.right, start, end);
		} else {
			int total = Math.min(root.val.end, end) - Math.max(root.val.start, start);
			total += search_interval_BST(root.left, start, root.val.start);
			total += search_interval_BST(root.right, root.val.end, end);
			return total;
		}
	}

	private static int search_interval_in_array(Interval[] list, int start, int end) {
		if (list == null || start >= end) {
			return 0;
		}
		int total = 0;
		for (Interval i : list) {
			// classic question, how to judge two lines are overlapped?
			if (i.start < end && i.end > start) {
				total += Math.min(i.end, end) - Math.max(i.start, start);
			}
		}
		return total;
	}

	/**
	 * Facebook interview
	 * Given a list of players, Find the champion that one can beat all. If there's no such player, return null.
	 * Each two player fight, we know who wins.
	 *
	 * Algo:
	 * 1.First round, playoff, winner meet the next one. Until one left.
	 * 2.2nd round, the let winner fight with all others, if beat all. return winner. else, return null.
	 */
	static Player findMax(Player[] players) {
		LinkedList<Player> list = new LinkedList(Arrays.asList(players));
		while(list.size() > 1) {
			Player a = list.pop(), b = list.pop();
			if (a.beat(b)) {
				//first win
				list.push(a);
			} else {
				list.push(b);
			}
		}

		Player best = list.pop();
		for (Player p : players) {
			if (p != best && !best.beat(p)) {
				//if best lose
				return null;
			}
		}
		return best;
	}

	/**
	 * Find the celebrity, celebrity knows no one, everyone knows celebrity.
	 * For every peson, there's an knows method
	 * O(n) algorithm. Use stacks.
	 *
	 * Algo:
	 * 1.Take the 1st and 2nd one, say a and b.
	 * 2.If a knows b, remove a.
	 * 3.If a don't know b, remove b.
	 * 4.unitl only one left, loop through everyone, see if that everyone know that one.
	 * 5.If yes, return that one, else return null.
	 */
	static Person find_celebrity(Person[] people) {
		LinkedList<Person> list = new LinkedList(Arrays.asList(people));
		while (list.size() > 1) {
			Person a = list.pop(), b = list.pop();
			if (a.knows(b)) {
				list.push(b);
			} else {
				list.push(a);
			}
		}
		Person c = list.pop();
		for (Person p : people) {
			if (p != c && !p.knows(c)) {
				return null;
			}
		}
		return c;
	}

	/**
	 * Coin change problem. DP.
	 * Algo:
	 * For meony i. opt[i] = min(for denomination d that smaller than i, 1 + opt[i-d])
	 */
	static int coin_change(int[] denominations, int money) {
		int[] opt = new int[money+1];
		// 对每一分钱都找零，即保存子问题的解以备用，即填表
		for (int i = 1; i <= money; i++) {
			// 当用最小币值的硬币找零时，所需硬币数量最多
			int min = i;

			// 遍历每一种面值的硬币，看是否可作为找零的其中之一
			for (int value : denominations) {
				// 若当前面值的硬币小于当前的cents则分解问题并查表
				if (value <= i) {
					int temp = opt[i - value] + 1;
					min = Math.min(min, temp);
				}
			}
			opt[i] = min;
		}
		return opt[money];
	}

	/**
	 * This is a o(n^3) algo.
	 * to Make it O(n*n*log(n)). Use hashmap<String, ArrayList>. As same as anagramGroup in StringManipulation
	 */
	static void sortAnagram(String[] list) {
		if (list == null || list.length <= 2) {
			return;
		}
		LinkedList<String> options = new LinkedList(Arrays.asList(list));

		int i = 0;
		while(options.size() > 0) {
			String s = options.pop();
			list[i++] = s;
			Iterator<String> iter = options.iterator();
			while (iter.hasNext()) {
				String cur = iter.next();
				if (isAnagram(s, cur)) {
					list[i++] = cur;
					iter.remove();
				}
			}
		}
	}

	static boolean isAnagram(String a, String b) {
		HashMap<Character, Integer> map = new HashMap();
		for (char c : a.toCharArray()) {
			int count = map.containsKey(c) ? map.get(c) + 1 : 1;
			map.put(c, count);
		}

		for (char c : b.toCharArray()) {
			if (map.containsKey(c)) {
				int count = map.get(c) - 1;
				if (count == 0) {
					map.remove(c);
				} else {
					map.put(c, count);
				}
			} else {
				return false;
			}
		}

		return map.size() == 0;
	}

	/**
	 * Sorted String array with empty elem. Use Binary search.
	 * If mid is empty, go right find the 1st non-empty. Do comparision.
	 * Use m as runner, mid as the flag index.
	 */
	static int search_string_array_with_empty(String[] a, String t) {
		if (a == null || a.length == 0) {
			return -1;
		}
		int start = -1, end = a.length;
		while (end - start > 1) {
			int mid = (end - start) / 2 + start;
			int m = mid;
			if (a[mid].isEmpty()) {
				while (m < end && a[m].isEmpty()) {
					m++;
				}
				if (m == end) {
					end = mid;
					continue;
				}
			}
			if (a[m].equals(t)) {
				return m;
			} else if (a[m].compareTo(t) > 0) {
				end = mid;
			} else {
				start = mid;
			}
		}
		return -1;
	}

	/**
	 * search in a matrix, this matrix's row and col are sorted
	 * Search from the up-right corner.
	 * if t smaller, col--. else row++
	 */
	static boolean search_in_sorted_matrix(int[][] a, int t) {
		if (a == null) {
			return false;
		}
		int row = 0, col = a[0].length - 1;
		while (row < a.length && col >= 0) {
			if (a[row][col] == t) {
				return true;
			} else if (a[row][col] > t) {
				col--;
			} else {
				row++;
			}
		}
		return false;
	}

	/**
	 * Twitter phone interview
	 * shuffle according to prev num's pattern.
	 * pattern could be anything.
	 *
	 * 1.two prev num's sum equal to third num.
	 * 2.two prev num's square equal to third square.(This func use)
	 */
	static ArrayList<Integer> shuffle(ArrayList<Integer> a) {
		if (a == null || a.size() == 0) {
			return null;
		}
		ArrayList<Integer> res = new ArrayList();
		return shuffle_helper(res, a) ? res : null;
	}

	private static boolean shuffle_helper(ArrayList<Integer> res, ArrayList<Integer> remain) {
		if (remain.size() == 0) {
			return true;
		}

		for (int i = 0; i < remain.size(); i++) {
			int num = remain.get(i);
			if (res.size() < 2 || (num * num) == Math.pow(res.get(res.size() - 1), 2) + Math.pow(res.get(res.size() - 2), 2)) {
				res.add(num);
				ArrayList<Integer> copy = new ArrayList(remain);
				copy.remove(i);
				boolean temp = shuffle_helper(res, copy);
				if (temp) {
					return true;
				}
				res.remove(res.size() - 1);
			}
		}
		return false;
	}


	/**
	 * The bulp game.
	 * Given a matrix, only 1 and 0.
	 * set elem to 1. Others elem srround will switch value.
	 * return if there's a way to make all matrix 0.
	 *
	 * Algo:
	 * loop all elem, if this elem is 1 and its surround is all 1. Set to 0.
	 * Then check board. if still 1 remains, recusive call self. else return true.
	 * out of loop return false.
	 */
	static boolean bulp_game(int[][] m) {
		if (m == null) {
			return false;
		}
		int rlen = m.length, cLen = m[0].length;
		for (int row = 0; row < rlen; row++) {
			for (int col=0; col < cLen; col++) {
				if (m[row][col] == 1 && isSurroundOne(m, row, col)) {
					setSurround(m, row, col, 0);
					if (checkBoard(m)) {
						return true;
					} else {
						boolean res = bulp_game(m);
						if (res) {
							return true;
						} else {
							setSurround(m, row, col, 1);
						}
					}
				}
			}
		}
		return false;
	}

	private static boolean isSurroundOne(int[][] m, int row, int col) {
		int[][] pos = {{row - 1, col - 1}, {row - 1, col}, {row - 1, col + 1}, {row, col - 1}, {row, col}, {row, col + 1}, {row + 1, col - 1}, {row + 1, col}, {row + 1, col + 1}};
		for (int[] p : pos) {
			if (isLeagal(m, p[0], p[1]) && m[p[0]][p[1]] == 0) {
				return false;
			}
		}
		return true;
	}

	private static void setSurround(int[][] m, int row, int col, int val) {
		int[][] pos = {{row - 1, col - 1}, {row - 1, col}, {row - 1, col + 1}, {row, col - 1}, {row, col}, {row, col + 1}, {row + 1, col - 1}, {row + 1, col}, {row + 1, col + 1}};
		for (int[] p : pos) {
			if (isLeagal(m, p[0], p[1])) {
				m[p[0]][p[1]] = val;
			}
		}
	}

	private static boolean isLeagal(int[][] m, int row, int col) {
		int rlen = m.length, cLen = m[0].length;
		return row >= 0 && row < rlen && col >= 0 && col < cLen;
	}

	private static boolean checkBoard(int[][] m) {
		for (int[] row : m) {
			for (int i : row) {
				if (i == 1) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * find rotate first/break index
	 */
	static int find_rotate_first_index(int[] a) throws IllegalArgumentException{
		if (a == null || a.length == 0) {
			throw new IllegalArgumentException();
		}
		int start = 0, end = a.length - 1;
		while (end > start) {
			int mid = (end - start) / 2 + start;
			if (a[mid] < a[end]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}

	/**
	 * Given a matrix, each row and each col is sorted, return the k smallest.
	 */
	static ArrayList<Integer> k_small_in_sorted_matrix(int[][] m, int k) throws IllegalArgumentException {
		if (m == null || k < 0 || k > m.length * m[0].length) {
			throw new IllegalArgumentException();
		}
		int rlen = m.length, clen = m[0].length;
		ArrayList<Integer> res = new ArrayList();
		boolean[][] visited = new boolean[rlen][clen];
		PriorityQueue<MatrixCell> heap = new PriorityQueue();

		heap.offer(new MatrixCell(0, 0, m[0][0]));
		visited[0][0] = true;
		while (res.size() < k) {
			MatrixCell c = heap.poll();
			res.add(c.val);
			int[][] next = {{c.row, c.col + 1}, {c.row + 1, c.col}};
			//put next smaller to the heap
			for (int[] pos : next) {
				if (pos[0] < rlen && pos[1] < clen && !visited[pos[0]][pos[1]]) {
					heap.offer(new MatrixCell(pos[0], pos[1], m[pos[0]][pos[1]]));
					visited[pos[0]][pos[1]] = true;
				}
			}
		}
		return res;
	}

	/**
	 * Meeting room problem
	 * A new meeting can be fit into current schedule or not.
	 */
	static boolean hasConflict(List<Interval> list, Interval newInterval) throws IllegalArgumentException{
		if (list == null || newInterval == null) {
			throw new IllegalArgumentException();
		}
		Collections.sort(list);
		for (Interval i : list) {
			if (i.end <= newInterval.start) {
				continue;
			} else if (i.start >= newInterval.end) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * YP interview Question.
	 * Given a ramdom LinkedList and num n. Loop through this list, in a circle.
	 * Everytime move forward, n--. if n == 0. take the node into result list, and n = n - 1.
	 * When n == 0 and loop to the end of list, break.
	 */
	static LinkedList<Integer> yp_loop_remove(LinkedList<Integer> list, int n) {
		if (list == null || n <= 0 ) {
			return null;
		}
		int m = n, count = 0, i = 0;
		LinkedList<Integer> res = new LinkedList();
		while (true) {
			if (count == m) {
				res.add(list.remove(i)); //NO i++, because we've removed current index
				if (i == list.size()) {
					break;
				}
				m = m == 0 ? n : --m;
				count = 0;
			} else {
				count++;
				i = (i + 1) % list.size();
			}
		}
		return res;
	}


}


abstract class Person {
	abstract boolean knows(Person a);
}

abstract class Player {
	abstract boolean beat(Player to);
}

class MatrixCell implements Comparable<MatrixCell> {
	int row, col, val;

	MatrixCell(int r, int c, int v) {
		row = r;
		col = c;
		val = v;
	}

	public int compareTo(MatrixCell to) {
		return val - to.val;
	}
}

class Node <T> {
	T val;
	Node left, right;
	Node (T val) {
		this.val = val;
	}
}

class Interval implements Comparable<Interval>{
	int start, end;
	Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Interval to) {
		return this.start == to.start ? this.start - to.start : this.end - to.end;
	}
}


/**
 * Data structure for K-Sorted-Stream merge
 */
interface IStream<T> {
	boolean hasNext();
	T next();
	T peek();
}

interface OStream<T> {
	void append(T val);
}

class AnagramComparator implements Comparator<String> {
	public int compare(String a, String b) {
		char[] ac = a.toCharArray(), bc = b.toCharArray();
		Arrays.sort(ac);
		Arrays.sort(bc);
		String sorted_a = new String(ac), sorted_b = new String(bc);
		return sorted_a.compareTo(sorted_b);
	}
}