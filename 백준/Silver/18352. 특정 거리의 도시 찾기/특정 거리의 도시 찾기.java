import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] nodes;
	static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 노드 수
		int M = Integer.parseInt(st.nextToken()); // 간선 수
		K = Integer.parseInt(st.nextToken()); // 최단 거리
		int X = Integer.parseInt(st.nextToken()); // 출발 도시 번호

		nodes = new ArrayList[N + 1];
		for (int i = 1; i <= N; ++i) {
			nodes[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			nodes[n1].add(n2);
		}

		boolean[] visited = new boolean[N + 1];
		BFS(X, visited);

	}

	static void BFS(int start, boolean[] visited) {
		Queue<Integer> q = new ArrayDeque<>();

		q.offer(start);
		visited[start] = true;
		int depth = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			if (depth == K)
				break;
			while (size-- > 0) {
				int cur = q.poll();

				for (int n : nodes[cur]) {
					if (!visited[n]) {
						q.offer(n);
						visited[n] = true;
					}
				}
			}
			depth++;
		}
		ArrayList<Integer> ans = new ArrayList<>();
		if (q.isEmpty())
			System.out.println(-1);
		else {
			while (!q.isEmpty()) {
				ans.add(q.poll());
			}
			Collections.sort(ans);
			ans.forEach(System.out::println);
		}

	}

}
