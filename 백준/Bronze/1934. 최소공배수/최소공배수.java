import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int GCD(int n1, int n2) {
		int max = Math.max(n1, n2);
		int min = Math.min(n1, n2);
		while(max % min != 0) {
			int tmp = max % min;
			max = min;
			min = tmp;
		}
		return min;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t = 0 ; t < T; ++t) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			System.out.println(n1*n2/GCD(n1,n2));
		}
	}

}
