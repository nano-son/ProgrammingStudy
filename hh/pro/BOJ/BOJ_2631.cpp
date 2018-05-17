#include <iostream>
/*
1 - N 까지의 유치원 아이들이 있는데
순서가 섞여있다.

최소한의 아이들 이동으로 정렬을 하라는 문제

일단 정렬되어있는 놈들을 최대로 고른 다음
나머지 놈들을 옮겨주면 된다는 생각으로 풀..(지 못함)
여튼 LIS를 생각해냈다면 쉽게 풀 문제

*/


int N;
int arr[201];
int lis[201];
int ans;

int binary_search(int start, int end, int value)
{
	int mid;
	while (start <= end)
	{
		mid = (start + end) / 2;
		
		if (lis[mid] > value) {
			end = mid - 1;
		}
		else if (lis[mid] < value) {
			start = mid + 1;
		}
		else
		{
			start = mid;
			break;
		}
	}

	return start;
}

int main()
{
	freopen("input.txt", "r", stdin);

	scanf("%d", &N);

	for (int i = 1; i <= N; i++)
	{
		scanf("%d", &arr[i]);
	}

	ans = 0;
	int last_index = 0;
	int pivot = 0;
	for (int i = 1; i <= N; i++)
	{
		if (lis[last_index] < arr[i])
		{
			lis[++last_index] = arr[i];
			ans++;
		}
		else {
			pivot = binary_search(1, last_index, arr[i]);
			if (lis[pivot] > arr[i])
				lis[pivot] = arr[i];
		}
	}

	printf("%d", N - ans);
}