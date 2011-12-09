package net.bluedash.trynetty;

import static org.junit.Assert.assertEquals;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.junit.Test;

public class DataTypeTest {

	@Test
	public void testChar() {
		/* char = one byte */
		ChannelBuffer buf = ChannelBuffers.buffer(1);
		buf.writeByte('c');

		assertEquals(true, buf.readable());
		assertEquals('c', (char) buf.readByte());
		assertEquals(false, buf.readable());
	}

	@Test
	public void testInt() {
		/* int = 4 bytes */
		ChannelBuffer buf = ChannelBuffers.buffer(4);
		buf.writeInt(1);

		assertEquals(true, buf.readable());
		assertEquals(1, (int) buf.readInt());
		assertEquals(false, buf.readable());

	}

	@Test
	public void testLong() {
		/* long = 8 bytes */
		ChannelBuffer buf = ChannelBuffers.buffer(8);
		buf.writeLong(1);
		assertEquals(true, buf.readable());
		assertEquals(1, (int) buf.readLong());
		assertEquals(false, buf.readable());
	}

	@Test
	public void testReadDataFragments() {
		ChannelBuffer buf = ChannelBuffers.buffer(8);
		buf.writeLong(1);
		assertEquals(true, buf.readable());
		for (int i = 0; i < 7; i++)
			assertEquals(0, (int) buf.readByte());
		assertEquals(1, (int) buf.readByte());
		assertEquals(false, buf.readable());		
	}

	@Test(expected = java.lang.ArrayIndexOutOfBoundsException.class)
	public void testNotEnoughSpace() {
		ChannelBuffer buf = ChannelBuffers.buffer(1);
		buf.writeInt(0);
	}

	@Test
	public void testSign() {
		ChannelBuffer buf = ChannelBuffers.buffer(1); /* one byte */

		/*
		 * byte is from -128 ~ 127
		 */
		buf.writeByte(255);
		assertEquals((byte) -1, buf.readByte());
		
		buf.clear();
		buf.writeByte(128);
		assertEquals((byte) -128, buf.readByte());

		buf.clear();
		buf.writeByte(127);
		assertEquals((byte) 127, buf.readByte());
		
		buf.clear();
		buf.writeByte(1000); /* longer bits will be truncated */
		assertEquals((byte) -24, buf.readByte());

		/* conversion logic demo */
		assertEquals(-1, convertIntToByteValue(255));
		assertEquals(-128, convertIntToByteValue(128));
		assertEquals(127, convertIntToByteValue(127));
		assertEquals(-24, convertIntToByteValue(1000));

	}

	public static int convertIntToByteValue(int i) {
		if (i >= 0 && i < 128)
			return i;

		i = ~i; /* taking the complement */
		i = i & 0xFF; /* 1 byte = 8 bits, truncate unnecessary bits. */
		i = i + 1;
		i = -i;
		return i;
	}

}
