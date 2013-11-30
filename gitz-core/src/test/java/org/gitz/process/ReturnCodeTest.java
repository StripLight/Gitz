package org.gitz.process;

import org.gitz.interfaces.ReturnCode;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Test the return code enum
 */
public class ReturnCodeTest {

	@Test
	public void testAll() {

		ReturnCode rt1 = ReturnCode.OK;
		assertEquals(rt1.getCode(), 0);

		ReturnCode rt2 = ReturnCode.INVALID_ARGS;
		assertEquals(rt2.getCode(), -1);

		ReturnCode rt3 = ReturnCode.UNSPECIFIED_ERROR;
		assertEquals(rt3.getCode(), -2);

	}
}
