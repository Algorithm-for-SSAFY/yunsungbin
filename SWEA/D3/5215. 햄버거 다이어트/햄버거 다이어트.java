import java.io.*;
import java.util.*;

public class Solution {
	static int N,L;
	static ArrayList<int[]> hambugi;
	static int maxScore;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int t = 1; t <= TC; ++t) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			hambugi = new ArrayList<>();
			maxScore = 0;
			
			for(int i = 0 ; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int kcal = Integer.parseInt(st.nextToken());
				hambugi.add(new int[] {score, kcal});
			}
			solve(0,0,0);
			sb.append(maxScore).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void solve(int idx, int curScore, int curKcal) {
		if(idx >= N) {
			if(curKcal <= L) maxScore = Math.max(maxScore, curScore);
			return;
		}
		
		solve(idx+1, curScore + hambugi.get(idx)[0], curKcal + hambugi.get(idx)[1]); // 선택한 것
		solve(idx+1, curScore, curKcal); // 선택 안한 것
		
	}
	

}
