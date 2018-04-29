# -*- coding: utf-8 -*-
import sys

a=3
b=3

print a is b


print sys.getrefcount(3)

# duel은 메모리에 생성된 변수를 없앤다.
del(a)
print sys.getrefcount(3)


a = [1,2,3]
b = a

a.append(4)

print b

from copy import copy
b = copy(a)
a.append(5)

print b
















