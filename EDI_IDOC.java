package com.havemester.po.EDI_IDOC;

import java.util.Arrays;

import com.havemester.po.mapping.test.ContainerImpl;
import com.havemester.po.mapping.test.TraceImpl;
import com.sap.aii.mapping.api.AbstractTrace;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mappingtool.tf.rt.ResultList;
import com.sap.aii.mappingtool.tf.rt.ResultListImpl;
import com.sap.aii.mappingtool.tf7.rt.Container;

public class IDOC {

	public static void main(String[] args) throws Exception {
		// initialize instance
		IDOC idoc = new IDOC();
		
		// initialize container
		Container container = (Container) new ContainerImpl();
		TraceImpl trace = (TraceImpl) container.getTrace();

		{
			// initialize result
			ResultList result = new ResultListImpl();
	
			String[] id   = { "__cC_", "F01", "F01", "F02", "F01" };
			String[] text = { "Line 1", "__cC_", "Line 2", "Line 3", "__cC_", "Line X", "__cC_", "Line 4" };
			String[] key  = { "F01" };
			
			idoc.getTextWithId(id, text, key, result, container);
			
			trace.result("getTextWithId: ", (ResultListImpl) result);
		}
		
		{
			// initialize result
			ResultList result = new ResultListImpl();
	
			String[] id   = { "KABE", "__cC_", "KABE" };
			String[] text = { "Line 1", "__cC_", "Line 2" };
			String[] key  = { "F01" };
			
			idoc.getTextWithId(id, text, key, result, container);
			
			trace.result("getTextWithId: ", (ResultListImpl) result);
		}
	}

	
	
	/*
	 * Get text from TDLINE for given id from TDID (IDOC E1EDKT01 / E1EDPT01)
	 * 
	 * (All values of a Queue)
	 */

	public void getTextWithId(String[] ids, String[] texts, String[] keys, ResultList result, Container container) throws StreamTransformationException {
//		GlobalContainer globalContainer = container.getGlobalContainer();
		AbstractTrace trace = container.getTrace();

		trace.addInfo("UDF getTextWithId - Begin");

		trace.addInfo ("Data-id:    " + ids.length    + " - " + Arrays.asList(ids));
		trace.addInfo ("Data-text:  " + texts.length  + " - " + Arrays.asList(texts));
		trace.addInfo ("Data-key:   " + keys.length   + " - " + Arrays.asList(keys));
	
		String key = keys[0];
		
		String text = null;
		int textC = 0;
		int count = 0;
		
		for (String id: ids) {
			if (id.equals(ResultList.CC)) {
				if (count == 0) {
					result.addSuppress();
				} else {
					count = 0;
				}
				
				result.addContextChange();
				continue;
			}
			
			while (! (text = texts[textC++]).equals(ResultList.CC)) {
				if (id.equals(key)) {
					result.addValue(text);
				}
				
				if (textC >= texts.length) {
					break;
				}
			}
		}
		
		if (count == 0) {
			result.addSuppress();
		}
		
		trace.addInfo("UDF getTextWithId - End");
	}		
}
