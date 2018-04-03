package com.zhimo.analytics.controllers;

/**
 * Created by raven on 23/03/2018.
 */
import com.zhimo.analytics.kafka.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.Map;

@Path("/events")
public class EventController {
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private Sender sender;

    @Autowired
    private String topic;

    @POST
    @Path("/send")
    @Produces("application/json")
    public Response sendEvent(@RequestBody String payload) throws URISyntaxException {
//    public Response sendEvent(@RequestParam Map<String, Object> payload) throws URISyntaxException {
        sender.send(topic, payload);
        return Response
                .status(200)
                .entity(payload)
                .build();
    }
}
