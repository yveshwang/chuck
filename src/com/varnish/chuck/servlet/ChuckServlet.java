package com.varnish.chuck.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChuckServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final int ITERATION = Integer.parseInt(System.getProperty("com.varnish.chuck.iteration", "1"));
	private AtomicInteger counter = new AtomicInteger(0);
	private static String content = "booo";
	static {
		try {
			InputStream in = ChuckServlet.class.getResourceAsStream("/com/varnish/chuck/resource/beer.svg");
			InputStreamReader is = new InputStreamReader(in);
			StringBuilder sb=new StringBuilder();
			BufferedReader br = new BufferedReader(is);
			String read = br.readLine();

			while(read != null) {
				sb.append(read);
				read =br.readLine();
			}
			content = sb.toString();
		} catch (IOException e) {
			content = "ioexception";
		}
	}
	
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
		//now read the svg a few times. since its around 41 bytes, so lets do say 6000 times to get around 200k kb worth of stuff.
		
		for( int i = 0; i < ITERATION; i++) {
			resp.getWriter().write("\n");
			resp.getWriter().write(content);
		}
	}
}
