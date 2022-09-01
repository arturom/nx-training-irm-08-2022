package org.nuxeo.training.pomanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.automation.AutomationService;
import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.OperationException;
import org.nuxeo.ecm.automation.test.AutomationFeature;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.test.DefaultRepositoryInit;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.runtime.RuntimeService;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.PartialDeploy;
import org.nuxeo.runtime.test.runner.TargetExtensions;

@RunWith(FeaturesRunner.class)
@Features(AutomationFeature.class)
@RepositoryConfig(init = DefaultRepositoryInit.class, cleanup = Granularity.METHOD)
@Deploy("org.nuxeo.training.pomanagement.pomanagement-core")
@PartialDeploy(bundle = "studio.extensions.amejia-SANDBOX", extensions = {TargetExtensions.ContentModel.class, TargetExtensions.Automation.class})
public class TestPurchaseOrders {
	
	@Inject
	protected CoreSession session;
	
	@Inject
	protected AutomationService automationService;
	
	@Test
	public void shouldDeployNuxeoRuntime() {
		RuntimeService runtime = Framework.getRuntime();
		assertNotNull(runtime);
	}
	
	@Test
	public void shouldHaveADescription() {
		DocumentModel doc = session.createDocumentModel("/", "My new Purchase Order", "PurchaseOrder");
		
		doc.setPropertyValue("purchaseorder:price", 100.00);
		doc.setPropertyValue("purchaseorder:quantity", 3);
		doc.setPropertyValue("purchaseorder:product", "COMP_DESKTOP");
		
		doc = session.createDocument(doc);
		
		String description = (String) doc.getPropertyValue("dc:description");
		assertEquals("PurchaseOrder", doc.getType());
		assertNotNull(description);
		assertTrue(description.startsWith("PO_"));
	}
	
	@Test
	public void shouldHaveCorrectTitle() {
		DocumentModel doc = session.createDocumentModel("/", "My Title", "PurchaseOrder");
		
		doc.setPropertyValue("purchaseorder:price", 100.00);
		doc.setPropertyValue("purchaseorder:quantity", 3);
		doc.setPropertyValue("purchaseorder:product", "COMP_DESKTOP");
		
		doc = session.createDocument(doc);
		
		doc = session.getDocument(doc.getRef());
		
		assertEquals("New Purchase Order", doc.getTitle());
	}
	
	@Test
	public void shouldNegotiatiePurchaseOrder() throws OperationException {
		DocumentModel doc = session.createDocumentModel("/", "My Title", "PurchaseOrder");
		
		doc.setPropertyValue("purchaseorder:price", 100.00);
		doc.setPropertyValue("purchaseorder:quantity", 3);
		doc.setPropertyValue("purchaseorder:product", "COMP_DESKTOP");

		doc = session.createDocument(doc);
		
		OperationContext ctx = new OperationContext(session);
		
		ctx.setInput(doc);

		assertEquals("draft", doc.getCurrentLifeCycleState());
		
		doc = (DocumentModel) automationService.run(ctx, "AC_PurchaseOrder_ToNegotiating");
		
		assertEquals("negotiating", doc.getCurrentLifeCycleState());
	}

}
