import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 치킨집 1~M개를 선택하는 경우의 수를 조합.
 * 모든 집을 각 치킨집에 대입해보며 짧은 것을 다 더한 값을 비교 
 */
public class Main {
	static int N, M, SIZE, ans;
	static int maps[][];
	static ArrayList<Point> h = new ArrayList<Point>();
	static ArrayList<Point> c = new ArrayList<Point>();

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maps = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int v = Integer.parseInt(st.nextToken());
				maps[i][j] = v;
				if (v == 1)
					h.add(new Point(i, j));
				else if (v == 2)
					c.add(new Point(i, j));
			}
		}
		ans = (int) 1e9;
		SIZE = c.size();
		solve(new ArrayList<Point>(), 0);
		System.out.println(ans);

	}

	public static void solve(ArrayList<Point> curC, int idx) {
		if (curC.size() > M || idx > SIZE)
			return;

		int sum = 0;
		for (Point hs : h) {
			int len = (int) 1e9;
			for (Point cs : curC) {
				len = Math.min(len, Math.abs(hs.x - cs.x) + Math.abs(hs.y - cs.y));

			}
			if (curC.size() > 0)
				sum += len;
		}
		if (sum != 0)
			ans = Math.min(ans, sum);
//		System.out.println(sum);
		// 선택하지 않았을 경우
		solve(curC, idx + 1);

		// 선택했을 경우
		if (idx < SIZE) {
			ArrayList<Point> tmp = new ArrayList<Point>(curC);
			tmp.add(c.get(idx));
			solve(tmp, idx + 1);
		}
	}

}
