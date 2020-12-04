package com.wartest.util;

/**
 * String Util
 * @author liu.zehu
 *
 */
public class StringUtil {
	/**
	 * Determine whether string is empty
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().equals(""));
	}
	
	/**
	 * Determine whether string is not empty
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return (str != null && !str.trim().equals(""));
	}
	
	/**
	 * Replace the quotation note in the search textfield to avoid sql injection
	 * @param string
	 * @return
	 */
	public static String replaceQuoteWithStar(String string) {
		char[] s = string.toCharArray();
		for (int i = 0; i < s.length; i++) {
			if (s[i] == '\'')
				s[i] = '*';
		}
		return String.valueOf(s);
	}
}
