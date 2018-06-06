# MongoDB 기초다지기

## 1강 MongoDB 설치 및 환경 세팅

### 1. MongoDB 유래

Humongous: 거대한

이 단어에서 유래되었다.

### 2. Windows에서 설치하기

1. 설치
<pre>
https://www.mongodb.com >> windows 환경에 맞게 .msi파일 받기
</pre>

2. 환경변수
<pre>
환경변수 -> Path추가 -> C:\Program Files\MongoDB\Server\3.2\bin
</pre>

3. data, log 폴더 생성
<pre>
데이터 폴더: MongoDB\data
로그폴더 : MongoDB\log
</pre>

4. 실행

<pre>
관리자 권한으로 명령 프롬프트 실행
> "mongod.exe" --dbpath "MongoDB\data" // 디렉토리는 귀찮아서 대충 적겠다.
</pre>

5. 설정파일 편집(편집: user권한 조정 필요)
<pre>
MongoDB\mongod.cfg 파일에서

dbpath = C:\Program Files\MongoDB\data
port = 27017
logpath = C:\Program Files\MOngoDB\logs\mongo.log

#웹관리 사용
rest = true
</pre>

6. 윈도우 서비스 등록하기
<pre>
> mongod.exe -f [.cfg 디렉토리] -install
#서비스 창 띄우고 MongoDB 실행하기 
</pre>

7. 클라 접속하기
<pre>
#접속
> mongo

#DB 목록
> show dbs

#DB 선택
> use local

#테이블(컬렉션) 목록
>show tables

#테이블 조회
db.startup_log.find()
db.startup_log.find().pretty<>
</pre>




