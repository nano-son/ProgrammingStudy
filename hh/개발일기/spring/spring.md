# Spring Framework

## Annotation 정리

> @ApiOperation:  [Swagger] 메서드(rest)에 대한 설명을 추가할 때 사용한다.
<pre> @ApiOperation(value="설명", notes="설명 디테일") </pre>
 
> @ApiParam: [Swagger] 

> @McApiMapParam: [???] 구글링에 검색이 안되는데 메서드의 파라미터 설명을 적으면 Swagger에서 매핑 되는 것 같다.
<pre>  @McApiMapParam(names= {"param:paramNm"})</pre> 

> @RequestBody: [Spring] HTTP 요청 body를 자바 객체로 전달 받을 수 있다.

```java
public RestResponse TEMP(@RequestBody @Valid tempVo vo) {
  return RestResponse.success();
}
```

> @ResonseBody: [Spring] 자바 객체를 HTTP 응답 몸체로 전송 근데 controller에 @RestController 어노테이션을 추가해주면 자동으로 된다.

```java
@RestController
public class tempController {
}
```

> @Valid: [Spring] 도메인 클래스에서 유효성 검증 어노테이션을 추가해준 다음 컨트롤러에서 그 검증 어노테이션을 검사해준다.


``` java
//domain 클래스에서 유효성 검증 어노테이션
public class Member {
  @NotNull
  private String id;
}
```
```java
//controller method에서 @Valid로 유효성 검사
@RequestMapping(value="/write.do",method=RequestMethod.POST)
public String write(@Valid Member member,BindingResult result){
   if(result.hasErrors()){
      return "writeForm"
   }
   ...
   return "writePro";
}
```

