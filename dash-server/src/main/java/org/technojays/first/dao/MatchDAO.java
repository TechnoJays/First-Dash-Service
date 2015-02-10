package org.technojays.first.dao;

import org.technojays.first.model.Match;
import org.technojays.first.model.metamodel.Match_;

import javax.persistence.criteria.Predicate;

/**
 * @author Derelle.Redmond
 * @since 2/9/2015.
 *
 * Data Access Object for Match Entities
 */
public class MatchDAO extends AbstractDAO<Match> {


    public MatchDAO() {
        super(Match.class);
    }

    public Match getByMatchNumber(Long matchNum) {
        QueryContainer<Match> qc = new QueryContainer<>(getEntityManager(), this.entityClass);

        Predicate cond1 = qc.getCriteriaBuilder().equal(qc.getRoot().get(Match_.matchNum), matchNum);
        qc.getCriteriaQuery().where(cond1);

        return getSingleResult(qc.getCriteriaQuery());

//        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<Match> criteria = builder.createQuery(Match.class);
//        Root<Match> matchRoot = criteria.from(Match.class);
//        criteria.select(matchRoot);
//        criteria.where(builder.equal(matchRoot.get(Match_.matchNum), matchNum));
//        return entityManager.createQuery(criteria).getSingleResult();
    }
}
