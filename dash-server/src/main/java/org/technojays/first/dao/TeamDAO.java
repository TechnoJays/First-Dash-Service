package org.technojays.first.dao;

import org.technojays.first.model.Team;
import org.technojays.first.model.metamodel.Team_;

import javax.persistence.criteria.Predicate;

/**
 * @author Derelle.Redmond
 * @since 2/5/2015.
 * <p/>
 * Data Access Object for Team Entities
 */
public class TeamDAO extends AbstractDAO<Team> {

    public TeamDAO() {
        super(Team.class);
    }

    /**
     * Retrieve team by team number
     * @param teamNum Team number to find
     * @return Team associated with given team number
     */
    public Team getByTeamNumber(Long teamNum) {
        QueryContainer<Team> qc = new QueryContainer<>(getEntityManager(), this.entityClass);

        Predicate cond1 = qc.getCriteriaBuilder().equal(qc.getRoot().get(Team_.teamNum), teamNum);
        qc.getCriteriaQuery().where(cond1);

        return getSingleResult(qc.getCriteriaQuery());
    }

}
