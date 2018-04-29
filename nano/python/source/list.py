

num_list = [1,2,3,4,5]

print "="*20
print num_list

print "="*20
split = num_list[1:4] 
print split

print "="*20
num_list[2] *=2
print num_list

print "="*20
num_list[2]=3
num_list[2:3]=[3,33,333]
print num_list

print "="*20
num_list[3:5]=[]
print num_list

print "="*20
del num_list[2]
print num_list

print "="*20
num_list.insert(2,3)
print num_list

print "="*20
num_list += [6,7,8,"hello"]
print num_list





