package com.havemester.sap.po.EDI;

import java.util.HashMap;
import java.util.Map;

import com.havemester.sap.po.mapping.test.ContainerImpl;
import com.havemester.sap.po.mapping.test.InputHeaderImpl;
import com.havemester.sap.po.mapping.test.InputParametersImpl;
import com.havemester.sap.po.mapping.test.OutputHeaderImpl;
import com.havemester.sap.po.mapping.test.OutputParametersImpl;
import com.sap.aii.mapping.api.DynamicConfiguration;
import com.sap.aii.mapping.api.DynamicConfigurationKey;
import com.sap.aii.mapping.api.InputHeader;
import com.sap.aii.mapping.api.InputParameters;
import com.sap.aii.mapping.api.OutputHeader;
import com.sap.aii.mapping.api.OutputParameters;
import com.sap.aii.mapping.api.StreamTransformationConstants;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mappingtool.tf7.rt.Container;



public class EDI_Params {

	public static void main(String[] args) throws Exception {
		
        // prepare input header
        InputHeader		ih		= new InputHeaderImpl();
        Map<String, Object> ihMap	= ((InputHeaderImpl) ih).getMap();
        
        ihMap.put("SenderService", "Test_SenderService");
        ihMap.put("SenderParty", "Test_SenderParty");
        ihMap.put("ConversationId", "2fd1d666-9253-11e7-b1cd-00000534c34f");
        ihMap.put("Interface", "SI_OrderCreate_out");
        ihMap.put("InterfaceNameSpace", "urn:test:OrderCreate_out");
        ihMap.put("MessageClass", "Test_MessageClass");
        ihMap.put("MessageId", "2fd1d666-9253-11e7-b1cd-00000534c34e");
        ihMap.put("ProcessingMode", "Test_Processing");
        ihMap.put("ReceiverInterface", "SI_OrderCreate_in");
        ihMap.put("ReceiverInterfaceNamespace", "urn:test:OrderCreate_in");
        ihMap.put("ReceiverParty", "Test_ReceiverParty");
        ihMap.put("ReceiverPartyAgency", "Test_ReceiverPartyAgency");
        ihMap.put("ReceiverPartyScheme", "Test_ReceiverPartyScheme");
        ihMap.put("ReceiverService", "Test_ReceiverService");
        ihMap.put("RefToMessageId", "2fd1d666-9253-11e7-b1cd-00000534c34d");
        ihMap.put("SenderInterface", "SI_OrderCreate_out");
        ihMap.put("SenderInterfaceNamespace", "urn:test:OrderCreate_out");
        ihMap.put("SenderParty", "Test_SenderParty");
        ihMap.put("SenderPartyAgency", "Test_SenderPartyAgency");
        ihMap.put("SenderPartyScheme", "Test_SenderPartyScheme");
        ihMap.put("SenderService", "Test_SenderService");
        ihMap.put("TimeSent", "2018-01-01T18:00:00");
        ihMap.put("VersionMajor", "1");
        ihMap.put("VersionMinor", "2");

        // prepare dynamic configuration
        DynamicConfiguration    dc  = (DynamicConfiguration) ih.get(StreamTransformationConstants.DYNAMIC_CONFIGURATION);
        DynamicConfigurationKey key = DynamicConfigurationKey.create("http://sap.com/xi/XI/System/File", "Filename");
        dc.put(key, "C:\\Test");
        
        // prepare output header
        OutputHeader oh = new OutputHeaderImpl();
        
        // prepare input parameters
        Map<String, Object> ipMap = new HashMap<String, Object>();
        ipMap.put("TEST", "1234");
        InputParameters ip = new InputParametersImpl(ipMap);     

        // prepare output parameters
        OutputParameters op = new OutputParametersImpl();

        // initialize container
        Container container = (Container) new ContainerImpl(ih, ip, oh, op);
    	
    	
	    System.out.println ("SAP System Name:              " + getSAPSystemName(container));
    	System.out.println ();
    	System.out.println ("Conversation ID:              " + getConversationId(container));
    	System.out.println ("Interface:                    " + getInterface(container));
    	System.out.println ("Interface Namespace:          " + getInterfaceNamespace(container));
	    System.out.println ("Message Class:                " + getMessageClass(container));
	    System.out.println ("Message ID:                   " + getMessageId(container));
	    System.out.println ("Processing Mode:              " + getProcessingMode(container));
    	System.out.println ("Receiver Interface:           " + getReceiverInterface(container));
    	System.out.println ("Receiver Interface Namespace: " + getReceiverInterfaceNamespace(container));
	    System.out.println ("Receiver Party:               " + getReceiverParty(container));
	    System.out.println ("Receiver Party Agency:        " + getReceiverPartyAgency(container));
	    System.out.println ("Receiver Party Scheme:        " + getReceiverPartyScheme(container));
	    System.out.println ("Receiver Service:             " + getReceiverService(container));
	    System.out.println ("Ref To Message Id:            " + getRefToMessageId(container));
    	System.out.println ("Sender Interface:             " + getSenderInterface(container));
    	System.out.println ("Sender Interface Namespace:   " + getSenderInterfaceNamespace(container));
	    System.out.println ("Sender Party:                 " + getSenderParty(container));
	    System.out.println ("Sender Party Agency:          " + getSenderPartyAgency(container));
	    System.out.println ("Sender Party Scheme:          " + getSenderPartyScheme(container));
	    System.out.println ("Sender Service:               " + getSenderService(container));
	    System.out.println ("Time Sent:                    " + getTimeSent(container));
	    System.out.println ("Major Version:                " + getVersionMajor(container));
	    System.out.println ("Minor Version:                " + getVersionMinor(container));
	    System.out.println ();
	    
	    System.out.println ("Get (MessageId):              " + getInputHeader("MessageId", container));
	    System.out.println ();
	    
	    System.out.println ("Dynamic Configuration:        " + getDynamicConfiguration("Filename", "http://sap.com/xi/XI/System/File", container));
	    System.out.println ("Set Dynamic Configuration:    " + setDynamicConfiguration("/test/dynamic", "Filename", "http://sap.com/xi/XI/System/File", container));
	    System.out.println ("Dynamic Configuration:        " + getDynamicConfiguration("Filename", "http://sap.com/xi/XI/System/File", container));
	}
	
	
	public static String getSAPSystemName (Container container) throws StreamTransformationException {
		String sysName = "";

		try {
			sysName = (String) System.getProperty("SAPSYSTEMNAME");
		} catch (Exception e) {
			sysName = "INVALID";
		}

		return sysName;
	}
	
	
	
