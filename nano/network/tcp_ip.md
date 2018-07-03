TCP/IP
====================

## Intro
tcp/ip를 처음 접했을 때는 대학교 2학년 때였다. 
로컬 개발의 한계를 느낄 때 즈음 네트워크를 통해 다른 컴퓨터의 프로그램과 통신하는 방법은 상당히 매력적으로 다가왔다.

 하지만 패킷, 라우터, 논리주소와 물리주소, ocp 계층 등은 너무나 혼란스러웠다. 단기간에 많은 내용을 이해하기 어려웠고 결국 시험이 끝남과 동시에 많은 지식을 잊어버렸다. 그러면서 합리화가 찾아왔다. 사실 사용하는 법만 알면 되는거 아닌가? 라는 의문이 들었다.

 현업을 와서 느낀 것이 있다면
1. 내가 사용할 줄 안다고 해서 그것을 잘 아는 것은 아니다.
2. TCP의 아주 로우한 부분까지 이해하면 아주 도움이 되는 것이 많다.
3. 서버 개발자라면 2를 만족하는 것이 좋다.

그래서 다시 기억을 더듬으면서 다시 tcp 개념을 다잡을 것이다.

## tcp overview

- Connection-oriented : Provides establishment, maintenance and termination of a logical connection
- Reliable data transfer :
    - Provide absolutely perfect service to higher layer
    - Error control, Flow control, Congestion control
    - In-order byte stream : Each octet is numbered sequenttially
- Support only point to point
    - in the logical connection, there are only one sender and one receiver
    - Does not support multicast


# Tcp segment 
tcp의 헤더 기본 사이즈는 20 byte이고, option은 최대 40byte까지 가능하므로, 헤더의 최대 사이즈는. 60byte이다.
Tcp 헤더는 항상 4의 배수로 되는데 이를 맞추기 위해 Option에 EOP(End Of Option), NOP(No Operation Option)이 유일한 단일 바이트 옵션으로 존재한다. 이를 패딩처럼 활용해서 4byte를 억지로라도 맞춘다.
<사진>

#### 주의 
<pre>
6개의 flag
U : URG(urgent data) 로 긴급 데이터를 담은 세그먼트를 표시할 때 사용한다. 실제로 거의 사용 안 된다.
A : ACK 이 세그먼트가 Acknowledgement 를 알리기 위한 목적인지 표시
P : PSH(Push Data now) 얼른 하위 레이어로 보내라는 플래그
R/S/F : RESET, SYN, FIN : Connection control (이 3개 중 하나라도 1이라면 TCP 세그먼트는 데이터를 실어나르지 못한다. RESET은 비정상 경우임

Rcvr window size : 송신자가 자신의 여유 버퍼사이즈를 기록하여 넘기는 것으로, 수신측은 이를 받아서 보내줄 데이터의 양을 결정할 수 있으므로, Flow control이 일어나게된다.
</pre>

### Sequence number
- byte단위로 기록되며, 11번째 바이트~20번째 바이트(총 10바이트)를 보내면 sequence number는 초기 값+11이다.
- 초기값은 랜덤하게 세팅된다.
    - 첫번째 이유 : 0부터 보내면 시퀀스 넘버 만으로 얼마나 많은 데이터가 오갔는지 유추가능하기에 보안적으로 허술
    - 두번째 이유 : 이전 연결에서 남아있던 세그먼트가 현재 연결에서 읽힐 수가 있음. 그래서 랜덤하게 시작하면 이를 회피가능.
- 32bit 라서 2의 32승의 모듈러 값이 유효한 값이다.

### Acknowledgement number
- Seq number of next byte expected from other side”
- Ack flag가 1이여야 한다.
- 송신자의 seq값 s와 ack값 a이 수신자가 받으면 다시 보낼때는 seq을 a로, ack를 s+1로 보낸다. 이게 반복

### Option
<pre>
- Single byte : EOP, NOP 
- Multi bytes :
    - Maximum segment size : 세그먼트의 최대 사이즈를 기록해두는 곳으로, 연결 설정 시 결정되고 연결 종료까지 변화 없음
    - Window scale factor
    - Timestamp
    - SACK-permitted
    - SACK
</pre>
Tcp 헤더는 항상 4의 배수로 되는데 이를 맞추기 위해 Option에 EOP(End Of Option), NOP(No Operation Option)이 유일한 단일 바이트 옵션으로 존재한다. 이를 패딩처럼 활용해서 4byte를 억지로라도 맞춘다.

### Timestamp option
Used to measure round trip time (from sending tcp segment to receiving ACK)
Based on Greenwich standard time
<사진>

<pre>
송신측에서 round trip 시간 측정을 위해 TS value를 세팅해서 보냄
받는 측에서는 이 값을 그대로 복사해서 TS Echo Replay에 둔다. 다시 원래 송신자가 받을때는 current time - TS Echo를 하면 원하는 값이 나온다.

그러면 왜 복사할까?
수신측에서 여러개의 패킷(세그먼트)을 받았고 이들 각각 Timestamp option이 추가되어 있으면??
한꺼번에 하나의 패킷에 Timestamp를 찍어서 보내도 된다.
그러면 어느 패킷의 time stamp인지를 기록하기 위해서 Time Echo를 쓴다.
그러면서 Ack은 여러 패킷을 받은 것을 고려해서 기입한다.
</pre>

## Connection
TCP  sender, receiver establish connection before exchanging data segments
- Triggers allocation of transport entity resources (ex. buffers)
- Negotiation of params (ex. Initial seq no, flow control info (receive window), MSS

Connection determined by source and destination sockets (IP address of host, port number)
포트 쌍에서는 오직 하나의 connection만 존재 할 수 있다.

### connection establishment
Connection open : active(client) / passive(server)

#### 3-way handshaking
너무나 많이 들어봤을 거다. 이제는 완벽하게 익히자
<사진>










