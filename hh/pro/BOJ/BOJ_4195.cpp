#include <stdio.h>
#include <map>
using namespace std;

/*
	문제 자체는 UNION FIND 기본개념으로 쉽게 풀리지만
	막상 C++에서 map을 쓰자니 어려웠다.

	C++에도 파이썬 딕셔너리와 같이
	map["test"] 와 같이 쓸 수 있는 map 자료형이 있었다.
	알았다면 금방 풀었을 텐데
*/



int T;
int f, ff;

int parent[200001];
int child_count[200001];

char a[21], b[21];

int find(int u)
{
	if (u == parent[u])
		return u;
	return parent[u] = find(parent[u]);
}

int merge(int u, int v)
{
	u = find(u);
	v = find(v);

	if (u != v) {
		parent[u] = v;
		child_count[v] += child_count[u];
		child_count[u] = 1;
	}
	return child_count[v];
}

int main()
{
	int f_count = 0;
	int u, v;
	freopen("input.txt", "r", stdin);
	scanf("%d", &T);

	while (T--)
	{
		scanf("%d", &f);
		ff = f * 2;
		f_count = 0;

		for (int i = 0; i < ff; i++)
		{
			parent[i] = i;
			child_count[i] = 1;
		}

		map<string, int> m;
		for (int i = 0; i < f; i++)
		{
			scanf("%s %s", a, b);
			if (m.count(a) == 0)
				m[a] = f_count++;
			u = m[a];
			if (m.count(b) == 0)
				m[b] = f_count++;
			v = m[b];
			
			printf("%d\n", merge(u, v));

		}
	}
}