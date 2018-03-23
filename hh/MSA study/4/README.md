## 요구사항

> 상품(상품명, 상품 카테고리, 가격, 상품 설명)
1. 상품 등록
2. 상품 조회(등록된 모든 상품)
3. 상품 삭제

> 회원(사용자명, 패스워드)
1. 회원 등록
2. 회원 인증
3. 회원 탈퇴

> 구매관리(사용자 정보, 상품 정보, 구매 일자)
1. 구매
2. 구매 내역 조회


### 상품 관리 REST API

|기능     |메서드    |URI    |
|---      |--           |-- |
|상품 등록|POST|/goods|
|상품 조회|GET|/goods|
|상품 삭제|DELETE|/goods|


1. 상품 등록 param

<pre>
name(String), category(String), price(Number), description(String)
</pre>
2. 상품 등록 결과
<pre>
errorcode(Number), errormessage(Sring)
</pre>
3. 상품 조회 결과
<pre>
errorcode(Number), errormessage(String), results(Array)
</pre>

<pre>
<code>
CREATE TABLE IF NOT EXISTS `goods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `category` varchar(128) NOT NULL,
  `price` int NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `members` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `password` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `purchases` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int NOT NULL,
  `goodsid` int NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

</code></pre>