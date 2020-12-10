import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B11585 {
	static int[] getPi(String Pattern) {
		int[] pi = new int[Pattern.length()];

		int j = 0;

		for (int i = 1; i < Pattern.length(); i++) {

			while (j > 0 && Pattern.charAt(i) != Pattern.charAt(j)) {
				j = pi[j - 1];
			}
			if (Pattern.charAt(i) == Pattern.charAt(j)) {
				pi[i] = ++j;
			}
		}
		return pi;
	}

	static int GCD(int a, int b) {
		if (a > b) {
			int tmp = a;
			a = b;
			b = tmp;
		}

		while (a % b != 0) {
			int tmp = a % b;
			a = b;
			b = tmp;
		}
		return b;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());

		while (st.hasMoreTokens()) {
			sb.append(st.nextToken().charAt(0));
		}

		br.readLine();

//		System.out.println(Arrays.toString(getPi(sb.toString())));

		int[] Pi = getPi(sb.toString());

		int son = 1;
		if (N != 1 && Pi[N - 1] != 0) {
			son = N / (Pi[N - 1] - Pi[Pi[N - 1] - 1]);
		}

		int gcd = GCD(N, son);

		son /= gcd;
		N /= gcd;

		System.out.println(son + "/" + N);
	}

}
