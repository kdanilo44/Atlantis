/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.atlantisprojectfull;

import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author user
 */
@Stateless(name = "CalcTempMetricManager")//nom EJB du session bean
public class CalcTempMetricManager {

    public CalcTempMetric findById(Long id) {
        EntityManager em = GetEntityManager();
        return em.find(CalcTempMetric.class, id);
    }

    public List<CalcTempMetric> findByNameDeviceDay(String id_device) {
        EntityManager em = GetEntityManager();
        String queryStr = "SELECT m FROM CalcTempMetric m WHERE m.id_device = ?1 AND m.type_calc = 1 ORDER BY m.date_temp DESC";
        TypedQuery<CalcTempMetric> query = em.createQuery(queryStr, CalcTempMetric.class);
        query.setParameter(1, id_device);
        return query.setMaxResults(7).getResultList();
    }

    public List<CalcTempMetric> findByNameDeviceWeek(String id_device) {
        EntityManager em = GetEntityManager();
        String queryStr = "SELECT m FROM CalcTempMetric m WHERE m.id_device = ?1 AND m.type_calc = 2 ORDER BY m.date_temp DESC";
        TypedQuery<CalcTempMetric> query = em.createQuery(queryStr, CalcTempMetric.class);
        query.setParameter(1, id_device);
        return query.setMaxResults(4).getResultList();
    }

    public List<CalcTempMetric> findByNameDeviceMonth(String id_device) {
        EntityManager em = GetEntityManager();
        String queryStr = "SELECT m FROM CalcTempMetric m WHERE m.id_device = ?1 AND m.type_calc = 3 ORDER BY m.date_temp DESC";
        TypedQuery<CalcTempMetric> query = em.createQuery(queryStr, CalcTempMetric.class);
        query.setParameter(1, id_device);
        return query.setMaxResults(12).getResultList();
    }

    public void create(CalcTempMetric calcTempMetric) {
        EntityManager em = GetEntityManager();
        em.persist(calcTempMetric);
    }

    public EntityManager GetEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bsPU");
        return emf.createEntityManager();
    }
}
