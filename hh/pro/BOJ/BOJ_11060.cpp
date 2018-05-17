#include <iostream>

int dp[1001];
int arr[1001];
int N;

void init()
{
	for (int i = 0; i <= N; i++)
	{
		dp[i] = -1;
	}
	dp[1] = 0;
}

int main()
{
	freopen("input.txt", "r", stdin);

	scanf("%d", &N);
	init();
	for (int i = 1; i <= N; i++)
	{
		scanf("%d", &arr[i]);
	}

	for (int i = 1; i <= N - 1; i++)
	{
		if (dp[i] == -1)
			continue;
		for (int j = i + 1; j <= i + arr[i] && j <= N; j++)
		{
			if (dp[j] == -1)
				dp[j] = dp[i] + 1;
			else {
				if (dp[j] > dp[i] + 1)
					dp[j] = dp[i] + 1;
			}
		}
	}

	printf("%d", dp[N]);
}