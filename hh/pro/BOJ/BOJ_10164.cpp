#include <stdio.h>
#include <cmath>
//9
int N, M, K;
long long dp[16][16];
int map[16][16];
long long ans;

void init();
long long task(int n, int m)
{
	int i, j;
	long long *ret = &dp[n][m];

	if (*ret != 0)
		return *ret;

	*ret = 0;
	if (n > 1)
		*ret += task(n - 1, m);
	if (m > 1)
		*ret += task(n, m - 1);

	return *ret;
}

int main()
{
	int i, j;
	int cnt;
	freopen("input.txt", "r", stdin);
	scanf("%d %d %d", &N, &M, &K);

	cnt = 0;
	for (i = 1; i <= N; i++)
	{
		for (j = 1; j <= M; j++)
		{
			map[i][j] = ++cnt;
		}
	}
	dp[1][1] = 1;
	if (K == 0)
		printf("%lld", task(N, M));
	else {
		int new_N = (int)ceil((double)K / M);
		int new_M = K % M;
		if (new_M == 0)
			new_M = M;
		printf("%lld", task(new_N, new_M) * task(N - new_N + 1, M - new_M + 1));

	}
}