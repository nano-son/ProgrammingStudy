def greeting(name, hobby):
	print "hello world! this is greeting function! ",
	print "your name is %s, and your hobby is %s !! awesome!!" %(name, hobby)

print 'asd';
print "="*20
greeting('nano', 'playing soccer')


# print str(math(3,5,'-'))

def math(a,b,operator):
	if operator=="+":
		return a+b
	elif operator=='-':
		return a-b
	elif operator=='/':
		return a/b
	elif operator=='*':
		return a*b
	else:
		print "error! wrong operator!!"
	return


print str( math(1,2,'+') )
print str( math(1,5,'-'))


def print_num(n):
	for i in range(0,n+1):
		print str(i)+" ",
	print "bye!"

print print_num(10) #it would print 'none'. because, print_num function return nothing.

print str(math(math(1,2,'+'),10,'*'))


with open("hello.txt",'r') as f:
	while True:
		line = f.readline()
		if not line: break
		if '\n' in line:
			print line,
		else:
			print line















