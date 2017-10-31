import java.util.*;

public class StringManipulation {

	public static void main(String[] args) throws Exception {
//		System.out.println(reverse("123456"));
//		System.out.println(reverseWord("www.google.com", '.'));

//		System.out.println(simplifyPath("/../.."));
//		System.out.println(removeDupChar("I am a boy"));

//		System.out.println(Arrays.toString(split("abcd", "d")));

//		String tweet = "I am OK";
//		Set<HyperText> set = new HashSet();
//		set.add(new HyperText(2,3, "AM"));
//		set.add(new HyperText(5,6, "0k"));
//		System.out.println(tweetToHyperText(tweet, set));

//		String[] a = {"abc", "xyz", "cba", "zxy", "bac", "yzx", "bca", "xxx", "zzz"};
//		System.out.println(anagramGroups(a));
		System.out.println(anagramGroups("This isth haha HAHA boy girl"));

//		System.out.println(regex_match("aab", "c*a*b"));

//		System.out.println(strStr("0", "3"));
	}

	static String reverse(String str) {
		if (str == null) {
			return null;
		}

		char[] array = str.toCharArray();
		for (int i = 0, j = array.length - 1; i < j; i++, j--) {
			char temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		return new String(array);
	}

	/**
	 * Reverse word in string.
	 * In-place reverse.
	 *
	 * Solution: reverse each word, then reverse whole sentence.
	 */
	static String reverseWord(String s, char separator) {
		if (s == null || s.length() <= 1) {
			return s;
		}
		char[] a = s.toCharArray();
		int start = 0, end = 0;
		while (end <= a.length) {
			if (end == a.length || s.charAt(end) == separator) {
				reverse(a, start, end-1);
				end++;
				start = end;
			} else {
				end++;
			}
		}
		reverse(a, 0, a.length-1);
		return new String(a);
	}

	//start and end is inclusive
	static void reverse(char[] a, int start, int end) {
		while (start < end) {
			char temp = a[start];
			a[start] = a[end];
			a[end] = temp;
			start++;
			end--;
		}
	}

	//i, j is inclusive
	static String LCString(String s1, String s2) {
		int l1 = s1.length(), l2 = s2.length();
		int[][] opt = new int[l1][l2];

		for (int i = 0; i < l1; i++) {
			opt[0][i] = s1.charAt(i) == s2.charAt(0) ? 1 : 0;
		}

		for (int i = 0; i < l2; i++) {
			opt[i][0] = s2.charAt(i) == s1.charAt(0) ? 1 : 0;
		}

		int max = 0;
		int s1_start = 0;
		for (int i = 1; i < l1; i++) {
			for (int j = 1; j < l2; j++) {
				opt[j][i] = s1.charAt(i) == s2.charAt(j) ? opt[j-1][i-1] + 1 : 0;

				if (opt[j][i] > max) {
					max = opt[j][i];
					s1_start = i - max + 1;
				}
			}
		}
		return s1.substring(s1_start, s1_start + max);
	}

	static long stringToLong(String s) {
		if (s == null || s.length() == 0) {
			throw new NullPointerException();
		}

		boolean isPos = s.charAt(0) != '-';
		if (!isPos) {
			s = s.substring(1, s.length());
		}

		long n = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			n *= 10;
			n += c - '0';
		}
		return isPos ? n : -n;
	}

	/**
	 * Simplify Linux Path.
	 * val/./test/.. should be simplified as val
	 * Algo:
	 * Use Stack, when input is . or .., do pop or push accordingly.
	 */
	static String simplifyPath(String path) {
		if (path == null || path.length() == 0) {
			return path;
		}
		String[] a = path.split("/");
		LinkedList<String> stack = new LinkedList();
		for (String x : a) {
			if (x.equals(".")) {
				continue;
			} else if (x.equals("..")) {
				if (stack.size() > 0) {
					if (stack.size() == 1 && stack.peek().equals("")) {
						continue;   //root, no more ..
					} else {
						stack.pop();
					}
				} else {
					stack.push(x);
				}
			} else {
				stack.push(x);
			}
		}
		if (stack.size() == 0 && !a[0].equals("")) {
			stack.push(".");
		}
		StringBuilder sb = new StringBuilder();
		for (String x : stack) {
			sb.append(x).append("/");
		}
		return sb.toString();
	}

