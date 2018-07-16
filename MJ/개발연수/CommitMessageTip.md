# Technical Writing

## Commit Message

<b>하나의 커밋에 단 하나의 논리적 변경사항을 담아라.</b><br>
Ex) Add a new feature….  ,  Fix a specific bug
<br>
<br>
- 커밋 메세지만을 보고 다른 개발자가 똑같은 기능을 개발할 수 있도록.
- 게으른 로그는 X
- 기능을 여러개 했으면 쪼개서 올리는게 낫다.
- 제목은 동사원형으로 시작해라. (명령문)
- 대문자로 시작.
- 제목에는 마침표를 달지 말아라.
- 코드 값을 그대로 쓸 때는 그게 어떤 건지 명시를 해주면 좋다. Ex) createTemp() Method 이런식으로 Method라는 걸 알려줌
- If applied, this commit will (     ) 여기에 넣어서 말이 되면 통과다.
<br>
<br>
<br>
> - 왜 커밋하려는 패치가 필요한가?<br>
> - 목적에 어떻게 접근하는가? 포기한 접근 방법이 있다면 기술.<br>
> - 패치를 적용함에 따라 어떤 영향이 있나? (개선 결과 -> 수치(범위로 표현 가능), Side effects, 벤치마킹 결과)<br>
