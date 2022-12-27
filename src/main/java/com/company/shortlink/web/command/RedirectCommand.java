package com.company.shortlink.web.command;

import com.company.shortlink.link.Link;
import com.company.shortlink.link.LinkService;
import com.company.shortlink.serviceprovider.ServiceProvider;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectCommand implements Command{
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        String shortLink=req.getRequestURI().replace("/","");

        LinkService linkService= ServiceProvider.get(LinkService.class);
        Link link = linkService.getByShortLink(shortLink);

        link.setOpenCount(link.getOpenCount()+1);
        linkService.save(link);

        String fullLink=link.getLink();
        resp.sendRedirect(fullLink);
    }
}
