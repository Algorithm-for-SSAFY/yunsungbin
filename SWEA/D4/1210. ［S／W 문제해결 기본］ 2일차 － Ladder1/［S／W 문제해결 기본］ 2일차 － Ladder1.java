import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 다시 올라가는 경우는 없다.
 * 3방 탐색 ( 하, 좌, 우 )
 * 단, 좌,우 이동이 하보다 우선되어야 한다.
 * 이동할 경우, 나머지 3방향 모두 visited 처리를 해버린다.
 */
public class Solution {
	static int maps[][];
	static boolean[][] visited;
	static int ansX = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		List<Integer> startX = new ArrayList<>();
		StringTokenizer st;
		for (int t = 1; t <= 10; ++t) {
			sb.append("#").append(t).append(" ");
			br.readLine(); // 테케 소모
			maps = new int[100][100];
			ansX = -1;
			startX = new ArrayList<>();
			for (int i = 0; i < 100; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; ++j) {
					maps[i][j] = Integer.parseInt(st.nextToken());
					if (i == 0 && maps[0][j] == 1) // 첫째줄의 1 리스트
						startX.add(j);
				}
			}

			for (int x : startX) {
				visited = new boolean[100][100];
				DFS(new int[] { 0, x });
				if (ansX >= 0) {
					sb.append(x).append("\n");
					break;
				}
			}

		}
		System.out.println(sb);

	}

	static void DFS(int[] cur) {

		int r = cur[0];
		int c = cur[1];		
		if(ansX >= 0) {
			ansX = c;
			return;
		}
		if (maps[r][c] == 2) {
			ansX = c;
			return;
		}

		visited[r][c] = true; // 방문 처리

		//좌
		if (c - 1 >= 0 && maps[r][c - 1] != 0 && !visited[r][c - 1]) {
			DFS(new int[] { r, c - 1 }); 
			if (ansX >= 0)
				return; 
		}
		//우
		else if (c + 1 < 100 && maps[r][c + 1] != 0 && !visited[r][c + 1]) {
			DFS(new int[] { r, c + 1 }); 
			if (ansX >= 0)
				return; 
		}
		//하
		else if (r + 1 < 100 && maps[r + 1][c] != 0 && !visited[r + 1][c]) {
			DFS(new int[] { r + 1, c });
			if (ansX >= 0)
				return; 
		}
	}

}
