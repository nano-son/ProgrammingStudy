#화살표 함수

>중괄호 갯수를 줄일 수 있는 코딩 스타일이라고 하는데 아직 잘 모르겠다. 아래 두 코드는 같은 코드이다. dynamic this 와 lexical this 이 부분을 공부해야된다.

<pre>
<code>

friends.forEach(function (friend) {
	console.log(that.name + " says hello to " + friend)
});

</code>
</pre>

<pre>
<code>

friends.forEach(friend => {
	console.log(this.name + " says hello to " + friend)
});

</code>
</pre>


