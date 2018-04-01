#include <stdio.h>
#define MAX 101
#define WHITE 100
#define GRAY 101
#define BLACK 102

int dfsAll(int flag);
void dfs(int r, int c, int flag);
void init();
void visit_init();

char map[MAX][MAX];
int visit[MAX][MAX];
int dx[4] = {0,0,1,-1};
int dy[4] = {1,-1,0,0};
int n;

int main() {
    init();
    visit_init();
    printf("%d ",dfsAll(0));
    visit_init();
    printf("%d\n",dfsAll(1));
}

int dfsAll(int flag) {
    int ret=0;
    int i, j;
    
    for(i=0; i<MAX; ++i)
        for(j=0; j<MAX; ++j)
            if(visit[i][j] == WHITE) {
                dfs(i, j, flag);
                ret++;
            }
    
    return ret;
}

void dfs(int r, int c, int flag) {
    int nr, nc, i;
    visit[r][c] = GRAY;
    for(i=0; i<4; ++i) {
        nr = r + dx[i];
        nc = c + dy[i];
        if(nr>=0 && nr<n && nc>=0 && nc<n && visit[nr][nc] == WHITE) {
            if(map[r][c] == map[nr][nc])
                dfs(nr, nc, flag);
            else if(flag==1) {
                if((map[r][c] == 'R' && map[nr][nc]=='G') || (map[r][c] == 'G' && map[nr][nc]=='R'))
                    dfs(nr, nc, flag);
            }
        }
    }
    visit[r][c] = BLACK;
}

void visit_init() {
    int i, j;
    for(i=0; i<n; ++i)
        for(j=0; j<n; ++j)
            visit[i][j] = WHITE;
}

void init() {
    int i, j;
    scanf("%d", &n);
    for(i=0; i<n; ++i)
        for(j=0; j<n; ++j)
            scanf(" %c",  &map[i][j]);
}

