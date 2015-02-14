package org.technojays.first.inject;

import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.technojays.first.util.ConfigUtil;
import org.technojays.first.util.FDC;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

/**
 * @author Derelle.Redmond
 * @since 2/7/2015.
 */
public class DashGuiceH4ServletModule extends ServletModule {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // Thread Local here for one entity
//    private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE = new ThreadLocal<>();

    private static Properties h4Properties;

    protected void configureServlets() {
        h4Properties = new Properties();
        String configFile = System.getProperty(FDC.DASH_H4_CONFIG_FILE);
        logger.info("Building Persistence Manager - Servlet Persistence");
        JpaPersistModule persistModule = new JpaPersistModule(FDC.H4_MANAGER);
        persistModule.properties(ConfigUtil.loadConfig(h4Properties, configFile));
        logger.info("Installing Persistence Manager for filter {}", FDC.PERSISTENCE_FILTER);
        install(persistModule);
        filter(FDC.PERSISTENCE_FILTER).through(PersistFilter.class);
    }

    @Singleton
    @Provides
    @Inject
    public PersistenceInit initPersistence(PersistService persistService) {
        return new PersistenceInit(persistService);
    }

    /**
    @Provides
    @Inject
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory(FDC.H4_MANAGER, h4Properties);
    }

    @Provides
    @Inject
    public EntityManager provideEntityManager(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = ENTITY_MANAGER_CACHE.get();
        if (entityManager == null) {
            ENTITY_MANAGER_CACHE.set(entityManager = entityManagerFactory.createEntityManager());
        }
        return entityManager;
    } **/
}
