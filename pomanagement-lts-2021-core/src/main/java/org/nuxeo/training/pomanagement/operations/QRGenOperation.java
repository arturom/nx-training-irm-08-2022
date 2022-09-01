package org.nuxeo.training.pomanagement.operations;

import org.apache.commons.lang3.StringUtils;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.PathRef;
import org.nuxeo.training.pomanagement.QRGenService;

/**
 *
 */
@Operation(id=QRGenOperation.ID, category=Constants.CAT_DOCUMENT, label="Generate QR", description="Describe here what your operation does.")
public class QRGenOperation {

    public static final String ID = "Document.QRGenOperation";
    
    @Context
    protected QRGenService qrService;

    @OperationMethod
    public String run(DocumentModel doc) {
    	return qrService.buildQrCode(doc);
    }
}
