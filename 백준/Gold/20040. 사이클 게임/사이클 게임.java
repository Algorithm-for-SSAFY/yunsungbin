import java.io.*;
import java.util.*;

public class Main {

    public static int[] parent;
    public static int[] rank;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int dotNum, orderNum;
        int answer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        dotNum = Integer.parseInt(st.nextToken());
        orderNum = Integer.parseInt(st.nextToken());

        parent=new int[dotNum];
        rank = new int[dotNum];

        // parent 초기화
        for (int x = 0; x < dotNum; x++) {
            parent[x] = x;
        }

        // 조건 입력 받는 중
        for (int x = 1; x <= orderNum; x++) {
            int one = 0, two = 0;

            st = new StringTokenizer(br.readLine());

            one = Integer.parseInt(st.nextToken());
            two = Integer.parseInt(st.nextToken());

            // 사이클 생성
            if(!union(one,two)){
                answer = x;
                break;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();

    }

    // 집합 생성
    public static boolean union(int one, int two){
        int oneParent = findParent(one);
        int twoParent = findParent(two);

        if(oneParent==twoParent)
            return false;

        // 랭크가 더 높은 것에 낮은거 추가해야 함.
        // oneParent가 더 랭크가 높은 경우 swap
        if(rank[oneParent]>rank[twoParent]){
            int temp = oneParent;
            oneParent = twoParent;
            twoParent = temp;
        }else if(rank[oneParent]==rank[twoParent]){
            // 랭크가 같은 경우 rank+1
            rank[twoParent]++;
        }
        
        parent[oneParent] = twoParent;
        
        return true;
    }

    // 부모 찾기
    public static int findParent(int x){
        if(parent[x]==x)
            return x;
        else
            return parent[x] = findParent(parent[x]);
    }
}