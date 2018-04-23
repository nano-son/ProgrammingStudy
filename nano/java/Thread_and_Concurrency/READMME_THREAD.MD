Thread
===========================

쓰레드를 사용하면 동시에 여러 작업을 수행할 수 있다.

얼핏보면 프로세스와 비슷한데, 중요한 차이점이 있다.
```
 프로세스는 컴퓨터에서 실행되고 있는 완전한 하나의 프로그램이다. 또한 프로세스간 별도의 메모리 공간을 사용하여 서로 침범하지 않는다. 이에 비해 쓰레드는 프로세스 내에서 실행되고, 쓰레드 간 자원은 공유된다.

하나의 프로세스는 하나 이상의 쓰레드가 구동되고 있다.
단 하나의 쓰레드만 존재할 때, 이를 메인 쓰레드라고 한다.
```

## 자바의 Thread 

#### 기본적인 Thread 생성
자바의 쓰레드 생성 방식은 크게 두 가지로 나뉜다.

```
1. Thread 클래스를 상속받는 방법
2. Runnable 인터페이스를 구현하는 방법
```

#### Thread 클래스를 상속받기
Thread클래스를 상속하면 run 메소드를 오버라이드 하여야하고, Thread객체의 start메소드를 호출해서 쓰레드를 실행시킨다.
```
	Thread myThread = new Thread(){
            @Override
            public void run() {
                for(int i=0; i<10; ++i)
                    System.out.println("hello");
            }
        };
        myThread.start();
```

#### Runnable 인터페이스 구현
```
	Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<1000; ++i)
                    System.out.println("hello world");
            }
        };
        myRunnable.run();
```

#### 여담으로, 안드로이드에서는 AsyncTask라는 추상클래스를 제공한다. 안드로이드 정책상, 메인 쓰레드에서만 뷰 갱신이 가능하지만, AsyncTask를 통해 쓰레드를 사용하면서도 뷰 갱신이 가능하다. (결국 까보면 메인쓰레드에서 갱신하는거지만 추상화하여 사용자는 느낄 수 없다.)


