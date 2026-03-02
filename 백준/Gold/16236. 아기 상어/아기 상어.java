import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 크기가 같으면 위 ->좌 순으로 먹는다
 * 크기가 작은 물고기는 먹기가 가능하고,크기가 같은 물고기는 지나갈 수 있다 
 * 가장 가까운 물고기를 먹으러간다. 
 */
public class Main {
	static int N,M, ans;
	static int maps[][];
	
	static int di[] = {-1,0,0,1};
	static int dj[] = {0,-1,1,0};
	
	static int bi, bj; // 아기 상어 위치 
	static int bSize = 2; // 아기 상어 크기
	static int eat = 0; // 현재 아기상어가 먹은 먹이 수

	static class Point{
		int x,y,d;
		Point(int y, int x,int d){
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		ans = 0;
		maps = new int[N][N];
		for(int i = 0 ; i < N ;++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N; ++j) {
				int v = Integer.parseInt(st.nextToken());
				maps[i][j] = v;
				if(v == 9) {
					bi = i;
					bj = j;
					maps[i][j] = 0;
				}
			}
		}
		
		solve(new Point(bi,bj,0));
		System.out.println(ans);
	}
	
	static void solve(Point start) {
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		
		q.offer(start);
		visited[start.y][start.x] = true;
		
		while(!q.isEmpty()) {
			visited = new boolean[N][N];
			boolean eatFlag = false;
			int bestY= 1000, bestX = 1000;
			int bestLen = 1000;
			while(!q.isEmpty()) {
				Point curPoint = q.poll();
				visited[curPoint.y][curPoint.x] = true;
				int cy = curPoint.y;
				int cx = curPoint.x;
				for(int k = 0 ; k < 4; ++k) {
					int ny = cy + di[k];
					int nx = cx + dj[k];
					if(bestLen < curPoint.d+1) continue;
					if(nx < 0 || ny < 0 
							|| nx >= N || ny >= N 
							|| visited[ny][nx] 
							|| maps[ny][nx] > bSize) continue;
//					System.out.println(ny + ", " + nx + "로 이동 ");
					visited[ny][nx] = true;
					q.offer(new Point(ny,nx,curPoint.d+1));
					if(maps[ny][nx] > 0 && maps[ny][nx] < bSize) {
						if(bestY > ny) {
							bestY = ny;
							bestX = nx;
							bestLen = curPoint.d+1;
						} else if ( bestY == ny && bestX > nx) {
							bestY = ny;
							bestX = nx;
							bestLen = curPoint.d+1;
						}
						eatFlag = true; // 먹을준비 오케이 
					}
				}
				
			}

			if(eatFlag) {
				eat++;
				if(eat >= bSize) {
					eat = 0;
					bSize++;
				}
				q.clear();
				q.offer(new Point(bestY,bestX,0));
				maps[bestY][bestX] = 0;
				ans += bestLen;
				bi = bestY;
				bj = bestX;
			}
			
		}
		
	}
	

}
