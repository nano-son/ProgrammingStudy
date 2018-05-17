/*
	내 풀이에 빠져서 왜 틀린지 안보인 문제,

	map 안에 1로된 가장 큰 정사각형의 넓이를 찾는 문제

	나는 현재 (i,j)가 오른쪽 밑에 끝이라 생각하고 최대 넓이를 dp에 넣었다.
	최대 넓이 구하는 방법은 (i-1, j-1)이 채워져 있고 나머지 부분이 1로 가득채워져있으면 dp[i-1][j-1]에 +1을 했다.
	예외적인 경우는 나머지 부분이 0인 곳이 있어도 (i,j)는 1이 아닌 케이스가 있다.

	예를 들면,

	1110
	1111
	1111
	1111

	에서 오른쪽 맨 밑 부분 dp가 예외가 뜬다.

*/

#include <stdio.h>
#include <math.h>

int map[1001][1001];
int dp[1001][1001];
int N, M;
int ret = 0;

int min(int a, int b)
{
	if (a < b)
		return a;
	else
		return b;
}

int main()
{
	int i, j, k;
	char temp;
	freopen("input.txt", "r", stdin);
	scanf("%d %d", &N, &M);
	scanf("\n");
	for (i = 1; i <= N; i++)
	{
		for (j = 1; j <= M; j++)
		{
			
			scanf("%c", &temp);
			map[i][j] = temp - '0';
		}
		scanf("\n");
	}

	for (i = 1; i <= N; i++)
	{
		for (j = 1; j <= M; j++)
		{
			if (map[i][j] != 0)
			{
				int temp = min(dp[i - 1][j - 1], dp[i - 1][j]);
				dp[i][j] = min(temp, dp[i][j - 1]) + 1;

				if (ret < dp[i][j]) {
					ret = dp[i][j];
				}
			}
		}
	}

	printf("%d", ret*ret);

}