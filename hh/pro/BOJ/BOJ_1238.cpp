/*
	N명이 지금 파티한다
	M개 단방향 도로

	N이 1000까지 오는 문제인데
	다익스트라를 최대 2천번 돌려도 1초만에 돌아갔다.

	다익스트라 도덕책...
*/

#include <stdio.h>
#include <queue>

#define INFINITE 999999999

using namespace std;

struct node {
	int v;
	int cost;
};

struct cmp {
	bool operator() (node a, node b)
	{
		return a.cost > b.cost;
	}
};

int N, M, X;
vector<node> adj[1001];
priority_queue<node, vector<node>, cmp> pq;
int dist[1001];
int ret[1001];

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
	pq = priority_queue<node, vector<node>, cmp>(); // pq init
	for (i = 1; i <= N; i++)
	{
		dist[i] = INFINITE;
		ret[i] = 0;
	}
}

int dijkstra(int sou, int des)
{
	int cur_v;
	int cur_cost;
	int next_v;
	int next_cost;
	int distance = 0;

	init();
	
	pq.push(make_node(sou, 0));
	dist[sou] = 0;

	while (!pq.empty())
	{
		cur_v = pq.top().v;
		cur_cost = pq.top().cost;
		pq.pop();

		if (cur_cost > dist[cur_v])
			continue;

		for (node x : adj[cur_v])
		{
			next_v = x.v;
			next_cost = cur_cost + x.cost;
			
			if (dist[next_v] > next_cost)
			{
				dist[next_v] = next_cost;
				pq.push(make_node(next_v, next_cost));
			}
		}
	}
	distance += dist[des];

	init();
	pq.push(make_node(des, 0));
	dist[des] = 0;

	while (!pq.empty())
	{
		cur_v = pq.top().v;
		cur_cost = pq.top().cost;
		pq.pop();

		if (cur_cost > dist[cur_v])
			continue;

		for (node x : adj[cur_v])
		{
			next_v = x.v;
			next_cost = cur_cost + x.cost;

			if (dist[next_v] > next_cost)
			{
				dist[next_v] = next_cost;
				pq.push(make_node(next_v, next_cost));
			}
		}
	}
	distance += dist[sou];

	return distance;
}


int main()
{
	int from, to, cost;
	int ans = 0;
	freopen("input.txt", "r", stdin);
	
	scanf("%d %d %d", &N, &M, &X);

	for (int i = 0; i < M; i++)
	{
		scanf("%d %d %d", &from, &to, &cost);
		adj[from].push_back(make_node(to, cost));
		
	}
	
	for (int i = 1; i <= N; i++)
	{
		if (i == X)
			ret[i] = 0;
		else {
			
			ret[i] = dijkstra(i, X);
			if (ret[i] > ans)
				ans = ret[i];
		}
	}
	printf("%d", ans);
}
