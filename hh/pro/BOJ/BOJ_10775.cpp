#include <iostream>
#include <algorithm>

using namespace std;

int G, P;
int parent[100001];
int height[100001];

void init()
{
	for (int i = 0; i <= G; i++)
	{
		parent[i] = i;	
	}
}

int find(int u)
{
	if (parent[u] == u) return u;

	return parent[u] = find(parent[u]);
}

void union2(int u, int v)
{
	u = find(u);
	v = find(v);

	if (u == v) return; 
	parent[u] = v;

}

int main()
{
	int visit;
	int ret;
	int gate;
	freopen("input.txt", "r", stdin);
	scanf("%d", &G);
	scanf("%d", &P);

	init();
	for (ret = 0; ret < P; ret++)
	{
		scanf("%d", &gate);
		visit = find(gate);

		if (visit == 0)
			break;
		union2(visit, visit - 1);
	}
	printf("%d", ret);
}