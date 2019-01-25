import hashlib
chars = "0123456789"
file = open("Assets/passwords.txt", "w")

for i in range(len(chars)):
    for j in range(len(chars)):
        p = chars[i] + chars[j]
        hash = hashlib.new("md4", p.encode("utf-16le")).hexdigest()
        file.write(p + " " + hash + "\n") 
        print p, hash
