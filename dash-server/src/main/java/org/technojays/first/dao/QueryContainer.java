package org.technojays.first.dao;


import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author Derelle.Redmond
 * @since 2/10/2015.
 *
 * Container for highly related query objects
 */
public class QueryContainer<T> {

    private final CriteriaBuilder criteriaBuilder;
    private final CriteriaQuery<T> criteriaQuery;
    private final Root<T> root;

    public QueryContainer(EntityManager em, Class<T> clazz) {
        criteriaBuilder = em.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(clazz);
        root = criteriaQuery.from(clazz);
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public CriteriaQuery<T> getCriteriaQuery() {
        return criteriaQuery;
    }

    public Root<T> getRoot() {
        return root;
    }
}
