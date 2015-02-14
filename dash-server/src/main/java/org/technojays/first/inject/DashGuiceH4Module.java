package org.technojays.first.inject;

import com.google.inject.*;
import com.google.inject.name.Names;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.technojays.first.dao.EventDAO;
import org.technojays.first.dao.MatchDAO;
import org.technojays.first.dao.TeamDAO;
import org.technojays.first.service.H4MatchService;
import org.technojays.first.service.H4TeamService;
import org.technojays.first.service.MatchService;
import org.technojays.first.service.TeamService;
import org.technojays.first.util.ConfigUtil;
import org.technojays.first.util.FDC;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Derelle.Redmond
 * @since 2/1/2015.
 * <p/>
 * Guice Configuration / Injection for Hibernate for Service
 */
public class DashGuiceH4Module implements Module {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // Thread Local here for one entity
    private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE = new ThreadLocal<>();

    private static Properties h4Properties;

    @Override
    public void configure(Binder binder) {
        String configFile = System.getProperty(FDC.DASH_H4_CONFIG_FILE);

        h4Properties = new Properties();
        h4Properties = ConfigUtil.loadConfig(h4Properties, configFile);
        Names.bindProperties(binder, h4Properties);

        binder.bind(TeamService.class).to(H4TeamService.class).in(Singleton.class);
        binder.bind(MatchService.class).to(H4MatchService.class).in(Singleton.class);
        binder.bind(TeamDAO.class).asEagerSingleton();
        binder.bind(MatchDAO.class).asEagerSingleton();
        binder.bind(EventDAO.class).asEagerSingleton();
    }

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
    }


}
