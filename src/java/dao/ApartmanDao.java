package dao;

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

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PosrednikPU");
    private static EntityManager em = emf.createEntityManager();

    public static Apartman unesi(Apartman apartman, String username, String pass) {

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
            em.getTransaction().begin();

            em.persist(apartman);

            em.getTransaction().commit();

        } catch (final NoResultException nre) {
            return null;
        }

        return apartman;
    }

}
