import base64

#FUNCTIONS
def getInput():
    return raw_input("Enter message:")

#ENCODING DATA
encoded_data = base64.b64encode( getInput() )

#DECODING DATA
decoded_data = encoded_data.decode("base64")

#Print results
print "encoded message:", encoded_data
print "decoded message:", decoded_data

