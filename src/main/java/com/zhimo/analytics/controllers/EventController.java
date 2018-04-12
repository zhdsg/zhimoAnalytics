package com.zhimo.analytics.controllers;

/**
 * Created by raven on 23/03/2018.
 */
import com.zhimo.analytics.kafka.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import org.json.JSONObject;

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
    public Response sendEvent(@RequestBody String payload, @Context HttpServletRequest request) throws URISyntaxException {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        JSONObject jsonPayload = new JSONObject(payload);
        jsonPayload.put("ip", ip);
        jsonPayload.put("u_a", request.getHeader("User-Agent"));
        sender.send(topic, jsonPayload.toString());
        return Response
                .status(200)
                .entity(payload)
                .build();
    }
}
