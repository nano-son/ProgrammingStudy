Spring
============================



## 프레임워크란?

> 특정한 목적에 맞게 프로그래밍을 쉽게 하기 위한 약속
<br>
<br>

## 스프링(SPRING)


> 자바언어를 기반으로, 다양한 어플리케이션을 제작하기 위한 약속된 프로그래밍 틀
<br>
 예전 EJB는 고가의 장비가 필요하고, 개발환경 및 설정, 테스트 환경에 많은 애로사항 존재.
스프링은 톰캣을 쓸 수 있고, 코드의 경량화, 개발 중 테스트가 쉽다.
<br>
<br>

## DI (Dependency Injection) & IOC (Inversion of Controller) 컨테이너

하나의 객체에서 다른 객체가 필요할 때 직접 생성해서 사용하는 방법과, 필드를 만들어 놓고 setter로 넣거나 생성자를 활용해 외부에서 필요한 객체를 넣어줄 수도 있다.

이 때 필요할 때마다 넣어줄 수 있게 객체들을 담고 있는 컨테이너를 IOC컨테이너라고 한다.
<br>



### DI (Dependency Injection) 의존성 주입

각 계층 사이, 각 클래스 사이에 필요로 하는 의존 관계를 컨테이너가 자동으로 연결해 주는 것

각 클래스 사이의 의존 관계를 빈 설정(Bean Definition) 정보를 바탕으로 컨테이너가 자동으로 연결해 주는 것


<br>


Spring이란 부품을 생성하고 조립하는 라이브러리 집합체이다.


- 작업을 수행하는 쪽에서 Object를 생성하는 제어 흐름의 개념을 거꾸로 뒤집는다.
- IoC 에서는 Object가 자신이 사용할 Object를 생성하거나 선택하지 않는다.
- 또한 Object는 자신이 어떻게 생성되고 어떻게 사용되는지 알 수 없다.
- 모든 Object는 제어 권한을 위임받는 특별한 Object에 의해서 만들어지고 사용된다.

<br>
<br>

### @Component
> xml에서 bean으로 객체를 생성하는 대신 클래스 선언부 위에 사용해 객체를 생성한다.

클라이언트에서 사용하기 위해서는 아이디가 필요하므로 @Component("아이디")와 같이 사용한다.

아이디를 지정하지 않았다면 자동으로 클래스 이름의 첫 글자를 소문자로 변경한 아이디가 붙는다.
<br>


### 의존성 주입 어노테이션

어노테이션 | 설명 |
--------|-----|
@Autowired|주로 변수 위에 설정하여 해당 타입의 객체를 찾아서 자동으로 할당한다.
@Qualifier|특정 객체의 이름을 이용하여 의존성 주입할 때 사용한다.
@Inject|@Autowired와 동일한 기능
@Resource|@Autowired와 @Qualifier의 기능을 결합한 어노테이션이다.

<br>

### @Autowired
> 생성자나 메소드, 멤버변수 위에 모두 사용할 수 있지만 대부분 멤버변수 위에 사용한다.
<br> 멤버변수 위에 사용하면, 의존성 주입에 사용했던 Setter메소드나 생성자는 필요없다.

Autowired는 변수타입을 기준으로 찾기 때문에 같은 타입이 여러 개 있을 경우 에러 발생. (다형성)

그럴 때 @Qualifier를 사용한다.

<br>

### @Qualifier
> 의존성 주입될 객체의 아이디나 이름을 지정하여 선택할 수 있다.

<br>

### XML과 어노테이션의 병행

XML은 관리와 해석이 어렵고, 어노테이션은 유지보수가 어렵기 떄문에 두 가지를 병행해서 사용한다.

하지만 라이브러리 형태로 제공되는 클래스는 반드시 XML 설정을 통해서만 사용할 수 있다.

<br>

### PreparedStatement
```java
    String sql = "UPDATE NAMECARD SET COMPANY=? WHERE NO=?";
    PreparedStatement pstmt;
    
    pstmt = con.prepareStatement(sql);
    pstmt.setString(1, "율도국");
    pstmt.setInt(2, 1);
    pstmt.executeUpdate();
```

?로 정해 놓은 곳에 값을 넣을 때 setXXXX()메소드를 사용한다.

<br>

### DAO(Data Access Object)에서 메소드 명 관리

기능|메소드 이름|
---|--------|
등록|insert테이블명|
수정|update테이블명|
삭제|delete테이블명|
상세 조회|get테이블명(혹은 select테이블명)|
목록 검색|get테이블명List(혹은 select테이블명List)|

<br>

## AOP(Aspect Oriented Programming)

### 관심 분리(Separation of Concerns)
> 메소드마다 공통으로 등장하는 로깅이나 예외, 트랜잭션 처리 같은 코드들을 <b>횡단 관심(Crosscutting Concerns)</b>이라고 하고, 실제 수행되는 핵심 비즈니스 로직을 <b>핵심 관심(Core Concerns)</b>라고 한다.

로깅이나 예외처리, 트랜잭션 관리 등 부가적인 코드를 효율적으로 관리하는 데 주목한다.

<br>

## AOP 용어 정리

- JoinPoint : 클라이언트가 호출하는 모든 비즈니스 메소드. 포인트컷 대상 혹은 포인트컷 후보라고도 한다.

- PointCut : 필터링된 조인포인트. 비즈니스 메소드 중에서 우리가 원하는 특정 메소드에서만 기능을 수행시키기 위해서 필요하다.

- Advice : 횡단 관심에 해당하는 공통 기능의 코드. 해당 메소드가 언제 동작할지 결정할 수 있다. 로그를 찍는 기능이면 로그찍는 메소드가 어드바이스이다.

- Weaving : 포인트컷으로 지정한 핵심 관심 메소드가 호출될 때, 어드바이스에 해당하는 횡단 관심 메소드가 삽입되는 과정을 말한다.

- Aspect (Advisor) : 포인트컷과 어드바이스의 결합. 어떤 포인트컷 메소드에 대해서 어떤 어드바이스 메소드를 실행할지 결정한다.

<br>

## AOP 엘리먼트

- \<aop:config\> : 루트 엘리먼트. 하위에는 \<aop:aspect\>, \<aop:pointcut\>이 올 수 있다.
 
- \<aop:pointcut\> : 포인트 컷을 지정하기 위해서 사용. \<aop:config\>, \<aop:aspect\>의 자식으로 사용할 수 있다. 유일한 아이디를 할당하여 참조할 때 사용할 수 있다.

- \<aop:aspect\> : 핵심 관심에 해당하는 포인트컷 메소드와 횡단 관심에 해당하는 어드바이스 메소드를 결합하기 위해 사용한다.

```xml
<aop:aspect ref="log">
 <aop:before pointcut-ref="getPointcut" method="printLog"/>
</aop:aspect>
```

- \<aop:advisor\> : 애스팩트와 같은 기능이지만 트랜잭션 설정과 같은 몇몇 특수한 경우는 무조건 어드바이스를 사용해야 한다.

<br>

## Advice 동작 시점
> \<aop:aspect\> 하위에 \<aop:XXXX\> 형태로 들어가 동작 시점을 결정한다.
- before
- after-returning
- after-throwing
- after

