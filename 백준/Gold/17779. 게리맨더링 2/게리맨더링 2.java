import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int maps[][]; // 위치별 인원을 담을 맵
	static int area[][]; // 선거구를 나눌 맵
	static int cnt1, cnt2, cnt3, cnt4, cnt5; // 구역별 인원
	static int minDiff = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		maps = new int[N + 1][N + 1];
		area = new int[N + 1][N + 1];

		for (int i = 1; i <= N; ++i)
			Arrays.fill(area[i], 0);

		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solve();

		System.out.println(minDiff);
	}

	static void solve() {
		for(int x = 1; x <= N; x++) {
			for(int y = 1; y <= N; y++) {
				for(int d1 = 1; d1 <= N-x; d1++) {
					for(int d2 = 1; d2 <= N-x-d1; d2++) {
						if(x+d1+d2 > N || y+d2 > N || y-d1 < 1) break;

						for (int i = 1; i <= N; ++i)
							Arrays.fill(area[i], 0);
						
						makeArea5(x,y,d1,d2);
						makeAreaOthers(x,y,d1,d2);
						int tmp =calcDiff();
//						if(tmp == 22) {
//							printMap();
//							System.out.println("=====================");
//						}
						minDiff = Math.min(tmp, minDiff);
					}	
				}
			}	
		}

	}

	static void makeArea5(int x, int y, int d1, int d2) { // x = c = j, y = r = i
	    // Boundary 1
	    for (int i = 0; i <= d1; i++) area[x + i][y - i] = 5;

	    // Boundary 2
	    for (int i = 0; i <= d2; i++) area[x + i][y + i] = 5;

	    // Boundary 3
	    for (int i = 0; i <= d2; i++) area[x + d1 + i][y - d1 + i] = 5;

	    // Boundary 4
	    for (int i = 0; i <= d1; i++) area[x + d2 + i][y + d2 - i] = 5;
	    
		for (int i = 1; i <= N; i++) {
			int startX = -1, endX = -1;
			for (int j = 1; j <= N; j++) {
				if (startX < 0 && area[i][j] == 5)
					startX = j;
				if (startX > 0 && area[i][j] == 5)
					endX = j;
			}
			if (startX > 0 && endX > 0)
				for (int j = startX+1; j < endX; j++)
					area[i][j] = 5;
		}

	}

	static void makeAreaOthers(int x, int y, int d1, int d2) {
		for (int r = 1; r <= x + d1; ++r) { // make Area 1
			for (int c = 1; c <=y; ++c) {
				if (area[r][c] == 5)
					continue;
				area[r][c] = 1;
			}
		}
		for (int r = 1 ; r <= x+d2; ++r) { // make Area 2
			for (int c = y+1; c <= N; ++c) {
				if (area[r][c] == 5)
					continue;
				area[r][c] = 2;
			}
		}
		for (int r = x+d1; r <= N; ++r) { // make Area 3
			for (int c = 1 ; c <= y-d1+d2; ++c) {
				if (area[r][c] == 5) {break;}
				area[r][c] = 3;
			}
		}
		for (int r = x+d2+1; r <= N; ++r) { // make Area 4
			for (int c = y - d1 + d2; c <= N; ++c) {
				if (area[r][c] == 5)
					continue;
				area[r][c] = 4;
			}
		}
	}

	static int calcDiff() {
		cnt1 = cnt2 = cnt3 = cnt4 = cnt5 =0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (area[i][j] == 1)
					cnt1 += maps[i][j];
				else if (area[i][j] == 2)
					cnt2 += maps[i][j];
				else if (area[i][j] == 3)
					cnt3 += maps[i][j];
				else if (area[i][j] == 4)
					cnt4 += maps[i][j];
				else if (area[i][j] == 5)
					cnt5 += maps[i][j];
			}
		}
		
		int max = Math.max(Math.max(Math.max(cnt1, cnt2), Math.max(cnt3, cnt4)),cnt5);
		int min = Math.min(Math.min(Math.min(cnt1, cnt2), Math.min(cnt3, cnt4)),cnt5);
//		if(max - min == 82)
//		System.out.println(cnt1 + ", " + cnt2 + ", " + cnt3 + ", " + cnt4 + ", " + cnt5 + "|| max : " + max + ", min : " + min);

		return max - min;
	}

	static void printMap() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(area[i][j] + " ");
			}
			System.out.println();
		}
	}

}