	public static String getConversationId (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getConversationId();
	}
	
	public static String getDynamicConfiguration (String name, String namespace, Container container) throws StreamTransformationException {
		DynamicConfiguration dc = (DynamicConfiguration) container.getInputHeader().get(StreamTransformationConstants.DYNAMIC_CONFIGURATION);

		if (dc == null) {
			throw new StreamTransformationException ("Dynamic configuration not available.");
		}
		
		DynamicConfigurationKey key  = DynamicConfigurationKey.create(namespace, name);
		return dc.get(key);
	}
	
	public static String getInputHeader (String name, Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().get(name);
	}
	
	public static String getInputParameter (String name, Container container) throws StreamTransformationException {
		return 	container.getInputParameters().getString(name);
	}
	
	public static String getInterface (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getInterface();
	}

	public static String getInterfaceNamespace (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getInterfaceNamespace();
	}

	public static String getMessageClass (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getMessageClass();
	}

	public static String getMessageId (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getMessageId();
	}

	public static String getProcessingMode (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getProcessingMode();
	}

	public static String getReceiverInterface (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getReceiverInterface();
	}

	public static String getReceiverInterfaceNamespace (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getReceiverInterfaceNamespace();
	}

	public static String getReceiverParty (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getReceiverParty();
	}
	
	public static String getReceiverPartyAgency (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getReceiverPartyAgency();
	}

	public static String getReceiverPartyScheme (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getReceiverPartyScheme();
	}

	public static String getReceiverService (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getReceiverService();
	}
	
	public static String getRefToMessageId (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getRefToMessageId();
	}

	public static String getSenderInterface (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getSenderInterface();
	}

	public static String getSenderInterfaceNamespace (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getSenderInterfaceNamespace();
	}

	public static String getSenderParty (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getSenderParty();
	}
	
	public static String getSenderPartyAgency (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getSenderParytAgency();
	}

	public static String getSenderPartyScheme (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getSenderPartyScheme();
	}

	public static String getSenderService (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getSenderService();
	}
	
	public static String getTimeSent (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getTimeSent();
	}

	public static String getVersionMajor (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getVersionMajor();
	}

	public static String getVersionMinor (Container container) throws StreamTransformationException {
		return (String) container.getInputHeader().getVersionMinor();
	}
	
	public static String setDynamicConfiguration (String value, String name, String namespace, Container container) throws StreamTransformationException {
		DynamicConfiguration dc = (DynamicConfiguration) container.getInputHeader().get(StreamTransformationConstants.DYNAMIC_CONFIGURATION);

		if (dc == null) {
			throw new StreamTransformationException ("Dynamic configuration not available.");
		}
		
		DynamicConfigurationKey key  = DynamicConfigurationKey.create(namespace, name);
		dc.put(key, value);
		
		return value;
	}
}
