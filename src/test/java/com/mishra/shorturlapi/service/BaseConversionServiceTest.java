/**
 * 
 */
package com.mishra.shorturlapi.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author vishal
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class BaseConversionServiceTest {
	

    private BaseConversionService baseConversion = new BaseConversionService();

    @Test
    public void encode_lessThan62() {
        assertEquals("j", baseConversion.encode(9));
    }

    @Test
    public void encode_moreThan62() {
        assertEquals("br", baseConversion.encode(79));
    }

    @Test
    public void decode_singleCharacter() {
        assertEquals(12, baseConversion.decode("m"));
    }

}
