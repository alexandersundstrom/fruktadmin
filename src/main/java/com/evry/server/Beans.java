package com.evry.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class Beans {
    private static ApplicationContext context;

    private Beans() {}

    private static ApplicationContext getContext() {
        if(context == null) {
            context = new ClassPathXmlApplicationContext("Beans.xml");
        }

        return context;
    }

    static Object getBean(String bean) {
        return getContext().getBean(bean);
    }
}
