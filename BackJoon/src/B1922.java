import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1922 {
	static int N;
	static int M;

	static class Edge implements Comparable<Edge> {
		int a;
		int b;
		int w;

		public Edge(int a, int b, int w) {
			super();
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return w - o.w;
		}
	}

	static int parrent[];
	static int level[];

	static int Find(int x) {
		if (x == parrent[x]) {
			return x;
		}

		int xx = Find(parrent[x]);

		return parrent[x] = xx;
	}

	static boolean Union(int x, int y) {
		x = Find(x);
		y = Find(y);
		if (x == y)
			return false;

		// level 을 이용해서 자식이 적은애들을 많은 애들 믿에넣는다.

		if (level[x] > level[y]) {
			parrent[y] = x;
		} else {
			parrent[x] = y;
		}

		if (level[x] == level[y]) {
			level[y]++;
		}
		return true;

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parrent = new int[N + 1];
		level = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			parrent[i] = i;
			level[i] = 1;
		}
		Edge edge[] = new Edge[M];

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edge[m] = new Edge(a, b, w);
		}

		Arrays.sort(edge);

		int cnt = 0;
		int ans = 0;
		for (int m = 0; m < M && cnt != N - 1; m++) {
			if (Union(edge[m].a, edge[m].b)) {
				cnt++;
				ans += edge[m].w;
			}
		}
		
		System.out.println(ans);
	}
}
