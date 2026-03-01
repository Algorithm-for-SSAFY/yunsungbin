import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N,M, ans;
	static int maps[][];
	
	static ArrayList<Rect> rects = new ArrayList<>();
	
	static class Rect{
		int[] di;
		int[] dj;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = 0;
		maps = new int[N][M];
		
		for(int i = 0 ; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; ++j) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeRects();
		solve();
		System.out.println(ans);
		
		
	}
	
	public static void solve() {
		for(int i = 0 ; i < N; ++i) {
			for(int j = 0 ; j < M; ++j) {
				for(Rect r : rects) {
					int ci = i; 
					int cj = j;
					boolean check = true;
					int sum = maps[i][j];
					for(int d = 0 ; d < 3; ++d) {
						ci += r.di[d];
						cj += r.dj[d];
						if(ci < 0 || cj < 0 || ci >= N || cj >= M) {
							check = false;
							break;
						}
						sum += maps[ci][cj];
					}
					if(check) {
						ans = Math.max(ans, sum);
					}
				}
			}
		}
	}
	
	public static void makeRects() {
		Rect rect = new Rect(); // l
		rect.di = new int[] {0,0,0};
		rect.dj = new int[] {1,1,1};
		rects.add(rect);	
		
		rect = new Rect(); // ㅡ 
		rect.di = new int[] {1,1,1};
		rect.dj = new int[] {0,0,0};
		rects.add(rect);
		
		
		rect = new Rect(); // ㅁ 
		rect.di = new int[] {0,1,0};
		rect.dj = new int[] {1,0,-1};
		rects.add(rect);
		
		rect = new Rect();// ㅜ 
		rect.di = new int[] {0,1,-1};
		rect.dj = new int[] {1,0,1};
		rects.add(rect);
		
		rect = new Rect(); // ㅏ 
		rect.di = new int[] {1,1,-1};
		rect.dj = new int[] {0,0,1};
		rects.add(rect);
		
		rect = new Rect(); // ㅗ 
		rect.di = new int[] {0,-1,1};
		rect.dj = new int[] {1,0,1};
		rects.add(rect);
		
		rect = new Rect(); // ㅓ 
		rect.di = new int[] {-1,1,1};
		rect.dj = new int[] {1,0,0};
		rects.add(rect);
		
		rect = new Rect(); // ㄴㄱ 
		rect.di = new int[] {1,0,1};
		rect.dj = new int[] {0,1,0};
		rects.add(rect);
		
		rect = new Rect(); // ㅢ 
		rect.di = new int[] {0,-1,0};
		rect.dj = new int[] {1,0,1};
		rects.add(rect);

		rect = new Rect(); // ㄴ 
		rect.di = new int[] {1,1,0};
		rect.dj = new int[] {0,0,1};
		rects.add(rect);

		rect = new Rect(); // ㅢ 
		rect.di = new int[] {0,0,-1};
		rect.dj = new int[] {1,1,0};
		rects.add(rect);

		rect = new Rect(); // ㄱ 
		rect.di = new int[] {0,1,1};
		rect.dj = new int[] {1,0,0};
		rects.add(rect);

		rect = new Rect();// ㅣ- 
		rect.di = new int[] {1,-1,0};
		rect.dj = new int[] {0,1,1};
		rects.add(rect);
		
		// 대칭들
		rect = new Rect();
		rect.di = new int[] {0,-1,-1};
		rect.dj = new int[] {1,0,0};
		rects.add(rect);
		
		
		rect = new Rect();
		rect.di = new int[] {0,1,1};
		rect.dj = new int[] {1,-1,0};
		rects.add(rect);

		rect = new Rect();
		rect.di = new int[] {0,0,1};
		rect.dj = new int[] {1,1,0};
		rects.add(rect);

		rect = new Rect();
		rect.di = new int[] {1,0,0};
		rect.dj = new int[] {0,1,1};
		rects.add(rect);

		rect = new Rect();
		rect.di = new int[] {-1,0,-1};
		rect.dj = new int[] {0,1,0};
		rects.add(rect);

		rect = new Rect();
		rect.di = new int[] {0,1,0};
		rect.dj = new int[] {1,0,1};
		rects.add(rect);
	}
}
