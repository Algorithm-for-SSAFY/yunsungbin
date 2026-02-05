
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N 0 , S 1
 * 12시부터 시계방향.
 * 비교 대상군
 * 1 2 3 4
 * 2 6 2 6
 * 6 - (n%2)*4
 */
public class Solution{
	static int[][] mags = new int[4][8];
	static int K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; ++t) {
			sb.append("#").append(t).append(" ");

			K = Integer.parseInt(br.readLine());

			for (int i = 0; i < 4; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; ++j) {
					mags[i][j] = Integer.parseInt(st.nextToken());
				}
			}


			for (int i = 0; i < K; ++i) {
				st = new StringTokenizer(br.readLine());
				int target = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				lotate(target - 1, dir);
	

			}

			sb.append(calc()).append("\n");
		}
		System.out.println(sb);
	}

	public static void lotate(int start, int dir) { // start 0~3, dir -1, 1
		int[] isRotate = new int[4]; // 0~3번에 따라 회전 여부 설정
		int[] dirs = new int[4];
		for (int i = 0; i < 4; ++i)
			isRotate[i] = 0;
		isRotate[start] = 1;
		dirs[start] = dir;
		for (int i = start, j = dir; i < 3; ++i, j *= -1) {
			// 만약 현재 자석이 회전 예정이라면
			if (isRotate[i] == 1) {
				// 만약 내 맞물림점과 다음 자석의 맞물림점이 다르다면, 다음 자석을 회전 예약
				if (mags[i][2] != mags[i + 1][6]) {
					isRotate[i + 1] = 1;
					dirs[i+1] = j * -1;
				}
			}
		}

		for (int i = start, j = dir; i > 0; --i, j *= -1) {
			// 만약 현재 자석이 회전 예정이라면
			if (isRotate[i] == 1) {
				// 만약 내 맞물림점과 다음 자석의 맞물림점이 다르다면, 다음 자석을 회전 예약
				if (mags[i][6] != mags[i - 1][2]) {
					isRotate[i - 1] = 1;
					dirs[i-1] = j * -1;
				}
			}
		}

		// 회전 수행
		for (int i = 0; i < 4; ++i) {
			// 내가 회전 한다면?
			if (isRotate[i] == 1) {
				if (dirs[i] > 0) { // 1은 시계
					asc(i);
					dir = -1;
				} else if (dirs[i] < 0) {
					desc(i);
					dir = 1;
				}
			}
		}

	}

	static void desc(int idx) {
		int tmp = mags[idx][0]; // 첫 번째 값 저장
		for (int i = 0; i < 7; i++) {
			mags[idx][i] = mags[idx][i + 1]; // 왼쪽으로 한 칸씩 밀기
		}
		mags[idx][7] = tmp; // 마지막 자리에 tmp 넣기
	}

	static void asc(int idx) {
		int tmp = mags[idx][7]; // 마지막 값 저장
		for (int i = 7; i > 0; i--) {
			mags[idx][i] = mags[idx][i - 1]; // 오른쪽으로 한 칸씩 밀기
		}
		mags[idx][0] = tmp; // 첫 번째 자리에 tmp 넣기
	}

	static int calc() {
		int sum = 0;
		for (int i = 0; i < 4; ++i) {
			sum += mags[i][0] * Math.pow(2, i);
		}
		return sum;
	}

	static void print() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; ++j) {
				System.out.print(mags[i][j] + " ");
			}
			System.out.println();
		}
	}

}
