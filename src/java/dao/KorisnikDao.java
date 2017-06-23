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

     public static Korisnik dohvati(String username, String pass) {
          CriteriaBuilder builder = em.getCriteriaBuilder();
          CriteriaQuery<Korisnik> criteria = builder.createQuery(Korisnik.class);
          Root<Korisnik> from = criteria.from(Korisnik.class);
          criteria.select(from);
          Predicate where = builder.equal(from.get(Korisnik_.username), username);
          where = builder.and(where, (builder.equal(from.get(Korisnik_.password), pass)));
          criteria.where(where);
          TypedQuery<Korisnik> typed = em.createQuery(criteria);
          try {
               return typed.getSingleResult();
          } catch (final NoResultException nre) {
               return null;
          }
     }
}
