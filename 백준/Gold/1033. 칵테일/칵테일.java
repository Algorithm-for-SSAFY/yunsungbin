import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static class Node{
		int e;
		long n1, n2;
		
		public Node(int e, long n1, long n2) {
			super();
			this.e = e;
			this.n1 = n1;
			this.n2 = n2;
		}
		
	}
	
	static ArrayList<Node>[] nodes;
	static boolean[] visited;
	static long[] D;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		visited = new boolean[N];
		nodes = new ArrayList[N];
		D = new long[N];
		for(int i = 0 ; i < N; ++i)
			nodes[i] = new ArrayList<>();
		
		long lcm = 1;
		
		for(long i = 0 ; i < N - 1; ++i) {
			st = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			long v1 = Integer.parseInt(st.nextToken());
			long v2 = Integer.parseInt(st.nextToken());
			
			nodes[n1].add(new Node(n2, v1, v2));
			nodes[n2].add(new Node(n1, v2, v1));
			lcm *= (v1*v2 / gcd(v1,v2));
		}
		D[0] = lcm;
		DFS(0);
		long mgcd = lcm;
		for(int i = 1; i < N; ++i) {
			mgcd = gcd(mgcd, D[i]);
		}
		
		for( int i = 0 ; i < N; ++i) {
			System.out.print(D[i] / mgcd + " ");
		}

	}
	
	static long gcd(long v1, long v2) {
		if (v2 == 0)
			return v1;
		else
			return gcd(v2, v1 % v2);
	}
	static void DFS(int cur) {
		visited[cur] = true;
		
		for(Node n : nodes[cur]) {
			if(!visited[n.e]) {
				D[n.e] = D[cur] * n.n2 / n.n1; 
				DFS(n.e);
			}
		}
		
	}
}
