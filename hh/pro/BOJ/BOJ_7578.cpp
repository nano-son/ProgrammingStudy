#include <stdio.h>
#include <algorithm>
#include <map>
#include <vector>
#include <cmath>

/*
	세그먼트 트리의 본질을 이용해보자
	어떤 구간의 대표값을 알아내기 쉬운 자료구조이다.
	구간의 값을 빠르게 찾고 싶을 때 쓰자.
*/


using namespace std;

int N;
int visited[500001];
int A[500001];
int B[500001];

void update(vector<long long> &tree, int node, int start, int end, int index, int diff) {

	if (index < start || end < index) {
		return;
	}
	tree[node] = tree[node] + diff;
	if (start != end) {
		int mid = (start + end) / 2;
		update(tree, node * 2, start, mid, index, diff);
		update(tree, node * 2 + 1, mid + 1, end, index, diff);
	}
}

long long query(vector<long long> &tree, int node, int start, int end, int left, int right) {

	if (end < left || start > right)
		return 0;
	if (left <= start && end <= right)
		return tree[node];

	int mid = (start + end) / 2;

	long long ret = 0;
	ret += query(tree, node * 2, start, mid, left, right);
	ret += query(tree, node * 2 + 1, mid + 1, end, left, right);

	return ret;
}

int main() {

	freopen("input.txt", "r", stdin);
	scanf("%d", &N);
	map<int, int> m;
	
	for (int i = 0; i < N; i++) {
		scanf("%d", &A[i]);
	}
	for (int i = 0; i < N; i++) {
		scanf("%d" , &B[i]);
		m[B[i]] = i;
	}

	vector<int> arr(N);
	int h = (int)ceil(log2(N));
	vector<long long> tree(1 << (1 + h));

	//init(arr, tree, 1, 0, N - 1);
	for (int i = 0; i < 1 << (1 + h); i++) {
		tree[i] = 0;
	}

	int visited;
	long long ans = 0;

	for (int i = 0; i < N; i++) {

		visited = m[A[i]];
		update(tree, 1, 0, N - 1, visited, 1);
		ans += query(tree, 1, 0, N - 1, visited + 1, N - 1);
	}

	printf("%lld\n", ans);

}