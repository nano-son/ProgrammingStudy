#include <iostream>
#include <algorithm>
#include <limits.h>

using namespace std;

int N;
int W[17][17];
int dp[17][1 << 17];

int ans = 0;

void init()
{
	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= 1 << N; j++)
			dp[i][j] = -1;
	}
}

int min(int a, int b)
{
	if (a < b)
		return a;
	return b;
}

int TSP(int current, int visited)
{
	if (visited == (1 << N) - 1)
		return W[current][1];

	if (dp[current][visited] != -1)
		return dp[current][visited];

	int ret = 100000000;

	for (int i = 1; i <= N; i++) {
		int next = i;

		if ((visited & (1 << (next - 1))) != 0)
			continue;

		if (W[current][next] == 0)
			continue;

		int temp = W[current][next] + TSP(next, visited + (1 << (next - 1)));
		ret = min(ret, temp);

	}

	return dp[current][visited] = ret;

}

int main()
{
	freopen("input.txt", "r", stdin);

	scanf("%d", &N);

	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			scanf("%d", &W[i][j]);
		}
	}
	init();
	printf("%d", TSP(1, 1));
	

}