my_hash = {"name":"nano", "age":26, "hometown":"gyungsan"}

print my_hash
print "name:%s" %my_hash['name']

my_hash["hello"] = "world"

print my_hash


del my_hash["hello"]
print my_hash


h2 = {}
h2[1] = "1"
print h2


print my_hash.keys()
print my_hash.values()
print list(my_hash.keys())


print "hello" in my_hash
print "name" in my_hash
print 'nano' in my_hash







