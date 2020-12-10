package com.wartest.util;

import javax.swing.JOptionPane;

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
	
	/**
	 * Print out the connection error
	 */
	public static void connectionError() {
		JOptionPane.showMessageDialog(null, "Looks like you have some connection failure."
				+ "\nPlease open com/wartest/util/DbUtil.java and check your MySQL username and password.");
	}
	
	/**
	 * Print out the disconnection error
	 */
	public static void disconnectionError() {
		JOptionPane.showMessageDialog(null, "Looks like you have some disconnection failure."
				+ "\nPlease open com/wartest/util/DbUtil.java and check your MySQL username and password.");
	}
	
}
