/*
	2초짜리 문제,
	팬더가 대나무를 그 좌표에서 다먹고 상하좌우로 이동한다.
	대신 현재지역보다 많은 대나무를 가지고 있는 곳으로만 이동한다 아니면 죽는다.
	팬더가 가장 오래 살아남는 일 수를 구하는 문제.

	거꾸로 생각해서 풀었다. 점점 적은 대나무를 찾아 가는 방법으로 했다.
	왠지는 모르겠다. priority queue에 큰 순서로 뽑아서 주변에 자신보다 큰 숫자가 있으면 그 좌표의 값에 +1한 값을
	저장해서 풀었다.

	생각해보니 거꾸로 풀 이유가 없는 문제.

	C++로 pq를 처음으로 구글링 안하고 풀었다.
	priority_queue<노드, 저장방식, 비교> 를 까먹지 말자

	struct cmp {
		bool operator() (node a, node b) {
			return a.cnt < b.cnt;
		}
	}
	가 이 문제에서 배울점

*/



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

