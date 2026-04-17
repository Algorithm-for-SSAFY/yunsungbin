import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N;
    public static int I;
    public static List<int[]> orders = new ArrayList<>();
    public static Map<Integer, Integer> D = new HashMap<>();
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 입력값을 빠르게 int 타입으로 바꾸기 위한 StringBuilder
        StringBuilder inputTypeString = new StringBuilder();
        
        // 정답을 만들기 위해 입력을 배열로 받는다.
        // 배열로 받아 sort() 를 사용하면 보다 쉽게 정답을 만들 수 있다.
        int[] inputTypeArray = new int[N];
        
        for(int i = 0; i < N; i++){
            inputTypeArray[i] = Integer.parseInt(st.nextToken());
            inputTypeString.append(inputTypeArray[i]);
        }
        int input = Integer.parseInt(inputTypeString.toString());
        
        // D 는 <노드 번호 : 비용> 으로 이루어진 Map 으로, 처음 입력값은 비용이 0 으로 저장해놓는다.
        D.put(input, 0);
        
        // 정답을 만들기 위해 sort() 를 사용한다. 이를 통해 오름차순을 간단하게 만들 수 있다.
        Arrays.sort(inputTypeArray);

        // 배열의 값을 int 로 바꾸기 위해 StringBuilder 를 사용한다.
        // 배열 -> String -> Integer
        for (int j : inputTypeArray) {
            sb.append(j);
        }
        int ans = Integer.parseInt(sb.toString());
        
        // 추후 StringBuilder 를 사용하기 위해 미리 StringBuilder 를 초기화해준다.
        sb.setLength(0);

        // 명령의 수
        I = Integer.parseInt(br.readLine());
        for(int i = 0; i < I; i++){
            st = new StringTokenizer(br.readLine());
            // 성능의 상승을 위해 기존 Vector 클래스에서 기본 int[] 으로 바꿔주었다.
            int[] order = new int[3];
            order[0] = Integer.parseInt(st.nextToken()) - 1;
            order[1] = Integer.parseInt(st.nextToken()) - 1;
            order[2] = Integer.parseInt(st.nextToken());
            // 각 명령들을 orders 에 넣어준다.
            orders.add(order);
        }

        // int[] 하나는 노드 하나를 의미한다.
        // 각 노드는 {노드 번호, 비용} 으로 구성된다.
        // 비용이 낮은 순으로 뽑아내기 위해 Comparator 를 조금 수정해준다.
        // 성능의 향상을 위해 Node 클래스를 만들지 않고 기본 int[] 으로 바꿔주었다.
        PriorityQueue<int[]> hq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        // PQ 에 시작 노드를 넣어주고 다익스트라를 시작한다.
        hq.add(new int[]{input, 0});
        while(!hq.isEmpty()){
            int[] start = hq.poll();
            // 만약 현재 노드가 정답이라면 바로 알고리즘을 종료해준다.
            if(start[0] == ans)
                break;

            // 명령을 실행해준다.
            for(int i = 0; i < I; i++){
                // 현재 노드와 명령을 입력해주면 다음 노드를 리턴해주는 메소드 action 을 사용한다.
                int[] next = action(start, orders.get(i));
                int nextNum = next[0];
                // 만약 기존에 다음 노드가 탐색된 적이 있고,
                // 기존에 저장되어있던 비용이 지금 계산한 비용보다 작다면 그냥 다음으로 넘어간다.
                // 비용을 리뉴얼할 필요가 없기 떄문이다.
                if(D.containsKey(nextNum) && D.get(nextNum) <= next[1]){
                    continue;
                }
                // 기존에 다음 노드가 탐색된 적이 없거나,
                // 있더라도, 지금 계산한 비용이 더 크면
                // 비용을 리뉴얼 해주어야 하므로 D와 PQ에 저장해준다.
                D.put(nextNum, next[1]);
                hq.add(next);
            }
        }

        // 만약 PQ가 Empty 가 될때까지 진행을 했는데도,
        // 정답노드까지 도달하지 못했다면 -1을,
        // 그것이 아니라면 D에 저장된 value 를 프린트해준다.
        System.out.println(D.getOrDefault(ans, -1));
    }

    private static int[] action(int[] start, int[] order) {
        // 현재 노드 번호
        int now = start[0];
        // 현재 노드까지의 비용
        int cost = start[1];

        // 노드 번호와 명령을 입력해주면 다음 노드 번호를 리턴해주는 메소드를 호출
        int next = swap(now, order);
        // 비용은 기존의 비용 + 지금 실행한 명령의 비용이 된다.
        cost += order[2];

        return new int[]{next, cost};
    }

    private static int swap(int now, int[] order) {
        // 용이한 명령의 수행을 위해 배열의 형태로 노드 번호를 바꿔준다.
        int[] a = new int[N];
        for(int i = N - 1; i >= 0; i--) {
            // 만약 10으로 나눠서 나머지가 0이 된다면,
            // 중간에 10이 존재한다는 의미로,
            // 100으로 나눈 나머지를 저장해주고, 100으로 나눠준 값을 다시 저장한다.
            if(now % 10 == 0){
                a[i] = now % 100;
                now /= 100;
            }
            // 그렇지 않다면 그냥 자릿수에 맞게 배열에 저장해준다.
            else{
                a[i] = now % 10;
                now /= 10;
            }
        }

        // swap 을 진행한다.
        int temp = a[order[0]];
        a[order[0]] = a[order[1]];
        a[order[1]] = temp;

        // 다시 배열을 int 타입으로 변환하기 위해
        // StringBuilder 를 사용해준다.
        for (int j : a) {
            sb.append(j);
        }
        int returnInteger = Integer.parseInt(sb.toString());
        // 다음에 다시 StringBuilder 를 사용하기 위해 sb 를 초기화해준다.
        sb.setLength(0);
        return returnInteger;
    }
}