#include <stdio.h>

long long dp[501][501];
long long cost[501][501];
int p[501][501];
int T;
int arr[501];
int N;

void init()
{
	int i, j;
	int sum = 0;
	for (i = 0; i <= N; i++)
	{
		sum = arr[i];
		for (j = i; j <= N; j++)
		{
			cost[i][j] += sum;
			dp[i][j] = 0;
			sum += arr[j + 1];
		}
		
	}
}

long long task()
{
	int i, j;
	int k, l;
	int diagonal;
	


	for (diagonal = 0; diagonal < N; diagonal++)
	{
		for (i = 1; i <= N - diagonal; i++)
		{		
			j = i + diagonal;
			if (i == j)
				dp[i][j] = 0;
			else {
				
				
				
				dp[i][j] = 9999999999;
				for (k = i; k < j; k++)
				{
					if (dp[i][j] > dp[i][k] + dp[k + 1][j])
						dp[i][j] = dp[i][k] + dp[k + 1][j];
				}
				dp[i][j] += arr[j] - arr[i - 1];
			}
			
		}
	}

	return dp[1][N];
}

int main()
{
	int n;
	freopen("input.txt", "r", stdin);
	scanf("%d", &T);

	while (T--)
	{
		scanf("%d", &N);
		init();
		for (int i = 1; i <= N; i++)
		{

			scanf("%d", &n);
			arr[i] = arr[i - 1] + n;
		}
		
		printf("%lld\n", task());
	}
}