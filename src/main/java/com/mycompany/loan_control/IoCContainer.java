package com.mycompany.loan_control;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class IoCContainer {
    private static final Injector injector = Guice.createInjector(new AppModule());

    public static <T> T getInstance(Class<T> type) {
        return injector.getInstance(type);
    }
}
