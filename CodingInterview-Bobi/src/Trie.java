import sun.net.www.content.text.plain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Trie {
	HashMap<Character, HashMap> dict;
	private final static char END = '.';

	Trie(String[] wordList) {
		dict = new HashMap<>();
		for (String word : wordList) {
			HashMap<Character, HashMap> currentDict = dict;
			for (char c : word.toCharArray()) {
				if (currentDict.containsKey(c)) {
					currentDict = (HashMap)currentDict.get(c);
				} else {
					HashMap<Character, HashMap> nextDict = new HashMap<>();
					currentDict.put(c, nextDict);
					currentDict = nextDict;
				}
			}
			currentDict.put(END, null);
		}
	}

	public boolean contains(String word) {
		word += END;
		HashMap<Character, HashMap> currentDict = this.dict;
		for (char c : word.toCharArray()) {
			if (currentDict.containsKey(c)) {
				currentDict = currentDict.get(c);
			} else {
				return false;
			}
		}
		return true;
	}

	public String[] getAllRemain(String s) throws IllegalArgumentException{
		if (s == null) {
			throw new IllegalArgumentException();
		}
		HashMap<Character, HashMap> currentDict = this.dict;
		for (char c : s.toCharArray()) {
			if (currentDict.containsKey(c)) {
				currentDict = currentDict.get(c);
			} else {
				return new String[0];
			}
		}

		ArrayList<StringBuilder> res = new ArrayList();
		getAllPathHelper(new StringBuilder(), res, s.charAt(s.length() - 1), currentDict);

		String[] strs = new String[res.size()];
		for (int i = 0; i < res.size(); i++) {
			strs[i] = res.get(i).toString();
		}
		return strs;
	}

	//DFS
	private void getAllPathHelper(StringBuilder path, ArrayList<StringBuilder> res, char c, HashMap<Character, HashMap> map) {
		if (c == END) {
			res.add(new StringBuilder(path));
			return;
		}
		path.append(c);
		//loop all child
		for (char x : map.keySet()) {
			getAllPathHelper(path, res, x, map.get(x));
		}
		path.deleteCharAt(path.length() - 1);
	}

	public static void main(String[] args) throws Exception {
		String[] wordList = {"boy", "boys", "baby"};
		Trie tree = new Trie(wordList);
		for (String word : wordList) {
			System.out.println(tree.contains(word));
		}
		System.out.println(tree.contains("e"));
		System.out.println(Arrays.toString(tree.getAllRemain("x")));
	}
}
