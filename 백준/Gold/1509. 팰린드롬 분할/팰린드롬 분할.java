import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static boolean[][] P;
    public static int[] F;
    public static String input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();

        // P[i][j] 는 i 번째 문자부터 j 번째 문자까지의 문자열이 팰린드롬인지 아닌지를 알 수 있는 배열이다.
        P = new boolean[input.length() + 1][input.length() + 1];
        // F[i] 는 i 번째 문자까지의 최소 팰린드롬 분할의 수이다.
        // 그렇다면 우리가 최종적으로 원하는 정답은 F[input.length() - 1] 이다.
        F = new int[input.length() + 1];

        // 길이가 1인 문자는 무조건 팰린드롬이다.
        for(int i=1; i<=input.length(); i++){
            P[i][i] = true;
        }

        // 문자열 길이가 0 인 문자열의 최소 팰린드롬 분할의 개수는 0 이다.
        F[0] = 0;
        // 처음 시작의 최소 팰린드롬 분할의 개수는 1이므로, 1로 초기화해준다.
        F[1] = 1;
        for(int i=2; i<=input.length(); i++){
            // 우선 최소 팰린드롬 분할의 개수는 이전 팰린드롬 분할 개수 + 1 이다.
            // 만약 이보다 작은 수가 나올 수 있다면 바꿔준다.
            int min = F[i-1] + 1;

            for(int j=1; j<i; j++){
                if(input.charAt(j - 1) == input.charAt(i - 1)){
                    // 같은 문자 사이에 팰린드롬이 아에 없거나, 팰린드롬이 존재한다면,
                    if(j + 1 == i || P[j + 1][i - 1]){
                        // 전체를 팰린드롬으로 설정하고
                        P[j][i] = true;
                        // 만들어지는 팰린드롬 분할의 수가 min 보다 작으면 초기화 해준다.
                        min = Math.min(min, F[j - 1] + 1);
                    }
                }
            }
            // min 값은 F 에 저장한다.
            F[i] = min;
        }
        System.out.println(F[input.length()]);
    }
}