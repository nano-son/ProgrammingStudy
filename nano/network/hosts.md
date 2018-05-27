hosts 파일
=========================

## hosts file??
> 간단하게 말해서 ip 전화번호부 역할의 파일이다.

도메인 시스템을 거치지 않고도 특정 ip에 이름을 내가 부여할 수 있다.
모든 os에는 hosts 파일이 존재한다. 운영체제마다 파일의 위치는 다를 수 있으니 검색해보도록.

mac os 의 경우에는 /etc/hosts이다.
hosts파일은 시스템, 보안적으로 매우 중요한 파일이기에 관리자 권한이 필요하다.
sudo cat /etc/hosts를 하니 다음과 같이 나왔다.
<pre>

# BEGIN hosts added by Pulse
211.40.142.21   vpn.kakaocorp.com
# END hosts added by Pulse
##
# Host Database
#
# localhost is used to configure the loopback interface
# when the system is booting.  Do not change this entry.
##
127.0.0.1	localhost
255.255.255.255	broadcasthost
::1             localhost

</pre>

살펴보면 127.0.0.1 에 대한 아이피를 직접 치는 대신 localhost를 쳐도 된다.
즉, 브라우저에 localhost를 치면 브라우저는 최우선적으로 hosts파일을 확인해서 'localhost'에 해당하는 아이피가 있는지 확인하고 연결해준다.

이렇게 동작하는 이유는 브라우저의 동작원리 때문이다. hosts가 중요하다는 이유는 바로 여기에 있다.
브라우저에 도메인 명을 입력하면 브라우저는 해당 컴퓨터의 hosts파일을 가장 먼저 확인한다. 
매칭되는 주소가 없으면 그제서야 라우터로부터 ip주소를 얻어오게 된다.


이러한 점 때문에 편리할 수 있지만 잘못 사용했을 때 보안 위험이 존재할 수 있다.

만약 해커가 내 컴퓨터의 hosts파일에 접근해서
<pre>
123.123.123.123   www.naver.com 
</pre>
이라고 입력한다면 내가 브라우저에 www.naver.com을 치게 되면 해커의 서버로 접속하게 된다.

물론 ssl(https)를 사용하는 사이트의 경우 눈썰미가 좋다면 눈치챌 수도 있지만, 발견하기 쉽지는 않다.
따라서 웬만하면 hosts를 건드리지 않는게 좋다. 

그래도 역시 잘 사용하면 편리하다.
