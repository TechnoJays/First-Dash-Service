package org.technojays.first.dao;

import org.technojays.first.model.Team;
import org.technojays.first.model.Team_;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.Predicate;
import java.util.List;

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
     *
     * @param teamNum Team number to find
     * @return Team associated with given team number
     */
    public Team getByTeamNumber(Long teamNum) {
        QueryContainer<Team> qc = new QueryContainer<>(getEntityManager(), this.entityClass);

        Predicate cond1 = qc.getCriteriaBuilder().equal(qc.getRoot().get(Team_.teamNum), teamNum);
        qc.getCriteriaQuery().where(cond1);
        return getSingleResult(qc.getCriteriaQuery());
    }

    /**
     * Retrieve teams matching on team name
     *
     * @param name Name of the team
     * @return Teams that have a matching team name
     */
    public List<Team> getByTeamName(String name) {
        QueryContainer<Team> qc = new QueryContainer<>(getEntityManager(), this.entityClass);

        Predicate cond1 = qc.getCriteriaBuilder().like(qc.getRoot().get(Team_.name), addLikeFilter(name));
        qc.getCriteriaQuery().where(cond1);

        return getResultList(qc.getCriteriaQuery());
    }

    /**
     * Retrieve teams matching on team short name
     *
     * @param shortName Short name of the teams
     * @return Teams that have a matching short name
     */
    public List<Team> getByShortName(String shortName) {
        QueryContainer<Team> qc = new QueryContainer<>(getEntityManager(), this.entityClass);

        Predicate cond1 = qc.getCriteriaBuilder().like(qc.getRoot().get(Team_.shortName), addLikeFilter(shortName));
        qc.getCriteriaQuery().where(cond1);

        return getResultList(qc.getCriteriaQuery());
    }




}
