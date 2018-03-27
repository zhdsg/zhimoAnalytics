package com.zhimo.analytics;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AnalyticsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new AnalyticsApplication().configure(new SpringApplicationBuilder(AnalyticsApplication.class)).run(args);
	}
}
