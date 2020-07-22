import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class B14725 {
	static class TrieNode {
		TreeMap<String, TrieNode> children;

		public TrieNode() {
			children = new TreeMap<String, TrieNode>();
		}
	}

	static TrieNode root;

	public static void main(String[] args) throws NumberFormatException, IOException {
		root = new TrieNode();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());

			int K = Integer.parseInt(st.nextToken());

			// try �˰��� ����

			int level;

			TrieNode pCrawl = root;

			for (level = 0; level < K; level++) {
				String str = st.nextToken();

				// �������� �ʴ� ��� ����
				if (!pCrawl.children.containsKey(str)) {
					pCrawl.children.put(str, new TrieNode());
				}
				pCrawl = pCrawl.children.get(str);
			}
		}
		
		// ���� search �Ѵ�. �̶� �̸� ������ ����Ѵ�. ��͸� ���°� ���ڴ�.
		DFS(root, 0);
		
		System.out.println(sb.toString());

	}

	static StringBuilder sb = new StringBuilder();

	public static void DFS(TrieNode curNode, int level) {
		Iterator<String> keys = curNode.children.keySet().iterator();
		while (keys.hasNext()) {
			for (int i = 0; i < level; i++) {
				sb.append('-').append('-');
			}
				String key = keys.next();
				sb.append(key);
				sb.append('\n');
				DFS(curNode.children.get(key), level + 1);
		}

	}
}
