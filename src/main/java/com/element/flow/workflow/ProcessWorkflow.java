package com.element.flow.workflow;

import org.jsoup.nodes.Element;

import com.amazonaws.services.simpleworkflow.flow.annotations.Execute;
import com.amazonaws.services.simpleworkflow.flow.annotations.Workflow;
import com.amazonaws.services.simpleworkflow.flow.annotations.WorkflowRegistrationOptions;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.element.flow.process.ElementDataConverter;

@Workflow(dataConverter=ElementDataConverter.class)
@WorkflowRegistrationOptions(defaultExecutionStartToCloseTimeoutSeconds = 3600)
public interface ProcessWorkflow {
	@Execute(version = "1.0")
	public Promise<Element> listStores();
}
