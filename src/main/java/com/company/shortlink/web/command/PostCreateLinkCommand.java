package com.company.shortlink.web.command;

import com.company.shortlink.link.Link;
import com.company.shortlink.link.LinkService;
import com.company.shortlink.link.ShortLinkGenerator;
import com.company.shortlink.serviceprovider.ServiceProvider;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class PostCreateLinkCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        String fullUrl = req.getParameter("fullUrl");
        String shortUrl;
        LinkService linkService = ServiceProvider.get(LinkService.class);
        do {
            shortUrl = new ShortLinkGenerator().generate();
        }
        while (linkService.getByShortLink(shortUrl)!=null);

        Link link=new Link();
        link.setShortLink(shortUrl);
        link.setLink(fullUrl);
        linkService.save(link);

        resp.sendRedirect("/list");

        resp.setContentType("text/html");


        Context simpleContext = new Context(
                req.getLocale(),
                Collections.emptyMap()
        );

        engine.process("create_link", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
