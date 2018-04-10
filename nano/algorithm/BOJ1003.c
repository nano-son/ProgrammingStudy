#include <stdio.h>
#define MAX 41
int d[MAX][2];

int main() {
    int testCase, i;
    d[0][0] = 1;
    d[0][1] = 0;
    d[1][0] = 0;
    d[1][1] = 1;
    
    for(i=2; i<MAX; ++i) {
        d[i][0] = d[i-1][0] + d[i-2][0];
        d[i][1] = d[i-1][1] + d[i-2][1];
    }
    
    scanf("%d", &testCase);
    while(testCase-- > 0) {
        int t;
        scanf("%d",&t);
        printf("%d %d\n", d[t][0], d[t][1]);
    }
}

