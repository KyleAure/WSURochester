package CRC8;

// The generator is x^8 + x^2 + x + 1
// The only method is checksum() which takes the byte array data,
// and returns the computed checksum for data.

/**
 * CRC8 class provided by instructor
 */
public class CRC8   {
	public byte checksum(byte[] data)   {
		short _register = 0;
		short bitMask = 0;
		short poly = 0;
		_register = data[0];
		
		for (int i=1; i<data.length; i++)  {
			_register = (short)((_register << 8) | (data[i] & 0x00ff));
			poly = (short)(0x0107 << 7);
			bitMask = (short)0x8000;

			while (bitMask != 0x0080)  {
				if ((_register & bitMask) != 0) {
					_register ^= poly;
				}
				poly = (short) ((poly&0x0000ffff) >>> 1);
				bitMask = (short)((bitMask&0x0000ffff) >>> 1);
			}  //end while
		}  //end for
		return (byte)_register;
	}
}
