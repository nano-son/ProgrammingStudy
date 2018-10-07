import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M, X;
    static int[][] map;
    static int[][] d;
    static int INF = 987654321;

    public static void main(String[] args) throws Exception {
        init();
        int result = 0;

        floyd();
        for (int i = 1; i <= N ; i++) {
            if(d[i][X] + d[X][i] < INF)
                result = Math.max(result, d[i][X] + d[X][i]);
        }
        System.out.println(result);
    }

    public static void floyd() {
        /**
         * d[i][j][k] = i에서 j로 가는데 k까지의 vertex 거쳐서 가는 최단 거리
         * d[i][j][k] = min(d[i][k][k-1] + d[k][j][k-1], d[i][j][k-1])
         */
        for (int i = 1; i <= N ; i++) {
            Arrays.fill(d[i], INF);
            for (int j = 1; j <= N ; j++) if(map[i][j] > 0)
                    d[i][j] = map[i][j];
        }


        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
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

        map = new int[N+1][N+1];
        d = new int[N+1][N+1];

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
