/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.*;
import beans.Prodavac;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import messages.SobeMessage;

/**
 *
 * @author malenicn
 */
public class SobaDao {

    public static Soba unesi(Soba soba) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PosrednikPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.persist(soba);

            em.getTransaction().commit();
            em.close();

        } catch (final NoResultException nre) {
            return null;
        }

        return soba;
    }

    public static Apartman dohvatiZaApartman(Apartman apartman) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PosrednikPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<Soba> criteria = builder.createQuery(Soba.class);
//        Root<Soba> from = criteria.from(Soba.class);
//        criteria.select(from);
//
//        criteria.where(
//                builder.equal(from.get("idApartman"), apartman.getId())
//        );
        TypedQuery<Soba> typed = em.createNamedQuery("dohvatiSobe", Soba.class).setParameter(1, apartman.getId());
        try {
            List<Soba> result = typed.getResultList();
            apartman.setSobe(result);

            em.merge(apartman);

            em.getTransaction().commit();
            em.close();

            return apartman;
        } catch (final NoResultException nre) {
            return null;
        }
    }

    public static Soba izmeni(Soba soba) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PosrednikPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.merge(soba);

            em.getTransaction().commit();
            em.close();

        } catch (final NoResultException nre) {
            return null;
        }

        return soba;
    }

    public static Object obrisi(Soba soba) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PosrednikPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Soba s = em.merge(soba);
        em.remove(s);

        em.getTransaction().commit();
        em.close();
        return new Object();
    }

    public static SobeMessage dohvatiSve() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PosrednikPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Soba> res = em.createNamedQuery("dohvatiSveSobe", Soba.class)
                .getResultList();

        em.getTransaction().commit();
        em.close();
        SobeMessage msg = new SobeMessage();
        msg.setSobe(res);
        return msg;
    }

}
