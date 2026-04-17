import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] odds = new int[N+1], evens = new int[N+1];
        for (int i = 1; i <= N; i++) {
            int j = 0;
            while (i-j > 0 && i+j <= N) {
                if (nums[i-j] == nums[i+j]) odds[i] = 1 + 2*j;
                else break;
                j++;
            }
        }

        for (int i = 1; i <= N; i++) {
            int j = 0;
            while (i-j > 0 && i+j+1 <= N) {
                if (nums[i-j] == nums[i+j+1]) evens[i] = 2 + 2*j;
                else break;
                j++;
            }
        }


        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int l = (E-S)+1;
            if (l % 2 == 1) {
                sb.append(odds[(E+S)/2] >= l? 1+"\n": 0+"\n");
            } else {
                sb.append(evens[(E+S)/2] >= l? 1+"\n": 0+"\n");
            }
        }
        System.out.println(sb);
    }

}