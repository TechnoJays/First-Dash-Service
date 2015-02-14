package org.technojays.first.inject;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import org.technojays.first.dao.EventDAO;
import org.technojays.first.dao.MatchDAO;
import org.technojays.first.dao.TeamDAO;
import org.technojays.first.service.H4MatchService;
import org.technojays.first.service.H4TeamService;
import org.technojays.first.service.MatchService;
import org.technojays.first.service.TeamService;

/**
 * @author DaPortlyJester
 * @since 5/3/2015
 */
public class DashH4ServiceInjection implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(TeamService.class).to(H4TeamService.class).in(Singleton.class);
        binder.bind(MatchService.class).to(H4MatchService.class).in(Singleton.class);
        binder.bind(TeamDAO.class).asEagerSingleton();
        binder.bind(MatchDAO.class).asEagerSingleton();
        binder.bind(EventDAO.class).asEagerSingleton();
    }
}
