import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class B6543 {

	static int V, E;
	static ArrayList<Integer> graph[];
	static ArrayList<Integer> reGraph[];
	static Stack<Integer> stack;
	static boolean visit[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while (true) {
			st = new StringTokenizer(br.readLine());
			if ((V = Integer.parseInt(st.nextToken())) == 0)
				break;

			E = Integer.parseInt(st.nextToken());

			// Size Init
			graph = new ArrayList[V + 1];
			reGraph = new ArrayList[V + 1];
			visit = new boolean[V + 1];

			for (int v = 1; v <= V; v++) {
				graph[v] = new ArrayList<Integer>();
				reGraph[v] = new ArrayList<Integer>();
			}

			st = new StringTokenizer(br.readLine());

			for (int e = 0; e < E; e++) {
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());

				graph[u].add(v);
				reGraph[v].add(u);
			}

			stack = new Stack<>();

			for (int v = 1; v <= V; v++) {
				if (!visit[v])
					dfs(v);
			}

			ArrayList<Integer> list = new ArrayList<Integer>();
			Arrays.fill(visit, false);

			while (!stack.isEmpty()) {
				int node = stack.pop();
				if (!visit[node]) {

					int t = list.size();

					SCC(node, list);
					boolean check = true;

					for (int i = t; i < list.size(); i++) {
						int len = graph[list.get(i)].size();
						for (int j = 0; j < len; j++) {
							// 하나라도 다른게 존재한다면 지금 추가된 SCC 모드는 제거하겠다.
							if (!visit[graph[list.get(i)].get(j)]) {
								check = false;
								break;
							}
						}
						if (!check)
							break;
					}
					
					// 현재 템포에서 추가된 SCC를 제거하는 코드
					if (!check) {
						while (list.size() > t) {
							list.remove(list.size() - 1);
						}
					}
				}

			}
			Collections.sort(list);
			for (int l : list) {
				sb.append(l).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());

	}

	static void dfs(int node) {
		visit[node] = true;

		for (int next : graph[node]) {
			if (!visit[next]) {
				dfs(next);
			}
		}
		stack.add(node);
	}

	// stack에 나온걸 가지고 찾는 dfs
	public static void SCC(int node, ArrayList<Integer> list) {
		visit[node] = true;
		list.add(node);
		for (int next : reGraph[node]) {
			if (!visit[next]) {
				SCC(next, list);
			}
		}
	}

}
