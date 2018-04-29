python 공부
=============================

## print
version별로 차이가 있는데, 2점대는 print "hello"와 같은 방식으로 하면되고
3점대는 print(""hello")와 같이 사용하면 된다.
```
print "hello"
print "hello"+str(10)
print "hello"+str(int("20"))
print """ 다행 문자열열열
많은 줄을 출력할 수 있다아아아
느아아아아
"""
print "hello"+" world" +'h' #hello worldh 라고 출력됨
```

##변수
python은 동적 타입의 언어라서 자바스크립트, 루비와 같이 형인자 선언이 없다.
c, java같은 경우 int a, String t 와 같이 변수에 타입을 정하고 사용하는 '강 타입' 언어이다.
동적 타입은 변수의 타입이 컴파일 타임이 아닌 런타임에 결정된다.
따라서 데이터 타입에 관계없이 코드를 작성할 수 있고 이는 개발 편리성으로 이어진다.
다만, 동적으로 타입이 결정되니 런타임 에러를 잡아내기 힘들 수 도 있다.

```
#이것은 주석이다
#a, b에 정수를 할당
a=10
b=20
c =a*b

#문자열을 할당
my_str = "hello world"
my_long_str = """ hello
world
!!!
"""

my_double = 10.01

```

##Escaping character
다른 언어랑 별반 다를게 없다
```
print "hello\"world"
my_str = 'this is \'test string\''

```

##문자열 다루기
```
str = "PYTHON"
character = str[3]  #'H'
length = len(str) #6
int_to_str = str(10) #"10"

lower_case = str.lower() #"python"
upper_case = "hello World.upper() #"HELLO WORLD"

#String formatting with '%'
str = "%s %s %d" %("hello", "world", 10) # "hello world 10"
print "hello %s %d" %("nano", 1000) # hello nano 1000 라고 출력됨


#짧은 예시
name = raw_input("What is your name? ")
quest = raw_input("What is your quest? ")
color = raw_input("What is your favorite color? ")

print "Ah, so your name is %s, your quest is %s, " \
"and your favorite color is %s." % (name, quest, color)
```

## 모듈
모듈은 python의 강력한 장점이다.
많은 모듈들이 존재하고 쉽게 가져와 사용할 수 있다.


