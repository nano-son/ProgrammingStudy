kill 명령어
============================

manual설명을 살펴보자
```
NAME
     kill -- terminate or signal a process

SYNOPSIS
     kill [-s signal_name] pid ...
     kill -l [exit_status]
     kill -signal_name pid ...
     kill -signal_number pid ...

DESCRIPTION
     The kill utility sends a signal to the processes specified by the pid operands.

     Only the super-user may send signals to other users' processes.

     The options are as follows:

     -s signal_name
             A symbolic signal name specifying the signal to be sent instead of the default TERM.

     -l [exit_status]
             If no operand is given, list the signal names; otherwise, write the signal name corresponding
             to exit_status.

     -signal_name
             A symbolic signal name specifying the signal to be sent instead of the default TERM.

     -signal_number
             A non-negative decimal integer, specifying the signal to be sent instead of the default TERM.

     The following PIDs have special meanings:

     -1      If superuser, broadcast the signal to all processes; otherwise broadcast to all processes
             belonging to the user.

     Some of the more commonly used signals:

     1       HUP (hang up)
     2       INT (interrupt)
     3       QUIT (quit)
     6       ABRT (abort)
     9       KILL (non-catchable, non-ignorable kill)
     14      ALRM (alarm clock)
     15      TERM (software termination signal)

     Some shells may provide a builtin kill command which is similar or identical to this utility.  Consult
     the builtin(1) manual page.
```

위키피디아의 설명도 함께 보자
```
kill은 유닉스 계열 운영 체제에서 시스템상에서 동작하고 있는 프로세스에 간단한 메시지(시그널)를 보내는 명령어이다. 기본적으로 보내는 메시지는 종료 메시지이고 프로세스에 종료하는 것을 요구한다. 하지만 kill은 그 명칭과는 조금 다르게 종료와 무관한 메시지를 보낼 때도 사용한다. kill 명령어는 내부적으로 kill()이란 시스템 콜을 사용하여 구현하고, 프로세스 식별자(PID)로 지시한 프로세스와 프로세스 그룹 식별자(PGID)로 지시한 프로세스 그룹에 시그널을 보낸다. kill은 예전부터 독립적인 유틸리티로 제공되었으나 많은 프로그램은 약간 다른 형태의 kill 명령어를 가지고 있다.

사용자가 일반적으로 가장 많이 사용하는 시그널은 SIGTERM과 SIGKILL인데, kill은 이 외에도 서로 다른 다양한 종류의 시그널을 보내는 것이 가능하다. 기본적으로 보내지는 시그널은 SIGTERM이다. 이 시그널을 받은 프로세스는 프로그램을 종료하기 전에 프로그램을 종료하는 처리(환경설정을 파일에 기록한다던지...)를 수행하는 것이 가능하다.

프로세스는 SIGKILL과 SIGSTOP을 제외한 모든 시그널에 대해서 시그널에 대한 처리를 하는 별도의 루틴을 만들 수 있다. 이것은 프로세스가 시그널을 받았을 때 어떤 특별한 처리를 하기 위함이다. 예외적으로, 프로세스는 SIGKILL과 SIGSTOP에 대한 처리를 할 수 없도록 되어있다. SIGKILL은 프로세스를 강제 종료하는 명령이고 SIGSTOP은 SIGCONT 시그널을 받을 때까지 프로그램 실행을 정지시키는 명령이다.

유닉스는 권한없는 사용자가 프로세스를 종료하는 것을 방지하기 위해 보안 기능을 제공하고 있다. 기본적으로는 어떤 프로세스가 다른 프로세스로 시그널을 보낼 때, 시그널을 보내는 프로세스의 소유자가 시그널을 받는 프로세스의 소유자와 같은지, 최고 관리자인지 확인하게 된다.

사용 가능한 시그널은 고유한 명칭이 있고 특정한 숫자와 대응되어 있다. 유닉스의 설계에 따라서 시그널과 숫자의 대응이 다른 것은 주의할 필요가 있다. SIGTERM은 많은 경우 15이고, SIGKILL은 많은 경우 9이다.

일반적으로 해당 Process에 문제가 있어서 다시 시작하고자 할 때에는 SIGHUP, SIGTERM, SIGKILL의 순서로 각각 시도하길 바란다.
```

주로
```
kill -9 [pid]
```
와 같이 사용한다. -9 는 시그널의 번호로, KILL 이라는 시그널을 보낸다.
<br>kill을 잘 이해하기 위해서는 시그널의 종류와 특징을 이해하는 것이 좋다.