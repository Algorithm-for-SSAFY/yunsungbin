import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N,M,K;
	static long[] tree;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 트리의 사이즈 
		M = Integer.parseInt(st.nextToken()); // 수의 변경 횟수 
		K = Integer.parseInt(st.nextToken()); // 구간의 곱 
		
		int treeHeight = 0;
		int len =  N;
		while(len != 0) {
			len /= 2;
			treeHeight++;
		}
		
		int treeSize = (int) Math.pow(2, treeHeight + 1 );
		int leftNodeStartIndex  = treeSize / 2 - 1;
		tree = new long[treeSize+1];
		Arrays.fill(tree, 1);
		
		for(int i = leftNodeStartIndex + 1 ; i <= leftNodeStartIndex + N; ++i) {
			tree[i] = Integer.parseInt(br.readLine());
		}
		
		setTree(treeSize - 1);
		
		for(int i = 0 ; i < M+K; ++i) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			long e = Long.parseLong(st.nextToken());
			
			if(order == 1) {
				changeVal(leftNodeStartIndex+s,e);
			}
			else if (order == 2) {
				s += leftNodeStartIndex;
				e += leftNodeStartIndex;
				System.out.println(findMul(s,(int)e) % 1000000007);
			}
			
		}
	}
	
	public static long findMul(int s, int e) {
		long result = 1;
		while(s <= e) {
			if( s % 2 == 1) {
				result *= tree[s];
				result %= 1000000007;
				s++;
			}
			if( e % 2 == 0) {
				result *= tree[e];
				result %= 1000000007;
				e--;
			}
			s /= 2;
			e /= 2;
		}
		return result;
	}
	
	public static void changeVal(int index, long val) {
	    tree[index] = val;
	    index /= 2;

	    while(index > 0) {
	        tree[index] = (tree[index * 2] * tree[index * 2 + 1]) % 1000000007;
	        index /= 2;
	    }
	}
	
	public static void setTree(int i) {
		while( i > 1 ) {
			tree[i/2] *= tree[i];
			tree[i/2] %= 1000000007;
			i--;
		}
	}
}
