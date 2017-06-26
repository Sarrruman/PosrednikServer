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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import messages.Login;
import utils.Helpers;
import utils.TipZahteva;

public class Main {
    
    @Resource(lookup = "Zahtevi")
    static Queue zahtevi;
    @Resource(lookup = "Odgovori")
    static Topic odgovori;
    @Resource(lookup = "jms/__defaultConnectionFactory")
    static ConnectionFactory connectionFactory;
    
    public static JMSContext context = connectionFactory.createContext();
    public static JMSConsumer consumer = context.createConsumer(Main.zahtevi);
    public static JMSProducer producer = context.createProducer();
    
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
//          
//          
//          Korisnik k = KorisnikDao.dohvati("123", "123");
//          if (k!=null) {
//               System.out.println("Username: " + k.getUsername() + ", pass: " + k.getPassword());
//          } else {
//               System.out.println("Ne postoji");
//          }

        //new Admin().start();
        System.out.println("Listening for requests started!");
        
        while (true) {
            Message message = consumer.receive();

            // sve poruke koje se primaju moraju biti tipa ObjectMessage
            if (message instanceof ObjectMessage) {
                try {
                    obradiPoruku(message);
                } catch (JMSException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Stigla poruka koja nije tipa ObjectMessage");
                System.exit(1);
            }
        }
        
    }
    
    private static void obradiPoruku(Message message) throws JMSException {
        ObjectMessage objectMessage = (ObjectMessage) message;
        Object objekat = objectMessage.getObject();
        int tip = objectMessage.getIntProperty("tip");
        
        ObjectMessage odgovor = null;
        switch (TipZahteva.fromInteger(tip)) {
            case LOGIN_KUPAC:
                odgovor = obradaLogin((Login) objekat, producer, "kupac");
            case LOGIN_PRODAVAC:
                odgovor = obradaLogin((Login) objekat, producer, "prodavac");
            case IZMENI_PODATKE_KUPAC:
                odgovor = izmeniPodatkeKupca((Kupac) objekat);
            case IZMENI_PODATKE_PRODAVAC:
                odgovor = izmeniPodatkeProdavca((Prodavac) objekat);
            
        }
        Destination destination = Main.odgovori;
        odgovor.setStringProperty("id", message.getStringProperty("id"));
        producer.send(destination, odgovor);
    }
    
    private static ObjectMessage obradaLogin(Login login, JMSProducer producer, String tip) throws JMSException {
        ObjectMessage odgovor = context.createObjectMessage();
        
        if (tip.equals("kupac")) {
            Kupac k = KorisnikDao.dohvatiKupca(login.getUsername(), login.getPassword());
            odgovor.setObject(k);
        } else {
            Prodavac k = KorisnikDao.dohvatiProdavca(login.getUsername(), login.getPassword());
            odgovor.setObject(k);
        }
        return odgovor;
    }
    
    private static ObjectMessage izmeniPodatkeKupca(Kupac kupac) throws JMSException {
        ObjectMessage odgovor = context.createObjectMessage();
        Kupac k = KorisnikDao.izmeniKupca(kupac);
        odgovor.setObject(k);
        return odgovor;
    }
    
    private static ObjectMessage izmeniPodatkeProdavca(Prodavac prodavac) throws JMSException {
        ObjectMessage odgovor = context.createObjectMessage();
        Prodavac p = KorisnikDao.izmeniProdavca(prodavac);
        odgovor.setObject(p);
        return odgovor;
    }
    
}
