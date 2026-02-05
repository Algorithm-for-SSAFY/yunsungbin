import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		ArrayList<Integer> edges;
		char type;
	}
	static int V, E;
	static ArrayList<Node> nodes;
	static ArrayList<Integer> ans[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		

		StringTokenizer st;
		for(int t = 0 ; t < T; ++t) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			nodes = new ArrayList<>();
			nodes.add(null); // 0번 소모
			
			for(int i = 1; i <= V; ++i) {
				nodes.add(new Node());
				nodes.get(i).edges = new ArrayList<>();
			}
			
			for(int i = 0 ; i < E; ++i) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				
				nodes.get(n1).edges.add(n2);
				nodes.get(n2).edges.add(n1);
			}
			int i = 0;
			boolean[] visited = new boolean[V+1];
			for(i = 1 ; i <= V; i++) {
				if(!solve(i,visited)){
					break;
				}
			}
			if(i > V) System.out.println("YES");
			else System.out.println("NO");
		}
		

	}
	

	/*
	 * 임의의 정점을 시작으로, A 칠하기, 인접 노드는 B 칠하기 반복
	 * 만약, visited된 노드라면, 내 자신의 색과 visited된 노드의 색을 비교.
	 */
	public static boolean solve(int start, boolean[] visited) {
		if(visited[start]) return true;
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		visited[start] = true;
		nodes.get(start).type = 'A';
		while(!q.isEmpty()) {
			int curIdx = q.poll();
			Node cur = nodes.get(curIdx);
			for(int n : cur.edges) {
				Node subNode = nodes.get(n);
				if(!visited[n]) {
					subNode.type = cur.type == 'A' ? 'B' : 'A';

					q.offer(n);
					visited[n] = true;
				}else {
					if(subNode.type == cur.type) {
						return false;
					}
				}
			}
		}

		return true;
	}

}
