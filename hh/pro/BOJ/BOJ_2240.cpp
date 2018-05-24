#include <stdio.h>
#include <algorithm>

using namespace std;

//T초에 W번남았을때 i나무에 있다.
int dp[1001][31][3];
int arr[1001][3];

int T, W;

int main() {
	int temp;
	freopen("input.txt", "r", stdin);
	scanf("%d %d", &T, &W);

	for (int i = 1; i <= T; i++) {
		scanf("%d", &temp);
		arr[i][temp] = 1;
	}
	dp[1][W][1] = arr[1][1];
	dp[1][W][2] = 0;
	dp[1][W - 1][1] = 0;
	dp[1][W - 1][2] = arr[1][2];

	for (int i = 2; i <= T; i++) {
		
		for (int j = 0; j <= W; j++) {


			if (j != W) {
				dp[i][j][1] = max(dp[i - 1][j][1] + arr[i][1], dp[i - 1][j + 1][2] + arr[i][1]);
				dp[i][j][2] = max(dp[i - 1][j + 1][1] + arr[i][2], dp[i - 1][j][2] + arr[i][2]);

			}
			else {
				dp[i][j][1] = dp[i - 1][j][1] + arr[i][1];
				dp[i][j][2] = 0;
			}
		}

	}

	int ret = 0;
	for (int i = 0; i <= W; i++) {
		ret = max(ret, dp[T][i][1]);
		ret = max(ret, dp[T][i][2]);
	}
	printf("%d", ret);
}