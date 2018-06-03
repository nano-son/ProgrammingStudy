Strategy Pattern
=========================

말그대로 '전략' 패턴이다. 여기서 말하는 전략이란 무엇일까?
상황에 땨라서 다른 방법을 사용한다는 것이 전략패턴의 핵심이다.

즉, 그때 그때 다른 객체를 사용하는 것이 전략패턴이다.

간단한 전략패턴의 예로는 sort시 사용하는 Comparator를 들 수 있다.
소팅할 때마다 다른 조건으로 소팅이 되어야한다면 조건마다 sort를 구현할 수는 없는 노릇이다.

따라서 sort할 전략을 가진 Comparator 객체만 전달하여 동적으로 전략을 교체할 수 있다.
``` java
	String[] arr = {"hello", "nano", "abc", "aba"};
        Arrays.sort(arr, (s1, s2)->s1.compareTo(s2));
        Arrays.stream(arr).forEach(System.out::println);

        System.out.println("------------------------");
        Arrays.sort(arr, (s1, s2)->-(s1.compareTo(s2)));
        Arrays.asList(arr).stream().forEach(e-> System.out.println(e));
```

전략패턴을 효과적으로 사용하기 위해서는 인터페이스를 활용하는 것이 좋다.
전략을 사용할 입장에서는 interface 타입으로 받고, 전략을 결정하는 쪽에서는 인터페이스의 구현체를 남기는 것이다.
이렇게 되면 특정 클래스를 전략을 사용하는 쪽에서 몰라도 되기에 전략을 교체한다고 해도 전략을 사용하는 쪽은 코드 수정이 전혀 없어도 되어 유연하다.













