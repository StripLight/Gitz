package org.gitz;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Helper class with static assert methods
 */
public class Assert {

	/**
	 * Verify the arrays match
	 * @param bits
	 * @param commandBits
	 */
	public static void assertArray(String[] bits, String[] commandBits) {

        assertEquals("array size should be the same", bits.length, commandBits.length);

        for (String bit : bits) {
            boolean b = false;
            for (String c : commandBits) {
                if (bit.equals(c)) {
                    b = true;
                    break;
                }
            }
            assertTrue("elem " + bit + " should be in  " + Arrays.toString(commandBits), b);
        }
    }

}
