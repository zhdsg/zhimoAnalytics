package com.zhimo.analytics;

/**
 * Created by raven on 23/03/2018.
 */
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;
import com.zhimo.analytics.controllers.EventController;
import com.zhimo.analytics.utils.CORSResponseFilter;

@Component
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {
        register(EventController.class);
        register(CORSResponseFilter.class);
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }
}
