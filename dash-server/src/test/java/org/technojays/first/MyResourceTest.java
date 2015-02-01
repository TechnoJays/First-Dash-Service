package org.technojays.first;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.technojays.first.rest.BaseResource;

import javax.ws.rs.core.Application;

import static org.junit.Assert.assertEquals;

public class MyResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(BaseResource.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        final String responseMsg = target().path("myresource").request().get(String.class);
        assertEquals("Hello, Heroku!", responseMsg);
    }
}
