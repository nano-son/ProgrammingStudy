import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final int ROW = 9;
    static final int COL = 4;
    static String[][] cards = new String[ROW][4];
    static double[] dp = new double[1<<21];
    static final int[] END = new int[ROW];
    static final int[] FIVE = new int[ROW];

    public static void main(String[] args) {
        Arrays.fill(dp, -1);
        Arrays.fill(END, 4);

        Scanner s = new Scanner(System.in);
        for(int i=0; i<9; ++i) {
            String in = s.nextLine();
            String[] temp = in.split(" ");
            for(int j=0; j<COL; ++j)
                cards[i][j] = temp[COL-j-1];
        }
        FIVE[0] = 1;
        for(int i=1; i<ROW; ++i)
            FIVE[i] = FIVE[i-1]*5;

        System.out.println(recur(new int[9]));
    }

    //지금까지 온 확률을 어떻게 기록해둘까. 아 이거를 잘 기록해두면 그게 곧
    public static double recur(int[] idxs) {
        double ret = 0;

        if (Arrays.equals(idxs, END))
            return 1;

        int dpIdx = 0;
        for(int i=0; i<ROW; ++i) dpIdx += idxs[i] * FIVE[i];
        if(dp[dpIdx] != -1)
            return dp[dpIdx];

        int cnt = 0;
        List<Integer> pair = new ArrayList<>();

        for (int i = 0; i < ROW; ++i) {
            if(idxs[i] >= COL)
                continue;

            for(int j=i+1; j<ROW; ++j) {
                if(idxs[j] >= COL)
                    continue;
                if (cards[i][idxs[i]].charAt(0) == cards[j][idxs[j]].charAt(0)) {
                    cnt++;
                    pair.add(i*10 + j);
                }
            }
        }

        if(cnt==0)
            return 0;

        double probability = (1/(double)cnt);
        for(int i=0; i<pair.size(); ++i) {
            int[] next_idxs = idxs.clone();
            next_idxs[pair.get(i)/10]++;
            next_idxs[pair.get(i)%10]++;
            ret += probability * recur(next_idxs);
        }

        dp[dpIdx] = ret;
        return ret;
    }
}
