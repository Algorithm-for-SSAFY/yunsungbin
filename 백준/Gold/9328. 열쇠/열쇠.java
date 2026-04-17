// BOJ - 열쇠(9328번)
// BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int n, m;
	public static char[][] map;
	public static int max_cnt;
	public static boolean[][] visited;
	public static boolean[] key;
	public static ArrayList<Node>[] door;
	public static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=TC;test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			max_cnt = 0;
			map = new char[n+2][m+2];
			door = new ArrayList[26];
			key = new boolean[26];
			visited = new boolean[n+2][m+2];
			for(int i=0;i<26;i++) {
				door[i] = new ArrayList<Node>();
			}
			
			
			for(int i=0;i<n;i++) {
				String[] data = br.readLine().split("");
				for(int j=0;j<m;j++) {
					map[i+1][j+1] = data[j].charAt(0);
				}
			}
			
			
			for(int i=0;i<n+2;i++) {
				map[i][0] = '.';
				map[i][m+1] = '.';
			}
			
			for(int i=0;i<m+2;i++) {
				map[0][i] = '.';
				map[n+1][i] = '.';
			}
			
			String[] str = br.readLine().split("");
			for(int i=0;i<str.length;i++) {
				if(str[i].charAt(0) == '0') break;
				key[str[i].charAt(0) - 'a'] = true;
			}
			bfs();
			sb.append(max_cnt+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0));
		visited[0][0] = true;
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int d=0;d<4;d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				if(nx < 0 || nx >= (n+2) || ny < 0 || ny >= (m+2) || visited[nx][ny]) continue;
				if(map[nx][ny] == '*') continue;
				if(map[nx][ny] - 'A' >= 0 && map[nx][ny] - 'A' <= 25) {
					if(key[map[nx][ny]-'A']) {
						visited[nx][ny] = true;
						map[nx][ny] = '.';
						q.add(new Node(nx, ny));
					} else {
						door[map[nx][ny]-'A'].add(new Node(nx, ny));
					}
				} else if(map[nx][ny] -'a' >= 0 && map[nx][ny] - 'a' <= 25) {
					int alph = map[nx][ny]-'a';
					key[map[nx][ny]-'a'] = true;
					map[nx][ny] = '.';
					visited[nx][ny] = true;
					q.add(new Node(nx, ny));

					for(Node d_node:door[alph]) {
						visited[d_node.x][d_node.y] = true;
						q.add(new Node(d_node.x, d_node.y));
					}
				} else if(map[nx][ny] == '$') {
					map[nx][ny] = '.';
					max_cnt++;
					visited[nx][ny] = true;
					q.add(new Node(nx, ny));
				} else if(map[nx][ny] == '.'){
					visited[nx][ny] = true;
					q.add(new Node(nx, ny));
				}
			}
		}
	}

}