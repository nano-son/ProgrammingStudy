package BOJ_2163;

import java.util.Scanner;

/**
 * 2163 초콜릿 자르기
 * 
 * 수열 점화식 구해서 쉽게 풀 수 있지만 dp로 풀어봤다. 프로시험은 dp니깐
 * 
 * N,M 초콜릿을 1x1 초콜릿으로 전부 부시는데 쪼개는 횟수 구하는 문제
 * dp[N][M]은 가로 세로 중 긴 쪽으로 한번 나누고 남은 두 조각의 dp를 더해주는 식으로 푸니까 풀리더라
 * 
 *
 */
public class BOJ_2163 {

    private static int N,M;
    private static long[][] dp;
    
    public static void main(String[] args) {
        init();
        task();
//        for (int i = 1; i <= N; i++) {
//            
//            for (int j = 1; j <= M; j++) {
//                
//                System.out.printf("%d ", dp[i][j]);
//            }
//            System.out.println();
//        }
        System.out.printf("%d", dp[N][M]);
    }
    
    public static void init(){
        
        Scanner in = new Scanner(System.in);
        
        N = in.nextInt();
        M = in.nextInt();
        
        dp = new long[N + 1][];
        
        for (int i = 1; i <= N; i++) {
            
            dp[i] = new long[M + 1];
            
        }
        
        for (int i = 1; i <= N; i++) {
            
            for (int j = 1; j <= M; j++) {
                
                dp[i][j] = 0;
                
            }
            
        }
        dp[1][1] = 1;
       
    }
    
    public static void task() {
        
        for (int i = 1; i <= N; i++) {
            
            for (int j = 1; j <= M; j++) {
                
                if((i == 1 || i == 2) && (j == 1 || j == 2)) {
                    dp[i][j] = 1;
                    if(i == 2 && j == 2)
                        dp[i][j] = 3;
                    continue;
                }
                
                
               
                if (i >= j) {
                    if (j == 1) {
                        dp[i][j] = dp[i-1][j] + 1;
                    } else {
                        dp[i][j] = 1 + dp[i/2][j] + dp[i - i/2][j];
                    }
                    
                }
                else if (i < j) {
                    if(i == 1) {
                        dp[i][j] = dp[i][j-1] + 1;
                    } else {
                        dp[i][j] = 1 + dp[i][j/2] + dp[i][j - j/2];
                    }
                    
                }
                
            }
            
        }
        
    }
}