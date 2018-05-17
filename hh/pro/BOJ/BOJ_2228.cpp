#include <stdio.h>
#define INF 999999999
int N;
int M;

int dp[101][101];
int sum[101];
bool visited[101][101];

int max(int a, int b)
{
	if (a > b)
		return a;
	return b;
}


int process(int n, int m)
{
	int *ret = &dp[n][m];

	if (m == 0) return 0;
	if (n < 0) return -INF;

	if (visited[n][m])
		return *ret;

	visited[n][m] = true;
	*ret = process(n - 1, m);

	for (int i = n; i > 0; i--)
	{
		*ret = max(*ret, process(i - 2, m - 1) + sum[n] - sum[i - 1]);
	}
	return *ret;
}
int main()
{
	int i;
	int  temp; 
	freopen("input.txt", "r", stdin);
	scanf("%d %d", &N, &M);

	for (i = 1; i <= N; i++)
	{
		scanf("%d", &temp);
		sum[i] = sum[i - 1] + temp;
	}

	printf("%d", process(N, M));
}