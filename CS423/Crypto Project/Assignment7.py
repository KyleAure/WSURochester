#!/usr/bin/env python
#Install Crypto using sudo easy_install pycrypto
from Crypto.Cipher import AES
import base64
import os

# the block size for the cipher object; must be 16 per FIPS-197
BLOCK_SIZE = 16

# the character used for padding--with a block cipher such as AES, the value
# you encrypt must be a multiple of BLOCK_SIZE in length.  This character is
# used to ensure that your value is always a multiple of BLOCK_SIZE
PADDING = '{'

# one-liner to sufficiently pad the text to be encrypted
pad = lambda s: s + (BLOCK_SIZE - len(s) % BLOCK_SIZE) * PADDING

# one-liners to encrypt/encode and decrypt/decode a string
# encrypt with AES, encode with base64
EncodeAES = lambda c, s: base64.b64encode(c.encrypt(pad(s)))
DecodeAES = lambda c, e: c.decrypt(base64.b64decode(e)).rstrip(PADDING)

# generate a random secret key
secret = "ThisIsASecret123"
# create a cipher object using the random secret
cipher = AES.new(secret)

# encode a string
encoded = EncodeAES(cipher, 'This is a secret message with import information XD.')
print 'Encrypted string:', encoded
print 'Secret Key:', secret

# decode the encoded string
decoded = DecodeAES(cipher, encoded)
print 'Decrypted string:', decoded 
