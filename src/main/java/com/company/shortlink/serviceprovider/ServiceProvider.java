package com.company.shortlink.serviceprovider;

import com.company.shortlink.link.HibernateLinkService;
import com.company.shortlink.link.LinkService;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {
    private static final Map<Class<?>, Object> SERVICES = new HashMap<>();

    static {
        SERVICES.put(LinkService.class, new HibernateLinkService());
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> tClass) {
        return (T) SERVICES.get(tClass);
    }
}
