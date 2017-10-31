import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BitManipulation {
	public static void main(String[] args) {
//		int a = 0b10110, b = 0b1000000000;
//		int number = updateBitFromIToJ(b, a, 2, 6);
//		System.out.println(Integer.toBinaryString(number));
//		intToBinaryString(b);
//		System.out.println(doubleToBinaryString(0.75));
//		System.out.println(getBinaryLength(1024));

//		System.out.println(intToBinaryString(nextSmallWithSameOneBit(a)));
//		System.out.println(addBinary("111", "000"));
//		int[] a = {1,1,2,2,33,33,21};
//		System.out.println(find_num_appear_odd_times(a));

//		int[] a = {0,1, 2, 3, 4, 5,6,7,8,9,10};
//		System.out.println(findMissingNumber(a));

//		System.out.println(plusOne(0));

		System.out.println(getBigger(-1,-2));
	}

	static int getBit(int number, int i) {
		return (number & (1 << i)) == 1 ? 1 : 0;
	}

	static int setBit(int number, int i) {
		return number | (1 << i);
	}

	static int clearBit(int number, int i) {
		int mask = ~(1 << i);
		return mask & number;
	}

	static int clearBitsFromIToHigh(int number, int i) {
		// inclusive
		int mask = (1 << i) - 1;
		return mask & number;
	}

	static int clearBitsFromIToLow(int number, int i) {
		int mask = ~((1 << ++i) - 1);
		return number & mask;
	}

	static int updateBit(int number, int i, int bit) {
		int mask = ~(bit << i);
		return number & mask | (bit << i);
	}

	static int updateBitFromIToJ(int number, int updateNum, int i, int j) {
		int allOnes = ~0;
		int left = allOnes << (j + 1);
		int right = 1 << (i + 2) - 1;
		int mask = left | right;
		int cleared = number & mask;
		return updateNum << i | cleared;
	}

	static String intToBinaryString(int num) {
		int mask = 1;
		StringBuilder bits = new StringBuilder();
		while (num != 0) {
			int bit = num & mask;
			num = num >> 1;
			bits.insert(0, bit);
		}

		return bits.toString();
	}

	static String doubleToBinaryString(double num) {
		if (num <= 0 || num >= 1) {
			return "ERROR";
		}

		StringBuilder bits = new StringBuilder(".");
		double unit = 0.5;
		while (num != 0) {
			double temp = num - unit;
			if (temp >= 0) {
				bits.append("1");
				num = temp;
			} else {
				bits.append("0");
			}
			unit /= 2;
		}

		return bits.toString();
	}

	static int getBinaryLength(int a) {
		int i = 1;
		while (a != 1) {
			a = a >> 1;
			i++;
		}
		return i;
	}

	static boolean isPowerOf2(int num) {
		return (num & (num - 1)) == 0;
	}

	static int differentBitBetween(int a, int b) {
		// Use XOR, aka ^
		int count = 0;
		for (int c = a ^ b; c != 0; c = c & (c - 1)) {
			count++;
		}
		return count;
	}

	static int swapOddEvenBits(int x) {
		return ((x & 0xaaaaaaaa) >> 1) | ((x & 0x55555555) << 1);
	}

	static int nextBigWithSameOneBit(int num) {
		int i = 0, x = num;

		//find 1
		while (!((x & 1) == 1)) {
			x >>= 1;
			i++;
		}

		//find 0
		while (!((x & 1) == 0)) {
			x >>= 1;
			i ++;
		}
		num = setBit(num, i);
		num = clearBit(num, i - 1);
		return num;
	}

	static int nextSmallWithSameOneBit(int num) {
		int i = 0, x = num;

		//find 0
		while (!((x & 1) == 0)) {
			x >>= 1;
			i ++;
		}

		//find 1
		while (!((x & 1) == 1)) {
			x >>= 1;
			i++;
		}

		num = clearBit(num, i);
		num = setBit(num, i - 1);
		return num;
	}

	/**
	 * Check this num's bit representation is palindrom or not.
	 * For example, 9 is 1001.
	 */
	static boolean bitPalindrome(int x) {
		int rev = 0, copy = x;
		while (copy > 0) {
			rev = (rev << 1) | (copy & 1); //rev left shift, set the last bit to the cpoy's last bit.
			copy = copy >> 1;
		}
		return rev == x;
	}

	/**
	 * Add two binary string, return a binary string.
	 * Need to exam the input string first, use regex
	 */
	static String addBinary(String a, String b) {
		if (!isValidBinary(a) || !isValidBinary(b)) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		int i = 0, carrier = 0;
		while (i < a.length() || i < b.length()) {
			int aIndex = a.length() - 1 - i, bIndex = b.length() - 1 - i;
			int x = aIndex >= 0 ? a.charAt(aIndex) - '0' : 0;
			int y = bIndex >= 0 ? b.charAt(bIndex) - '0' : 0;
			int sum = x + y + carrier;
			carrier = sum / 2;
			sum %= 2;
			sb.append(sum);
			i++;
		}
		if (carrier > 0) {
			sb.append(carrier);
		}
		return sb.reverse().toString();
	}

	private static boolean isValidBinary(String s) {
		if (s == null) {
			return false;
		}
		Pattern p = Pattern.compile("^[01]+$");
		Matcher m = p.matcher(s);
		return m.matches();
	}

	/**
	 * Given an array of num. Only one appears odd times. return that num
	 * Use XOR.
	 */
	static int find_num_appear_odd_times(int[] a) {
		if (a == null || a.length == 0) {
			throw new IllegalArgumentException();
		}

		int num = 0;
		for (int i : a) {
			num ^= i;
		}
		return num;
	}

	/**
	 * Find all missing number from 0 to 16(2^4).
	 * The actual question should be 0 to (2 ^ 32 - 1). input should a stream or iterator.
	 *
	 * Use byte array. each num take one bit.
	 */
	static ArrayList<Integer> findMissingNumber(int[] a) throws IllegalArgumentException {
		if (a == null) {
			throw new IllegalArgumentException();
		}

		byte[] bytes = new byte[(1 << 1)];
		for (int num : a) { //actual should be a iterator
			int arrayIndex = num / 8;
			int bitIndex = num % 8;
			bytes[arrayIndex] = setBit(bytes[arrayIndex], bitIndex);
		}

		ArrayList<Integer> res = new ArrayList();
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] != 255) {
				ArrayList<Integer> indexArray = getZeroBit(bytes[i]);
				for (int bitIndex : indexArray) {
					res.add(i * 8 + bitIndex);
				}
			}
		}
		return res;
	}

	static byte setBit(byte b, int i) {
		return (byte)(b | (1 << i));
	}

	static ArrayList<Integer> getZeroBit(byte b) {
		ArrayList<Integer> res = new ArrayList();

		for (int i = 0; i < 8; i++) {
			if ((b & 1) == 0) {
				res.add(i);
			}
			b = (byte)(b >> 1);
		}

		return res;
	}

	/**
	 * Plus one use bit
	 */
	static int plusOne(int n) {
		int copy = n, i = 0;
		while (copy >= 0) {
			if ((copy & 1) == 0) {
				n = setBit(n, i);
				break;
			} else {
				n = clearBit(n, i);
			}
			i++;
			copy = copy >> 1;
		}
		return n;
	}

	/**
	 * Use bit to do the add
	 */
	static int add(int a, int b) {
		if (b == 0) {
			return a;
		}
		int sum = a ^ b; //without carrier
		int carrier = a & b;
		return add(sum, carrier);
	}

	/**
	 * return the bigger num without using comparator.
	 * To avoid overflow, we need to avoid doing (a-b) when a and b is different sign.
	 * SO:
	 * 1)x = sign(a) when a and b is different sign.
	 * 2)x = sign(a - b) when a and b is same sign.(no overflow here)
	 * 3)y = flip(x)
	 * return a * x + b * y
	 */
	static int getBigger(int a, int b) {
		//This will fail when a - b overflow.
		//int x = getSign(a - b);
		//int y = flip(x);
		//return a * x + b * y;

		int isDiff = getSign(a) ^ getSign(b); //isDiff == 1 mean a and b is diff
		int x = isDiff * getSign(a) + flip(isDiff) * getSign(a - b); //here even a - b overflow, it will be * 0, so no effect.
		int y = flip(x);
		return a * x + b * y;
	}

	/**
	 * if a >= 0 return 1, else return 0.
	 */
	static int getSign(int a) {
		return flip((a >> 31) & 1);
	}

	/**
	 * input is only 1 or 0.
	 */
	static int flip(int bit) {
		return 1 ^ bit;
	}
}
