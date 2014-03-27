package com.element.flow.workflow;

import org.jsoup.nodes.Element;

import com.amazonaws.services.simpleworkflow.flow.annotations.Execute;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;

public class ProcessWorkflowImpl implements ProcessWorkflow {
	
	//@Autowired
	ProcessActivitiesClient activitiesClient = new ProcessActivitiesClientImpl();
	
	@Override
	@Execute(version = "3.0")
	public Promise<Element> listStores() {
		Promise<Element> merchListOut = activitiesClient.retrieveDocumentElements();
		//return merchListOut;
		return merchListOut;
		//return null;
	}
	
}
