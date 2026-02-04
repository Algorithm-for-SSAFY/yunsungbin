import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static long GCD(long n1, long n2) {
		long max = Math.max(n1, n2);
		long min = Math.min(n1, n2);
		while(max % min != 0) {
			long tmp = max % min;
			max = min;
			min = tmp;
		}
		return min;
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long n1 = Long.parseLong(st.nextToken());
		long n2 = Long.parseLong(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for(long i = 0 ; i < GCD(n1,n2); ++i) {
			sb.append(1);
		}
		System.out.println(sb.toString());

	}

}
