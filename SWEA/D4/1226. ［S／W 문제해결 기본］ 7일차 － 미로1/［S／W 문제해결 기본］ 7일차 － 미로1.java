import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Solution {

	static int maps[][];

	static int di[] = {-1,1,0,0};
	static int dj[] = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= 10; ++t) {
			sb.append("#").append(t).append(" ");
			br.readLine();
			int[] start = new int[2];
			int[] end = new int[2];
			maps = new int[16][16];
			
			for(int i = 0 ; i < 16; ++i) {
				String line = br.readLine();
				for (int j = 0; j < 16; j++) {
					int ch = line.charAt(j) - '0';
					maps[i][j] = ch;
					
					if(ch == 2) {
						start[0] = i;
						start[1] = j;
					}
					if(ch == 3) {
						end[0] = i;
						end[1] = j;
					}
				}
			}
			
			BFS(start);
			
			if(maps[end[0]][end[1]] == 1) sb.append(1).append("\n");
			else sb.append(0).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void BFS(int[] start) {
		ArrayDeque<int[]> q = new ArrayDeque<int[]>();
		
		q.add(start);
		maps[start[0]][start[1]] = 1;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			maps[cur[0]][cur[1]] = 1;
			
			for(int k = 0 ; k < 4; ++k) {
				int ni = cur[0] + di[k];
				int nj = cur[1] + dj[k];
				if( ni < 0 || nj < 0 || ni >= 16 || nj >= 16 || maps[ni][nj] == 1) continue;
				q.add(new int[] {ni,nj});
			}
		}
	}
}
