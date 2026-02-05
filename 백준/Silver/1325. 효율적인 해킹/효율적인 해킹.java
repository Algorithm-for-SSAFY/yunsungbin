import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static ArrayList<Integer> nodes[];
	static ArrayList<Integer> ans[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nodes = new ArrayList[N + 1];
		ans = new ArrayList[N + 1];
		for (int i = 1; i <= N; ++i) {
			nodes[i] = new ArrayList<>();
			ans[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());

			nodes[n2].add(n1);
		}

		int maxHack = 0;
		for (int i = 1; i <= N; ++i) {
			boolean[] visited = new boolean[N + 1];
			int hack = solveBFS(i, visited, 1);
			maxHack = Math.max(hack, maxHack);
			ans[hack].add(i);
		}

		Collections.sort(ans[maxHack]);
		for (int n : ans[maxHack])
			System.out.print(n + " ");
	}

	static int solveDFS(int cur, boolean[] visited, int hack) {
		visited[cur] = true;

		for (int n : nodes[cur]) {
			if (!visited[n]) {
				hack = Math.max(hack, solveDFS(n, visited, hack + 1));
			}
		}
		return hack;
	}

	static int solveBFS(int start, boolean[] visited, int hack) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		visited[start] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int n : nodes[cur]) {
				if (!visited[n]) {
					hack++;
					q.offer(n);
					visited[n] = true;
				}
			}
		}
		return hack;
	}

}
