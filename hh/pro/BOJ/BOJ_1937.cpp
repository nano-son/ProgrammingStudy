


#include <stdio.h>
#include <queue>

using namespace std;

struct node {

	int x;
	int y;
	int cnt;

};

struct cmp {

	bool operator() (node a, node b) {
		return a.cnt < b.cnt;
	}
};

priority_queue<node, vector<node>, cmp> pq;
int N;
int map[501][501];
int dp[501][501];


node newnode(int y, int x, int cnt)
{
	node ret;
	ret.x = x;
	ret.y = y;
	ret.cnt = cnt;

	return ret;
}

int task(int x, int y, int cnt)
{
	int max = 0;
	if (x > 1) {

		if (max < dp[y][x - 1] && map[y][x] < map[y][x - 1]) {

			max = dp[y][x - 1];
		}
	}
	if (y > 1) {

		if (max < dp[y - 1][x] && map[y][x] < map[y - 1][x]) {
			max = dp[y - 1][x];
		}
	}
	if (x < N) {
		if (max < dp[y][x + 1] && map[y][x] < map[y][x + 1]) {
			max = dp[y][x + 1];
		}
	}
	if (y < N) {
		if (max < dp[y + 1][x] && map[y][x] < map[y + 1][x]) {
			max = dp[y + 1][x];
		}
	}
	return max;

}


int main()
{
	int cur_x;
	int cur_y;
	int cur_cnt;
	int ret = -1;

	freopen("input.txt", "r", stdin);
	scanf("%d", &N);



	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			scanf("%d", &map[i][j]);
			pq.push(newnode(i, j, map[i][j]));
			dp[i][j] = 0;
		}

	}

	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			cur_x = pq.top().x;
			cur_y = pq.top().y;
			cur_cnt = pq.top().cnt;

			dp[cur_y][cur_x] = 1 + task(cur_x, cur_y, cur_cnt);

			if (ret < dp[cur_y][cur_x]) {
				ret = dp[cur_y][cur_x];
			}

			pq.pop();
		}

	}
	printf("%d", ret);

}

