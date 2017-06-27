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
import javax.persistence.criteria.ParameterExpression;
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

        criteria.where(
                builder.and(
                        builder.equal(from.get("username"), username),
                        builder.equal(from.get("password"), pass)
                )
        );
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
        criteria.where(
                builder.and(
                        builder.equal(from.get("username"), username),
                        builder.equal(from.get("password"), pass)
                )
        );
        TypedQuery<Prodavac> typed = em.createQuery(criteria);
        try {
            return typed.getSingleResult();
        } catch (final NoResultException nre) {
            return null;
        }
    }

    public static Kupac izmeniKupca(Kupac k) {
        // provera da li username vec postoji
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Kupac> criteria = builder.createQuery(Kupac.class);
        Root<Kupac> from = criteria.from(Kupac.class);
        criteria.select(from);
        List<Kupac> results = em.createQuery(criteria).getResultList();

        boolean uslov = true;
        for (Kupac kTek : results) {
            if (kTek.getUsername().equals(k.getUsername()) && !kTek.getId().equals(k.getId())) {
                uslov = false;
                break;
            }
        }
        if (!uslov) {
            return null;
        }
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
