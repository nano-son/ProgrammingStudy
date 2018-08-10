import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Problem1487 {
    static Set<Integer> priceSet;
    static int MAX = 99999999;
    static int n;
    static int maxBenefit=0;
    static int minPrice = MAX;
    static int[] price;
    static int[] post;
    static String[] temp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        price = new int[n];
        post = new int[n];
        priceSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            price[i] = Integer.parseInt(temp[0]);
            post[i] = Integer.parseInt(temp[1]);
            priceSet.add(price[i]);
        }

        priceSet.stream().forEach(p-> {
            int sum = 0;
            for (int i = 0; i < n ; i++) {
                if(p<=price[i] && p>post[i]) {
                    sum += (p - post[i]);
                }
            }

            if(maxBenefit < sum) {
                maxBenefit = sum;
                minPrice = p;
            } else if(sum > 0 && maxBenefit == sum && p < minPrice) {
                minPrice = p;
            }
        });

        System.out.println(minPrice < MAX ? minPrice : 0);
    }
}

