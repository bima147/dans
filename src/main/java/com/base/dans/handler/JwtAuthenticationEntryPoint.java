package com.base.dans.handler;
/*
IntelliJ IDEA 2023.3.6 (Ultimate Edition)
Build #IU-233.15026.9, built on March 21, 2024
@Author bimaprakoso a.k.a Bima Prakoso
Java Developer
Created on 19/06/24 21.41
@Last Modified 19/06/24 21.41
Version 1.0
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Autowired
	ObjectMapper mapper ;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {

		response.setHeader("Content-Type", "application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		Map<String, Object> data = new HashMap<>();
		data.put("status", false);
		data.put("timestamp", Calendar.getInstance().getTime());
		data.put("error", e.getMessage());
		response.getOutputStream().println(mapper.writeValueAsString(data));
	}
}