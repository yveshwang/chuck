package com.varnish.chuck.servlet;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChuckServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private AtomicInteger counter = new AtomicInteger(0);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		output("GET", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		output("POST", req, resp);
	}
	
	private void output(String method, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuffer buffer = req.getRequestURL();
		buffer.append(" "+method);
		buffer.append(" "+counter.incrementAndGet());
		buffer.append(" "+System.currentTimeMillis());
		resp.getWriter().write(buffer.toString());
	}
}
