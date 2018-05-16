package com.havemester.sap.po.EDI;

import com.havemester.sap.po.mapping.test.ContainerImpl;
import com.sap.aii.mapping.api.AbstractTrace;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mappingtool.tf.rt.ResultList;
import com.sap.aii.mappingtool.tf.rt.ResultListImpl;
import com.sap.aii.mappingtool.tf7.rt.Container;

public class EDI_Name {

	public static void main(String[] args) throws Exception {
		ResultList result1 = new ResultListImpl();
		ResultList result2 = new ResultListImpl();
		ResultList result3 = new ResultListImpl();
		
	    // initialize container
	    Container container = (Container) new ContainerImpl();

	    String[] name1   = { "Christian Maria Stefan", "Christian Stefan", "Christian Stefan" };
	    String[] name2   = { "Müller-Herrenberg", "Müller", "Müller" };
	    String[] name3   = { "", "" };
	    int[]    maxL    = { 16, 16, 16 };
	    int[]    number  = { 1, 2, 3 };

	    nameSplit(name1, name2, name3, maxL, number, result1, result2, result3, container);
	}

	
	
	/*
	 * Concatenate name strings and split after length number of characters, return number portion of string
	 * 
	 * (All values of a context)
	 */
	
	public static void nameSplit(String[] name1, String[] name2, String[] name3, int[] maxLength, int[] number,
			ResultList result1, ResultList result2, ResultList result3, Container container) throws StreamTransformationException {
		AbstractTrace trace=container.getTrace();

		trace.addInfo("UDF nameSplit - Begin");
		
		for (int counter = 0; counter < name1.length; counter++) {
			String[] result = { "", "", ""};

			String n1 = name1.length > counter ? name1[counter].trim() : "";
			String n2 = name2.length > counter ? name2[counter].trim() : "";
			String n3 = name3.length > counter ? name3[counter].trim() : "";
			
			String name = ((n1 + " " + n2).trim() + " " + n3).trim();
			int len = name.length();
		
			int length = maxLength[0];
		
			trace.addInfo("  INPUT: " + name + " - " + len);

			if ((n1.length() <= length) &&
				(n2.length() <= length) &&
				(n3.length() <= length)) {
							
				result[0] = n1;
				result[1] = n2;
				result[2] = n3;
			} else {
				if (len <= length) {
					result[0] = name;
			
				} else {
					if (len <= (2 * length)) {
						String tmp = name.substring(0, length);
						Integer n;
							
						n = tmp.lastIndexOf (' ');
						    
						if (n < 0) {
							n = length;
						}
			
						result[0] = name.substring (0, n).trim();
						result[1] = name.substring (n + 1).trim();
			
						if (result[1].length() > length) {
							result[0] = tmp;
							result[1] = name.substring(length).trim();
						}
					} else {
						result[0] = name.substring(0, length).trim();
					  result[1] = name.substring(length, 2 * length).trim();
			
						if (name.length() >= 3 * length) {
							result[2] = name.substring(2 * length,3 * length).trim();
						} else {
							result[2] = name.substring(2 * length).trim();
						}
					}
				}
			}
			
			for (int i = 0; i < 3; i++) {
				trace.addInfo ("  RESULT" + i + " (len: " + result[i].length() + "): \"" + result[i] + "\"");
			}
		
			result1.addValue(result[number[counter] - 1]);
		}
		
		trace.addInfo("UDF nameSplit - End");
	}
}
