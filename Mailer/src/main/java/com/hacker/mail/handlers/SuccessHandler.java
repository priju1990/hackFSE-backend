package com.hacker.mail.handlers;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	EntityManagerFactory emf ;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		logger.info("************* Entry onAuthenticationSuccess ************* ");
		
		
		EntityManager entityManager = emf.createEntityManager();
		
		System.out.println("Login service"+authentication.getName());
		entityManager.getTransaction().begin();
		List <String> role  =entityManager.createQuery("SELECT role FROM Admin where empID="+request.getParameter("username")+" and pwd="+"'"+request.getParameter("password")+"'").getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println(role.get(0));
		Map<String, String> jsonResponse = new HashMap<String, String>();
		jsonResponse.put("Role", role.get(0));
		String json = new Gson().toJson(jsonResponse);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		logger.info("************* Exit onAuthenticationSuccess ************* ");
	}

}