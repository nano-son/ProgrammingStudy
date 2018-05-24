#include <stdio.h>
#include <algorithm>
#include <vector>
#include <cmath>

using namespace std;
typedef long long ll;

int N;

void init(vector<int> &arr, vector<int> &tree, int node, int start, int end) {

	if (start == end) {
		tree[node] = start; //리프노드라면 node에 index를 넣는다.
	}

	int mid = (start + end) / 2;
	init(arr, tree, node * 2, start, mid);
	init(arr, tree, node * 2 + 1, mid + 1, end);


	//각 구간에서 가장 높이가 낮은 것을 노드에 넣어준다.
	if (arr[tree[node * 2]] <= arr[tree[node * 2 + 1]]) {
		tree[node] = tree[node * 2];
	}
	else {
		tree[node] = tree[node * 2 + 1];
	}
}

// 구간에서 가장 최소인 높이 막대 구하기
int query(vector<int> &arr, vector<int> &tree, int node, int start, int end, int left, int right) {

	if (left > end || right < start) {
		return -1;
	}
	if (left <= start && end <= right) {
		return tree[node];
	}

	int mid = (start + end) / 2;
	int m1 = query(arr, tree, node * 2, start, mid, left, right);
	int m2 = query(arr, tree, node * 2 + 1, mid + 1, end, left, right);

	if (m1 == -1)
		return m2;
	if (m2 == -1)
		return m1;

	if (arr[m1] <= arr[m2])
		return m1;
	else
		return m2;

}

ll getMax(vector<int> &arr, vector<int> &tree, int start, int end) {

	int m = query(arr, tree, 1, 0, N - 1, start, end);
	
	//넓이
	ll area = (ll)(end - start + 1) * (ll)arr[m];

	//m 왼쪽에 막대기가 붙어있니?
	if (start <= m - 1) {

		ll tmp = getMax(arr, tree, start, m - 1);

		if (area < tmp) {
			area = tmp;
		}

	}
	
	if (m + 1 <= end) {

		ll tmp = getMax(arr, tree, m + 1, end);

		if (area < tmp)
			area = tmp;
	}

	return area;
}

int main() {

	freopen("input.txt", "r", stdin);

	while (1) {

		scanf("%d", &N);
		
		if (N == 0) return 0;

		vector<int> arr(N);
		int h = (int)ceil(log2(N));
		vector<int> tree(1 << (1 + h));


		for (int i = 0; i < N; i++) {
			scanf("%d", &arr[i]);
		}

		init(arr, tree, 1, 0, N - 1);

		printf("%lld\n", getMax(arr, tree, 0, N - 1));
	}
	

}