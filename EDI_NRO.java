package com.havemester.sap.po.EDI;

import com.havemester.sap.po.mapping.test.ContainerImpl;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mappingtool.tf7.rt.Container;

public class EDI_NRO {

	public static void main(String[] args) throws Exception {
		// initialize instance
		EDI_NRO nro = new EDI_NRO();
		
		// initialize container
		Container container = (Container) new ContainerImpl();
		
		System.out.println ("NRO getNext:           " + nro.getNext("EDIFACT_DESADV", container));
		System.out.println ("NRO getPrevious:       " + nro.getPrevious("EDIFACT_DESADV", container));
		System.out.println ("NRO getSegmentCounter: " + nro.getSegmentCounter(container));
	}
	
	
	
	/*
	 * Get next sequence number from number range object <name>
	 */
	
	public String getNext(String name, Container container) throws StreamTransformationException {
		return ("$B2B_UEBNR" + name.trim() + "$B2B_END_UEBNR");
	}
	
	
	
	/*
	 * Get previous sequence number from number range object <name>
	 */
	
	public String getPrevious(String name, Container container) throws StreamTransformationException {
		return ("$B2B_UEBNR_BEFORE" + name.trim() + "$B2B_END_UEBNR");
	}
	
	
	
	/*
	 * Return EDIFACT segment counter
	 */
	
	public String getSegmentCounter (Container container) throws StreamTransformationException {
		return "$B2B_SEG_COUNTER";
	}
}
