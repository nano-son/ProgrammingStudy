병행성
=================================

## 동기화에 대해서 잘 알고 있는가??

코드 하나를 살펴보자
```
static flag = false;

....

public static void test5() throws Exception{
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            int i=0;
            while(!flag)
                i++;
        }
    });
    thread.start();
    TimeUnit.SECONDS.sleep(1);
    flag = true;
}
```

이 코드의 실행 결과는 어떨까? 아마 끝나지 않을 거다.
이런 개똥같은 결과는 쓰레드와 자바 메모리 모델의 관계 때문에 발생한 것이다.

#### 병행성을 이해하기 위해서는 자바 메모리 모델과 하드웨어 메모리 구조에 대한 이해가 필수적이다.

```
참고 사이트
http://parkcheolu.tistory.com/14

```
꼭 이해하고 넘어가길..!













