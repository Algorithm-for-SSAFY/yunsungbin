import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int K;
    static int[] numList;
    static int[] disjointSet = new int[4000001];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());
        K = Integer.parseInt(st1.nextToken());

        numList = new int[M];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            numList[i] = Integer.parseInt(st2.nextToken());
        }
        // 정렬
        Arrays.sort(numList);

        StringTokenizer st3 = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++){
            // 이진 탐색을 통해 주어진 수보다 큰 수들 중 가장 작은 수를 찾는다.
            int ans = findUpperThan(Integer.parseInt(st3.nextToken()));
            System.out.println(find(ans)); // 찾은 수의 id 값을 출력한다.
            // 이때 찾은 id 의 값이 민수가 가지고 있는 수들 중 가장 큰 수 였다면,
            // 그 다음 수를 union 해줄 수 없기 때문에, 그 경우가 아닌 경우에 대해서만 union 을 진행해준다.
            // 또한, 문제에서 민수가 카드를 내지 못하는 상황은 없다고 가정했으므로 문제가 되는 상황은 만들어지지 않는다.
            if(find(ans) != numList[M - 1])
                // 찾은 수와 그 다음 수를 union 해준다.
                // 그 다음 수는 엄밀히 정의하면 찾은 수의 id 값보다 큰 수들 중 가장 작은 수를 의미한다.
                // 따라서 findUpperThan 메서드를 다시 한 번 더 이용한다.
                union(find(ans), findUpperThan(find(ans)));
        }
    }

    private static int findUpperThan(int a) {
        int left = 0;
        int right = M - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(numList[mid] == a){
                // 해당 수를 찾았다면 그 다음 수를 return 해준다.
                return numList[mid + 1];
            }
            else if(numList[mid] > a){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        // 해당 수를 못 찾았다면, left 가 그 다음 수를 가르킨다.
        // 따라서 left 가 가르키는 수를 return 해준다.
        return numList[left];
    }

    public static int find(int a){
        if(disjointSet[a] == 0)
            return a;
        else{
            int id = find(disjointSet[a]);
            disjointSet[a] = id;
            return id;
        }
    }

    public static void union(int a, int b){
        if(find(a) == find(b))
            return;
        else{
            disjointSet[find(a)] = find(b);
            return;
        }
    }
}