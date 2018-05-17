/*
	10942 팰린드롬?

	자연수 N개를 칠판에 적는다.
	질문(S,E)을 M개 한다.

	S 번째 수부터 E번째 까지 수가 팰린드롬인지 물어본다.
	대답은 팰린드롬이다 아니다로 대답,

	문제에 팰린드롬이 뭔지 안나와있다 ㅅㅂ, 팰린드롬은 토마토 기러기 같은 걸 말하나보다.

	팰린드롬은 i,j 구간을 구한다고 하면 i+1, j-1 구간이 펠린드롬이어야 하고 양 끝 i점과 j점이 같아야 한다.

*/

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
			
			if (diagonal == 0) { // 1칸만 물어보면 팰린드롬
				dp[i][j] = 1;
				continue;
			}

			if (diagonal == 1){ // 바로 옆이면 같아야 팰린드롬
				if (arr[i] == arr[j])	dp[i][j] = 1;
				continue;
			}

				//팰린드롬은 i,j 구간을 구한다고 하면 i+1, j-1 구간이 펠린드롬이어야 하고 양 끝 i점과 j점이 같아야 한다.
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