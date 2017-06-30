package dao;

import beans.Adresa;
import beans.Apartman;
import beans.Kupac;
import beans.Prodavac;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ApartmanDao {

    public static Apartman unesi(Apartman apartman, String username, String pass) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PosrednikPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

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
            Prodavac prodavac = typed.getSingleResult();

            apartman.setProdavac(prodavac);

            em.persist(apartman);

            em.getTransaction().commit();
            em.close();

        } catch (final NoResultException nre) {
            return null;
        }

        return apartman;
    }

    public static Apartman izmeni(Apartman apartman) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PosrednikPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Apartman res = em.merge(apartman);
        em.getTransaction().commit();
        em.close();
        return res;
    }

    public static String obrisi(Apartman apartman) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PosrednikPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            Adresa adr = em.merge(apartman.getAdresa());
            Apartman ap = em.merge(apartman);
            em.remove(adr);
            em.remove(ap);

            em.getTransaction().commit();
            em.close();
            return new String();
        } catch (Exception e) {
            return null;
        }
    }

}
