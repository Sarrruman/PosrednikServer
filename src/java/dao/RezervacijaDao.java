package dao;

import beans.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import messages.ListaRezervacija;
import messages.RezervacijaMessage;

public class RezervacijaDao {

    public static String unesi(RezervacijaMessage msg) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PosrednikPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            Kupac kupac = em.merge(msg.getKupac());
            Soba soba = em.merge(msg.getSoba());

            Rezervacija rezervacija = new Rezervacija();
            rezervacija.setDatumPrijave(msg.getDatumOd());
            rezervacija.setDatumOdjave(msg.getDatumDo());
            rezervacija.setSoba(soba);
            rezervacija.setKupac(kupac);

            em.persist(rezervacija);

            em.getTransaction().commit();
            em.close();

        } catch (final NoResultException nre) {
            return null;
        }

        return new String();
    }

    public static ListaRezervacija dohvatiSve(Soba soba) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PosrednikPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // clear cache
        em.getEntityManagerFactory().getCache().evictAll();
        try {
            List<Rezervacija> res = em.createNamedQuery("dohvatiRezervacije", Rezervacija.class).setParameter(1, soba.getId())
                    .getResultList();
            em.getTransaction().commit();
            em.close();
            ListaRezervacija r = new ListaRezervacija();
            r.setRezervacije(res);
            return r;
        } catch (final NoResultException nre) {
            return null;
        }
    }
}
