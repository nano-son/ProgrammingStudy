#include <algorithm>
#include <stdio.h>
#include <math.h>

using namespace std;

int len[20001];
int parent[20001];
int N;

void init()
{
	for (int i = 0; i <= N; i++) {
		parent[i] = i;
		len[i] = 0;
	}
}

int find(int u) {
	/*
		find하면서 각 노드들의 특정값 갱신!!
	*/
	if (u == parent[u]) {
		return u;
	}
	int tmp = find(parent[u]);

	len[u] += len[parent[u]];
	parent[u] = tmp;
	return tmp;
}

void merge(int u, int v) {

	len[u] = abs(u - v) % 1000;
	parent[u] = v;

}

int main() {

	int T;
	char op;
	int op1, op2;
	freopen("input.txt", "r", stdin);
	scanf("%d", &T);

	while (T--) {

		scanf("%d", &N);
		init();
		
		while (1) {
			scanf(" %c", &op);
			if (op == 'E') {
				scanf("%d", &op1);
				find(op1);
				printf("%d\n", len[op1]);
			}
			else if(op == 'I') {
				scanf("%d %d", &op1, &op2);
				merge(op1, op2);
			}
			else {
				break;
			}

		}
		
	}
}