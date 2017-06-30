package dao;

import beans.*;
import java.sql.Date;
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

            // provera da li je soba vec zauzeta u tom terminu
            List<Rezervacija> rezervacije = em.createNamedQuery("dohvatiRezervacije", Rezervacija.class).setParameter(1, soba.getId())
                    .getResultList();

            boolean moze = true;
            for (int i = 0; i < rezervacije.size(); i++) {
                if (!proveriDatum(rezervacije.get(i), msg.getDatumOd(), msg.getDatumDo())) {
                    moze = false;
                    break;
                }
            }

            if (moze) {
                em.persist(rezervacija);
                em.getTransaction().commit();
                em.close();
                return new String();
            } else {
                em.getTransaction().commit();
                em.close();
                return null;
            }
        } catch (final NoResultException nre) {
            return null;
        }

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

    private static boolean proveriDatum(Rezervacija r, Date datumOd, Date datumDo) {
        if (datumOd.compareTo(r.getDatumPrijave()) >= 0 && datumOd.compareTo(r.getDatumOdjave()) <= 0) {
            return false;
        }
        if (datumDo.compareTo(r.getDatumPrijave()) >= 0 && datumDo.compareTo(r.getDatumOdjave()) <= 0) {
            return false;
        }
        if (datumOd.compareTo(r.getDatumPrijave()) <= 0 && datumDo.compareTo(r.getDatumOdjave()) >= 0) {
            return false;
        }

        return true;
    }
}
