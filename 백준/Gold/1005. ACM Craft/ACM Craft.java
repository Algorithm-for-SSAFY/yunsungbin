import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int T, N, K, TARGET, ans;
	static Building[] nodes;

	static class Building {
		ArrayList<Integer> prev = new ArrayList<Integer>();
		ArrayList<Integer> next = new ArrayList<Integer>();
		int t;

		Building(int t) {
			this.t = t;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; ++t) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 건물의 개수
			K = Integer.parseInt(st.nextToken()); // 건설순서 규칙 수
			ans = 0;
			nodes = new Building[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				int time = Integer.parseInt(st.nextToken());
				nodes[i] = new Building(time);
			}

			for (int i = 0; i < K; ++i) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				nodes[to].prev.add(from);
				nodes[from].next.add(to);
			}

			TARGET = Integer.parseInt(br.readLine()); // 지어야할 목
			
			solve();
			
			sb.append(nodes[TARGET].t).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void solve() {

		// 1. prev가 0인 건물들을 모두 담고 시작
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		Building[] origin = new Building[N+1];
		
		for (int i = 1; i <= N; ++i) {
			if (nodes[i].prev.size() == 0) {
				q.offer(i);
				visited[i] = true;
			}
			origin[i] = new Building(nodes[i].t);
		}

		while (!q.isEmpty()) {
			while (!q.isEmpty()) {
				int curBuilding = q.poll();
				Building cur = nodes[curBuilding];

				for (int next : cur.next) {
					nodes[next].prev.removeIf(v -> v == curBuilding);
					nodes[next].t = Math.max(nodes[next].t, cur.t + origin[next].t);
				}
			}
			
			// 0차수를 다 돌렸을 때, 0이 된 친구들을 다시 담기 
			for (int i = 1; i <= N; ++i) {
				if (nodes[i].prev.size() == 0 && !visited[i]) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}

	}

}
