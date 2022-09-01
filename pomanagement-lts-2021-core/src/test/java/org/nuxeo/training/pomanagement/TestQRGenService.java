package org.nuxeo.training.pomanagement;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.platform.test.PlatformFeature;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import javax.inject.Inject;

@RunWith(FeaturesRunner.class)
@Features({ PlatformFeature.class })
@Deploy("org.nuxeo.training.pomanagement.pomanagement-core")
public class TestQRGenService {

    @Inject
    protected QRGenService qrgenservice;

    @Test
    public void testService() {
        assertNotNull(qrgenservice);
    }
}
