#include <stdio.h>
#include <algorithm>

using namespace std;

int T;
int L, N, B;

typedef struct node {
	int x;
	int len;
	int fun;
	int cost;
}node;

node arr[10001];
int dp[1001][1001]; 

void init() {
	for (int i = 0; i <= L; i++) {

		for (int j = 0; j <= B; j++) {

			dp[i][j] = -1;
		}
	}
}

bool compare(node a, node b) {
	if (a.x == b.x && a.len == b.len)
		return a.fun > b.fun;
	return a.x < b.x;
}

int main(){

	int x, len, fun, cost;
	freopen("input.txt", "r", stdin);
	scanf("%d", &T);

	for (int tc = 1; tc <= T; tc++){

		scanf("%d %d %d", &L, &N, &B);
		init();
		for (int i = 0; i < N; i++){

			scanf("%d %d %d %d", &x, &len, &fun, &cost);
			arr[i].x = x;
			arr[i].len = len;
			arr[i].fun = fun;
			arr[i].cost = cost;
		}
		
		sort(arr, arr + N, compare);
		
		for (int i = 0; i < N; i++) {

			x = arr[i].x;
			len = arr[i].len;
			fun = arr[i].fun;
			cost = arr[i].cost;

			
			if (x == 0) {
				dp[x + len][B - cost] = fun;
			}
			else {

				for (int j = cost; j <= B; j++) {
					if (dp[x][j] != -1) 
						dp[x + len][j - cost] = max(dp[x + len][j - cost], dp[x][j] + fun);
				}

			}

		}
		int ret = -1;
		for (int i = 0; i <= B; i++) {
			ret = max(ret, dp[L][i]);
		}
		printf("#%d %d\n", tc, ret);
	}
}