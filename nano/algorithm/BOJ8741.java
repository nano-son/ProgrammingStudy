import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Nano.son on 2018. 4. 28.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        int n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
//        long result=0;
//        n = (1<<n)-1;
//
//        result += (n+1)*(n/2);
//        if(n%2!=0)
//            result += (n+1)/2;
//
//        System.out.println(Long.toBinaryString(result));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0; i<n; ++i)
            writer.write("1");
        for(int i=0; i<n-1; ++i)
            writer.write("0");
        writer.flush();
    }
}
