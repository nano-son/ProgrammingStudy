/*
	bfs문제 연습하기 좋은 문제
	꼭 깔끔한 코드를 배울 필요가 있다.
*/

#include <iostream>
#include <queue>
#include <algorithm>
//8
using namespace std;
int map[1001][1001];
int N, M;
int ans;
bool visited[1001][1001];
int visited_cnt = 0;
struct node {
	pair<int, int> p;
	int d;
};

node make_node(int n, int m, int d)
{
	node ret;
	ret.p.first = n;
	ret.p.second = m;
	ret.d = d;

	return ret;
}

queue<node> que;

void q_push(int n, int m, int d)
{
	
	visited[n][m] = true;
	visited_cnt++;
	
	if (n > 1 && !visited[n - 1][m] && map[n - 1][m] == 0) {
		map[n - 1][m] = 1;
		que.push(make_node(n - 1, m, d + 1));
	}
	if (m > 1 && !visited[n][m - 1] && map[n][m - 1] == 0) {
		map[n][m - 1] = 1;
		que.push(make_node(n, m - 1, d + 1));
	}
	if (n < N && !visited[n + 1][m] && map[n + 1][m] == 0) {
		map[n + 1][m] = 1;
		que.push(make_node(n + 1, m, d + 1));
	}
	if (m < M && !visited[n][m + 1] && map[n][m + 1] == 0) {
		map[n][m + 1] = 1;
		que.push(make_node(n, m + 1, d + 1));
	}
	if (d + 1 > ans)
		ans = d + 1;
}


int main()
{
	freopen("input.txt", "r", stdin);
	scanf("%d %d", &M, &N);

	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= M; j++)
		{
			scanf("%d", &map[i][j]);
		}
	}

	//1인 곳 dept 0 으로 push
	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= M; j++)
		{
			if (map[i][j] == 1) {
				visited[i][j] = true;
				que.push(make_node(i, j, 0));
				visited_cnt++;
			}
		}
	}
	node cur;
	bool flag = false;
	while (!que.empty())
	{
		cur = que.front();
		que.pop();

		q_push(cur.p.first, cur.p.second, cur.d);
	}

	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= M; j++)
		{
			if (map[i][j] == 0) {
				printf("-1");
				flag = true;
			}
		}
	}

	if(!flag)
		printf("%d\n", ans - 1);
}