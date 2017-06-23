package beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kupac")
public class Kupac extends Korisnik {

     @Column(name = "brKartice", length = 50)
     private String brKartice;

     @OneToMany(mappedBy = "kupac")
     private List<Rezervacija> rezervacije;

     public String getBrKartice() {
          return brKartice;
     }

     public void setBrKartice(String brKartice) {
          this.brKartice = brKartice;
     }

     public List<Rezervacija> getRezervacije() {
          return rezervacije;
     }

     public void setRezervacije(List<Rezervacija> rezervacije) {
          this.rezervacije = rezervacije;
     }

     @Override
     public String toString() {
          return "beans.Kupac{korisnik: " + super.toString() + ", cartNumber=" + brKartice + "}";
     }
}
