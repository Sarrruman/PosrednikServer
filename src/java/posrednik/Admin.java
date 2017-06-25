package posrednik;

import beans.Korisnik;
import dao.KorisnikDao;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import messages.Login;

public class Admin extends Thread {

    private enum TipZahteva {
        LOGIN, DOHVATI_PODATKE, LOGOUT, IZMENI_PODATKE;

        public static TipZahteva fromInteger(int x) {
            switch (x) {
                case 0:
                    return LOGIN;
                case 1:
                    return DOHVATI_PODATKE;
                case 2:
                    return LOGOUT;
                case 3:
                    return IZMENI_PODATKE;
            }
            return null;
        }
    }

    public void run() {
        JMSContext context = Main.connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(Main.zahtevi);
        JMSProducer producer = context.createProducer();
        TextMessage odgovor = context.createTextMessage();

        while (true) {
            Message message = consumer.receive();
            if (message instanceof ObjectMessage) {
                try {
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    Object objekat = objectMessage.getObject();
                    int tip = objectMessage.getIntProperty("tip");

                    if (TipZahteva.fromInteger(tip).equals(TipZahteva.LOGIN)) {
                        try {
                            obradaLogin((Login) objekat, producer, odgovor);
                        } catch (JMSException e) {
                            e.printStackTrace();
                            System.exit(1);
                        }
                    }
                } catch (JMSException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void obradaLogin(Login login, JMSProducer producer, TextMessage odgovor) throws JMSException {
        Destination destination = Main.odgovori;

        Korisnik k = KorisnikDao.dohvati(login.getUsername(), login.getPassword());
        if (k != null) {
            odgovor.setText("ok");
        } else {
            odgovor.setText("error");
        }
        producer.send(destination, odgovor);
    }

}
