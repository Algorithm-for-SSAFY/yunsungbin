import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N,M,C;
	static int[][] map, maxMap;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(in.readLine());
		
		for(int tc = 1; tc <= TC; ++tc) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			maxMap = new int[N][N];
		
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j = 0 ; j < N ; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			System.out.println("#"+tc+" "+getMaxBenefit());
		}

	}
	
	private static int getMaxBenefit() {
		makeMaxMap();
		return processCombination();
	}
	
	private static void makeMaxMap() {
		for(int i = 0 ; i < N; ++i) {
			for(int j = 0 ; j <= N-M; ++j) {
				subset(i,j,0,0,0);
			}
		}
	}
	
	private static void subset(int i, int j, int cnt, int sum, int powSum) {
		if(sum>C) return;
		if(cnt == M) {
			if(maxMap[i][j-M] < powSum) maxMap[i][j-M] = powSum;
			return;
		}
		
		subset(i,j+1,cnt+1,sum+map[i][j],powSum+map[i][j]*+map[i][j]);
		subset(i,j+1,cnt+1,sum,powSum);
	}
	
	private static int processCombination() {
		int aBenefit, bBenefit, max = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) {
				aBenefit = maxMap[i][j];
				bBenefit = 0;
				for(int i2 = i; i2 < N; i2++) {
					int start = (i==i2) ? j+M : 0;
					for(int j2 = start ; j2 <= N-M; ++j2) {
						if(bBenefit < maxMap[i2][j2]) {
							bBenefit = maxMap[i2][j2];
						}
					}
				}
				if(max<aBenefit+bBenefit) {
					max = aBenefit+bBenefit;
				}
			}	
		}
		return max;
	}

}
