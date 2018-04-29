
s1 = set([1,2,3])
print s1

s2 = set("Hello")
print s2


s3 = set(["hello", "world"])
print s3

print "\nintersection==============="
s1 = set([1,2,3,4,5,6])
s2 = set([2,4,6,7,8])
print s1&s2
print s1.intersection(s2)

print "\nunion===================="
print s1 | s2
print s1.union(s2)

print "\ndifferences============="
print s1-s2
print s2-s1
print s1.difference(s2)
print s2.difference(s1)


















