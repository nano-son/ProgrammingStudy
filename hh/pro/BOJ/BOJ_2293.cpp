

#include <stdio.h>

int N;
int coin[101];
int K;
int dp[10001];

int my_min(int x, int y)
{
	if (x < y) {
		return x;
	}
	else
		return y;
}

int main()
{
	int i, j;
	int sum = 0;
	
	freopen("input.txt", "r", stdin);
	
	scanf("%d", &N);
	scanf("%d", &K);
	dp[0] = 1;
	for (i = 1; i <= N; i++)
	{
		scanf("%d", &coin[i]);
		
		for (j = 0; j + coin[i] <= K; j++)
		{
			dp[j + coin[i]] += dp[j];
		}

	}


	printf("%d", dp[K]);
}