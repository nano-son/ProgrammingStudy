# Union Find

> Disjoint Set을 표현할 때 사용하는 자료구조


## 연산

### 1. init: 모든 노드들이 자기 자신의 번호로 부모를 갖는다.
``` c++
void init()
{
    vector<int> parent;
    for (int i = 0; i < n; i++)
        parent[i] = i;
}
```

### 2. find: u의 부모를 찾아준다.
``` c++

void find(int u)
{
    if (u == parent[u]) 
        return u;

    //return find(parent[u]);  이런식으로 짜면 트리 높이가 높아짐
    return parent[u] = find(parent[u]);
}
```

### 3. Union: u와 v 트리를 합쳐준다.

``` c++
/*
    이런식으로 짜면 트리 높이가 높아져서 워스트케이스에 높은 시간복잡도를 가진다.
*/
void Union(int u, int v)
{
    u = find(u);
    v = find(v);

    if (u == v) return;
    parent[u] = v;
}
```

``` c++
void Union(int u, int v)
{
    u = find(u);
    v = find(v);

    if (u == v) return;

    if (rank[u] > rank[v])
        swap(u, v);
    parent[u] = v;

    if(rank[u] == rank[v]) ++rank[v];
}
```
