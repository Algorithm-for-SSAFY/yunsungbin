import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M,K;
	static long[] tree;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 트리의 사이즈 
		M = Integer.parseInt(st.nextToken()); // 수의 변경 횟수 
		K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수 
		
		int treeHeight = 0;
		int len = N;
		while(len != 0) {
			len /= 2;
			treeHeight++;
		}
		
		int treeSize = (int) Math.pow(2, treeHeight+1);
		int leftNodeStartIndex = treeSize / 2 - 1;
		tree = new long[treeSize+1]; // 숫자 그대로 쓰기 위해.
		
		for(int i = leftNodeStartIndex + 1; i <= leftNodeStartIndex + N; ++i) {
			tree[i] = Long.parseLong(br.readLine());
		} // 중간부터 왼쪽 끝까지 기본 값을 채워놓기 
		
		setTree(treeSize - 1); // why minus 1?
		
		for(int i = 0 ; i < M+K; ++i) {
			st = new StringTokenizer(br.readLine());
			long a = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			long e = Long.parseLong(st.nextToken());
			if(a == 1) { // Value Change
				chageVal(leftNodeStartIndex + s, e);
			} else if ( a == 2 ) { // Sum
				s += leftNodeStartIndex;
				e += leftNodeStartIndex;
				System.out.println(getSum(s, (int) e));
			} else {
				return;
			}
		}
		
	}
	
	static void setTree(int i) {
		while(i != 1) {
			tree[i/2] += tree[i];
			i--;
		}
	}
	
	static void chageVal(int idx, long val) {
		long diff = val - tree[idx];
		while(idx > 0) {
			tree[idx] += diff;
			idx /= 2;
		}
	}
	
	static long getSum(int s, int e) {
		long partSum = 0;
		while( s <= e ) {
			if(s % 2 == 1) {
				partSum += tree[s];
				s++;
			}
			if(e % 2 == 0) {
				partSum += tree[e];
				e--;
			}
			s /= 2;
			e /= 2;
			
		}
		return partSum;
	}
	
}
