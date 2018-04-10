import java.util.*;

/**
 * Created by Nano.son on 2018. 4. 6.
 */
public class Problem1781 {
    private static List<Task> tList = new ArrayList<>();
    private static int[] forest;
    private static int N;
    private static int cnt=0;
    PriorityQueue p;

    public static void main(String[] args) {
        init();
        tList.sort((t1, t2)->t2.cup-t1.cup);
        tList.stream().forEach(e->{
            int pos = e.deadLine;
            for(; forest[pos] != pos; pos = forest[pos]);
            if (pos > 0) {
                cnt+=e.cup;
                unionTree(pos - 1, pos);
            }
        });

        System.out.println(cnt);
    }

    public static void init() {
        Scanner in = new Scanner(System.in);
        int cup, deadLine;
        N = in.nextInt();
        forest = new int[N+1];
        forest[0] = 0;
        for (int i = 1; i <= N; ++i) {
            deadLine = in.nextInt();
            cup = in.nextInt();
            Task t = new Task(i);
            t.cup = cup;
            t.deadLine = deadLine;
            tList.add(t);
            forest[i] = i;
        }
    }

    //r1에 들어가야할 위치가 있다.
    public static void unionTree(int r1, int r2) {
        int newRoot = r1;
        for(;forest[newRoot]!=newRoot; newRoot = forest[newRoot]);
        forest[r2] = newRoot;
    }
}

class Task {
    int cup;
    int deadLine;
    int number;

    public Task(int number) {
        this.number = number;
    }
}

