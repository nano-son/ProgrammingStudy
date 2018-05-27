#include <stdio.h>
#include <algorithm>
#define MAX 9999999999

using namespace std;

/*
	알고리즘 수업시간에 했던
	행렬곱 문제,
	다시 풀어봤더니 빨리 풀 수 있었다.
	거의 외운수준

	구간을 나누는 dp 문제 중 대표문제
*/

pair<int, int> arr[501];
int dp[501][501];
int N;
int op;

void init()
{
	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			if (i == j)
				dp[i][j] = 0;
			else
				dp[i][j] = MAX;
		}
	}
}

int main()
{
	freopen("input.txt", "r", stdin);
	scanf("%d", &N);
	
	for (int i = 1; i <= N; i++)
	{
		scanf("%d %d", &arr[i].first, &arr[i].second);
	}
	init();
	for (int diagonal = 1; diagonal <= N; diagonal++)
	{
		for (int i = 1; i <= N - diagonal + 1; i++)
		{
			
			int j = i + diagonal - 1;

			for (int k = i; k <= j - 1; k++)
			{
				op = arr[i].first * arr[k].second * arr[j].second;
				dp[i][j] = min(dp[i][j], dp[i][k] + dp[k + 1][j] + op);
			}
		}
	}

	printf("%d", dp[1][N]);
}