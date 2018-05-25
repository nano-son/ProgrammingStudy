// K¹øÂ° ¼ö

#include <stdio.h>
#include <algorithm>

using namespace std;

typedef struct node {
	int idx;
	int val;
}node;

node arr[100001];
int N, M;

bool compare(node a, node b) {
	return a.val < b.val;
}

int main()
{
	freopen("input.txt", "r", stdin);

	scanf("%d %d", &N, &M);
	for (int i = 0; i < N; i++) {
		scanf("%d", &arr[i].val);
		arr[i].idx = i;
	}

	sort(arr, arr + N, compare);
	
	int start;
	int end;
	int K;
	
	for (int i = 0; i < M; i++) {
		int cnt = 0;
		scanf("%d %d %d", &start, &end, &K);

		for (int j = 0; j < N; j++) {

			if (arr[j].idx >= start - 1 && arr[j].idx <= end - 1) {
				cnt++;
				if (cnt == K)
				{
					printf("%d\n", arr[j].val);
					break;
				}

			}

		}
	}
	
}