#include <stdio.h>
#include <algorithm>

/*
	최고이득 길찾기 문제인데
	보통 문제는 오른쪽, 아래쪽 밖에 못간다면
	이 문제의 특이점은 왼쪽으로도 갈 수 있다는 점이다.

	그 점 때문에 한 줄을 단위로 왼쪽으로만 갔을 때와
	오른쪽으로만 갔을 때를 따로 저장해두어서
	마지막에 dp배열에 그 두 방법중 최대값을 저장해둔다.

	임의로 1부터 시작하는 습관 덕분에 0번째 칸에 0이 들어가서 편하다 느꼈지만
	이 문제에서는 배열에 음수도 올 수 있기 때문에 문제가 되었었다.
*/


using namespace std;

int N, M;
int map[2001][2001];
int dp[2001][2001];
int tmp[2][2001];


void init()
{
	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= M; j++)
			scanf("%d", &map[i][j]);
	}
}
int main()
{
	int temp;
	freopen("input.txt", "r", stdin);

	scanf("%d %d", &N, &M);
	init();

	for (int i = 1; i <= M; i++) {
		dp[1][i] = dp[1][i - 1] + map[1][i];
	}
	for (int i = 2; i <= N; i++) {

		tmp[0][0] = dp[i - 1][1]; 
		for (int j = 1; j <= M; j++) {
			tmp[0][j] = max(dp[i - 1][j], tmp[0][j - 1]) + map[i][j];
		}

		tmp[1][M + 1] = dp[i - 1][M]; 
		for (int j = M; j >= 1; j--) {
			tmp[1][j] = max(dp[i - 1][j], tmp[1][j + 1]) + map[i][j];
		}

		for (int j = 1; j <= M; j++) {
			dp[i][j] = max(tmp[0][j], tmp[1][j]);
		}
	}


	printf("%d", dp[N][M]);
}