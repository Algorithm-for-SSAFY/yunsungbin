import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 
문자	의미
.	평지(전차가 들어갈 수 있다.)
*	벽돌로 만들어진 벽
#	강철로 만들어진 벽
-	물(전차는 들어갈 수 없다.)
^	위쪽을 바라보는 전차(아래는 평지이다.)
v	아래쪽을 바라보는 전차(아래는 평지이다.)
<	왼쪽을 바라보는 전차(아래는 평지이다.)
>	오른쪽을 바라보는 전차(아래는 평지이다.)
^v<>
 */
public class Solution {
	static int W,H,M,dir;
	static String controls;
	static char maps[][];
	static int[] di = {-1,1,0,0}; // 상하좌우 순
	static int[] dj = {0,0,-1,1};
	
	static int[] tank;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= TC; ++t) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			maps = new char[H][W];
			tank = new int[2];
			for(int i = 0 ; i < H; ++i) {
				String line = br.readLine();
				for(int j = 0 ; j < W; ++j) {
					char ch = line.charAt(j);
					maps[i][j] = ch;
					if("^v<>".contains(String.valueOf(ch))) {
						tank[0] = i;
						tank[1] = j;
						if("^".equals(String.valueOf(ch))) dir = 0;
						if("v".equals(String.valueOf(ch))) dir = 1;
						if("<".equals(String.valueOf(ch))) dir = 2;
						if(">".equals(String.valueOf(ch))) dir = 3;

					}
				}
			}

			M = Integer.parseInt(br.readLine());
			controls = br.readLine();
			
			for(int c = 0 ; c < M; ++c) { // task
				solve(controls.charAt(c));
			}
			printMap(sb);
		}
		System.out.println(sb);
	}
	
	static void solve(char ct) {
		switch(ct) {
			case 'U': dir = 0; move(); break;
			case 'D': dir = 1; move(); break; 
			case 'L': dir = 2; move(); break;
			case 'R': dir = 3; move(); break;
			case 'S': shot(); break;
		}
	}
	
	static void move() { // 0 1 2 3  상 하 좌 우
		switch(dir) {
			case 0 : maps[tank[0]][tank[1]] = '^'; break;
			case 1 : maps[tank[0]][tank[1]] = 'v'; break;
			case 2 : maps[tank[0]][tank[1]] = '<'; break;
			case 3 : maps[tank[0]][tank[1]] = '>'; break;
		}
		int ni = tank[0] + di[dir];
		int nj = tank[1] + dj[dir];
		if(ni < 0 || nj < 0 || ni >= H || nj >= W || maps[ni][nj] != '.') return;
		
		maps[ni][nj] = maps[tank[0]][tank[1]]; // 평지를 탱크로
		maps[tank[0]][tank[1]] = '.'; // 있던 곳은 평지로
		tank[0] = ni;
		tank[1] = nj;
	}
	
	static void shot() {
		for (int i = 0; i <= Math.max(H, W); i++) {
			int ni = tank[0] + di[dir] * i;
			int nj = tank[1] + dj[dir] * i;
			if(ni < 0 || nj < 0 || ni >= H || nj >= W || maps[ni][nj] == '#') break;
			if(maps[ni][nj] == '*') {
				maps[ni][nj] = '.';
				break;
			}
		}
	}
	
	static void printMap(StringBuilder sb) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				sb.append(maps[i][j]);
			}
			sb.append("\n");
		}
	}
}
