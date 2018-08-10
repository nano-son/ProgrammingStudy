import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static final int MAN = 0;
    static final int WOMAN = 1;
    static int[] d;
    static Student[] students;
    static Map<Integer, Diff> sMap;

    public static void main(String[] args) throws Exception{
        init();
        System.out.println(algorithm());
    }

    public static int algorithm() {
        int max=0;
        int m=0, w=0;
        sMap = new HashMap<>();
        sMap.put(0, new Diff(-1));

        for (int i = 0; i < students.length; ++i) {
            if(students[i].sex == MAN) m++;
            else w++;

            d[i] = m-w;
            if(sMap.containsKey(m-w))
                sMap.get(m-w).apply(i);
            else
                sMap.put(m - w, new Diff(i));
        }

        for(int i=1; i<students.length; ++i) {
            Diff sameDiff = sMap.get(d[i]);
            if(sameDiff.isOnlyOne())
                continue;
            int first = sameDiff.first;
            int last = sameDiff.last;
            if(first < i)
                max = Math.max(max, Math.abs(students[i].pos - students[first+1].pos));
            if(last > i)
                max = Math.max(max, Math.abs(students[i+1].pos - students[last].pos));
        }

        return max;
    }

    public static void init() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String temp[];
        int n = Integer.parseInt(br.readLine());
        d = new int[n];
        students = new Student[n];

        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            students[i] = new Student(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }
        Arrays.sort(students, (s1, s2)-> s1.pos - s2.pos);
    }
}

class Student {
    int sex;
    int pos;

    public Student(int sex, int pos) {
        this.sex = sex;
        this.pos = pos;
    }
}


class Diff {
    int first;
    int last = NONE;
    private static final int NONE = -2;

    public Diff(int first) {
        this.first = first;
    }
    
    public void apply(int num) {
        if(num < first) {
            first = num;
        }
        if (num > last) {
            last = num;
        }
    }
    public boolean isOnlyOne() {
        return last == NONE;
    }
}
