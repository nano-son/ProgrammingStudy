메소드 시그니처와 오버라이드 & 오버로드
===================================

## Method Signature
<pre>
Two methods or constructors, M and N, have the same signature if they have the same name, the same type parameters (if any) (§8.4.4), and, after adapting the formal parameter types of N to the the type parameters of M, the same formal parameter types.
</pre>

두 메소드는 다음 조건을 만족하면 같은 signature를 가진다고 할 수 있다.
* 메소드 이름
* 파라미터 수
* 파라미터 타입의 순서


### Example
아래 두 메소드는 같은 Signature라고 할 수 있다.

<pre>
 public MyClass {   
    
    public String myMethod(String s1, Integer i1) {
        ....
    }
    
    private String myMethod(String s2, Integer i2) {
        ....
    }
 }
</pre>

비록 파라미터의 이름이 s1, s2와 같이 다르지만 이는 메소드 시그니처의 조건에 포함되지 않는다.
<br/>중요한 점은 메소드 시그니처가 같은 메소드가 같은 클래스에 2개 이상 존재할 수 없다는 것이다.
위 클래스는 그러므로 컴파일 되지 않는다.

#### 반환형은 메소드 시그니처에 포함되지 않는다.

만약 반환형이 메소드 시그니처에 포함된다면 이런 경우 애매해진다.
이름과 파라미터 수, 타입은 모두 같은데 하나는 Integer, 하나는 Double을 반환한다고 가정하자.
<pre>
System.out.println(myFunction());
</pre>
이렇다면 jvm은 어떤 메소드를 호출할지 결정하지 못한다.

이런 상황이 발생할 수 있기 때문에 반환형은 메소드 시그니처에 포함되지 않는다.


<br/>
<br/>

Overload
===================================

메소드의 이름은 같지만 메소드 시그니처가 다른 메소드가 한 클래스에 2개 이상 존재하게 되는 경우
말그대로 과적이다.

메소드 이름은 같지만 메소드 시그니처가 다르려면 파라미터의 수 또는 타입이 달라야한다.

### Example
<pre>
public MyClass {   
    
    public String myMethod(String s1, Integer i1) {
        ....
    }
    
    private String myMethod(String s2) {
        ....
    }
 }
</pre>

좀 더 복잡한 예시를 살펴보자.
<pre>
public class MySubClass extends MySuperClass {
    public static void main(String[] args) {
        System.out.println(myMethod("kkk"));
        System.out.println(myMethod(1));
    }

    public static String myMethod(String s1) {
        return "hello";
    }

    private static String myMethod(Object o1) {
        return "hi";
    }
}
</pre>

myMethod("kkk")의 경우 둘 중 어느 것이 불려도 이상할 것이 없다. 
<br/>(String은 Object의 하위 클래스이니까)
<br/>그럼 이런 경우도 햇갈릴 수 있으니 메소드 시그니처로 제한을 둬야하지 않을까?

이런 경우에 가장 적절한 메소드를 찾을 수 있다.
<br/>"kkk" 인자는 String으로, 둘 중 String파라미터를 인자로 받는 메소드를 선택하는 것이 더 적절하다.
<br/>반대로 myMethod(1)의 경우 String이 아니므로 Object를 인자로 받는 메소드가 호출되는 것이 적절하다.

출력결과:
<pre>
hello
hi
</pre>


한 가지 더 살펴보자
<pre>
public class MySubClass extends MySuperClass {
    public static void main(String[] args) {
        System.out.println(myMethod(new B()));
        System.out.println(myMethod(new C()));
    }

    public static String myMethod(A a) {
        return "hello";
    }

    private static String myMethod(B b) {
        return "hi";
    }
}

class A {

}

class B extends A {

}

class C extends B {

}
</pre>

출력 결과가 어떻게 나올까?
<br/>타입이 정확히 일치 하는 것이 없으면 가까운 상위클래스에 해당되는 메소드가 호출된다.
<br/>잘 모르겠다면 상속에 대한 공부를 해야할 듯 싶다.

# Override
상속 혹은 구현의 관계에서 하위 클래스가 상위 클래스의 메소드를 재정의 혹은 구현하는 것이다.
<br/>오버라이드는 다음과 같은 조건을 만족해야한다.

* 메소드 시그니처가 동일해야한다.
* 반환형의 타입을 그대로 하거나 혹은 하위 클래스로 정의하여야한다.

이 두가지만 만족하면 된다.

### Example

<pre>
class C implements T{
    @Override
    public StringBuilder test(Object p) {
        return null;
    }
}

interface T {
    Object test(Object p) throws IOException;
}
</pre>

* 인터페이스의 반환형은 Object이지만 오버라이드하면서 반환형을 Object의 하위 클래스인 String으로 변경했다.
* 인터페이스의 경우 IOException을 발생시킬 수 있다고 명시해두었지만 오버라이딩 할 때 이를 명시하지 않아도 된다.

이 경우 정상적으로 오버라이딩 된다.

하지만 아래의 경우는 컴파일 에러가 발생한다.

<pre>
class C implements T{
    @Override
    public StringBuilder test(Object p) throws Exception {
        return null;
    }
}

interface T {
    Object test(Object p) throws IOException;
}
</pre>

<pre>
Error:(32, 26) java: test(java.lang.Object) in method_signature.C cannot implement test(java.lang.Object) in method_signature.T
  overridden method does not throw java.lang.Exception
</pre>
클래스 C는 T라고 볼 수 있다. (인터페이스로 가릴 수 있으니까)
<br/>그런데 T의 메소드는 IOException을 발생할 수도 있다고 표현한다.
<br/>그렇다면 실제 발생할 수 있는 예외는 모두 IOException 종류에 속해야한다.
<br/>오버라이딩 하면서 이 정의에 포함되어야하는데, Exception은 IOException에 비해서 상위클래스이다.

따라서 오버라이딩 할 수 없다.

<pre>
class C implements T{
    @Override
    public StringBuilder test(Object p) throws FileNotFoundException {
        return null;
    }
}

interface T {
    Object test(Object p) throws IOException;
}
</pre>

FileNotFoundException 의 경우 IOException의 종류 중 하나로 속하기 때문에 
<br/>"IOException이 발생할 것이다" 라는 인터페이스 규격을 위반하지 않는다.