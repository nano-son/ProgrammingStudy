# 세그멘트 트리

> 주어진 배열에서 어떤 구간의 대표하는 값을 출력하기 또는 어떤 변화를 수정하는데 log n 타임 소요하는 자료구조

- 리프 노드 : 입력 받은 배열 그 자체
- 다른 노드 : 왼쪽 자식과 오른쪽 자식의 대표값


## 만들기(init)

> N이 2의 제곱꼴이면 트리 노드 수가 2*N-1개 필요하겠지만
아니라면 2^(H+1) -1 개가 될 것이다.

``` c++
//ll 은 long long
ll init(vector<ll> &a, vector<ll> &tree, int node, int start, int end) {
    if (start == end) {
        return tree[node];
    }
    else {
        // 합을 구하는 트리라는 예제
        return tree[node] = init(a, tree, node*2, start, mid) + 
                            init(a, tree, node*2+1, mid+1, end);
    }
}
```

## 합구하기

``` c++
//start와 end는 지금 탐색하고 있는 구간
//left와 right는 지금 값을 구할 구간
ll sum(vector<ll> &tree, int node, int start, int end, int left, int right) {

    if(left > end || right < start) {
        return 0;
    }
    if (left <= start && end <= right) {
        return tree[node];
    }
    return sum(tree, node*2, start, mid, left, right)
        + sum(tree, node*2+1, mid+1,end, left, right);
}

```

## UPDATE

``` c++
//node는 현재 노드 번호
//start, end 입력 배열 기준 구간 인덱스 번호
//index 입력 배열 기준 현재 인덱스 번호
//diff 수정 하고 나서 일어나는 변화
void update(vector<ll> &tree, int node, int start, int end, int index, ll diff) {
    if (index < start || index > end) return;
    tree[node] = tree[node] + diff;
    if(start != end) {
        update(tree, node*2, start, mide, index, diff);
        update(tree, node*2+1, mide+1, end, index, diff);
    }
}
```