	/**
	 * Easy, just remove the dup.
	 * I am a boy => I amboy.
	 */
	static String removeDupChar(String s) {
		if (s == null || s.length() <= 1) {
			return s;
		}
		StringBuilder sb = new StringBuilder();
		int[] set = new int[256];
		for (char c : s.toCharArray()) {
			if (set[c] == 0) {
				sb.append(c);
				set[c]++;
			}
		}
		return sb.toString();
	}

	/**
	 * Implement String.split()
	 */
	static String[] split(String s, String separator) throws IllegalArgumentException {
		ArrayList<String> list = new ArrayList();
		if (s == null || separator == null) {
			throw new IllegalArgumentException();
		}
		int len = s.length(), slen = separator.length();
		if (len == 0 || slen == 0 || slen > len) {
			return new String[]{s};
		}

		int i = 0, start = 0;
		while (i <= (len - slen)) {
			String next = s.substring(i, i + slen);
			if (next.equals(separator)) {
				list.add(s.substring(start, i));
				start = i + slen;
				i += slen;
			} else {
				i++;
			}
		}
		if (start < len) {
			list.add(s.substring(start, len));
		}
		return list.toArray(new String[list.size()]);
	}


	static String join(List<String> list, String separator) throws IllegalArgumentException{
		if (list == null || separator == null) {
			throw new IllegalArgumentException();
		}
		StringBuilder sb = new StringBuilder();
		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
			String next = iter.next();
			sb.append(next);
			if (iter.hasNext()) {
				sb.append(separator);
			}
		}
		return sb.toString();
	}

	/**
	 * Given a tweet and a set of HyperText.
	 * Between the start and end, those part of tweet should be translated to hypertext.
	 */
	static String tweetToHyperText(String tweet, Set<HyperText> htSet) {
		if (tweet == null || htSet == null) {
			return null;
		}

		TreeSet<HyperText> set = new TreeSet(htSet);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tweet.length(); i++) {
			if (!set.isEmpty() && i == set.first().start) {
				HyperText ht = set.pollFirst();
				sb.append(ht.text);
				i = ht.end;
			} else {
				sb.append(tweet.charAt(i));
			}
		}

		return sb.toString();
	}

	/**
	 * Given a string, detect its anagram is palindrome or not
	 */
	static boolean is_anagram_palindrome(String S) {
		if (S == null || S.length() == 0) {
			return false;
		}
		int[] count = new int[26];
		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			count[(c - 'a')] += 1;
		}
		int odd = 0;
		for (int c : count) {
			if ((c & 1) == 1) {
				odd++;
			}
		}
		return odd <= 1;
	}

	/**
	 * '?' Matches any single character.
	 * '*' Matches any sequence of characters (including the empty sequence).
	 * Algo:
	 * Greedy, star match 0 char, move forward.
	 * When we can't move forward, back to star position, let star match 1 character, move forward.
	 * When we can't move forward, back to star position, let * match 2 char, move forward.
	 * until * match all the remain character.
	 * See we used up all pattern or not
	 */
	static public boolean wildCardMatch(String s, String p) {
		if (s == null || p == null) {
			return false;
		}

		int i = 0, j = 0, star_j = -1, star_i = 0;

		while(i < s.length()) {
			//one * and multiple *, same effect
			while(j < p.length() && p.charAt(j)=='*') {
				star_j = j;  //* match 0 character
				star_i = i;
				j++;
			}

			if(j == p.length() || (p.charAt(j) != s.charAt(i) && p.charAt(j) != '?')) {
				//when not match, need to match star
				if(star_j == -1) {
					return false;
				} else {
					j = star_j + 1;
					i = ++star_i;     //* match 1 character
				}
			} else {
				//match 1 character
				i++;
				j++;
			}
		}

		while(j < p.length() && p.charAt(j) == '*') {
			j++;
		}
		return j == p.length();
	}

	/**
	 * This is different from code above, in regex_match
	 * '.' match any single char.
	 * '*' match 0 or more times of prev char
	 * Algo:
	 * For current i and j,
	 * 0)if p[j+1] != '*', we have to match current char, if not match, return false.
	 * 1)if p[j+1] == '*', so p[j - j+1] can appear 0 to more times. we need to match all (i, j+2), (i+1, j+2), (i+2,j+2)...
	 */
	static boolean regex_match(String s, String p) {
		if(p.length() == 0) {
			return s.length() == 0;
		}
		return regex_match_helper(s, p, 0, 0);
	}

	private static boolean regex_match_helper(String s, String p, int i, int j) {
		if(j==p.length())
			return i==s.length();

		if(j==p.length()-1 || p.charAt(j+1)!='*') {
			//p[j+1] != '*'
			if (i==s.length()|| (s.charAt(i)!=p.charAt(j) && p.charAt(j)!='.')) {
				return false;
			} else {
				return regex_match_helper(s, p, i + 1, j + 1);
			}
		} else {
			//p.charAt(j+1)=='*', match all s[i] that equals to p[j]
			//if not equal, break
			while(i<s.length() && (p.charAt(j)=='.' || s.charAt(i)==p.charAt(j))) {
				if (regex_match_helper(s,p,i,j+2)) {
					return true;
				}
				i++;
			}
			return regex_match_helper(s,p,i,j+2); //* match 0 char
		}
	}

	/**
	 * Facebook interview question
	 * return all the anagrams in group.
	 */
	static ArrayList<ArrayList<String>> anagramGroups(String[] a) {
		if (a == null || a.length == 0) {
			return null;
		}
		HashMap<String, ArrayList<String>> map = new HashMap();
		for (String s : a) {
			char[] c = s.toCharArray();
			Arrays.sort(c);
			String sorted = new String(c);
			if (!map.containsKey(sorted)) {
				ArrayList<String> list = new ArrayList(Arrays.asList(s));
				map.put(sorted, list);
			} else {
				map.get(sorted).add(s);
			}
		}

		//filter out all the single word string
		ArrayList<ArrayList<String>> res = new ArrayList();
		for (ArrayList<String> list : map.values()) {
			if (list.size() > 1) {
				res.add(list);
			}
		}
		return res;
	}

	/**
	 * As same as above, but input is String s.
	 * And use O(n) count sort algo
	 * Time: O(n * m), m is max length of word in string.
	 */
	static ArrayList<ArrayList<String>> anagramGroups(String s) throws Exception {
		if (s == null || s.length() == 0) {
			return null;
		}
		String[] a = s.trim().split(" ");
		HashMap<String, ArrayList<String>> map = new HashMap();
		for (String str : a) {
			String sorted = anagramSortHelper(str);
			if (map.containsKey(sorted)) {
				map.get(sorted).add(str);
			} else {
				ArrayList<String> list = new ArrayList(Arrays.asList(str));
				map.put(sorted, list);
			}
		}
		//filter out all the single word string
		ArrayList<ArrayList<String>> res = new ArrayList();
		for (ArrayList<String> list : map.values()) {
			if (list.size() > 1) {
				res.add(list);
			}
		}
		return res;
	}

	/**
	 * Because it's only contains letter, we can make sort and lowercase in O(n)
	 */
	private static String anagramSortHelper(String s) throws Exception {
		char[] a = s.toCharArray();
		int[] count = new int[26];
		for (char c : a) {
			if (c >= 'A' && c <= 'Z') {
				count[c - 'A'] += 1;
			} else if (c >= 'a' && c <= 'z') {
				count[c - 'a'] += 1;
			} else {
				throw new Exception();
			}
		}
		StringBuilder sb = new StringBuilder();
		for (char c = 'a'; c <= 'z'; c++) {
			int i = c - 'a';
			while (count[i] > 0) {
				sb.append(c);
				count[i]--;
			}
		}
		return sb.toString();
	}

	/**
	 * Find the subString in s. return the subString of s that contains sub
	 */
	static String strStr(String s, String sub) {
		if (s == null || sub == null || s.length() < sub.length()) {
			return null;
		}

		for (int i = 0; i <= s.length() - sub.length(); i++) {
			if (s.charAt(i) == sub.charAt(0)) {
				int j = 0;
				for (; j < sub.length(); j++) {
					if (s.charAt(i + j) != sub.charAt(j)) {
						break;
					}
				}
				if (j == sub.length()) {
					return s.substring(i);
				}
			}
		}
		return null;
	}
}

class HyperText implements Comparable<HyperText> {
	int start, end;
	String text;
	HyperText(int s, int e, String ht) {
		start = s;
		end = e;
		text = ht;
	}

	public int compareTo(HyperText to) {
		return this.start - to.start;
	}
}