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
import dao.ApartmanDao;
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
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    
    public static JMSContext context;
    public static JMSConsumer consumer;
    public static JMSProducer producer;
    
    public static void main(String[] args) {
        context = connectionFactory.createContext();
        consumer = context.createConsumer(Main.zahtevi);
        producer = context.createProducer();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PosrednikPU");
        EntityManager em = emf.createEntityManager();

//        em.getTransaction().begin();
//        Kupac korisnik = new Kupac();
//        korisnik.setEmail("123");
//        korisnik.setUsername("123");
//        korisnik.setPassword("123");
//
//        em.persist(korisnik);
//        em.getTransaction().commit();
//
//        Korisnik k = KorisnikDao.dohvatiKupca("123", "123");
//        if (k != null) {
//            System.out.println("Username: " + k.getUsername() + ", pass: " + k.getPassword());
//        } else {
//            System.out.println("Ne postoji");
//        }
        System.out.println(
                "Listening for requests started!");
        
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
                break;
            case LOGIN_PRODAVAC:
                odgovor = obradaLogin((Login) objekat, producer, "prodavac");
                break;
            case IZMENI_PODATKE_KUPAC:
                odgovor = izmeniPodatkeKupca((Kupac) objekat);
                break;
            case IZMENI_PODATKE_PRODAVAC:
                odgovor = izmeniPodatkeProdavca((Prodavac) objekat);
            case UNOS_APARTMANA:
                odgovor = unesiApartman(objekat, message.getStringProperty("username"), message.getStringProperty("password"));
            case IZMENA_APARTMANA:
                odgovor = izmeniApartman(objekat);
            case BRISANJE_APARTMANA:
                odgovor = obrisiApartman(objekat);
            
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
    
    private static ObjectMessage obrisiApartman(Object objekat) throws JMSException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static ObjectMessage izmeniApartman(Object objekat) throws JMSException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static ObjectMessage unesiApartman(Object objekat, String username, String password) throws JMSException {
        ObjectMessage odgovor = context.createObjectMessage();
        Apartman a = ApartmanDao.unesi((Apartman) objekat, username, password);
        odgovor.setObject(a);
        return odgovor;
    }
    
}
