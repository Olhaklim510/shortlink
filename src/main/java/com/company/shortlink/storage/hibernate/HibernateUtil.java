package com.company.shortlink.storage.hibernate;

import com.company.shortlink.link.Link;
import com.company.shortlink.storage.StorageConstants;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final HibernateUtil INSTANCE;
    private final SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil();
    }

    private HibernateUtil() {
        sessionFactory = new Configuration()
                .setProperty("hibernate.connection.url", StorageConstants.CONNECTION_URL)
                .addAnnotatedClass(Link.class)
                .buildSessionFactory();
    }

    public static HibernateUtil getINSTANCE() {
        return INSTANCE;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void close() {
        sessionFactory.close();
    }

    public static void main(String[] args) {
        HibernateUtil.getINSTANCE();
    }
}
