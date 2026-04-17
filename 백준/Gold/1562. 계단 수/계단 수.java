import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int mod = 1000000000;
        long ans = 0;
        int[][][] dp = new int[101][10][1<<10];
        for(int i=1; i<10; i++)
        {
            dp[1][i][1<<i] = 1;
        }
        for(int i=2; i<=n; i++)
        {
            for(int j=0; j<10; j++)
            {
                for(int k=0; k<(1<<10); k++)
                {
                    if(j==0) dp[i][j][k | 1<<j] = (dp[i][j][k | 1<<j] + dp[i-1][j+1][k]) % mod;
                    else if(j==9) dp[i][j][k | 1<<j] = (dp[i][j][k | 1<<j] + dp[i-1][j-1][k]) % mod;
                    else dp[i][j][k | 1<<j] = (dp[i][j][k | 1<<j] + dp[i-1][j-1][k] + dp[i-1][j+1][k]) % mod;
                }
            }
        }
        for(int i=0; i<10; i++)
        {
            ans = (ans + dp[n][i][(1<<10)-1]) % mod;
        }
        System.out.println(ans);
    }
}