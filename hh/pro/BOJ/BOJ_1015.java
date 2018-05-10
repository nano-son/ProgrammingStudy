/**
 * 
 * 배열 A(input)가 있고 오름차순인 B 배열에 대해서
 * A[P[i]] = B 가 성립하는 P 배열을 찾는 문제
 * 
 *  그냥 A 배열을 받으면서 그 숫자가 몇번 째로 큰지 저장하면 되는 문제다.
 *  똑똑하게 푸는 방법은 잘 모르겠다.
 *  2초길래 2차원 포문을 돌렸다.
 * 
 */
package BOJ_1015;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author 전현하
 *
 */
public class BOJ_1015 {

   
    private static int[] A;
    private static int[] B;
    private static int[] P;
    private static int[] pos;
    private static int N;
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        init();
        
        for(int i = 1; i <= N; i++) {
            System.out.printf("%d ", P[i]);
        }
        
    }
    
    public static void init() {
        
     
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        
        A = new int[N + 1];
        B = new int[N + 1];
        P = new int[N + 1];
        
        
        for (int i = 1; i <= N; i++) {
            A[i] = in.nextInt();
            
            for (int j = 1 ; j <= i - 1; j++) {
                
                if (A[i] >= A[j]) {
                    P[i]++;
                }
                else if (A[i] < A[j]) {
                    P[j]++;
                    
                }
                
            }
            
        }
    }

}
