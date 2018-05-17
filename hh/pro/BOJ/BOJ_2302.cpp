#include <iostream>

/*
	쉬운거라도 방심하지말자
*/

int N, M;
int arr[41];
int dp[41];

void init()
{
	for (int i = 1; i <= N; i++)
	{
		dp[i] = -1;
	}
	dp[1] = 1;
	dp[2] = 2;

}

int process(int n)
{
	int* ret = &dp[n];

	if (*ret != -1)
		return *ret;

	return *ret = process(n - 1) + process(n - 2);
}

int main()
{
	int ret = 1;
	freopen("input.txt", "r", stdin);

	scanf("%d", &N);
	scanf("%d", &M);
	for (int i = 1; i <= M; i++)
	{
		scanf("%d", &arr[i]);
	}
	init();
	int start = 1;
	int i = 1;
	while (i <= M)
	{
		if(arr[i] - start != 0)
			ret *= process(arr[i] - start);
		start = arr[i++] + 1;
	}

	if (N - start + 1 != 0)
		ret *= process(N - start + 1);

	printf("%d", ret);
}