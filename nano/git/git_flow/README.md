Git Flow
=============================
git에서 브랜치를 생성할 때 어떻게 네이밍을 할까?
어떤 브랜치를 어떤 목적으로 사용하고, 어느 브랜치와 주로 합칠까?

이런 고민들을 해본적이 있으면 git flow를 공부하는 것이 큰 도움이 될 것이다.

사실 브랜치를 어떤 목적으로 쓸지, 어떤 이름을 붙일지는 자유이다.
하지만 많은 사람이 함께 관리하면서 수많은 브랜치가 생겨나고, 다양하고 비슷한 이름의 브랜치들이 있으면 머리가 복잡해진다.

따라서 git을 git 답게 쓰기위해, 몇몇 사람들이 정책을 만들었고 이것이 업계에서 권장되는 편이다.

git flow는 크게 5가지 브랜치가 존재한다.
```
master , develop , feature , release , hotfix
```

```
master : 릴리즈 버전의 스냅샷이 저장되는 브랜치로, 버그가 없어야 함

develop : master에서 나온 브랜치로, 개발한 기능들이 추가된다.

feature : 항상 develop에서 나오는 브랜치로, 하나의 기능 개발을 위해 만들어지고, develop에 들어간후 삭제한다 (선택사항)

release : 배포 작업을 위한 브랜치로, 항상 develop에서 나온다. 이상적으로는 배포할 기능이 완벽하게구현되어야하며, 주로 QA 작업을 이 때 진행한다. QA 수정 사항을 적용하고, bugfix가 이루어진다.
배포되면 master, develop로 들어간다

hotfix : 실 서비스에 대한 급한 수정이 목적이며, 항상 master에서 나온다. 수정 후 master는 물론, develop으로 들어간다.
```

정말 잘 설명해주신 글이 있어 첨부함

### http://woowabros.github.io/experience/2017/10/30/baemin-mobile-git-branch-strategy.html

git flow plugin

### https://danielkummer.github.io/git-flow-cheatsheet/index.ko_KR.html

플러그인을 사용하면 편하게 git flow 정책에 맞게 사용할 수 있다. 예를 들어 보통 develop 브랜치에서 feature브랜치를 따내는데, 실수로 다른 브랜치에서 따게 될 수도 있다.
하지만 git flow를 사용하면 어떤 브랜치에서든 간에 피쳐 브랜치를 develop을 기준으로 만들어준다. 
아무튼 이런 자잘한 실수들을 방지 할 수 있어서 편하다.

