/*
	수열을 주고 그 부분수열 중 증가했다가 감소하는 케이스의 것들에서 제일 긴 것 찾기.
	각 원소까지 왔을 때 증가하고 있는 상태일 때 값, 감소하고 있을떄의 값 나눠서 dp 돌리면 되는 문제
	

	N이 1000개면 그냥 이중 포문을 돌리자
	괜히 더 줄여보려다 시간을 소비했다. N제곱으로 돌려도 0MS가 나오는 문제.
	
*/

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