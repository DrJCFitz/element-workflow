package com.element.flow.workflow;

import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.amazonaws.services.simpleworkflow.flow.annotations.Execute;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.element.flow.model.Merchant;
import com.element.flow.model.PortalProfile;
import com.newvem.swf.workflow.ProcessActivitiesClient;
import com.newvem.swf.workflow.ProcessActivitiesClientImpl;

public class ProcessWorkflowImpl implements ProcessWorkflow {
	
	//@Autowired
	ProcessActivitiesClient activitiesClient = new ProcessActivitiesClientImpl();
	
	@Override
	@Execute(version = "3.0")
	public Promise<Element> listStores(PortalProfile profile) {
		Promise<Element> merchListOut = activitiesClient.retrieveDocumentElements(profile);
		//return merchListOut;
		return merchListOut;
		//return null;
	}
	
}
