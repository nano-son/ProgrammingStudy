import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nano.son on 2018. 4. 25.
 */
public class Main {
    static int stack[]= new int[11];
    static int top=0;
    static List<BracketSet> bracketList = new ArrayList<>();
    static List<String> result = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String t = new BufferedReader(new InputStreamReader(System.in)).readLine();
        for(int pos=0; pos<t.length(); ++pos) {
            if(t.charAt(pos)=='(')
                stack[top++] = pos;
            else if (t.charAt(pos) == ')')
                bracketList.add(new BracketSet(stack[--top],pos));
        }

        result.add(t);
        for (int i = 0; i < bracketList.size(); ++i) {
            int size = result.size();
            BracketSet b = bracketList.get(i);
            for (int j = 0; j < size; ++j) {
                String temp = result.get(j);
                StringBuilder builder = new StringBuilder(temp.substring(0, b.left))
                        .append("_")
                        .append(temp.substring(b.left+1, b.right))
                        .append("_")
                        .append(temp.substring(b.right+1));
                result.add(builder.toString());
            }
        }
        result.remove(t);
        result.stream()
                .map(e->e.trim().replace("_",""))
                .distinct()
                .sorted(String::compareTo)
                .forEach(System.out::println);
    }
}

class BracketSet {
    int left;
    int right;

    public BracketSet(int left, int right) {
        this.left = left;
        this.right = right;
    }
}
