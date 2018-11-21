LoadingCache<K, V>
-------------------

com.google.guava:guava 안에 있는 local caching 모듈.

- 예를 들어 Map<String, Object>를 불러오는 메소드가 오래걸린다고 할 때, 한 번 호출한 결과를 저장하고 있다가 나중에 쓸 때 그 값만 돌려줌.
- Map처럼 key를 사용해서 기존에 쓴 것인지 아닌지를 판단

```
LoadingCache<String, Map<String, Object>> cache = CacheBuilder.newBuilder()
                                                              .expireAfterAccess(6, TimeUnit.HOURS) //6시간동안 유지
                                                              .recordStats()
                                                              .build(
                                                                new CacheLoader<String, Map<String, Object>>() {
                                                                  @Override
                                                                  public Map<String, Object> load(String key) throws Exception {
                                                                    return METHOD(key);
                                                                  }
                                                                }
                                                              });
```

이렇게 하고 기존에 오래 걸리는 메소드를 호출하는 부분에서 METHOD(key) 이렇게 쓰던걸 cache.get(key) 이렇게 사용하면 된다.

파라미터를 여러 개 전달하고 싶을 땐 key를 String으로 주지 않고 Map형태로 넘겨주면 여러 개의 파라미터를 가진 메소드도 사용할 수 있다.
