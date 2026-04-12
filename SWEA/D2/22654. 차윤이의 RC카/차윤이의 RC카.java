import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Solution {
	static int H, W;
	static int N;

	static char maps[][];
	static String orders[];
	
	static int rx, ry, rd;
	static int ix, iy, id;
	static int tx, ty;
	
	static int di[] = {-1,0,1,0};
	static int dj[] = {0,1,0,-1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= TC; ++t) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());

			H = Integer.parseInt(st.nextToken());
			maps = new char[H][H];
			
			for(int i = 0 ; i < H; ++i) {
				String line = br.readLine();
				for (int j = 0; j < H; j++) {
					char ch = line.charAt(j);
					maps[i][j] = ch;
					if("X".contains(""+ch)) {
						ry = i;
						rx = j;
						rd = 0;
					}
					if("Y".contains(""+ch)) {
						ty = i;
						tx = j;
					}
					
				}
			}
			

			N = Integer.parseInt(br.readLine());
			
			for(int n = 0 ; n < N; ++n) {
				st = new StringTokenizer(br.readLine());
				int orderSize = Integer.parseInt(st.nextToken());
				orders = st.nextToken().split("");
				ix = rx; iy = ry; id = rd;
				
				for(int i = 0 ; i < orders.length; ++i) {
					String order = orders[i];
					
					if("A".contains(order)) {
						int ny = iy + di[id];
						int nx = ix + dj[id];
						if(ny < 0 || nx < 0 || ny >= H || nx >= H || maps[ny][nx] == 'T') continue;
						ix = nx;
						iy = ny;
					}else if("R".contains(order)){
						id = (id+1) % 4;
					} else if ("L".contains(order)) {
						id = (id - 1) < 0 ? 3 : id-1;
					}
				}
				if(ix == tx && iy == ty) sb.append("1").append(" ");
				else sb.append("0").append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);

	}
	


	
}
