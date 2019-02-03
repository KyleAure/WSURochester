#Written for python 2
# CONSTANTS
# ALPHA - string (aka array of chars) representing english alphabet
# MOD - length of alphabet where rotation occures
# SHIFT - shift constant for encoding and decoding
ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
MOD = 26
SHIFT = 13

#FUNCTIONS
def getInput():
    return raw_input("Enter message, like HELLO:")

# LOOP - for each character in input string
# find it's location in alpha and then offset it to new location
# add new character to output string.
def rot13( str_in ):
    n = len(str_in)
    str_out = ""

    for i in range(n):
        c = str_in[i]
        loc = ALPHA.find(c)
        newLoc = (loc + SHIFT) % MOD
        str_out += ALPHA[newLoc]
    return str_out

# Print results
encoded = rot13(getInput())
decoded = rot13(encoded)
print "Obfuscated Verson:", encoded
print "Decoded Version:", decoded