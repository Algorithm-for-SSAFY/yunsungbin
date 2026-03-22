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
		
		int treeHeight = 0;
		int len =  N;
		
		while( len != 0) {
			len /= 2;
			treeHeight++;
		}
		
		int treeSize = (int) Math.pow(2, treeHeight + 1 ); // 어차피 2배 할거니까 + 1
		int leftNodeStartIndex = treeSize / 2 - 1;
		tree = new long[treeSize+1];
		Arrays.fill(tree, (long)1e9);
		
		for(int i = leftNodeStartIndex + 1; i <= leftNodeStartIndex + N; ++i) {
			tree[i] = Integer.parseInt(br.readLine());
		}
		
		
		setTree(treeSize - 1);
		
		for(int i = 0 ; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken()); 
			int e = Integer.parseInt(st.nextToken());
			
			System.out.println(findMin(s+leftNodeStartIndex,e+leftNodeStartIndex));
		}
	}
	
	public static void setTree(int i) {
		while(i > 1) {
			tree[i/2] = Math.min(tree[i/2], tree[i]);
			i--;
		}
	}
	
	public static long findMin(int s, int e) {
		long minVal = (long)1e9;
		
		// TODO : 세그먼트 트리에서 최솟값을 찾으세
		while( s <= e) {
			
			if(s % 2 == 1) {
				minVal = Math.min(minVal, tree[s]);
				s++;
			}
			if( e % 2 == 0) {
				minVal = Math.min(minVal, tree[e]);
				e--;
			}
			
			s /= 2;
			e /= 2;
		}
		
		
		return minVal;
	}
}
