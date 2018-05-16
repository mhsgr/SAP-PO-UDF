package com.havemester.sap.po.EDI;

import java.util.HashMap;
import java.util.Map;

import com.havemester.sap.po.mapping.test.ContainerImpl;
import com.sap.aii.mapping.api.AbstractTrace;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mappingtool.tf7.rt.Container;
import com.sap.aii.mappingtool.tf7.rt.GlobalContainer;



public class EDI_Map {

	public static void main(String[] args) throws Exception {
	    // initialize container
		Container container = (Container) new ContainerImpl();

		hasValue ("test", "MAP_PARTYID", container);
		getValue ("test", "MAP_PARTYID", container);
		putValue("5533800", "4711", "MAP_PARTYID", container);
		putValue("5533801", "4712", "MAP_PARTYID", container);
		hasValue ("5533800", "MAP_PARTYID", container);
		getValue ("5533800", "MAP_PARTYID", container);
		hasValue ("5533802", "MAP_PARTYID", container);
		getValue ("5533802", "MAP_PARTYID", container);
	}

	
	
	public static String putValue(String key, String value, String name, Container container) throws StreamTransformationException {
		GlobalContainer globalContainer = container.getGlobalContainer();
		AbstractTrace trace = container.getTrace();

		trace.addInfo("UDF putValue - Begin");
		trace.addInfo("  Key:   " + key);
		trace.addInfo("  Value: " + value);
		
		boolean mapNew = false;
		
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String,String>) globalContainer.getParameter(name);
		
		if (map == null) {
			map = new HashMap<String,String>();
			mapNew = true;
		}
		
		map.put(key, value);
	
		if (mapNew) {
			globalContainer.setParameter(name, map);
			trace.addInfo("  Stored map as global parameter");
		}
		
		trace.addInfo("UDF putValue - End");
		
		return value;
	}

	

	public static String hasValue(String key, String name, Container container) throws StreamTransformationException {
		GlobalContainer globalContainer = container.getGlobalContainer();
		AbstractTrace trace = container.getTrace();

		trace.addInfo("UDF hasValue - Begin");
		
		String result = "false";
		
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String,String>) globalContainer.getParameter(name);
		
		if (map == null) {
			trace.addInfo("  Map with name " + name + " not found");
		} else {
			String value = map.get(key);
		
			if (value == null) {
				trace.addInfo("  No value for key " + key + " found");
			} else {
				trace.addInfo("  Value for key " + key + ": " + value);
				result = "true";
			}
		}
		
		trace.addInfo("UDF hasValue - End");
		
		return result;
	}

	
	
	public static String getValue(String key, String name, Container container) throws StreamTransformationException {
		GlobalContainer globalContainer = container.getGlobalContainer();
		AbstractTrace trace=container.getTrace();

		trace.addInfo("UDF getValue - Begin");
		
		String result = "";
		
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String,String>) globalContainer.getParameter(name);
		
		if (map == null) {
			trace.addInfo("  Map with name " + name + " not found");
		} else {
		
			String value = map.get(key);
		
			if (value == null) {
				trace.addInfo("  No value for key " + key + " found");
			} else {
				trace.addInfo("  Value for key " + key + ": " + value);
				result = value;
			}
		}
		
		trace.addInfo("UDF getValue - End");
		
		return result;
	}

}
