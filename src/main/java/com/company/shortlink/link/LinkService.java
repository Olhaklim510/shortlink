package com.company.shortlink.link;

import java.util.List;

public interface LinkService {
    Link getByShortLink (String shortlink);
    void save (Link link);
    void deleteByShortLink (String shortLink);
    List<Link> listAll();
    List <Link> search (String query);
}
