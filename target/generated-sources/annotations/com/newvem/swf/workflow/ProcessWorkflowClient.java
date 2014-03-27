/*
 * This code was generated by AWS Flow Framework Annotation Processor.
 * Refer to Amazon Simple Workflow Service documentation at http://aws.amazon.com/documentation/swf 
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
 package com.newvem.swf.workflow;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.flow.WorkflowClient;

/**
 * Generated from {@link com.element.flow.workflow.ProcessWorkflow}. 
 * Used to invoke child workflows asynchronously from parent workflow code.
 * Created through {@link ProcessWorkflowClientFactory#getClient}.
 * <p>
 * When running outside of the scope of a workflow use {@link ProcessWorkflowClientExternal} instead.
 */
public interface ProcessWorkflowClient extends WorkflowClient
{

    /**
     * Generated from {@link com.element.flow.workflow.ProcessWorkflow#listStores}
     */
    Promise<org.jsoup.nodes.Element> listStores(com.element.flow.model.PortalProfile profile);

    /**
     * Generated from {@link com.element.flow.workflow.ProcessWorkflow#listStores}
     */
    Promise<org.jsoup.nodes.Element> listStores(com.element.flow.model.PortalProfile profile, Promise<?>... waitFor);

    /**
     * Generated from {@link com.element.flow.workflow.ProcessWorkflow#listStores}
     */
    Promise<org.jsoup.nodes.Element> listStores(com.element.flow.model.PortalProfile profile, StartWorkflowOptions optionsOverride, Promise<?>... waitFor);

    /**
     * Generated from {@link com.element.flow.workflow.ProcessWorkflow#listStores}
     */
    Promise<org.jsoup.nodes.Element> listStores(Promise<com.element.flow.model.PortalProfile> profile);

    /**
     * Generated from {@link com.element.flow.workflow.ProcessWorkflow#listStores}
     */
    Promise<org.jsoup.nodes.Element> listStores(Promise<com.element.flow.model.PortalProfile> profile, Promise<?>... waitFor);

    /**
     * Generated from {@link com.element.flow.workflow.ProcessWorkflow#listStores}
     */
    Promise<org.jsoup.nodes.Element> listStores(Promise<com.element.flow.model.PortalProfile> profile, StartWorkflowOptions optionsOverride, Promise<?>... waitFor);

}