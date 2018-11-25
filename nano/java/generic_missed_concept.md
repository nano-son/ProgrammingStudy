generic 간과한 개념

클래스에 선언
class myGenericClass<T> {

}
호출 : myGenericClass<String>();

메소드에 선언
public <K, V> setKeyValue(K key, V value) {

}

호출: refer.<String, Integer>setKeyValue("hello", 10);

===================================

상위 클래스를 제한하는 방법
<T extends Fruit> : Fruit를 상속하는 클래스라면 무엇이라도

하위 클래스를 제한하는 방법
<T super Food> : Food가 상속하는 클래스라면 무엇이든지

A와 B가 상속관계에 있다고 해서
myGenericClass<A>와 myGenericClass<B>가 상속관계에 있는 것은 아니다.

와일드카드는 제네릭이 적용된 클래스의 참조변수에 사용하는 것이다.
class FruitBox<T> {

}
가 있는 상태에서 아래와 같은 변수 선언이 가능하다.

FruitBox<? extends Fruit> box = new FruitBox<Apple>();

===================================

제네릭 클래스도 클래스다. 상속관계에 놓일 수 있다.

class BB <T> extends myGenericClass<T> {
    ...
}

class CC extends BB<String> {
    ...
}

===================================

런타임에 자료형이 결정된다.

===================================
