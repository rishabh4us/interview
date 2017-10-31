import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class CombinationAndPermutation {
	public static void main(String[] args) {
//		int[] a = {1,2,3,0};
//		char[] b = {'h', 'a', 't'};
//		ArrayList<Object> objList = new ArrayList<Object>(Arrays.asList(ArrayUtils.toObject(b)));
//		ArrayList<Integer> aList = new ArrayList<Integer>(Arrays.asList(ArrayUtils.toObject(a)));
//		System.out.println(permutations(aList));
//		nextPermutation(aList);
//		System.out.println(aList);

//		System.out.println(getKthPermutation(4, 6));

//		int[] a = {4,3,2,1,2,2,5};
//		System.out.println(phone_letter_combination("012"));
		System.out.println(three_array_sum(new int[]{1,2,3,4}, new int[]{5,6,7,8}, new int[]{9,0}, 10));
	}

	/**
	 * Given a collection of numbers, return all possible permutations.
	 * For example,
	 * [1,2,3] have the following permutations:
	 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
	 *
	 * Solution:
	 * DFS, recursive solution.
	 * 每次循环, 以第i个元素开头, 求剩下的所有的以a[i]为第一个元素的permutation.
	 *
	 * Follow up:
	 * If there's duplicate elem in array, Remove all the duplicate permutation.
	 * Solution:
	 * Sort first, when looping permute, check this elem is different with last one.
	 */
	static ArrayList<ArrayList<Object>> permutations(ArrayList<Object> a) {
		Collections.sort(a, new MyComparator());
		ArrayList<ArrayList<Object>> result = new ArrayList<>();
		permute(a, 0 , result);
		return result;
	}

	static void permute(ArrayList<Object> a, int start, ArrayList<ArrayList<Object>> result) {
		if (start >= a.size()) {
			result.add((ArrayList<Object>) a.clone());
		}

		for (int i = start; i < a.size(); i++) {
			if (i > start && a.get(i).equals(a.get(i - 1))) {
				// skip the same one, when sorted
				continue;
			}
			MyUtils.swap(a, start, i);
			permute(a, start + 1, result);
			MyUtils.swap(a, start, i);
		}
	}

	/**
	 * Next permutation 即找到下一个刚好比当前大的数.
	 * 123, 下一个比123大, 而且也是这几个digit组合的就是132, 再下一个是213, 231,312,321->123.
	 *
	 * 1,2,3 → 1,3,2
	 * 3,2,1 → 1,2,3
	 * 1,1,5 → 1,5,1
	 *
	 * Solution:
	 * 1. Find the highest index i such that s[i] < s[i+1]. If no such index exists, the permutation is the last permutation.
	 * 2. Find the highest index j > i such that s[j] > s[i]. Such a j must exist, since i+1 is such an index.
	 * 2. Swap a[j], a[i].
	 * 3. Reverse the sequence from a[i + 1] up to end.
	 */
	static void nextPermutation(ArrayList<Integer> a) {
		int i = a.size() - 2;
		for (; i >= 0; i--) {
			if (a.get(i) < a.get(i + 1)) {
				break;
			}
		}

		if (i == -1) {
			MyUtils.reverse(a, 0, a.size() - 1);
		} else {
			int j = i + 1;
			for (;j < a.size(); j++) {
				if (a.get(j) > a.get(i)) {
					break;
				}
			}
			MyUtils.swap(a, j, i);
			MyUtils.reverse(a, i + 1, a.size() - 1);
		}
	}

	/**
	 * The set [1,2,3,…,n] contains a total of n! unique permutations.

	 * By listing and labeling all of the permutations in order,
	 * We get the following sequence (ie, for n = 3):

	 * "123"
	 * "132"
	 * "213"
	 * "231"
	 * "312"
	 * "321"
	 * Given n and k, return the kth permutation sequence.
	 * Note: Given n will be between 1 and 9 inclusive.
	 *
	 * Solution:
	 * From left to right, ith digit has (n-i)! permutations
	 * n = 3, k = 5.
	 * digit = [1,2,3]
	 * factorial = [2, 1, 0 or 1]
	 * 第一位, 5有2个2, 所以取digit[2]. k=1
	 * 第二位, k没有大于1, 所以取digit[0], k=1.
	 * 最后一位, 直接取剩下的数字.
	 */
	static String getKthPermutation(int n, int k) {
		ArrayList<Integer> digit = new ArrayList<>();
		StringBuilder buf = new StringBuilder();

		//put all digits in the acsending order.
		for (int i = 1; i <= n; i++) {
			digit.add(i);
		}

		//compute factorial
		int[] factorial = new int[n];
		factorial[0] = 1;
		for (int i = 1; i < n; i++) {
			factorial[i] = factorial[i - 1] * i;
		}

		//reverse, align to path_sum_output order
		MyUtils.reverse(factorial);
		int i = 0;
		while (i < n) {
			int index = 0;
			//loop through index
			while (k > factorial[i]) {
				k -= factorial[i];
				index++;
			}
			int d = digit.remove(index);
			buf.append(d);
			i++;

		}

		return buf.toString();
	}

	/**
	 * Combinations, use iterative
	 */
	static ArrayList<ArrayList<Integer>> combine(int[] a, int k) {
		if (a == null || k == 0 || k > a.length) {
			return null;
		}

		ArrayList<ArrayList<Integer>> res = new ArrayList(), temp = new ArrayList();
		for (int d : a) {
			int size = temp.size();
			for (int i = 0; i < size; i++) {
				ArrayList<Integer> copy = new ArrayList(temp.get(i));
				copy.add(d);
				if (copy.size() == k) {
					res.add(copy);
				} else {
					temp.add(copy);
				}
			}
			ArrayList<Integer> newList = new ArrayList(Arrays.asList(d));
			if (newList.size() == k) {
				res.add(newList);
			} else {
				temp.add(newList);
			}
		}
		return res;
	}

	/**
	 * return all combinations of three sum
	 */
	static ArrayList<ArrayList<Integer>> threeSum(int[] a, int t) {
		if (a == null || a.length == 0) {
			return null;
		}

		Arrays.sort(a);
		ArrayList<ArrayList<Integer>> res = new ArrayList();
		for (int i = 0; i < a.length; i++) {
			int start = i + 1, end = a.length - 1;
			while (start < end) {
				int sum = a[i] + a[start] + a[end];
				if (sum == t) {
					res.add(new ArrayList(Arrays.asList(a[i], a[start++], a[end--])));
				} else if (sum > t) {
					end--;
				} else {
					start++;
				}
			}
		}
		return res;
	}

	/**
	 * KEY: 输入没有重复
	 * Give an array, Return all subset
	 *
	 * Solution:
	 * Recursive, A's subset = subset(A[1:]) + (A[0] + subset(A[1:])
	 */
	static ArrayList<ArrayList<Integer>> subset_I(ArrayList<Integer> a) {
		if (a.size() == 0) {
			ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
			temp.add(new ArrayList<Integer>());
			return temp;
		}
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();

		int first = a.remove(0);
		ArrayList<ArrayList<Integer>> nextSubset = subset_I(a);
		result.addAll(MyUtils.deepClone((ArrayList)nextSubset));

		// add a[start]
		for (ArrayList<Integer> list : nextSubset) {
			list.add(0, first);
		}
		result.addAll(nextSubset);

		return result;
	}

	/**
	 * KEY: 输入有重复, 输出不能有重复
	 * Subsets with Iterative and Duplicate input.
	 *
	 * Sort first, for removing duplicate and expedite processing large array
	 * Copy all the previous subsets, add i_th element, then add back to result.
	 *
	 */
	static ArrayList<ArrayList<Integer>> subset_II(ArrayList<Integer> a) {
		if (a == null) {
			return null;
		}

		// Processing a sorted array is much faster than processing a random array. And sorting time doesn't hurt.
		Collections.sort(a);

		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<Integer>());
		for (int i = 0; i < a.size(); i++) {
			// add those two lines to remove duplicate
			if (i > 0 && a.get(i).equals(a.get(i - 1))) {
				continue;
			}
			ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
			for (ArrayList<Integer> list : result) {
				//clone and add a[i]
				ArrayList<Integer> clone = (ArrayList) list.clone();
				clone.add(a.get(i));
				temp.add(clone);
			}

			result.addAll(temp);
		}
		return result;
	}

	/**
	 * KEY:输入没有重复, 一个元素只能用一次
	 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
	 * For example,
	 * If n = 4 and k = 2, a solution is:
	 * [2,4],[3,4],[2,3],[1,2],[1,3],[1,4],
	 *
	 * Solution:
	 * For [1, 2, 3, 4] = 1 + combine([2,3,4], k-1), 2 + combine([3,4], k - 1), 3 + combine([4,] k-1), 4 + combine([], k - 1)
	 */
	static ArrayList<ArrayList<Integer>> combination(int n, int k) {
		ArrayList<Integer> remain = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			remain.add(i);
		}
		return combine(remain, k);
	}

	static ArrayList<ArrayList<Integer>> combine(ArrayList<Integer> remain, int k) {
		ArrayList<ArrayList<Integer>> result = new ArrayList();
		if (k == 0) {
			result.add(new ArrayList<Integer>());
			return result;
		}

		while (remain.size() > 0) {
			int v = remain.remove(0);
			ArrayList<Integer> copy = new ArrayList<>(remain);
			ArrayList<ArrayList<Integer>> nextResult = combine(copy, k - 1);
			for (ArrayList<Integer> list : nextResult) {
				list.add(v);
			}
			result.addAll(nextResult);
		}
		return result;
	}

	/**
	 * KEY:输入可能有重复, 一个元素可以取多次. 这个和取Coin拼成一个值是一模一样.
	 *
	 * Combination sum, element can be re-used.
	 *
	 * For example, given candidate set 2,3,6,7 and target 7,
	 * A solution set is:
	 * [7]
	 * [2, 2, 3]
	 *
	 * Solution:
	 * DFS.
	 *
	 * Think of this:
	 * [2,3,6,7]
	 *  |
	 * [2,3,6,7]
	 *  |
	 * [2,3,6,7]
	 *  |
	 *  |_____
	 *  | \ \ \
	 * [2,3,6,7]
	 *
	 * But note, to avoid unneccessary loop.
	 * 0.对于任何复杂度比O(nLog(n))大的, 先sort.
	 * 1.每走一层下去, 都是从上一层的i开始, 不是从头, 避免重复.
	 * 2.由于是sorted, 一旦当前的和大于target, 这个level就退出.
	 */
	static ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int tartget) {
		HashSet<ArrayList<Integer>> result = new HashSet<>();
		if (candidates.length == 0) {
			return new ArrayList<>(result);
		}
		Arrays.sort(candidates);
		ArrayList<Integer> levelElem = new ArrayList<>();
		DFS(result, candidates, tartget, levelElem, 0, 0);
		return new ArrayList<>(result);
	}

	static void DFS(HashSet<ArrayList<Integer>> result, int[] candidates, int tartget, ArrayList<Integer> levelElem, int start, int curSum) {
		for (int i = start; i < candidates.length; i++) {
			levelElem.add(candidates[i]);
			curSum += candidates[i];
			if (curSum >= tartget) {

				if (curSum == tartget && !result.contains(levelElem)) {
					//check level already in result or not, avoid duplicate candidates.
					result.add(new ArrayList<>(levelElem));
				}

				levelElem.remove(levelElem.size() - 1);
				return;
			} else {
				DFS(result, candidates, tartget, levelElem, i, curSum);
			}
			// i element don't work and still less than tartget, remove this elem on levelElem and move on.
			curSum -= candidates[i];
			levelElem.remove(levelElem.size() - 1);
		}
	}


	/**
	 * KEY: 输出有重复, 一个元素只能取一次.
	 *
	 * Combination sum II, element can't be re-used.
	 *
	 *For example, given candidate set 10,1,2,7,6,1,5 and target 8,
	 * A solution set is:
	 * [1, 7]
	 * [1, 2, 5]
	 * [2, 6]
	 * [1, 1, 6]
	 *
	 * Solution:
	 * 跟上面的差不多, 就是每层往下的时候, 是从上一层的i+1开始, 就不会重复使用一个元素了.
	 */
	static ArrayList<ArrayList<Integer>> combinationSum_II(int[] candidates, int tartget) {
		HashSet<ArrayList<Integer>> result = new HashSet<>();
		if (candidates.length == 0) {
			return new ArrayList<>(result);
		}
		Arrays.sort(candidates);
		ArrayList<Integer> levelElem = new ArrayList<>();
		DFS_II(result, candidates, tartget, levelElem, 0, 0);
		return new ArrayList<>(result);
	}

	static void DFS_II(HashSet<ArrayList<Integer>> result, int[] candidates, int tartget, ArrayList<Integer> levelElem, int start, int curSum) {
		for (int i = start; i < candidates.length; i++) {
			levelElem.add(candidates[i]);
			curSum += candidates[i];
			if (curSum >= tartget) {

				if (curSum == tartget && !result.contains(levelElem)) {
					//Still need to check duplicate
					result.add(new ArrayList<>(levelElem));
				}

				levelElem.remove(levelElem.size() - 1);
				return;
			} else {
				DFS_II(result, candidates, tartget, levelElem, i + 1, curSum);
			}
			// i element don't work and still less than tartget, remove this elem on levelElem and move on.
			curSum -= candidates[i];
			levelElem.remove(levelElem.size() - 1);
		}
	}

	/**
	 * Given Pos int k, find all n < k.
	 * st a^pow + b ^pow = n and c^pow + d^pow =n. a, b, c, d less than k.
	 *
	 * Solution:
	 * DFS.
	 * Loop n from k to 0.
	 * For each k, loop i from k to 0, loop j fron i - 1 to 0.
	 * check i ^ pow + j ^ pow == k.
	 */
	static ArrayList<Integer> cubicRootSum(int k, int pow) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int n = k; n >= 0; n--) {
			HashSet<ArrayList<Integer>> set = new HashSet<>();
			ArrayList<Integer> elemList = new ArrayList<>(2);
			elemList.add(0);
			elemList.add(0);
			for (int i = n; i >= 0; i--) {
				elemList.set(0, i);
				if (Math.pow(i, pow) > k) {
					continue;
				}

				for (int j = i - 1; j >= 0; j--) {
					elemList.set(1, j);
					double sum = Math.pow(i, pow) + Math.pow (j, pow);
					if (sum < n) {
						break;
					} else if (sum == n && !set.contains(elemList)) {
						set.add(new ArrayList<>(elemList));
						if (set.size() >= 2) {
							System.out.println(set);
							result.add(n);
						}
						break;
					}
				}
			}
		}

		return result;
	}

	/**
	 * Facebook interview, letter combination.
	 * Iterative
	 */
	static ArrayList<String> phone_letter_combination(String digits) {
		if (digits == null) {
			return null;
		}
		String[] a = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
		ArrayList<StringBuilder> res = new ArrayList();

		for (char digit : digits.toCharArray()) {
			if (res.size() == 0) {
				for (char c : a[digit - '0'].toCharArray()) {
					StringBuilder sb = new StringBuilder();
					sb.append(c);
					res.add(sb);
				}
			} else {
				ArrayList<StringBuilder> copy = new ArrayList(res);
				res.clear();
				for (char c : a[digit - '0'].toCharArray()) {
					for (StringBuilder sb : copy) {
						StringBuilder temp = new StringBuilder(sb);
						temp.append(c);
						res.add(temp);
					}
				}
			}
		}

		return toStringList(res);
	}

	static ArrayList<String> toStringList(ArrayList<StringBuilder> list) {
		ArrayList<String> res = new ArrayList();
		for (StringBuilder sb : list) {
			res.add(sb.toString());
		}
		return res;
	}

	/**
	 * Three array, pick one num from each array.
	 * Three num should add up to target.
	 */
	static ArrayList<ArrayList<Integer>> three_array_sum(int[] a, int[] b, int[] c, int target) {
		if (a == null || b == null || c == null) {
			return null;
		}
		ArrayList<ArrayList<Integer>> res = new ArrayList();
		HashSet<Integer> set = new HashSet();
		for (int i : c) {
			set.add(i);
		}
		for (int i : a) {
			for (int j : b) {
				int remain = target - i - j;
				if (set.contains(remain)) {
					res.add(new ArrayList(Arrays.asList(i, j, remain)));
				}
			}
		}
		return res;
	}
}