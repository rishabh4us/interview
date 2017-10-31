import java.util.*;

public class Graph {
	List<Vertex> nodes;

	Graph(List<Vertex> nodes) {
		this.nodes = nodes;
	}

	public static void main(String[] args) {
		Vertex a = new Vertex(0), b = new Vertex(1), c = new Vertex(2), d = new Vertex(3), e = new Vertex(4);
		a.addTo(b);
		b.addTo(c);
		c.addTo(d);
		d.addTo(e);
		e.addTo(a);
		ArrayList<Vertex> list = new ArrayList(Arrays.asList(a,b,c,d,e));
//		System.out.println(hasCirclarDepend(list));
//		System.out.println(isBipartite());
	}

	/**
	 * Check file has circular dependency
	 * The core is check a graph has circle or not. We can use BFS to detect.
	 *
	 * O(n) for time.
	 */
	static boolean hasCirclarDepend(ArrayList<Vertex> list) {
		if (list == null || list.size() == 1) {
			return false;
		}
		HashSet<Vertex> visited = new HashSet();
		for (Vertex v : list) {
			if (!visited.contains(v)) {
				HashSet<Vertex> temp = new HashSet();
				if (hasCircle(v, temp)) {
					return true;
				}
				visited.addAll(temp);
			}
		}
		return false;
	}

	static boolean hasCircle(Vertex root, HashSet<Vertex> visited) {
		//BFS, check dup
		LinkedList<Vertex> q = new LinkedList();

		q.offer(root);
		while (q.size() != 0) {
			Vertex v = q.poll();
			if (visited.contains(v)) {
				return true;
			}
			for (Vertex a : v.toVerticeList) {
				q.offer(a);
			}
			visited.add(v);
		}
		return false;
	}

	static ArrayList<Vertex> topoSort(LinkedList<Vertex> noIncomeList, int totalNodeNum) {
		ArrayList<Vertex> result = new ArrayList();
		while (noIncomeList.size() != 0) {
			Vertex from = noIncomeList.poll();
			result.add(from);
			for (Vertex to : from.toVerticeList) {
				to.fromVerticeList.remove(from);
				if (to.fromVerticeList.size() == 0) {
					noIncomeList.add(to);
				}
			}
		}
		if (result.size() != totalNodeNum) {
			return null; //HAS CIRCLE!!!!
		}
		return result;
	}

	/**
	 * Facebook interview, given a list of friends, like a graph
	 * return is graph can be bipartite or not.
	 *
	 * Algo:
	 * 0)BFS search, each node give a color(true or false). its neighbor(friend) must be different color.
	 * 1)check all the nodes been colored or not.
	 */
	static boolean isBipartite(Graph graph) {
		//each node is sub connected graph
		if (graph == null) {
			return false;
		}
		HashSet<Vertex> visited = new HashSet();
		for (Vertex v : graph.nodes) {
			if (!visited.contains(v) && !isBipartiteHelper(v, visited)) {
				return false;
			}
		}
		return true;
	}

	static boolean isBipartiteHelper(Vertex v, HashSet<Vertex> visited) {
		if (v == null) {
			return false;
		}
		HashMap<Vertex, Boolean> map = new HashMap();
		LinkedList<Vertex> s = new LinkedList();
		s.add(v);
		map.put(v, true);
		while (!s.isEmpty()) {
			Vertex cur = s.poll();
			visited.add(cur);
			boolean cur_color = map.get(cur);
			for (Vertex n : cur.toVerticeList) {
				if (map.containsKey(n)) {
					if (map.get(n) == cur_color) {
						return false;
					}
				} else {
					map.put(n, !cur_color);
					s.offer(n);
				}
			}
		}

		return true;
	}

	static Vertex deepClone(Vertex v) {
		if (v == null) {
			return null;
		}

		HashMap<Vertex, Vertex> copyMap = new HashMap();
		LinkedList<Vertex> s = new LinkedList();
		copyMap.put(v, new Vertex(v.val));
		s.offer(v);

		//BFS copy
		while (!s.isEmpty()) {
			Vertex cur = s.poll();
			for (Vertex n : cur.toVerticeList) {
				if (!copyMap.containsKey(v)) {
					copyMap.put(n, new Vertex(n.val));
					s.offer(n);
				}
			}
		}

		//attach neighbor
		for (Vertex cur : copyMap.keySet()) {
			Vertex copy = copyMap.get(cur);
			for (Vertex n : cur.toVerticeList) {
				copy.addTo(copyMap.get(n));
			}
		}
		return copyMap.get(v);
	}
}

class Vertex {
	int val;
	List<Vertex> toVerticeList, fromVerticeList;

	Vertex(int val) {
		this.val = val;
		toVerticeList = new ArrayList();
	}

	void addTo(Vertex to) {
		toVerticeList.add(to);
	}

	void addFrom(Vertex from) {
		fromVerticeList.add(from);
	}
}

