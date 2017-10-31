public class DynamicProgramming {
	public static void main(String[] args) {

	}

	/**
	 * Max consecutive production, bot hpos and neg in array.
	 * Example: {-1, -10, 3, -5}.  Output: 150
	 *
	 * Dynamic Programming:
	 * max[i] is max production that end with i.
	 * min[i] is min production that end with i.
	 */
	static int maxConsequentProduction(int[] a) {
		int[] max = new int[a.length], min = new int[a.length];
		max[0] = min[0] = a[0];
		int value = a[0];
		for (int i = 1; i < a.length; i++) {
			max[i] = Math.max(a[i], Math.max(max[i - 1] * a[i], min[i - 1] * a[i]));
			min[i] = Math.max(a[i], Math.min(max[i - 1] * a[i], min[i - 1] * a[i]));
			value = Math.max(max[i], value);
		}
		return value;
	}


}
