import math
number_of_buckets = 10

def myHashFunction(value):
    return i%number_of_buckets

def myHashFunciton2(value):
    return abs(hash(value)) % number_of_buckets #value % 10

def compression(hash_value):
    return abs(hash_value)
elements = ["hello", "world", "this", "is", "my", "table"]
# for i in range(501):
#     print(f"{i} goes to bucket {myHashFunction(i)}")

for i in elements:
    print(f"{i} goes to bucket {myHashFunciton2(i)}")