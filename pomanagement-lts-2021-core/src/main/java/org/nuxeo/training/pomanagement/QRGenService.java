package org.nuxeo.training.pomanagement;

import org.nuxeo.ecm.core.api.DocumentModel;

public interface QRGenService {
	String buildQrCode(DocumentModel doc);
}
