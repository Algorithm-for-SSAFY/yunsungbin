import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static List<Integer> route = new ArrayList<>();
	static int[] p = new int[100001];
	static int idx = 0;
	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Arrays.fill(p, -1);
		
		BFS2(N, K,  new boolean[100001]);
		System.out.println(minCnt);
		for(int i = K; i != -1 ; i = p[i]) {
			route.add(i);
		}
		Collections.reverse(route);
		
		for (int i : route)
			System.out.print(i + " ");
	}

	static int minCnt = Integer.MAX_VALUE;

	public static void BFS2(int start, int dest, boolean[] visited) {
		Queue<int[]> q = new ArrayDeque<>();

		q.offer(new int[] { start, 0 });
		visited[start] = true;

		while (!q.isEmpty()) {
			int[] ary = q.poll();
			int cur = ary[0];
			int cnt = ary[1];
			if (cur == dest) {
				minCnt = cnt;
				break;
			}

	        int[] nexts = {cur - 1, cur + 1, cur * 2};
	        for (int next : nexts) {
	            if (next < 0 || next >= 100001) continue;
	            if (visited[next]) continue;

	            visited[next] = true;
	            p[next] = cur;
//	            dist[next] = dist[cur] + 1;
	            q.offer(new int[] {next, cnt + 1});
	        }
		}
	}
}
