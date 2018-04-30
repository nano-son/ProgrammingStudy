# -*- coding: utf8 -*-
from math import *
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

# 파일 입출력을 이렇게 할 수 있다.

with open("hello.txt",'r') as f:
	while True:
		line = f.readline()
		if not line: break
		if '\n' in line:
			print line,
		else:
			print line



def biggest_number(*args):
  print max(args)
  return max(args)
    
def smallest_number(*args):
  print min(args)
  return min(args)

def distance_from_zero(arg):
  print abs(arg)
  return abs(arg)

biggest_number(-10, -5, 5, 10)
smallest_number(-10, -5, 5, 10)
distance_from_zero(-10)


print type(max(1,2,3))
print type(pi)
print type("hello world")


inspectation_of_math = dir(math)
print inspectation_of_math


