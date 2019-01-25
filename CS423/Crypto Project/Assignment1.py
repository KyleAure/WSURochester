# ALPHABET - string (aka array of chars) representing english alphabet
alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

# USER INPUT - String and shift distance
str_in = raw_input("Enter message, like HELLO:")
shift = int(raw_input("Shift Value, like 3:"))

# GET - length of input string
# SETUP - output string
n = len(str_in)
str_out = ""

# LOOP - for each character in input string
# find it's location in alpha and then offset it to new location
# add new character to output string.
for i in range(n):
    c = str_in[i]
    loc = alpha.find(c)
    newLoc = (loc + shift) % 26
    str_out += alpha[newLoc]

# PRINT - resulting output string 
print "Obfuscated Verson:", str_out