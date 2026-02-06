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
            solve();
            sb.append(maxScore).append("\n");
        }
        System.out.println(sb);
    }
     
    public static void solve() {
    	
    	for (int i = 0; i < (1 << N); i++) { // 00000 ~ 11111 까지 반복
    		
    		int s = 0;
			int k = 0;
    		for (int j = 0; j < (N); j++) {
				if((i & (1 << j)) != 0) { // j는 0 ~ 5 까지이므로, 00001 ~ 10000
					s += hambugi.get(j)[0];
					k += hambugi.get(j)[1];
				}
				if(k > L) continue;
				maxScore = Math.max(maxScore, s);
			}
		}
         
    }
     
 
}