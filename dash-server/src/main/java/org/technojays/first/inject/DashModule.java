package org.technojays.first.inject;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import org.technojays.first.service.H4TeamService;
import org.technojays.first.service.TeamService;

/**
 * @author Derelle.Redmond
 * @since 2/14/2015.
 */
public class DashModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(TeamService.class).to(H4TeamService.class).in(Singleton.class);
    }
}
