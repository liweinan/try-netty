package net.bluedash.trynetty;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class PathTest {

	@Test
	public void testDifferentPaths() {
		System.out.println(File.separatorChar);
		System.out.println(File.pathSeparator);
		System.out.println(File.pathSeparatorChar);
		System.out.println(System.getProperty("java.home"));
		System.out.println(System.getProperty("java.class.path"));
		System.out.println(System.getProperty("user.home"));
		System.out.println(System.getProperty("user.dir")); /* current working directory */
		assertTrue(true);
	}
}
