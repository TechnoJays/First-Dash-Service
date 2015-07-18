package org.technojays.first.dao;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Derelle.Redmond
 * @since 2/9/2015
 * <p/>
 * Abstract DAO for all Entity types to implement
 */
public abstract class AbstractDAO<T> {
    protected Class<T> entityClass;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Inject
    //private EntityManager em;

    @Inject
    private Provider<EntityManager> entityManagerProvider;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Get entity manager for this DAO
     *
     * @return Entity Manager associated with this DAO
     */
    protected EntityManager getEntityManager() {
        return entityManagerProvider.get();
    }

    /**
     * Get the metamodel associated with the entity for this DAO
     *
     * @return metamodel for the entity associated with this metamodel
     */
    protected EntityType<T> getMetaModel() {
        return getEntityManager().getMetamodel().entity(entityClass);
    }

    @Transactional
    public void save(T entity) {
//        getEntityManager().getTransaction().begin();
        getEntityManager().persist(entity);
//        getEntityManager().getTransaction().commit();
    }

    @Transactional
    public void update(T entity) {
//        getEntityManager().getTransaction().begin();
        getEntityManager().merge(entity);
//        getEntityManager().getTransaction().commit();
    }

    @Transactional
    public void remove(Long entityId) {
//        getEntityManager().getTransaction().begin();
        T entity = find(entityId);

        if (entity != null)
            remove(entity);
//        getEntityManager().getTransaction().commit();
    }

    @Transactional
    public void remove(T entity) {
//        getEntityManager().getTransaction().begin();
        getEntityManager().remove(getEntityManager().merge(entity));
//        getEntityManager().getTransaction().commit();
    }

    /**
     * Find object of Type t by id
     *
     * @param id Id to search for
     * @return Object with given id
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Find all objects of type T
     *
     * @return List of objects of type T
     */
    public List<T> findAll() {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
                .createQuery(entityClass);
        cq.select(cq.from(entityClass));

        return getResultList(cq);
    }

    /**
     * Find all objects of type T
     *
     * @param start start of the range
     * @param end   end of the range
     * @return List of objects of type T in range
     */
    public List<T> findRange(int start, int end) {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
                .createQuery(entityClass);
        cq.select(cq.from(entityClass));

        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(end - start);
        q.setFirstResult(start);

        return q.getResultList();
    }

    /**
     * Get count of the associated object type
     *
     * @return count of this Entity
     */
    public int count() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(entityClass);
        cq.select(cb.count(root));
        Long count = getEntityManager().createQuery(cq).getSingleResult();

        return count.intValue();
    }

    /**
     * Get single result from criteria query
     *
     * @param cq Criteria Query to get single result from
     * @return Sadly returns null if the result set is empty until I become a better coder, otherwise, a single result
     */
    public T getSingleResult(CriteriaQuery<T> cq) {
        try {
            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (PersistenceException e) {
            //getEntityManager().getTransaction().rollback();
            logger.warn("Unable to get single result for query: {}", cq.getParameters().toString(), e);
            return null;
        }
    }

    /**
     * Get list result from criteria query
     *
     * @param cq Criteria Query to get results from
     * @return List of results, which is empty if no results are found
     */
    public List<T> getResultList(CriteriaQuery<T> cq) {
        try {
            return getEntityManager().createQuery(cq).getResultList();
        } catch (PersistenceException e) {
            //getEntityManager().getTransaction().rollback();
            logger.warn("Unable to get result list for query: {}", cq.toString(), e);
            return new ArrayList<T>();
        }
    }

    /**
     * Get list result by String attribute of entity class
     *
     * @param metaAttribute Metamodel attribute
     * @param value         String value of the model to search by
     * @return List of results for the attribute value
     */
    public List<T> getListByStringAttribute(SingularAttribute<T, String> metaAttribute, String value) {
        QueryContainer<T> qc = new QueryContainer<>(getEntityManager(), this.entityClass);

        Predicate condition = qc.getCriteriaBuilder().like(qc.getRoot().get(metaAttribute), addLikeFilter(value));
        qc.getCriteriaQuery().where(condition);

        return getResultList(qc.getCriteriaQuery());
    }

    /**
     * Get single result by attribute value of entity class
     *
     * @param metaAttribute Metamodel attribute for entity class
     * @param value         Value of the attribute to search by
     * @param <V>           Type of attribute value
     * @return Result for the attribute value
     */
    public <V> T getByAttribute(SingularAttribute<T, V> metaAttribute, V value) {
        QueryContainer<T> qc = new QueryContainer<>(getEntityManager(), this.entityClass);

        Predicate cond1 = qc.getCriteriaBuilder().equal(qc.getRoot().get(metaAttribute), value);
        qc.getCriteriaQuery().where(cond1);
        return getSingleResult(qc.getCriteriaQuery());
    }

    /**
     * Get list result by attribute value of entity class
     *
     * @param metaAttribute Metamodel attribute for entity class
     * @param value         Value of the attribute to search by
     * @param <V>           Type for attribute value
     * @return List of results for the attribute value
     */
    public <V> List<T> getListByAttribute(SingularAttribute<T, V> metaAttribute, V value) {
        QueryContainer<T> qc = new QueryContainer<>(getEntityManager(), this.entityClass);

        Predicate condition = qc.getCriteriaBuilder().equal(qc.getRoot().get(metaAttribute), value);
        qc.getCriteriaQuery().where(condition);

        return getResultList(qc.getCriteriaQuery());
    }

    /**
     * Get result matching on multiple attributes of entity class.
     * WARNING - Will only work for exact matches on Strings
     *
     * @param attributeObjectMap Map of metamodel attributes to values for the entity class
     * @return Result matching on all attribute values
     */
    public T getByAttributeMap(Map<SingularAttribute, Object> attributeObjectMap) {
        QueryContainer<T> qc = new QueryContainer<>(getEntityManager(), this.entityClass);

        Predicate predicate = qc.getCriteriaBuilder().conjunction();
        for (SingularAttribute attribute : attributeObjectMap.keySet()) {
            Object value = attributeObjectMap.get(attribute);
            Predicate condition = qc.getCriteriaBuilder().equal(qc.getRoot().get(attribute), value);
            predicate = qc.getCriteriaBuilder().and(condition, predicate);
        }
        qc.getCriteriaQuery().where(predicate);

        return getSingleResult(qc.getCriteriaQuery());
    }

    /**
     * Get result list matching on multiple attributes of entity class
     * WARNING - Will only work for exact matches on Strings
     *
     * @param attributeObjectMap Map of metamodel attributes to values for the entity class
     * @return List of results matching on all attributes
     */
    public List<T> getListByAttributeMap(Map<SingularAttribute, Object> attributeObjectMap) {
        QueryContainer<T> qc = new QueryContainer<>(getEntityManager(), this.entityClass);


        Predicate predicate = qc.getCriteriaBuilder().conjunction();
        for (SingularAttribute attribute : attributeObjectMap.keySet()) {
            Predicate condition = qc.getCriteriaBuilder().equal(qc.getRoot().get(attribute),
                    attributeObjectMap.get(attribute));
            predicate = qc.getCriteriaBuilder().and(condition, predicate);
        }
        qc.getCriteriaQuery().where(predicate);

        return getResultList(qc.getCriteriaQuery());
    }

    /**
     * Surround text to search on with '%' symbol
     *
     * @param textToSearch Text searching on
     * @return search text surrounded by '%'
     */
    public String addLikeFilter(String textToSearch) {
        return "%" + textToSearch + "%";
    }
}
