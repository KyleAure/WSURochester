# Written for python 2
# Inspired from http://coding4streetcred.com/blog/post/Asymmetric-Encryption-Revisited-(in-PyCrypto)
# PyCrypto docs available at https://www.dlitz.net/software/pycrypto/api/2.6/

from Crypto import Random
from Crypto.PublicKey import RSA
import base64

def generate_keys():
	# RSA modulus length must be a multiple of 256 and >= 1024
	modulus_length = 256*4 # use larger value in production
	privatekey = RSA.generate(modulus_length, Random.new().read)
	publickey = privatekey.publickey()
	return privatekey, publickey

def encrypt_message(a_message , publickey):
	encrypted_msg = publickey.encrypt(a_message, 32)[0]
	encoded_encrypted_msg = base64.b64encode(encrypted_msg) # base64 encoded strings are database friendly
	return encoded_encrypted_msg

def decrypt_message(encoded_encrypted_msg, privatekey):
	decoded_encrypted_msg = base64.b64decode(encoded_encrypted_msg)
	decoded_decrypted_msg = privatekey.decrypt(decoded_encrypted_msg)
	return decoded_decrypted_msg

########## BEGIN ##########
### Messages
a_message = 'This is a secret message with import information XD.'
em = 'RMpHwfVEDhyPra1XFFTEASGbcD4or56FqdbN7jnfkNo0OdCgHxVD5IM5/siepLfm9eS68onhRnEqOrTLXFQ1/J4yPcy8GzHyKZp1jsUdTg3DbmlZyugEe7mQfnh2YooixBjzbIWuz9EHpYdbAJ3fDbThf1VyOai8baVTgy9dtuc= - (172)'

### My Keys
myprivatekey = RSA.importKey(open('Keys/id_rsa', 'rb'))
mypublickey = RSA.importKey(open('Keys/id_rsa.pub', 'rb'))

### Joe's public key
joepublickey = RSA.importKey(open('Keys/id_rsa_joe.pub', 'rb'))

### To encode message
#encrypted_msg = encrypt_message(a_message , joepublickey)

### To decode a message.
decrypted_msg = decrypt_message(em, myprivatekey)

#print "%s - (%d)" % (privatekey.exportKey() , len(privatekey.exportKey()))
#print "%s - (%d)" % (publickey.exportKey() , len(publickey.exportKey()))
#print " Original content: %s - (%d)" % (a_message, len(a_message))
#print "Encrypted message: %s - (%d)" % (encrypted_msg, len(encrypted_msg))
print "Decrypted message: %s - (%d)" % (decrypted_msg, len(decrypted_msg))
