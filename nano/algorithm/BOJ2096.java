import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] swnd_min = new int[2][3];
    static int[][] swnd_max = new int[2][3];
    static int[] input = new int[3];
    static int COL = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i <n ; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < temp.length; j++) {
                input[j] = Integer.parseInt(temp[j]);
                swnd_min[0][j] = swnd_min[1][j];
                swnd_max[0][j] = swnd_max[1][j];
            }

            swnd_min[1][0] = min(swnd_min[0][0], swnd_min[0][1]) + input[0];
            swnd_min[1][1] = min(swnd_min[0][0], min(swnd_min[0][1], swnd_min[0][2])) + input[1];
            swnd_min[1][2] = min(swnd_min[0][1], swnd_min[0][2]) + input[2];

            swnd_max[1][0] = max(swnd_max[0][0], swnd_max[0][1]) + input[0];
            swnd_max[1][1] = max(swnd_max[0][0], max(swnd_max[0][1], swnd_max[0][2])) + input[1];
            swnd_max[1][2] = max(swnd_max[0][1], swnd_max[0][2]) + input[2];

        }

        System.out.println(
                new StringBuilder().append(max(swnd_max[1][0], max(swnd_max[1][1], swnd_max[1][2])))
                                   .append(" ")
                                   .append(min(swnd_min[1][0], min(swnd_min[1][1], swnd_min[1][2])))
        );
    }

    public static int min(int a, int b) { return a<b? a: b; }
    public static int max(int a, int b) { return a<b? b: a; }
}

