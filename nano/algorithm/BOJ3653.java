import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Nano.son on 2018. 4. 17.
 */
public class Main {
    static final int MAX = 1<<19;
    static Node[] segmentTree = new Node[MAX];
    static int[] heightOfDvd = new int[100001];
    static int n, m, nowHeight;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase;
        testCase = Integer.parseInt(br.readLine());

        while(testCase-- >0) {
            String[] t = br.readLine().split(" ");
            n = Integer.parseInt(t[0]);
            m = Integer.parseInt(t[1]);
            nowHeight = n;

            for(int i=1; i<=n; ++i)
                heightOfDvd[i] = n-i+1;
            setTree(1,1,n+m);

            String[] dvds = br.readLine().split(" ");
            for (int i = 0; i < dvds.length; ++i) {
                Integer d = Integer.parseInt(dvds[i]);
                System.out.print(algorithm(d)+" ");
            }
            System.out.println("");
        }
    }

    static int algorithm(int dvd) {
        int ret = 0;
        // dvd위로 몇개의 dvd들이 있는지 구함
        ret = recur(1, heightOfDvd[dvd]+1, nowHeight);
        //dvd를 맨 위로 올림
        update(heightOfDvd[dvd], -1);
        update(++nowHeight, 1);
        heightOfDvd[dvd] = nowHeight;
        return ret;
    }

    static int recur(int idx, int start, int end) {
        int ret = 0;
        int from = segmentTree[idx].from;
        int to = segmentTree[idx].to;

        if(start>end)
            return 0;

        if (from > end || to < start) //아무 관계 없음
            return 0;
        else if (from >= start && to <= end) //start,end 안에 from, to가 존재
            ret =  segmentTree[idx].sum;
        else if (from <= start && to >= end) //from,to안에 start, end가 존재
            ret =  recur(idx * 2, start, end) + recur(idx * 2+1, start, end);
        else if ((from <= end && to >= end) || (from <= start && start <= to)) //걸침
            ret =  recur(idx * 2, start, end) + recur(idx * 2+1, start, end);
        return ret;
    }

    //type
    static void update(int h, int type) {
        int idx = 1;
        while(true) {
            if(segmentTree[idx].from == h && segmentTree[idx].to == h)
                break;
            int mid = (segmentTree[idx].from + segmentTree[idx].to)/2;
            idx = idx*2;
            if(mid<h)
                idx++;
        }

        while(idx>0) {
            segmentTree[idx].sum += type;
            idx = idx/2;
        }
    }

    public static void setTree(int idx, int from, int to) {
        if(null == segmentTree[idx])
            segmentTree[idx] = new Node(from, to);
        else {
            segmentTree[idx].from = from;
            segmentTree[idx].to = to;
        }

        if(to<=n)
            segmentTree[idx].sum = (to-from)+1;
        else if (from<=n && n<to)
            segmentTree[idx].sum = (n-from)+1;
        else
            segmentTree[idx].sum = 0;

        if(from>=to)
            return;
        int mid = (from+to)/2;
        setTree(idx*2, from, mid);
        setTree(idx*2+1, mid+1, to);
    }
}

class Node {
    int from;
    int to;
    int sum;

    public Node(int from, int to) {
        this.from = from;
        this.to = to;
    }
}

