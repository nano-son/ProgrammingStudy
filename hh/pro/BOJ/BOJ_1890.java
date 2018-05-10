/**
 * 
 *  2차원 dp 중에 쉬운 문제
 *  map(input 배열)에 들어 있는 만큼 오른쪽, 아래로 이동하여 N,N 위치까지 오는 방법의 수
 *  
 *  처음에 dp 도는 순서를 무조건 사선방향으로 해야 되는 줄 알고 구현한다고 헤메다가
 *  그냥 정직하게 돌아도 된다는 걸 알게 되었다.
 *  java는 long long 이 없고 long 만 있고 %d만 써도 된다.
 * 
 */
package BOJ_1890;

import java.util.Scanner;

/**
 * @author 전현하
 *
 */
public class BOJ_1890 {

    private static int[][] map;
    private static int N;
    private static long[][] dp;
    
    public static void main(String[] args) {
        init();
        Task();
        System.out.printf("%d", dp[N][N]);
    }
    
    public static void init(){
        Scanner in = new Scanner(System.in);
        
        N = in.nextInt();
        
        map = new int[N + 1][];
        dp = new long[N + 1][];
        
        for (int i = 1; i <= N; i++) {
            map[i] = new int[N + 1];
            dp[i] = new long[N + 1];
        }
        
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = in.nextInt();
                
            }
        }
        
    }
    
    public static void Task() {
        
        dp[1][1] = 1;
        
        
        for (int i = 1; i <= N; i++) {
            
            for (int j = 1; j <= N; j++) {
                
                if (i == N && j == N) break;
                
                if (i + map[i][j] <= N) dp[i+map[i][j]][j] += dp[i][j];
                if (j + map[i][j] <= N) dp[i][j + map[i][j]] += dp[i][j];
                
            }
        }
        
   
    }

}
