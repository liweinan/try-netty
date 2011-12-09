package net.bluedash.trynetty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class TryChannelBuffer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ChannelBuffer buf = ChannelBuffers.buffer(4);
		buf.writeInt(1);

		while (buf.readable()) {
			System.out.println((char) buf.readByte());
			System.out.flush();
		}

	}

}
