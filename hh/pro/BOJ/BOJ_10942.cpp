

#include <stdio.h>

int dp[2001][2001];
int N, M;
int arr[2001];

void DP()
{
	int diagonal;
	int i, j;


	for (diagonal = 0; diagonal < N; diagonal++)
	{
		for (i = 1, j = diagonal + 1; i <= N - diagonal; i++, j++)
		{
			
			if (diagonal == 0) { 
				dp[i][j] = 1;
				continue;
			}

			if (diagonal == 1){ 
				if (arr[i] == arr[j])	dp[i][j] = 1;
				continue;
			}

				
			if (dp[i + 1][j - 1] == 1 && arr[i] == arr[j])
				dp[i][j] = 1;
		}
		
	}

}

int main()
{
	int i;
	int x, y;
	freopen("input.txt", "r", stdin);

	scanf("%d", &N);
	for (i = 1; i <= N; i++)
	{
		scanf("%d", &arr[i]);
	}
	DP();

	scanf("%d", &M);

	
	for (i = 1; i <= M; i++)
	{
		scanf("%d %d", &x, &y);
		printf("%d\n", dp[x][y]);
	}

}