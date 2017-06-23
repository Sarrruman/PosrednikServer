package beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "prodavac")
public class Prodavac extends Korisnik {

     @Column(name = "POSBr")
     private long POSBr;

     @OneToMany(mappedBy = "prodavac", fetch = FetchType.EAGER)
     private List<Apartman> apartmani;

     public long getPOSBr() {
          return POSBr;
     }

     public void setPOSBr(long POSBr) {
          this.POSBr = POSBr;
     }

     public List<Apartman> getApartmani() {
          return apartmani;
     }

     public void setApartmani(List<Apartman> apartmani) {
          this.apartmani = apartmani;
     }

}
