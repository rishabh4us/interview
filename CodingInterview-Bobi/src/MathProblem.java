import java.util.*;

public class MathProblem {
	public static void main(String[] args) throws Exception {
//		System.out.println(Arrays.toString(getPrimeNumberList(100)));

//		System.out.println(divide(1, 11));
//		Point[] points = new Point[]{new Point(-1, 0), new Point(1, 0), new Point(0, 1)};
//		System.out.println(isPointInPolyon(points, new Point(3, 0.5)));
//		System.out.println(max_subarray_product(new int[]{1,2,3,-1}));

//		Point[] points = new Point[]{new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1,1), new Point(-1, 0), new Point(-1, 1)};
//		System.out.println(how_many_square(points));

//		System.out.println(twitter_shortest_derivation(20));
//		int[] a = {3, -5, 0, -1, -3};
//		System.out.println(twitter_how_many_can_not_be_visited(a));

//		int[] a = {2, 1, 5, -6, 9};
//		System.out.println(twitter_how_many_even_pair(a));

//		int n = 170;
//		System.out.println(intToHex(n));
//		System.out.println(Integer.toHexString(n));

//		System.out.println(intToBinary(7));

//		System.out.println(parseInt("-11100000001"));

//		int[] a = {1,2,3,4,5,6,7,-8};
//		System.out.println(max_k_consecutive_sum(a, 1));

//		int[][] m = {{2,7,5},{3,1,1},{2,1,-7},{0,2,1},{1,6,8}};
//		System.out.println(twitter_matrix_part_sum_equal(m).size());

//		int[][] m = {{2,7,1},{3,1,1},{-2,-1,-7}};
//		System.out.println(maxSubMatrixSum(m));

//		Point[] points = new Point[]{new Point(0, 0), new Point(1, 1), new Point(3, 1), new Point(1,4), new Point(-1, -5), new Point(-1, 1)};
//		ArrayList<Point> list = new ArrayList(Arrays.asList(points));
//		for (Point p : k_cloest_to_origin(list, 6)) {
//			System.out.println(p.distance);
//		}

//		System.out.println(sqrt(10000));

//		System.out.println(jump_river(new int[]{1,1,1,1}));
//		System.out.println(jump_river(new int[]{1,1,1,1,1,1,1,1}));

//		System.out.println(MultiplyString("11", "0"));

		System.out.println(superPrime(9));
	}

