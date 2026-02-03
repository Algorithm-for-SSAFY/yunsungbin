import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {
	static List<Long> nums = new ArrayList<>();
	static long N;
	static void findPn(long n) {
		long tmp = n;
		for(long i = 2; i*i <= n; ++i) {
			if(n % i == 0) {
				nums.add(i);
				while(n%i==0) n /= i;
			}
		}
		if(n>1) nums.add(n);
	}
	
	static boolean isCoprime(long c) {
		
		for(long n : nums) {
			if(c%n == 0) return false;
		}
		return true;
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Long.parseLong(br.readLine());
		findPn(N);
		int cnt = 0;
		long res = N;
//		for(int k = 1; k < Math.sqrt(N); ++k) {
//			if(isCoprime(k)) {
//				cnt++;
//			}
//		}
		for(long n : nums) {
			res -= res/n;
		}
		System.out.println(res);
		
	}
}// 10 = 2 x 5
// 1 , 3, 7,9
