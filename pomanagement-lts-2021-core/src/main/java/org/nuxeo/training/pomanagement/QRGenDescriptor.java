package org.nuxeo.training.pomanagement;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

/**
 
  <qrcontent>
	  <separator value=";" />
	  <xpath value="dc:title" />
	  <xpath value="purchaseorder:price" />
  </qrcontent>

 * @author arturo
 *
 */

@XObject("qrcontent")
public class QRGenDescriptor {
	
	@XNode("separator@value")
	protected String separator;
	
	@XNodeList(value = "xpath@value", type = String[].class, componentType = String.class)
	protected String[] xpaths;

}
