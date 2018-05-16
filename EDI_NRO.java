package com.havemester.sap.po.EDI;

import com.havemester.sap.po.mapping.test.ContainerImpl;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mappingtool.tf7.rt.Container;

public class EDI_NRO {

	public static void main(String[] args) throws Exception {
		
		// initialize container
		Container container = (Container) new ContainerImpl();
		
		System.out.println ("NRO getNext:     " + getNext("EDIFACT_DESADV", container));
		System.out.println ("NRO getPrevious: " + getPrevious("EDIFACT_DESADV", container));
	}
	
	
	/*
	 * Get next sequence number from number range object <name>
	 */
	
	public static String getNext(String name, Container container) throws StreamTransformationException {
		return ("$B2B_UEBNR" + name.trim() + "$B2B_END_UEBNR");
	}
	
	
	
	/*
	 * Get previous sequence number from number range object <name>
	 */
	
	public static String getPrevious(String name, Container container) throws StreamTransformationException {
		return ("$B2B_UEBNR_BEFORE" + name.trim() + "$B2B_END_UEBNR");
	}
}
