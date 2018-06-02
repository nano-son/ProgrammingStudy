Proxy
==================

Proxy 패턴은 특정 객체의 클래스를 상속하고, 멤버로 객체 하나를 가진다.
그리고 메소드들을 오버라이딩하여, 호출 이전에 특정 작업을 수행한 후, 멤버(진짜 타켓)의 메소드를 호출하여 결과를 리턴하는 방식이다.

만약 수많은 클래스에 대해서 프록시 패턴을 작성해야된다면, 아주 아주 노가다일 것이다.
게다가 그 와중에 클래스가 새롭게 계속 생겨난다면??

그때 그때 동적으로 클래스 정보를 파악해서 프록시를 만들어 줄 수 없을까?
물론 있다!! reflection에 !!

이러한 프록시 패턴이 주로 쓰이는 곳은 jpa의 지연로딩이다.
예를 들어 User entity와 Card entity가 있는데 User Entity는 카드를 하나 소지하고 있는 관계라고 하자.
jpa는 User를 db로 부터 찾아올때 멤버인 Card도 가져와야할까?
단순하게 생각하면 쓸 일이 있으면 가져와야할 것 같다.

반대로 쓸 일이 없다면 굳이 가져올 필요가 없다.
가지고는 있지만 자주 쓰이지 않을 것 같은 멤버는 나중에 필요할때 가져오도록 비워둘 수 있다.
이러한 개념을 '지연로딩' 이라고 한다.

지연로딩 시, jpa는 멤버를 null로 해둘까? 아니다. 무언가 객체가 채워진다.

그럼 지연로딩이 아니지 않나?

실제로 jpa는 지연로딩 대상인 엔티티의 프록시를 만들어 그 프록시를 멤버에 할당한다.
물론 이러려면 프록시도 해당 멤버와 같은 형이어야한다.

프록시 안에는 실제 객체를 찾아올 수 있는 정보를 가지고 있고, 실제 객체를 null 멤버로 포함하고 있다.
그리고 이 프록시가 사용될 때(프록시 객체의 메소드가 호출될 때) 실제 객체를 불러온 후 실제 객체의 메소드를 호출한다.

예시를 보자

```java
/**
 * Created by Nano.son on 2018. 6. 2.
 */
public class ProxyObjectExample {
    public static void main(String[] args) {
        Test t = new Test();
        Object proxy = TraceProxy.makeProxy(t, null);
        ((TestInterface)proxy).greeting();
    }
}

interface TestInterface {
    void greeting();
}

class Test implements TestInterface{
    public void greeting() {
        System.out.println("hello world!");
    }
}


class TraceProxy implements InvocationHandler {
    private Object target;
    private String query;

    public TraceProxy(Object target) {
        this.target = target;
    }

    public TraceProxy(Object target, String query) {
        this.target = target;
        this.query = query;
    }

    public static Object makeProxy(Object target, @Nullable String query) {
        //return proxy
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new TraceProxy(target, query));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("this is proxyObject");

        if(target == null) {
            System.out.println("target object is null!! query:"+query);
//            target = ~~~;
            return null; //TODO: 객체 찾아올 수 있으면 이것도 주석처리해야함
        }

        return method.invoke(target, args);
    }
}
```

물론 코드상에는 DB연결이 없어서 객체를 찾는 행위를 하지 않았다.
실제로는 query와 같은 (진짜 프록시 객체는 어떤 정보를 저장하는지 모르겠다) 타겟을 검색 정보를 가지고 찾을 것 같다...


