import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static int N, M, A, B;
    static HashMap<Integer, List<Edge>> adjList;
    static int[] visit;
    public static void main(String[] args) throws Exception{
        init();
        long result = algorithm();
        System.out.println(result);
    }

    static long algorithm() {
        PriorityQueue<QNode> queue = new PriorityQueue<>(Comparator.comparingLong(QNode::getwSum));
        long ret = 0;

        queue.add(new QNode(0, A));
        while(queue.size()>0) {
            QNode cur = queue.poll();
            visit[cur.vertex] = 1;

            if(cur.vertex == B) {
                ret = cur.wSum;
                break;
            }

            if(adjList.containsKey(cur.vertex))
                for(Edge e : adjList.get(cur.vertex))
                    if(visit[e.to] == 0)
                        queue.add(new QNode(cur.wSum + e.weight, e.to));
        }

        return ret;
    }

    static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        visit = new int[N+1];
        adjList = new HashMap<>();

        int from, to, w;
        String[] temp;
        for (int i = 0; i < M ; i++) {
            temp = br.readLine().split(" ");
            from = Integer.parseInt(temp[0]);
            to = Integer.parseInt(temp[1]);
            w = Integer.parseInt(temp[2]);

            if(!adjList.containsKey(from))
                adjList.put(from, new ArrayList<>(Arrays.asList(new Edge(to, w))));
            else
                adjList.get(from).add(new Edge(to, w));
        }
        temp = br.readLine().split(" ");
        A = Integer.parseInt(temp[0]);
        B = Integer.parseInt(temp[1]);
    }
}

class Edge {
    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

class QNode {
    long wSum;
    int vertex;

    public QNode(long wSum, int vertex) {
        this.wSum = wSum;
        this.vertex = vertex;
    }

    public long getwSum() {
        return wSum;
    }
}

