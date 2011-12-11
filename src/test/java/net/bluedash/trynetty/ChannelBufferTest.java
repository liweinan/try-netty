package net.bluedash.trynetty;

import static org.junit.Assert.*;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.junit.Test;

public class ChannelBufferTest {

	@Test
	public void testIndexes() {
		ChannelBuffer buf = ChannelBuffers.buffer(1000);
		assertEquals(0, buf.readerIndex());
		assertEquals(0, buf.writerIndex());

		buf.writeInt(1);
		assertEquals(4, buf.writerIndex()); /* int will use 4 bytes */

		buf.readByte();
		assertEquals(1, buf.readerIndex());

		buf.discardReadBytes();
		assertEquals(0, buf.readerIndex());
		assertEquals(3, buf.writerIndex());

	}

}
