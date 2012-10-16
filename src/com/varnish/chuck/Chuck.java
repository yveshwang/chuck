package com.varnish.chuck;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.varnish.chuck.servlet.ChuckServlet;

public class Chuck {
	 public static void main(String[] args) throws Exception {
		 int port = Integer.parseInt(System.getProperty("com.varnish.chuck.port", "8080"));
		 Server server = new Server(port);		 
	     ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
	     context.setContextPath("/");
	     server.setHandler(context);
	     context.addServlet(new ServletHolder(new ChuckServlet()),"/*");
	     server.start();
	     server.join();
	 }
}
