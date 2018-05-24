#include <stdio.h>
#include <algorithm>
#include <string.h>

using namespace std;

char arr1[4001];
char arr2[4001];

int dp[4001][4001];
int ret;

int main() {

	freopen("input.txt", "r", stdin);

	scanf("%s", arr1);
	scanf("%s", arr2);

	for (int i = 0; i < strlen(arr1); i++) {

		for (int j = 0; j < strlen(arr2); j++) {

			if (arr1[i] == arr2[j]) {

				if (i == 0 || j == 0) {
					dp[i][j] = 1;
				}
				else {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}

				ret = max(ret, dp[i][j]);

			}

		}

	}
	printf("%d", ret);
}