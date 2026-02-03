import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node1233{
	String val;
	
	int left;
	int right;
	
	public Node1233(String val) {
		this.val = val;
		left = -1;
		right = -1;
	}
	
}

public class Solution {
	static Node1233[] nodes ; 
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int t = 1; t<=10;++t) {
			sb.append("#").append(t).append(" ");
			int N = Integer.parseInt(in.readLine());
			
			nodes = new Node1233[N+1]; 
			
			for(int i = 1; i <= N; ++i) {
				String[] line = in.readLine().split(" ");
				nodes[i] = new Node1233(line[1]);
				
				if(line.length > 2) nodes[i].left = Integer.parseInt(line[2]);
				if(line.length > 3) nodes[i].right = Integer.parseInt(line[3]);
			}
			
			sb.append(DFS(1) ? 1 : 0).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static boolean DFS(int cur) {
		if(cur < 0) return true;
		Node1233 n = nodes[cur];
		if(isOper(n.val) && (n.left < 0 || n.right < 0)) return false;
		if(!isOper(n.val) && (n.left > 0 || n.right > 0)) return false;
		
		return DFS(n.left) & DFS(n.right);
	}
	
	static boolean isOper(String val) {
		if(val.equals("+") || val.equals("-") || val.equals("*") || val.equals("/")) return true;
		return false;
	}
}
