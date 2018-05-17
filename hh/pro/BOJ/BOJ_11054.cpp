
#include <stdio.h>

#define UP 0
#define DOWN 1

int N;
int arry[1001];
int dp[2][1001];
int ret = 1;

int max(int a, int b)
{
	if (a > b)
		return a;
	else
		return b;
}

int main()
{
	freopen("input.txt", "r", stdin);
	scanf("%d", &N);

	for (int i = 1; i <= N; i++)
	{
		scanf("%d", &arry[i]);
	}

	dp[UP][1] = 1;	dp[DOWN][1] = 1;

	for (int i = 2; i <= N; i++)
	{
		for (int j = 1; j <= i - 1; j++)
		{
			if (arry[i] > arry[j])
			{
				if (dp[UP][i] < dp[UP][j])
				{
					dp[UP][i] = dp[UP][j];
				}
			}
			else if (arry[i] < arry[j])
			{
				if (dp[DOWN][i] < dp[DOWN][j])
				{
					dp[DOWN][i] = dp[DOWN][j];
				}

				if (dp[DOWN][i] < dp[UP][j])
				{
					dp[DOWN][i] = dp[UP][j];
				}


			}
			
		}
		dp[UP][i]++;
		dp[DOWN][i]++;
	
		if (ret < max(dp[UP][i], dp[DOWN][i]))
		{
			ret = max(dp[UP][i], dp[DOWN][i]);
		}
	}

	printf("%d", ret);
}