package dao;

import beans.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class KorisnikDao {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PosrednikPU");
    private static EntityManager em = emf.createEntityManager();

    public static Kupac dohvatiKupca(String username, String pass) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Kupac> criteria = builder.createQuery(Kupac.class);
        Root<Kupac> from = criteria.from(Kupac.class);
        criteria.select(from);
        Predicate where = builder.equal(from.get(Kupac_.username), username);
        where = builder.and(where, (builder.equal(from.get(Kupac_.password), pass)));
        criteria.where(where);
        TypedQuery<Kupac> typed = em.createQuery(criteria);
        try {
            return typed.getSingleResult();
        } catch (final NoResultException nre) {
            return null;
        }
    }

    public static Prodavac dohvatiProdavca(String username, String pass) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Prodavac> criteria = builder.createQuery(Prodavac.class);
        Root<Prodavac> from = criteria.from(Prodavac.class);
        criteria.select(from);
        Predicate where = builder.equal(from.get(Prodavac_.username), username);
        where = builder.and(where, (builder.equal(from.get(Prodavac_.password), pass)));
        criteria.where(where);
        TypedQuery<Prodavac> typed = em.createQuery(criteria);
        try {
            return typed.getSingleResult();
        } catch (final NoResultException nre) {
            return null;
        }
    }

    public static Kupac izmeniKupca(Kupac k) {
        em.getTransaction().begin();
        Kupac res = em.merge(k);
        em.getTransaction().commit();
        return res;
    }

    public static Prodavac izmeniProdavca(Prodavac k) {
        em.getTransaction().begin();
        Prodavac res = em.merge(k);
        em.getTransaction().commit();
        return res;
    }

}
