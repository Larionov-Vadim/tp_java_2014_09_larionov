package main;

import frontend.*;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;

/**
 * @author v.chibrikov
 */

public class Main {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length == 1) {
            String portString = args[0];
            port = Integer.valueOf(portString);
        }

        System.out.append("Starting at port: ").append(String.valueOf(port)).append('\n');

        //Servlet frontend = new Frontend();

        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //context.addServlet(new ServletHolder(frontend), "/api/v1/auth/signin");

        /* */
        AccountService accountService = new AccountService();

        // root and admin
        accountService.singUp("root", "root", "root@mail.ru");
        accountService.singUp("admin", "admin", "admin@mail.ru");

        // Создание сервлетов
        Servlet mainPageServlet = new MainPageServlet(accountService);
        Servlet signUpServlet = new SignUpServlet(accountService);
        Servlet signInServlet = new SignInServlet(accountService);
        Servlet logoutServlet = new LogOutServlet(accountService);

        //
        context.addServlet(new ServletHolder(mainPageServlet), "/main");
        context.addServlet(new ServletHolder(signUpServlet), "/sign_up");
        context.addServlet(new ServletHolder(signInServlet), "/login");
        context.addServlet(new ServletHolder(logoutServlet), "/logout");
        context.addServlet(new ServletHolder(new AdminPageServlet()), AdminPageServlet.adminPageURL);
        /* */

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}