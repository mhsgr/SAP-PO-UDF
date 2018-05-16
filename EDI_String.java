package com.havemester.sap.po.EDI;

import com.havemester.sap.po.mapping.test.ContainerImpl;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mappingtool.tf7.rt.Container;



public class EDI_String {

	public static void main(String[] args) throws Exception {
		// initialize instance
		EDI_String str = new EDI_String();
		
		// initialize container
		Container container = (Container) new ContainerImpl();

		String test = "0123456789";
		
		System.out.println("cutLeft            " + test + " (4)  : " + str.cutLeft(test, 4, container));
		System.out.println("cutLeftP           " + test + " (4)  : " + str.cutLeftP(test, 4, container));
		System.out.println("cutRight           " + test + " (4)  : " + str.cutRight(test, 4, container));
		System.out.println("cutRightP          " + test + " (4)  : " + str.cutRightP(test, 4, container));
		System.out.println("removeLeadingZeros " + test + "      : " + str.removeLeadingZeros(test, container));
		System.out.println("removeLeadingZeros " + "0000000000" + "      : " + str.removeLeadingZeros("0000000000", container));
		System.out.println("subString          " + test + " (4,4): " + str.subString(test, 4, 4, container));
		System.out.println("subStringP         " + test + " (8,4): " + str.subStringP(test, 8, 4, container));
		System.out.println("subString1         " + test + " (4)  : " + str.subString1(test, 4, container));
		System.out.println("subString1P        " + test + " (8)  : " + str.subString1P(test, 8, container));
		System.out.println("trimLeft           " + test + " (8)  : " + str.trimLeft(test, 8, container));
		System.out.println("trimLeftP          " + test + " (4)  : " + str.trimLeftP(test, 4, container));
		System.out.println("trimRight          " + test + " (8)  : " + str.trimRight(test, 8, container));
		System.out.println("trimRightP         " + test + " (4)  : " + str.trimRightP(test, 4, container));
	}


	
	/*
	 * Remove number of characters from left side of string
	 */
	
	public String cutLeft(String str, int len, Container container) throws StreamTransformationException {
		if (str.length() > len) {
			return str.substring(len); 
		} else {
			return "";
		}
	}
	
	
	
	/*
	 * Remove number of characters from left side of string
	 */

	public String cutLeftP(String str, int len, Container container) throws StreamTransformationException {
		return this.cutLeft(str, len, container);
	}
	
	
	
	/*
	 * Cut string after number of characters
	 */
	
	public String cutRight(String str, int len, Container container) throws StreamTransformationException {
		if (str.length() > len) {
			return str.substring(0, len); 
		} else {
			return str;
		}
	}



	/*
	 * Cut string after number of characters
	 */
	
	public String cutRightP(String str, int len, Container container) throws StreamTransformationException {
		return this.cutRight(str, len, container);
	}
	
	
	
	/*
	 * Remove leading zeros
	 */
	
	public String removeLeadingZeros(String str, Container container) throws StreamTransformationException {
		return str.replaceFirst("^0+(?!$)", "");
	}
	
	
	
	/*
	 * Return substring without exception if string is too short
	 */
	
	public String subString(String str, int start, int length, Container container) throws StreamTransformationException {
		int len = str.length();

		if (start > len) {
			return "";
		}

		if ((length + start) >= len) {
				return (str.substring(start));
		}

		return (str.substring (start, start + length));
	}


	
	/*
	 * Return substring without exception if string is too short
	 */
	
	public String subStringP(String str, int start, int length, Container container) throws StreamTransformationException {
		return subString(str, start, length, container);
	}

	
	
	/*
	 * Return substring starting at position without exception if string is too short
	 */
	
	public String subString1(String str, int start, Container container) throws StreamTransformationException {
		int len = str.length();

		if (start > len) {
			return "";
		}

		return (str.substring(start));
	}


	/*
	 * Return substring starting at position without exception if string is too short
	 */
	
	public String subString1P(String str, int start, Container container) throws StreamTransformationException {
		return subString1(str, start, container);
	}

	
	
	/*
	 * Trim string to length number of characters. Remove characters from left side of string.
	 */
	
	public String trimLeft(String str, int length, Container container) throws StreamTransformationException {
		int len = str.length();
		
		if (len > length) {
			return str.substring(len - length);
		} else {
			return str;
		}
	}


	
	/*
	 * Trim string to length number of characters. Remove characters from left side of string.
	 */

	public String trimLeftP(String str, int length, Container container) throws StreamTransformationException {
		return trimLeft(str, length, container);
	}



	/*
	 * Trim string to length number of characters. Remove characters from right side of string.
	 */

	public String trimRight(String str, int length, Container container) throws StreamTransformationException {
		int len = str.length();
		
		if (len > length) {
			return str.substring(0, length);
		} else {
			return str;
		}
	}

	
	
	/*
	 * Trim string to length number of characters. Remove characters from right side of string.
	 */

	public String trimRightP(String str, int length, Container container) throws StreamTransformationException {
		return trimRight(str, length, container);
	}
}