	static boolean isPrimeNumber(int n) {
		if (n < 2 || (n & 1) == 0) {
			return false;
		}
		int sqrt = (int)Math.sqrt(n);
		for (int i = 3; i <= sqrt; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	static int[] getPrimeNumberList(int max) {
		if (max < 2) {
			return new int[0];
		}

		boolean[] a = new boolean[max];
		Arrays.fill(a, true);
		a[0] = false;
		a[1] = false;
		a[2] = true;
		int lastPrime = 2, primeCount = 0;
		while (lastPrime != max) {
			for(int i = lastPrime + 1; i < max; i++) {
				if (a[i]) {
					a[i] = (i % lastPrime != 0);
				}
			}
			int i = lastPrime + 1;
			while (i < max && !a[i]) {
				i++;
			}
			lastPrime = i;
			primeCount++;
		}

		int[] primeList = new int[primeCount];
		for (int i = 0, j = 0; i < max; i++) {
			if (a[i]) {
				primeList[j++] = i;
			}
		}
		return primeList;
	}

	/**
	 * Twitter interview question,
	 * Given a n, return all the super prime that has length n
	 * A super prime = all the prefix is prime.
	 * E.g.: 7331 = 7, 73, 733, 7331
	 *
	 * Algo:
	 * 1.Must start with 2, 3, 5, 7
	 * 2.Must not be even.
	 * 3.Second to end digit must be odd.
	 */
	static ArrayList<Integer> superPrime(int n) {
		ArrayList<Integer> res = new ArrayList();
		if (n <= 0) {
			return res;
		}

		int[] first = {2, 3, 5, 7};
		StringBuilder sb = new StringBuilder();
		for (int i : first) {
			sb.append(i);
			n--;
			if (n == 0) {
				res.add(Integer.valueOf(sb.toString()));
			} else {
				superPrimeHelper(res, sb, n);
			}
			n++;
			sb.deleteCharAt(0);
		}
		return res;
	}

	private static void superPrimeHelper(ArrayList<Integer> res, StringBuilder sb, int remain) {
		if (remain == 0) {
			res.add(Integer.valueOf(sb.toString()));
			return;
		}

		for (int i = 1; i < 10; i+=2) {
			sb.append(i);
			long num = Long.valueOf(sb.toString());
			if (num > Integer.MAX_VALUE) {
				return; // to avoid overflow
			} else {
				if (isPrimeNumber((int)num)) {
					superPrimeHelper(res, sb, remain - 1);
				}
			}
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	/**
	 * count the trailing zero of n!
	 *
	 * Solution:
	 * 1.count the factors of 5 for all numbers. for i in [1, n], count i's factors_of_5
	 * 2.count how many 5 + how many 25 + how many 125... until n less than 2^k.
	 * Because 25 has two 5, one of them is taken by "how many 5", so "how many 25" will take another one.
	 */
	static int trailingZeroOfFactorial(int n) {
		int count = 0;
		if (n <= 0) {
			return -1;
		}
		for (int i=5; n/i >= 1; i*=5) {
			count += n / i;
		}
		return count;
	}

	static String divide(int a, int b) {
		if (a == 0) {
			return "0";
		}
		if (b == 0) {
			throw new ArithmeticException();
		}

		StringBuilder result = divide_helper(a, b, new StringBuilder());
		return divide_insert_dot(a, b, result);
	}

	private static StringBuilder divide_helper(int a, int b, StringBuilder sb) {
		if (a == 0 || sb.length() > 10) {
			return sb;
		}

		if (a < b) {
			int temp = a * 10;
			StringBuilder temp_str = new StringBuilder();
			while (temp < b) {
				temp *= 10;
				temp_str.append('0');
			}
			int result = temp / b;
			int remain = temp % b;
			if (remain == a) {
				temp_str.insert(0, "(").append(String.valueOf(result)).append(")");
				sb.append(temp_str);
				return sb;
			} else {
				sb.append(temp_str);
			}
			divide_helper(remain, b, sb);
		} else {
			int result = a / b;
			int remain = a % b;
			sb.append(result);
			divide_helper(remain, b, sb);
		}
		return sb;
	}

	//insert dot
	private static String divide_insert_dot(int a, int b, StringBuilder sb) {
		int d = a / b;
		int index = 0;
		while (d > 0) {
			d /= 10;
			index++;
		}

		if (index == 0) {
			sb.insert(0, "0.");
		} else if (index != sb.length()) {
			sb.insert(index, '.');
		}
		return sb.toString();
	}


	/**
	 * 看p是否在多边形的内部.
	 * 解法, p做一条#单向#水平线, 看和多边形的交点个数.
	 * 奇数则在内部.
	 */
	static boolean isPointInPolyon(Point[] list, Point p) {
		int cross = 0;
		for (int i=0; i<list.length; i++) {
			Point p1 = list[i], p2 = list[(i + 1) % list.length];

			//平行
			if (p1.y == p2.y) {
				continue;
			}
			//在y的范围内, 有交点
			if (p.y > Math.min(p1.y, p2.y) && p.y < Math.max(p1.y, p2.y)) {
				//计算x, 自己画下三角形, 用三角形和平行线的比例来算
				double x = (p1.x - p2.x) * (p2.y - p.y) / (p2.y - p1.y) + p2.x;
				if (x > p.x) {
					cross++;
				}
			}
		}
		return cross%2 != 0;
	}


	static int max_subarray_sum(int[] a) {
		if (a == null || a.length == 0) {
			throw new IllegalArgumentException();
		}
		int max_end_here = a[0], max_so_far = a[0];
		for (int i = 1; i < a.length; i++) {
			max_end_here = Math.max(a[i], max_end_here + a[i]);
			max_so_far = Math.max(max_so_far, max_end_here);
		}
		return max_so_far;
	}

	static int max_subarray_product(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int max_at = a[0], min_at = a[0];
		int max = max_at;
		for (int i=1; i<a.length; i++) {
			int prevMax = max_at, prevMin = min_at;
			max_at = Math.max(Math.max(a[i], a[i] * prevMin), a[i] * prevMax);
			min_at = Math.min(Math.min(a[i], a[i] * prevMin), a[i] * prevMax);
			max = Math.max(max, max_at);
		}
		return max;
	}

	/**
	 * Give a list of points, assume no duplicate
	 * Check how many square can be constructed by those points
	 */
	static int how_many_square(Point[] list) {
		if (list == null || list.length < 4) {
			return 0;
		}
		ArrayList<Point[]> pairs = getAllPair(list);
		int count = 0;
		for (int i = 0; i<pairs.size(); i++) {
			for (int j=i+1; j<pairs.size(); j++) {
				if (isSquare(pairs.get(i), pairs.get(j))) {
					count++;
				}
			}
		}
		return count/3;
	}

	private static ArrayList<Point[]> getAllPair(Point[] list) {
		HashSet<Point[]> res = new HashSet();
		for (int i = 0; i< list.length; i++) {
			for (int j=i+1; j < list.length; j++) {
				res.add(new Point[]{list[i], list[j]});
			}
		}
		return new ArrayList(res);
	}

	private static boolean isSquare(Point[] a, Point[] b) {
		double[] d = new double[6];
		d[0] = getDistance(a[0], a[1]);
		d[1] = getDistance(a[0], b[0]);
		d[2] = getDistance(a[0], b[1]);
		d[3] = getDistance(a[1], b[0]);
		d[4] = getDistance(a[1], b[1]);
		d[5] = getDistance(b[0], b[1]);
		Arrays.sort(d);
		if (d[0] == d[1] && d[1] == d[2] && d[2] == d[3] && d[4] == d[5] && Math.abs((d[4] * d[4]) - (d[0] * d[0] * 2)) < 0.1) {
			return true;
		} else {
			return false;
		}
	}

	private static double getDistance(Point a, Point b) {
		return Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
	}

	/**
	 * Twitter online-test.
	 * shortest derivation to get n. n >= 1.
	 * a[0] = 1, a[i] = a[i-1] + 1 or a[i] = a[i-1] * 2
	 * Given num n, return the min derivation steps.
	 */
	static int twitter_shortest_derivation(int n) {
		if (n == 1) {
			return 1;
		} else {
			int x = (int) (Math.log(n) / Math.log(2)); //there's no direct log2(x), but log10(x)/log10(2) = log2(x)
			return n - (int)Math.pow(2, x) + x + 1;
		}
	}

	//same as prev one, use bit
	static int twitter_shortestSequence(int n) {
		int res = 0;
		while (n > 0) {
			if ((n & 1) == 1) { //can be divide by 2
				--n;
			} else {
				n = n >> 1;
			}
			res++;
		}
		return res;
	}

	/**
	 * Twitter online-test.
	 * Give a array, from a[0], if a[0] = 1, then next = 0 + 1, a[1] can be visited, go to a[1]. if a[1] = 3, then next = 1 + 3, a[4] can be visited.
	 * return how many index can NOT be visited.
	 */
	static int twitter_how_many_can_not_be_visited(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int i = 0;
		boolean[] visited = new boolean[a.length];
		while (i >= 0 && i < a.length && !visited[i]) {
			visited[i] = true;
			i = a[i] + i;
		}
		int res = 0;
		for (boolean v : visited) {
			if (!v) {
				res++;
			}
		}
		return res;
	}

	/**
	 * Twitter online test.
	 * how many pairs add up is even and first index is less than second index.
	 * Time: O(n)
	 */
	static int twitter_how_many_even_pair(int[] a) {
		if (a == null || a.length <= 1) {
			return 0;
		}
		int odd = 0, res = 0;
		for (int i = 0; i < a.length; i++) {
			//odd
			if ((a[i] & 1) == 1) {
				res += odd;
				odd++;
			} else {
				int even = (i - odd);
				res += even;
			}
		}
		return res;
	}

	/**
	 * Twitter online test.
	 * Given a matrix, find all the point that:
	 * 1.sum of all row above this point == sum of all rows below
	 * 2.Sum of all cols left equal to sum of all cols right
	 */
	static ArrayList<int[]> twitter_matrix_part_sum_equal(int[][] m) {
		ArrayList<int[]> res = new ArrayList();
		if (m == null || m.length == 0) {
			return res;
		}
		int rlen = m.length, clen = m[0].length;
		int[] rsum = new int[rlen], csum = new int[clen];
		for (int row = 0; row < rlen; row++) {
			rsum[row] = MyUtils.sum(m[row]);
		}
		for (int col = 0; col < clen; col ++) {
			int sum = 0;
			for (int row = 0; row < rlen; row++) {
				sum += m[row][col];
			}
			csum[col] = sum;
		}

		int up = 0, down = MyUtils.sum(rsum) - rsum[0];
		for (int row = 0; row < rlen; row++) {
			int left = 0, right = MyUtils.sum(csum) - csum[0];
			for (int col = 0; col < clen; col++) {
				if (up == down && left == right) {
					res.add(new int[]{row, col});
				}
				left += csum[col];
				right = col == (clen - 1) ? right : right - csum[col + 1];
			}
			up += rsum[row];
			down = row == (rlen - 1) ? down : down - rsum[row + 1];
		}
		return res;
	}

	/**
	 * Int num to hex(16) string.
	 * 15 -> F, 17 -> 11.
	 * Use Integer.toHexString(n) to prove.
	 */
	static String intToHex(int n) {
		boolean isPos = n >= 0;
		n = Math.abs(n);
		StringBuilder sb = new StringBuilder();
		char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		while (n > 0) {
			if (n < 16) {
				sb.append(digits[n]);
				n -= n;
			} else {
				int count = n / 16;
				sb.append(digits[count]);
				n %= 16;
				if (n == 0) {
					sb.append(0);
				}
			}
		}

		if (!isPos) {
			sb.insert(0, '-');
		}
		return sb.toString();
	}

	/**
	 * int to Binary string
	 */
	static String intToBinary(int n) throws Exception {
		if (n < 0) {
			throw new Exception();
		}
		StringBuilder sb = new StringBuilder();
		while (n > 0) {
			if ((n & 1) == 1) {
				sb.append(1);
			} else {
				sb.append(0);
			}
			n = n >> 1;
		}
		return sb.reverse().toString();
	}

	static int parseInt(String s) throws NumberFormatException {
		if (s == null || s.length() == 0) {
			throw new NumberFormatException();
		}
		long res = 0;
		int len = s.length(), i = 0;
		boolean isNeg = false;
		//check first letter
		char first = s.charAt(i);
		if (first < '0') {
			if (first == '-') {
				isNeg = true;
			} else if (first != '+') {
				throw new NumberFormatException();
			}
			i++;
			if (len == 1) {
				throw new NumberFormatException(); //only - or +
			}
		}
		//skip leading 0
		while (i < len) {
			int d = charToDigit(s.charAt(i));
			if (d == 0) {
				i++;
			} else if (d == -1) {
				throw new NumberFormatException();
			} else {
				break;
			}
		}

		while(i < len) {
			int d = charToDigit(s.charAt(i++));
			if (d == -1) {
				throw new NumberFormatException();
			} else {
				res = res * 10 + d;
				if (res > Integer.MAX_VALUE) {
					throw new NumberFormatException();
				}
			}
		}

		return isNeg ? -(int)res : (int)res;
	}

	private static int charToDigit(char c) {
		if (c >= '0' && c <= '9') {
			return c - '0';
		} else {
			return -1;
		}
	}

	/**
	 * Max k consecutive sum
	 */
	static int max_k_consecutive_sum(int[] a, int k) throws IllegalArgumentException {
		if (a == null || k <= 0 || a.length < k) {
			throw new IllegalArgumentException();
		}

		int[] sum = new int[a.length];
		sum[0] = a[0];
		for (int i = 1; i < k; i++) {
			sum[i] = sum[i-1] + a[i];
		}

		int max = sum[k-1];

		for (int i = k; i < a.length; i++) {
			sum[i] = sum[i - 1] - a[i - k] + a[i];
			max = Math.max(max, sum[i]);
		}
		return max;
	}

	/**
	 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
	 *
	 * Solutino:
	 * For each point, loop other point, set up a hashTable, key is slope, value is count.
	 * If two point has same slope for outer looping point, they are on the same line.
	 */
	static int maxPointOnLine(List<Point> points) {
		if (points == null || points.size() == 0) {
			return 0;
		}
		int max = 0;
		for (Point a : points) {
			HashMap<Double, Integer> slopeTable = new HashMap<>();

			int duplicate = 0;
			for (Point b : points) {
				if (b != a) {
					if (a.x  == b.x && a.y == b.y) {
						duplicate++;
						continue;
					}

					double slope = a.x - b.x == 0 ? Float.MAX_VALUE :  (a.y - b.y) / (a.x - b.x);
					int count = slopeTable.containsKey(slope) ? slopeTable.get(slope) + 1 : 0;
					slopeTable.put(slope, count);
				}
			}

			for (Integer count : slopeTable.values()) {
				max = (count + duplicate) > max ? count + duplicate : max;
			}

		}

		return max;
	}

	/**
	 * return the max submatrix sum
	 */
	static int maxSubMatrixSum(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return 0;
		}
		int rlen = matrix.length, clen = matrix[0].length;
		int[] rowSum = new int[rlen];
		System.arraycopy(matrix[0], 0, rowSum, 0, clen);
		int max = max_subarray_sum(rowSum);
		for (int row = 1; row < rlen; row++) {
			for (int col = 0; col < clen; col++) {
				rowSum[col] += matrix[row][col];
			}
			int max_end_at = max_subarray_sum(rowSum), max_this_row = max_subarray_sum(matrix[row]);
			if (max_end_at > max_this_row) {
				max = Math.max(max, max_end_at);
			} else {
				max = Math.max(max, max_this_row);
				rowSum = matrix[row];
			}
		}
		return max;
	}

	/**
	 * Facebook interview Question
	 * K node cloest to origin.
	 * Top k propblem
	 */
	static List<Point> k_cloest_to_origin(ArrayList<Point> list, int k) {
		if (list == null || list.size() < k) {
			return null;
		}
		int i = partition(list, 0, list.size() - 1);
		int start = 0, end = list.size() - 1;
		while (i != k - 1) {
			if (i > k - 1) {
				end = i - 1;
			} else {
				start = i + 1;
			}
			i = partition(list, start, end);
		}
		return list.subList(0, k);
	}

	static private int partition(ArrayList<Point> list, int start, int end) {
		if (start == end) {
			return start;
		}
		int write = 0, pivot = (end - start) / 2 + start;
		Point v = list.get(pivot);
		MyUtils.swap(list, pivot, list.size() - 1);
		for (int i = 0; i < list.size() - 2; i++) {
			if (list.get(i).distance <= v.distance) {
				MyUtils.swap(list, write, i);
				write++;
			}
		}
		MyUtils.swap(list, write, list.size() - 1);
		return write;
	}

	static int sqrt(int x) {
		long start = -1, end = x + 1;
		while (end - start > 1) {
			long mid = (end - start) / 2 + start;
			long v = mid * mid;
			if (v == x) {
				return (int)mid;
			} else if (v > x) {
				end = mid;
			} else {
				start = mid;
			}
		}
		return (int)start;
	}

	/**
	 * Facebook
	 * 跳河问题。给一个0/1数组R代表一条河，0代表水，1代表石头。起始位置R[0]等于1，
	 初速度为1. 每一步可以选择以当前速度移动，或者当前速度加1再移动。只能停留在石
	 头上。问最少几步可以跳完整条河流。

	 给定数组为R=[1,1,1,0,1,1,0,0]，最少3步能过河：
	 第一步先提速到2，再跳到R[2]；
	 第二步先提速到3，再跳到R[5]；
	 第三步保持速度3，跳出数组范围，成功过河。

	 Algo:
	 Recursive + mem_cache = DP
	 */
	static int jump_river(int[] path) {
		if (path == null || path.length == 0) {
			return 0;
		}

		HashMap<AbstractMap.SimpleEntry<Integer, Integer>, Long> cache = new HashMap();
		long step = getStep(1, 0, path, cache);
		return step >= Integer.MAX_VALUE ? 0 : (int)step;
	}

	private static long getStep(int speed, int pos, int[] path, HashMap<AbstractMap.SimpleEntry<Integer, Integer>, Long> cache) {
		AbstractMap.SimpleEntry<Integer, Integer> pair = new AbstractMap.SimpleEntry(speed, pos);
		if (cache.containsKey(pair)) {
			return cache.get(pair);
		}

		long step = 0;
		if (pos >= path.length) {
			step = 0;
		} else if (path[pos] == 0) {
			step = Integer.MAX_VALUE;
		} else {
			step = 1 + Math.min(getStep(speed, pos + speed, path, cache), getStep(speed + 1, pos + speed + 1, path, cache));
			cache.put(pair, step);
		}
		return step;
	}

	/**
	 * Multiply two string
	 */
	static String MultiplyString(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return null;
		}
		int[] a = toReverseIntArray(s1), b = toReverseIntArray(s2);
		int[] prod = new int[s1.length() + s2.length()];
		int carrier = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				prod[i + j] += (a[i] * b[j] + carrier);
				carrier = prod[i + j] / 10;
				prod[i + j] %= 10;
			}
			if (carrier > 0) {
				prod[i + b.length] += carrier;
				carrier = 0;
			}
		}
		return reverseIntArrayToString(prod);
	}

	static private int[] toReverseIntArray(String s) {
		int[] a = new int[s.length()];
		for (int i = s.length() - 1; i >= 0; i--) {
			a[s.length() - 1 - i] = s.charAt(i) - '0';
		}
		return a;
	}

	static private String reverseIntArrayToString(int[] a) {
		StringBuilder sb = new StringBuilder();
		for (int i = a.length - 1; i >= 0; i--) {
			sb.append(a[i]);
		}
		//trim leading 0
		int i = 0;
		while (i < sb.length() && sb.charAt(i) == '0') {
			i++;
		}
		return i == sb.length() ? "0" : sb.substring(i);
	}
}


class Point {
	double x, y, distance;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
		this.distance = Math.sqrt(x * x + y * y);
	}
}