import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static int U, D, F, G, S;
    static int MAX = 1000001;
    static int[] visit = new int[MAX];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        F = in.nextInt();
        S = in.nextInt();
        G = in.nextInt();
        U = in.nextInt();
        D = in.nextInt();

        int result = bfs();
        System.out.println(result>=0 ? result : "use the stairs");
    }

    public static int bfs() {
        int ret = -1;
        QNode cur;

        LinkedList<QNode> queue = new LinkedList<>();
        queue.add(new QNode(S, 0));
        visit[S] = 1;

        while(queue.size() > 0) {
            cur = queue.pop();
            if(cur.floor == G) {
                ret = cur.cnt;
                break;
            }

            int nextUp = cur.floor + U;
            int nextDown = cur.floor - D;
            if(nextUp <= F && visit[nextUp] == 0) {
                queue.add(new QNode(nextUp, cur.cnt+1));
                visit[nextUp] = 1;
            }
            if(nextDown > 0 && visit[nextDown] == 0) {
                queue.add(new QNode(nextDown, cur.cnt+1));
                visit[nextDown] = 1;
            }
        }

        return ret;
    }
}

class QNode {
    int floor;
    int cnt;

    public QNode(int floor, int cnt) {
        this.floor = floor;
        this.cnt = cnt;
    }
}
