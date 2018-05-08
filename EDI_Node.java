package com.havemester.po.EDI_Node;

import com.havemester.po.mapping.test.ContainerImpl;
import com.havemester.po.mapping.test.TraceImpl;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mappingtool.tf.rt.ResultList;
import com.sap.aii.mappingtool.tf.rt.ResultListImpl;
import com.sap.aii.mappingtool.tf7.rt.Container;

public class node {

	public static void main(String[] args) throws Exception {
		node node = new node();
		
		// initialize container
		Container container = (Container) new ContainerImpl();
		TraceImpl trace = (TraceImpl) container.getTrace();

		System.out.println("passValue  test: " + node.passValue("test", container));
		System.out.println("clearValue test: " + node.clearValue("test", container));
					
		{
			// initialize result
			ResultList result = new ResultListImpl();
				
			node.getContextValue(new String[] { "1", "2" } , new int[] { 3 }, result, container);
			System.out.println("getContextValue: " +  ((ResultListImpl) result).get(0));
		}		
		
		{
			// initialize result
			ResultList result = new ResultListImpl();

			node.removeSUPRESS(new String[] { ResultList.SUPPRESS,  "Value 1",  "Value 2", ResultList.SUPPRESS }, result, container);
			trace.result("removeSUPPRESS: ", (ResultListImpl) result);
		}
	}

	
	
	/*
	 * Clear value (return empty string), except for SUPPRESS
	 */
	
	public String clearValue(String value, Container container) throws StreamTransformationException {
		if (value.equals(ResultList.SUPPRESS)) {
			return ResultList.SUPPRESS;
		}

		return "";
	}
	
	
	
	/*
	 * Pass input value to output (use for ifWithoutElse) to prevent SUPPRESS
	 */
	
	public String passValue(String value, Container container) throws StreamTransformationException {
		return value;
	}
	
	
	
	/*
	 * Return nth value of a context if it exists, return SUPPRESS otherwise
	 * 
	 * (All values of a context)
	 */

	public void getContextValue(String[] values, int[] index, ResultList result, Container container) throws StreamTransformationException {
		if ((values != null) && (values.length >= index[0])) {
			result.addValue(values[index[0] - 1]);
		} else {
			result.addSuppress();
		}
	}
	
	
	
	/*
	 * Remove SUPPRESS from queue
	 *
	 * (All values of a context)
	 */
	
	public void removeSUPRESS(String[] values, ResultList result, Container container) throws StreamTransformationException {
		if ((values != null) && (values.length > 0)) {
			for (String value: values) {
				if (! value.equals(ResultList.SUPPRESS)) {
					result.addValue(value);
				}
			}
		}
	}
}
