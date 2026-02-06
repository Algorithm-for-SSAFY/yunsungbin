import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {

	static int N,M;
	static ArrayList<Ingredient> ins;
	static int cnt;

	static class Ingredient{
		ArrayList<Integer> worst;
		public Ingredient() {
			this.worst = new ArrayList<>();
		}
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= TC; ++t) {
            sb.append("#").append(t).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            ins = new ArrayList<>(); // 재료들
            cnt = 0;
            
            ins.add(null); // 0번 소모
            for(int i = 1 ; i <= N; ++i) {
            	ins.add(new Ingredient());
            }
            
            for(int i = 0 ; i < M; ++i) {
            	st = new StringTokenizer(br.readLine());
                int i1 = Integer.parseInt(st.nextToken());
                int i2 = Integer.parseInt(st.nextToken());
                ins.get(i1).worst.add(i2);
                ins.get(i2).worst.add(i1);
            }
            
            solve(0,0);
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
	}
	/*
	 * 조합 재귀 ( 순서 상관 없으니까
	 * idx = 0 ~
	 */
	public static void solve(int idx, int val) {
		if(!check(val)) return; // 상극이 감지되면 컷
		if(idx >= N) {
			cnt++;
			return;
		}
		
		solve(idx+1, (int) (val + Math.pow(2, idx))); // idx번째를  골랐을 때
		solve(idx+1, val); // idx번째를  골랐을 때
	}
	
	public static boolean check(int val) { // val은.. 101 011등 현재 선택한 재료 000~111 
		for(int i = 0; i < N; ++i) {
			if(( val & (1 << i)) != 0) { // 만약 i가 val에 있다면
				for(int w : ins.get(i+1).worst) { // i의 worst를 긁어와서.
					if((val & (1 << (w-1))) != 0) // w번쨰 비트를 검사 ( 비트는 0부터 시작이러 w-1 )
						return false;
				}
			}
		}
		return true;
	}
	

}
