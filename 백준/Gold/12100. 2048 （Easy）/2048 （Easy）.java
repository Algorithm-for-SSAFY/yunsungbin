import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	static int N, map[][], ans = 0;
	static int dr[] = { -1, 1, 0, 0 };// 상하좌우
	static int dc[] = { 0, 0, -1, 1 };
	static boolean check[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0);
		System.out.println(ans);
	}

	private static void dfs(int idx) {
		if(idx == 5) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					ans = Math.max(ans, map[i][j]);
				}
			}
			return;
		}
		int origin[][] = new int[N][N];
		for(int i = 0; i < N; i++)
			origin[i] = map[i].clone();
		for (int d = 0; d < 4; d++) {
			move(map, d);
			dfs(idx+1);
			for (int i = 0; i < origin.length; i++) {
				map[i] = origin[i].clone();
			}
		}
	}

	private static void move(int map[][], int dir) {
		check = new boolean[N][N];
		if (dir == 0) {// 상
			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N; i++) {
					if (map[i][j] != 0) {
						int num = map[i][j];
						Point next = go(i, j, dir, map);
						map[i][j] = 0;
						map[next.x][next.y] += num;
					}
				}
			}
		} else if (dir == 1) {// 하
			for (int j = 0; j < N; j++) {
				for (int i = N - 1; i >= 0; i--) {
					if (map[i][j] != 0) {
						int num = map[i][j];
						Point next = go(i, j, dir, map);
						map[i][j] = 0;
						map[next.x][next.y] += num;
					}
				}
			}
		} else if (dir == 2) {// 좌
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != 0) {
						int num = map[i][j];
						Point next = go(i, j, dir, map);
						map[i][j] = 0;
						map[next.x][next.y] += num;
					}
				}
			}
		} else {// 우
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j >= 0; j--) {
					if (map[i][j] != 0) {
						int num = map[i][j];
						Point next = go(i, j, dir, map);
						map[i][j] = 0;
						map[next.x][next.y] += num;
					}
				}
			}
		}
	}

	private static Point go(int i, int j, int d, int map[][]) {
		Point p = new Point();
		int r = i;
		int c = j;
		while (true) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			// 범위 밖, 0이 아니면서 내 값과 같지 않을때
			if (nr < 0 || nc < 0 || nr >= N || nc >= N || (map[nr][nc] != 0 && map[i][j] != map[nr][nc])) {
				p = new Point(r, c);
				break;
			}
			//같은 경우
			if(map[i][j] == map[nr][nc]) {
				if(check[nr][nc]) {
					p = new Point(r, c);
					break;
				}else {
					check[nr][nc] = true;
				}
			}
			r = nr;
			c = nc;
		}
		return p;
	}
}