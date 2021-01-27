/**
 * 
 */
package com.mishra.shorturlapi.service;

import org.springframework.stereotype.Service;

/**
 * @author vishal
 *
 */

@Service
public class BaseConversionService {
	
	private static final String base62String = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // [a-z][A-Z][0-9]
	private char[] base62Characters = base62String.toCharArray();
	private int baseValue = base62Characters.length;
	
    /**
     * @param input : a number
     * @return : a short link
     */
    public String encode(long input){
        var encodedString = new StringBuilder();

        if(input == 0) {
            return String.valueOf(base62Characters[0]);
        }

        while (input > 0) {
            encodedString.append(base62Characters[(int) (input % baseValue)]);
            input = input / baseValue;
        }

        return encodedString.reverse().toString();
    }

    /**
     * @param input : a short link
     * @return : a number
     */
    public long decode(String input) {
        var characters = input.toCharArray();
        var length = characters.length;

        var decoded = 0;

        //counter is used to avoid reversing input string
        var counter = 1;
        for (int i = 0; i < length; i++) {
            decoded += base62String.indexOf(characters[i]) * Math.pow(baseValue, length - counter);
            counter++;
        }
        return decoded;
    }
	
	

}
