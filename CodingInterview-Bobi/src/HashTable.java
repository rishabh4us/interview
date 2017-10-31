import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Map;

@SuppressWarnings("ALL")
public class HashTable {
	public static void main(String[] args) throws Exception {
		HashTable table = new HashTable(10);
		table.put("1", 1);
		System.out.println(table.get("1"));
	}

	LinkedList<Map.Entry<String, Integer>>[] list;
	int size;

	HashTable(int size) throws IllegalArgumentException {
		if (size < 1) {
			throw new IllegalArgumentException();
		}

		list = new LinkedList[size];
		this.size = size;
		for (int i = 0; i < size; i++) {
			list[i] = new LinkedList();
		}
	}

	void put(String key, int v) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		int index = (int)hash(key);
		list[index].offer(new AbstractMap.SimpleEntry(key, v));
	}

	int get(String key) throws Exception {
		if (key == null) {
			throw new IllegalArgumentException();
		}

		int index = (int)hash(key);
		for (Map.Entry<String, Integer> map : list[index]) {
			if (map.getKey().equals(key)) {
				return map.getValue();
			}
		}
		throw new Exception();
	}

	long hash(String s) {
		long rolling = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			rolling = (rolling + c) % size;
		}
		return rolling;
	}
}
