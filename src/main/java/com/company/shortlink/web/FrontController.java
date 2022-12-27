package com.company.shortlink.web;

import com.company.shortlink.storage.DatabaseInitService;
import com.company.shortlink.web.command.CommandService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.JavaxServletWebApplication;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/*")
public class FrontController extends HttpServlet {

    private TemplateEngine engine;
    private CommandService commandService;

    @Override
    public void init(ServletConfig config) {
        new DatabaseInitService().initDb();

        JavaxServletWebApplication application = JavaxServletWebApplication.buildApplication(config.getServletContext());
        this.engine = new TemplateEngine();

        final WebApplicationTemplateResolver resolver = new WebApplicationTemplateResolver(application);
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setOrder(this.engine.getTemplateResolvers().size());
        resolver.setCacheable(false);
        this.engine.addTemplateResolver(resolver);

        commandService=new CommandService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        commandService.process(req, resp, engine);
    }
}
