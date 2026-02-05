import java.util.*;
import java.io.*;

public class Solution {
	static int N, X, M;
	static int knowns[][];
	static int cages[];
	static boolean isSolved;
	static int curMaxSum;
	static int maxCage[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int t = 1; t <= TC; ++t) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			knowns = new int[M][3]; // 경근이
			cages = new int[N + 1]; // 우리
			maxCage = new int[N + 1];
			isSolved = false;
			curMaxSum = 0;

			for (int i = 0; i < M; ++i) {
				st = new StringTokenizer(br.readLine());
				knowns[i][0] = Integer.parseInt(st.nextToken()); // 시작
				knowns[i][1] = Integer.parseInt(st.nextToken()); // 종료
				knowns[i][2] = Integer.parseInt(st.nextToken()); // 수
			}
			solve(1);
			if (checkForMax())
				for (int i = 1; i <= N; ++i)
					sb.append(maxCage[i]).append(" ");
			else {
				sb.append(-1);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static boolean check() { // 모든 조건을 만족했는지 여부
		for (int[] known : knowns) {
			int check = known[2];
			int sum = 0;
			for (int i = known[0]; i <= known[1]; i++) {
				sum += cages[i];
			}
			if (check != sum)
				return false;
		}
		return true;
	}

	static boolean checkForMax() { // 모든 조건을 만족했는지 여부
		for (int[] known : knowns) {
			int check = known[2];
			int sum = 0;
			for (int i = known[0]; i <= known[1]; i++) {
				sum += maxCage[i];
			}
			if (check != sum)
				return false;
		}
		return true;
	}

	static void solve(int cageNum) {
		if (cageNum > N) {
			if (check()) {
				if (checkSum()) { // 크면 무조건 바꿈
					for (int i = 1; i <= N; ++i) {
						maxCage[i] = cages[i];
					}
				} 
				else if (checkEqual() && checkLower()) { // 같으면 사전순도 비교
					for (int i = 1; i <= N; ++i) {
						maxCage[i] = cages[i];
					}

				}
			}
			return;
		}

		for (int i = 0; i <= X; i++) { // X는 각 우리별 최대 크기
			cages[cageNum] = X - i;
			solve(cageNum + 1);
			cages[cageNum] = X;
		}
	}

	static boolean checkSum() {
		int sum = 0;
		for (int i = 1; i <= N; i++)
			sum += cages[i];
		if (sum > curMaxSum) {
			curMaxSum = sum;
			return true;
		}
		return false;
	}

	static boolean checkEqual() {
		int sum = 0;
		for (int i = 1; i <= N; i++)
			sum += cages[i];
		return sum == curMaxSum;
	}

	static boolean checkLower() {
		int sum = 0;
		for (int i = 1; i <= N; i++)
			sum += maxCage[i];
		if (sum == 0)
			return true; // 초기상태 처리
		for (int i = 1; i <= N; ++i) {
			if (maxCage[i] > cages[i])
				return true;
			else if (maxCage[i] < cages[i])
				return false;
		}

		return false;
	}
}
