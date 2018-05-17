#include <stdio.h>

long long dp[68];

void init()
{
	for (int i = 0; i < 2; i++)
		dp[i] = 1;
	dp[2] = 2;
	dp[3] = 4;

}

long long TASK(int n)
{
	for (int i = 4; i <= n; i++)
	{
		dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3] + dp[i - 4];
	}

	return dp[n];
}

int main()
{
	int N;
	int t;
	freopen("input.txt", "r", stdin);
	init();
	scanf("%d", &t);

	while (t--)
	{
		scanf("%d", &N);

		if (dp[N] != 0)
			printf("%lld\n", dp[N]);
		else {
			printf("%lld\n", TASK(N));
		}
	}
}