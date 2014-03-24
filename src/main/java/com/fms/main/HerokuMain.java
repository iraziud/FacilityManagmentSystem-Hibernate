package com.fms.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.fms.util.FMSLogger;

public class HerokuMain extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		PrintWriter out = resp.getWriter();
		FMSLogger.out = out;

		out.write("Please wait.. running spring apps... ");
		out.println("\n\n\n");
		out.println("---------------------------------------------------------");
		out.println("\n\n\n");
		{
			PersonClient.main(null);
		}
		out.flush();
		out.println("\n\n\n");
		out.println("---------------------------------------------------------");
		out.println("\n\n\n");
		{
			FacilityUsageClient.main(null);
		}
		out.flush();
		out.println("\n\n\n");
		out.println("---------------------------------------------------------");
		out.println("\n\n\n");
		{
			MaintenanceUsageClient.main(null);
		}
		out.println("\n\n\n");
		out.println("---------------------------------------------------------");
		out.println("\n\n\n");
		out.flush();
	}

	public static void main(String[] args) throws Exception {
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		context.addServlet(new ServletHolder(new HerokuMain()), "/*");
		server.start();
		server.join();
	}
}
