package org.technojays.first.inject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.Provides;

import javax.inject.Singleton;

/**
 * @author Derelle.Redmond
 * @since 2/1/2015.
 */
public class JSONInjection implements Module {

    @Provides
    @Singleton
    ObjectMapper objectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        // Hibernate 4 Module handles lazy loading and other Hibernate stuffz
        mapper.writerWithDefaultPrettyPrinter();
        mapper.registerModule(new Hibernate4Module());
        return mapper;
    }

    @Provides
    @Singleton
    @Inject
    JacksonJsonProvider jacksonJsonProvider(ObjectMapper mapper) {
        return new JacksonJsonProvider(mapper);
    }

    @Override
    public void configure(Binder binder) {
    }
}
