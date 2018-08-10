import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] count = new int[10001];
        for (int i = 0; i < n; i++)
            count[Integer.parseInt(br.readLine())]++;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=1; i<10001; ++i)
            for(int j=0; j<count[i]; ++j)
                bw.write(i+"\n");
        bw.flush();
    }
}
