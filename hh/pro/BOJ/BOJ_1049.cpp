#include <stdio.h>

#define MAX 999999999
int N, M;
int minSet = MAX, min = MAX;


int main()
{
	int count;
	int countSet;
	long ret;
	int temp, tempSet;
	freopen("input.txt", "r", stdin);
	scanf("%d %d", &N, &M);

	for (int i = 0; i < M; i++)
	{
		scanf("%d %d", &tempSet, &temp);
		if (minSet > tempSet)
			minSet = tempSet;
		if (min > temp)
			min = temp;
	}
	count = N % 6;
	countSet = N / 6;
	ret = countSet * minSet + count * min;
	
	if (ret > (countSet + 1) * minSet)
		ret = (countSet + 1)*minSet;
	
	if (ret > min * N)
		ret = min * N;

	printf("%lld", ret);

}