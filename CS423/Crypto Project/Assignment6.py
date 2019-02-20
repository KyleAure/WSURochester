#Written for python 2
#For import run `pip install passlib`
from passlib.hash import sha512_crypt

chars = "0123456789"
salts = {"msH6N3AG", "HFp/Mlc0", "g8FtcZYm", "mMHjRszK", "Q3MJoO84"}
file = open("Assets/passwords2.txt", "w")


for i in range(len(chars)):
    for j in range(len(chars)):
        for k in salts:
            p = "secure" + chars[i] + chars[j]
            hash = sha512_crypt.using(salt=k, rounds=5000).hash(p)
            file.write(p + " " + hash + "\n") 
            print p, hash


