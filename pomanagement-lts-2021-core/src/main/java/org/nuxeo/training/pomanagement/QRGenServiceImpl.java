package org.nuxeo.training.pomanagement;

import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.runtime.model.ComponentContext;
import org.nuxeo.runtime.model.ComponentInstance;
import org.nuxeo.runtime.model.DefaultComponent;
import org.nuxeo.runtime.model.Extension;

public class QRGenServiceImpl extends DefaultComponent implements QRGenService {
	
	protected QRGenDescriptor generator;
	
	@Override
	public void registerContribution(Object contribution, String extensionPoint, ComponentInstance contributor) {
		if("qrcontent".equals(extensionPoint)) {
			generator = (QRGenDescriptor) contribution;
		}
	}

    /**
     * Component deactivated notification.
     * Called before a component is unregistered.
     * Use this method to do cleanup if any and free any resources held by the component.
     *
     * @param context the component context.
     */
    @Override
    public void deactivate(ComponentContext context) {
        super.deactivate(context);
        generator = null;
    }

	@Override
	public String buildQrCode(DocumentModel doc) {
		String[] values = new String[generator.xpaths.length];

		int i = 0;
		for (String xpath : generator.xpaths) {
			values[i] = doc.getPropertyValue(xpath).toString();
			i++;
		}
		
		return String.join(generator.separator, values);
	}
}
