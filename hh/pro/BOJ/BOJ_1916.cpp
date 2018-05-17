/*
	다익스트라 기본 문제
*/


#include <stdio.h>
#include <queue>

using namespace std;

struct node {
	int v;
	long long cost;
};

struct cmp {
	bool operator() (node a, node b)
	{
		return a.cost > b.cost;
	}
};

priority_queue<node, vector<node>, cmp> pq;
vector<node> adj[1001];
long long dist[1001];
int p[1001];

int V, E;

node make_node(int v, int cost)
{
	node ret;

	ret.v = v;
	ret.cost = cost;

	return ret;
}
void init()
{
	int i;

	for (i = 1; i <= V; i++)
	{
		dist[i] = 999999999;
	}
}
int main()
{	
	int i;
	int sou, des;
	int from, to, cost;
	int cur_v;
	int cur_cost;

	freopen("input.txt", "r", stdin);

	scanf("%d", &V);
	scanf("%d", &E);
	init();
	for (i = 0; i < E; i++)
	{
		scanf("%d %d %d", &from, &to, &cost);

		adj[from].push_back(make_node(to, cost));
		//adj[to].push_back(make_node(from, cost));

	}
	scanf("%d %d", &sou, &des);
	pq.push(make_node(sou, 0));
	dist[sou] = 0;

	while (!pq.empty())
	{
		cur_v = pq.top().v;
		cur_cost = pq.top().cost;

		pq.pop();

		if (dist[cur_v] < cur_cost)
			continue;

		for (node x : adj[cur_v])
		{
			long long next_cost = cur_cost + x.cost;
			if (dist[x.v] > next_cost)
			{
				dist[x.v] = next_cost;
				pq.push(make_node(x.v, next_cost));
			}
		}

	}
	printf("%lld", dist[des]);
}