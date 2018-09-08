top 명령어
===============================

<b>한줄 요약 : top - display and update sorted information about processes</b>

```
The  top program periodically displays a sorted list of system processes.  
The default sorting key is pid, but other keys can be used instead.  
Various output options are available.
```
주기적으로(periodically) 프로세스들의 정보를 업데이트하며 보여준다.
즉, 실시간으로 프로세스들의 정보를 간략하게 볼 수 있다.


top명령어는 프로세스 별로 소팅해서 보여주는데 디폴트 소팅 기준은 pid이다.

소팅 기준은 -o 옵션으로 설정할 수 있다.
cpu사용량, 메모리 사용량, 프로세스 실행 주체 등 다양한 기준으로 소팅이 가능하다.

자세한 사항은 man 참고!!

