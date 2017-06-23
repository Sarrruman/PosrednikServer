package posrednik;

import beans.Adresa;
import beans.Apartman;
import dao.KorisnikDao;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import beans.*;

public class Main {

     @Resource(lookup = "Zahtevi")
     static Queue zahtevi;
     @Resource(lookup = "Odgovori")
     static Topic odgovori;
     @Resource(lookup = "jms/__defaultConnectionFactory")
     static ConnectionFactory connectionFactory;

     public static void main(String[] args) {
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("PosrednikPU");
          EntityManager em = emf.createEntityManager();
//          em.getTransaction().begin();
//          Korisnik korisnik = new Korisnik();
//          korisnik.setEmail("123");
//          korisnik.setUsername("123");
//          korisnik.setPassword("123");
//          
//          em.persist(korisnik);
//          em.getTransaction().commit();
          
          
//          Korisnik k = KorisnikDao.dohvati("123", "123");
//          if (k!=null) {
//               System.out.println("Username: " + k.getUsername() + ", pass: " + k.getPassword());
//          } else {
//               System.out.println("Ne postoji");
//          }

          new Admin().start();
          System.out.println("Listening for requests started!");

     }
}
