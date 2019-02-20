# Written for Python3
# For imports install `pip install pycrypto pillow`
from PIL import Image
from Crypto.Cipher import AES

# File formatting and key
filename = "Assets/Unencrypted.png"
filename_out_encryped = "Assets/Encrypted"
filename_out_decrypted = "Assets/Decrypted"
format = "png"
key = "aaaabbbbccccdddd"
 
# AES requires that plaintexts be a multiple of 16, so we have to pad the data
def pad(data):
    return data + b"\x00"*(16-len(data)%16) 
 
# Maps the RGB 
def convert_to_RGB(data):
    r, g, b = tuple(map(lambda d: [data[i] for i in range(0,len(data)) if i % 3 == d], [0, 1, 2]))
    pixels = tuple(zip(r,g,b))
    return pixels

# process image for encryption
def process_image_enc(filename, mode=AES.MODE_ECB):
    # Opens image and converts it to RGB format for PIL
    im = Image.open(filename)
    data = im.convert("RGB").tobytes() 
 
    # Since we will pad the data to satisfy AES's multiple-of-16 requirement, we will store the original data length and "unpad" it later.
    original = len(data) 
 
    # Encrypts using desired AES mode (we'll set it to ECB by default)
    if(mode==AES.MODE_ECB):
        new = convert_to_RGB(aes_ecb_encrypt(key, pad(data))[:original]) 
    else:
        new = convert_to_RGB(aes_cbc_encrypt(key, pad(data))[:original]) 
    
    # Create a new PIL Image object and save the old image data into the new image.
    im2 = Image.new(im.mode, im.size)
    im2.putdata(new)
    
    #Save image
    im2.save(filename_out_encryped+str(mode)+"."+format, format)

def process_image_dec(filename, mode=AES.MODE_ECB):
    #Opens image file
    im = Image.open(filename)
    data = im.convert("RGB").tobytes()

    # Since we will pad the data to satisfy AES's multiple-of-16 requirement, we will store the original data length and "unpad" it later.
    original = len(data) 

    # Decrypts using desired AES mode (we'll set it to ECB by default)
    if(mode==AES.MODE_ECB):
        new = convert_to_RGB(aes_ecb_decrypt(key, pad(data))[:original])
    else:
        new = convert_to_RGB(aes_cbc_decrypt(key, pad(data))[:original])
    
    # Create a new PIL Image object and save the old image data into the new image.
    im2 = Image.new(im.mode, im.size)
    im2.putdata(new)

    #Save image
    im2.save(filename_out_decrypted+str(mode)+"."+format, format)
 
# CBC
def aes_cbc_encrypt(key, data, mode=AES.MODE_CBC):
    IV = "A"*16  #We'll manually set the initialization vector to simplify things
    aes = AES.new(key, mode, IV)
    new_data = aes.encrypt(data)
    return new_data

def aes_cbc_decrypt(key, data, mode=AES.MODE_CBC):
    IV = "A"*16  #We'll manually set the initialization vector to simplify things
    aes = AES.new(key, mode, IV)
    new_data = aes.decrypt(data)
    return new_data

# ECB
def aes_ecb_encrypt(key, data, mode=AES.MODE_ECB):
    aes = AES.new(key, mode)
    new_data = aes.encrypt(data)
    return new_data

def aes_ecb_decrypt(key, data, mode=AES.MODE_ECB):
    aes = AES.new(key, mode)
    new_data = aes.decrypt(data)
    return new_data
 
process_image_enc(filename, AES.MODE_ECB)
process_image_enc(filename, AES.MODE_CBC)

process_image_dec(filename_out_encryped+str(AES.MODE_ECB)+"."+format, AES.MODE_ECB)
process_image_dec(filename_out_encryped+str(AES.MODE_CBC)+"."+format, AES.MODE_CBC)
