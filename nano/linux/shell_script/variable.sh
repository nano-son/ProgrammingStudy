#!/bin/bash

echo "hello world"

#변수 선언
#주의할 점 : '=' 연산자 앞뒤에 공백이 없어야 변수에 값을 대입하는 것으로 인식한다.
#[변수명]=[값] 으로 대입을하면 전역변수로 선언된다.
my_var="hello"

echo $my_var

my_func() {
  # local 키워드를 붙임으로써 지역변수를 생성한다.
  local my_var="world"
  echo $my_var
}

my_func

#어떤 값이 출력될까?
echo $my_var

# -----------------------------------

# 예약 변수
# 이미 만들어진 변수들이 있다. 바로 예약변수들이다. 몇개만 살펴보자

# 유저의 홈
echo $HOME

# 현재 스크립트가 실행된 환경의 절대 경로
echo $PWD

# 스크립트가 실행되고 경과된 시간 (너무 빨라서 0으로 나올것으로 예상)
echo $SECONDS

#사용한 편집기
echo $EDITOR

#사용자의 이름
echo $USER

# 사용자 그룹
echo $GROUPS

# -------------------------------------

# 위치 매개 변수

# 첫번째 인자 (실행된 스크립트의 이름)
echo $0

# 두번째 인자 (./test.sh hello 로 실행했으면 hello가 두번째 인자)
echo $1

#10번째부터는 중괄호로 감싸줘야한다. ex. echo ${10}

# 매개변수의 총 개수
echo $#

# 전체 인자 값
echo $*
