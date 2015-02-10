package org.technojays.first.inject;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

/**
 * @author Derelle.Redmond
 * @since 2/7/2015.
 */
public class DashGuiceH4ServletModule extends ServletModule {

    protected void configureServlets() {
        install(new JpaPersistModule("first_dash_local"));
        filter("/*").through(PersistFilter.class);
    }
}
