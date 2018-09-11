package com.evry.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Beans {
    private static ApplicationContext context;

    private Beans() {}

    private static ApplicationContext getContext() {
        if(context == null) {
            context = new ClassPathXmlApplicationContext("Beans.xml");
        }

        return context;
    }

    public static <T> T getBean(String bean) {
        return (T)getContext().getBean(bean);
    }
}
