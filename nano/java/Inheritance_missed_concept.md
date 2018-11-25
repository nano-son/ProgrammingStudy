# 상속관계에 있어서 놓친 개념들

* 하위 클래스의 생성자는 반드시 상위 클래스의 생성자를 호출해야한다. 반드시.
    * super를 통해서 호출하지 않으면 디폴트 호출이 추가된다.
    * 하위 클래스에 생성자가 없으면 디폴트 생성자가 추가되면서, 해당 생성자 내에서는 디폴트 super를 호출한다.

접근제어 지시자
private 
default
protected
public

static 변수(메소드)의 상속
static 변수, 메소드는 인스턴스마다 존재하지 않고 클래스 메모리 상에 존재한다.
상속은 가능하다. 하지만 여러개가 존재하게 되는 것은 아님을 기억하자.

오버라이딩
super는 기본적으로 상위 클래스 객체를 나타낸다. super()는 생성자로 나타내고..
하위 클래스에서 오버라이딩된 상위클래스의 메소드를 호출하려면 super.메소드이름() 으로 호출한다.

A class를 상속하는 B Class가 있고 A의 m() 메소드를 B가 오버라이딩 한다고 했을 때
A a= new B();
a.m(); 를 호출하면 B클래스 상의 메소드가 호출된다. 
(의문이 든다면 인터페이스 혹은 추상클래스로 생각해봐라, 역으로 구현되지 않은 메소드가 호출되지 않는것이 적절하지 않는가?)

메소드 onlyInB()는 B에만 있다고 한다면
a.onlyInB();는 컴파일 에러가 발생한다.

컴파일러가 A의 클래스로 인식하기 때문이다.
런타임 상으로도 A의 클래스로 인식을 한다.
A클래스인데 거기에 없는 메소드를 호출하려고 하니 에러가 발생하는 것이다.

지금까지는 메소드 오버라이딩에 대해서 살펴봤다.

인스턴스 변수 오버라이딩
메소드는 마지막으로 오버라이딩된 메소드가 호출된다.
하지만 변수는 다르다.
동일한 이름, 동일한 자료형으로 하위클래스에 다시 선언되면, 인스턴스의 접근에 사용되는 참조변수의 자료형에 따라서 접근 가능한 변수가 달라진다.


오버라이딩과 접근제어
- 메소드를 오버라이딩하는 과정에서 접근의 허용범위를 좁히는 방식으로 변경할 수 없다.
- 즉, default 메소드를 오버라이딩 하면서 private 메소드로 만들 수는 없다.

그래서 인터페이스도 public이다. 인터페이스의 목적을 생각하면 public이 아닌게 이상하다.