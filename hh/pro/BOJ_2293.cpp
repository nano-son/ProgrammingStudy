/*

	동전 문제라길래 생각도 않고 알고리즘1 수업에 배운
	코드로 짜다가 다른 문제란걸 다 풀고 알았다.

	dp라고 말하기 애매한 dp문제가 제일 어려운 것 같다.

	dp 배열을 참조하는 순서가 중요했다. 그냥 0부터 쭉 조회하면 겹치는 경우가 너무 많이 나온다.
	코인을 scan 받고 그 코인을 추가로 사용했을 때의 경우만 sum해주는 방식으로 해서 중복을 없앴다.


*/


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