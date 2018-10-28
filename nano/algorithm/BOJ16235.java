import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    static int N, M, K;
    static Node[][] map = new Node[11][11];
    static int dx[] = new int[]{0, 0,1, 1,1,-1,-1,-1};
    static int dy[] = new int[]{1,-1,1,-1,0, 0,-1, 1};
    static int[][] A = new int[11][11];

    public static void main(String[] args) throws Exception {
        init();
        for (int i = 0; i < K; i++) {
            spring();
            summer();
            autumn();
            winter();
        }

        int answer = 0;
        for (int i = 1; i <= N ; i++)
            for (int j = 1; j <= N ; j++)
                answer += map[i][j].treeList.size();

        System.out.println(answer);
    }

    static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        K = Integer.parseInt(temp[2]);

        for (int i = 1; i <= N ; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < N ; j++) {
                A[i][j+1] = Integer.parseInt(temp[j]);
                map[i][j+1] = new Node();
            }
        }

        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            map[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])]
                    .addTree(new Tree(Integer.parseInt(temp[2]), 1));
        }
    }

    static void spring() {
        for (int i = 1; i <= N ; i++) for (int j = 1; j <= N ; j++) {
            Node node = map[i][j];
            if (node.treeList.size() <= 0) continue;

            node.treeList.sort(Comparator.comparing(Tree::getAge));
            for (int k = 0; k < node.treeList.size(); k++) {
                Tree t = node.treeList.get(k);
                if (t.state == 1 && node.energy >= t.age) {
                    node.energy -= t.age;
                    t.age++;
                } else {
                    t.state = 0;
                }
            }
        }
    }

    static void summer() {
        for (int i = 1; i <= N ; i++) for (int j = 1; j <= N; j++) {
            Node node = map[i][j];
            if (node.treeList.size() <= 0) continue;

            final int[] sum = { 0 };
            node.treeList.stream().filter(t -> t.state == 0).forEach(t -> sum[0] += t.age / 2);
            node.treeList.removeIf(t -> t.state == 0);
            node.energy += sum[0];
        }
    }

    static void autumn() {
        for (int i = 1; i <= N ; i++) for (int j = 1; j <= N; j++) {
            Node node = map[i][j];
            if (node.treeList.size() <= 0) continue;
            int count = (int) node.treeList.stream().filter(t-> t.age%5 == 0).count();
            if(count<=0) continue;

            for (int k = 0; k < 8; k++) {
                int nextRow = i+dx[k];
                int nextCol = j+dy[k];
                if(nextCol>=1 && nextCol<=N && nextRow>=1 && nextRow<=N)
                    for (int l = 0; l < count; l++)
                        map[nextRow][nextCol].treeList.add(new Tree(1,1));
            }
        }
    }

    static void winter() {
        for (int i = 1; i <= N ; i++) for (int j = 1; j <= N; j++)
            map[i][j].energy += A[i][j];
    }

}

class Node {
    int energy;
    ArrayList<Tree> treeList;

    public Node() {
        energy = 5;
        treeList = new ArrayList<>();
    }

    public void addTree(Tree tree) { treeList.add(tree); }
}

class Tree {
    int age;
    int state; //0:die, 1:alive

    public Tree(int age, int state) {
        this.age = age;
        this.state = state;
    }

    public int getAge() { return age; }
}
