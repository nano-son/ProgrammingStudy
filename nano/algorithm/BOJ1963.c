#include <stdio.h>
#define INF 987654321
#define MAX 10001
#define WHITE 100
#define GRAY 101
#define BLACK 102
typedef struct node {
    int num;
    int d;
} node;

int check(int t);
node deQueue();
void enQueue(node);
void init();
int isQueueEmpty();
int min(int a, int b);

int from, to;
int visit[MAX];
node queue[MAX];
int front=-1, rear=-1;
int mod[4] = {1000,100,10,1};

int main(int argc, const char * argv[]) {
    int testCase, ret;
    scanf("%d", &testCase);
    while(testCase-- > 0) {
        scanf("%d %d", &from, &to);
        init();
        ret = bfs();
        if(ret == INF)
            printf("Impossible\n");
        else
            printf("%d\n",ret);
    }
}

void init() {
    int i;
    for(i=1001; i<MAX; ++i)
        visit[i] = WHITE;
    front = rear = -1;
}

int bfs() {
    int i, j;
    node in = {from, 0};
    enQueue(in);
    visit[from] = GRAY;
    
    while(! isQueueEmpty()) {
        node t = deQueue();
        if(t.num == to)
            return t.d;
        for(i=0; i<4; i++) {
            int temp = t.num - ((t.num%(mod[i]*10))/(mod[i]))*mod[i];
            for(j=0; j<10; ++j) {
                int temp2 = temp + j*mod[i];
                if(temp2<1000)
                    continue;
                if(visit[temp2] == WHITE && check(temp2)) {
                    in.num = temp2;
                    in.d = t.d+1;
                    enQueue(in);
                    visit[temp2] = GRAY;
                }
            }
        }
        visit[t.num] = BLACK;
    }
    return INF;
}

int check(int t) {
    int i=0;
    if(t%2==0)
        return 0;
    for(i=3; i<t; i+=2)
        if(t%i==0)
            return 0;
    return 1;
}
          
int isQueueEmpty() {
    if(front == rear)
        return 1;
    return 0;
}

node deQueue() {
    node t = {-1,-1};
    if(front == rear)
        return t;
    return queue[++front];
}

void enQueue(node t) {
    if(rear >= MAX-1)
        return;
    queue[++rear] = t;
}
