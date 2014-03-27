package com.element.flow.workflow;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Element;

import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.Activity;
import com.amazonaws.services.simpleworkflow.flow.annotations.ActivityRegistrationOptions;
import com.element.flow.model.Merchant;
import com.element.flow.model.PortalProfile;
import com.element.flow.process.ElementDataConverter;

@ActivityRegistrationOptions(defaultTaskScheduleToStartTimeoutSeconds = 600,
defaultTaskStartToCloseTimeoutSeconds = 60)
@Activities(version="4.0", dataConverter=ElementDataConverter.class)
public interface ProcessActivities {
	//@Activity(name="parsePortal")
	//public List<Merchant> parsePortalForMerchants(PortalProfile profile) throws IOException;
	@Activity(name="retrieveDocElements")
	public Element retrieveDocumentElements(PortalProfile profile) throws IOException;
}
