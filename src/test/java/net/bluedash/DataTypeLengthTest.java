package net.bluedash;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class DataTypeLengthTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/* one char = one byte */
		ChannelBuffer buf = ChannelBuffers.buffer(1);
		buf.writeByte('c'); 

		while (buf.readable()) {
			System.out.println((char) buf.readByte());
			System.out.flush();
		}
		
		/* one int = 4 bytes */
		buf = ChannelBuffers.buffer(4);
		buf.writeInt(1);

		while (buf.readable()) {
			System.out.println((int) buf.readInt());
			System.out.flush();
		}
		
		buf = ChannelBuffers.buffer(1);
		try {
			buf.writeInt(1); /* int needs 4 bytes */
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.out.println("ArrayIndexOutOfBoundsException caught.");
		}
		
		
		

	}

}
