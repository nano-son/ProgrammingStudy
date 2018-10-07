import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static int N, M, X;
    static int[][] map;
    static int[] visit;
    static int[] minTimeFromX;
    public static void main(String[] args) throws Exception {
        init();
        int result = 0;
        fromX();
        for (int i = 1; i <= N ; i++) {
            result = Math.max(result, algorithm(i) + minTimeFromX[i]);
        }
        System.out.println(result);
    }

    public static int algorithm(int student) {
        int result = 0;
        PriorityQueue<QNode> queue = new PriorityQueue<>(Comparator.comparingInt(QNode::gettSum));
        Arrays.fill(visit, 0);
        queue.add(new QNode(student, 0));

        while(queue.size()>0) {
            QNode cur = queue.poll();
            if(visit[cur.vertex] != 0)
                continue;
            visit[cur.vertex] = 1;

            if(cur.vertex == X) {
                result = cur.tSum;
                break;
            }

            for (int i = 1; i <= N; i++) {
                if(map[cur.vertex][i] > 0 && visit[i]==0) {
                    queue.add(new QNode(i, cur.tSum + map[cur.vertex][i]));
                }
            }
        }

        return result;
    }

    public static void fromX() {
        PriorityQueue<QNode> queue = new PriorityQueue<>(Comparator.comparingInt(QNode::gettSum));
        Arrays.fill(visit, 0);
        queue.add(new QNode(X, 0));

        while(queue.size()>0) {
            QNode cur = queue.poll();
            if(visit[cur.vertex] != 0)
                continue;
            visit[cur.vertex] = 1;
            minTimeFromX[cur.vertex] = cur.tSum;

            for (int i = 1; i <= N; i++) {
                if(map[cur.vertex][i] > 0 && visit[i]==0) {
                    queue.add(new QNode(i, cur.tSum + map[cur.vertex][i]));
                }
            }
        }
    }

    public static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        X = Integer.parseInt(temp[2]);

        visit = new int[N+1];
        map = new int[N+1][N+1];
        minTimeFromX = new int[N+1];

        int from, to, time;
        for (int i = 0; i < M ; i++) {
            temp = br.readLine().split(" ");
            from = Integer.parseInt(temp[0]);
            to = Integer.parseInt(temp[1]);
            time = Integer.parseInt(temp[2]);
            map[from][to] = time;
        }
    }


}

class QNode {
    int vertex;
    int tSum;

    public QNode(int vertex, int tSum) {
        this.tSum = tSum;
        this.vertex = vertex;
    }

    public int gettSum() {
        return tSum;
    }
}

