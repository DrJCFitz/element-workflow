/*
 * Copyright 2012-2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.element.tests;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.amazonaws.services.simpleworkflow.flow.annotations.Asynchronous;
import com.amazonaws.services.simpleworkflow.flow.annotations.Wait;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.core.TryFinally;
import com.amazonaws.services.simpleworkflow.flow.junit.WorkflowTestBase;
import com.amazonaws.services.simpleworkflow.flow.junit.spring.FlowSpringJUnit4ClassRunner;
import com.element.flow.model.PortalProfile;
import com.element.flow.workflow.ProcessWorkflowClient;
import com.element.flow.workflow.ProcessWorkflowClientFactory;

@RunWith(FlowSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:com/element/test/config/ProcessTest-context.xml"})
public class ProcessTest {

    /*public static class TestProcessActivities implements ProcessActivities {
    	public Document document;
		
    	public Document getDocument() {
			return document;
		}
		
		@Override
		public Elements retrieveDocumentElements(final PortalProfile profile) throws IOException {
			document = Jsoup.connect(profile.getAllStoresUrl()).get();
			Elements storeLinks = document.select(profile.getRootElement());;
			return storeLinks;
		}

		@Override
		public Merchant parseElementsForMerchant(Element link,
				PortalProfile profile) {
			// TODO Auto-generated method stub
			return null;
		}
		
    }*/

    @Rule
    @Autowired
    public WorkflowTestBase workflowTest;
    
    @Autowired
    public ProcessWorkflowClientFactory workflowClientFactory;
    
    
    //@Autowired
    //public TestProcessActivities activitiesImpl;
    
    @Test
    public void testList() throws Exception {
    	/* The ProcessWorkflowClientFactory refers to whichever activity is paired to
    	 * the workflowTest
    	 */
    	ProcessWorkflowClient workflowClient = workflowClientFactory.getClient();
        Promise<Element> done = workflowClient.listStores(); 
        printLists(done);
    }
    
    @Asynchronous
    private void printLists(@Wait Promise<Element> lists) {
        //for (Element el : lists.get()) {
        	System.out.println(lists.get());
        //}
    }
    
    //@Test
    public void test() throws IOException {
        new TryFinally() {

            @Override
            protected void doTry() throws Throwable {
            	//workflow.listStores(ebatesProfile);
            }

            @Override
            protected void doFinally() throws Throwable {
            }
        };
    }

}
