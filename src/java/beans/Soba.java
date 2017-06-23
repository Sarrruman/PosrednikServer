package beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "soba")
public class Soba implements Serializable {

     private static final long serialVersionUID = 1L;
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

     @Column(name = "redBr")
     private long redBr;

     @Column(name = "brOsoba")
     private long brOsoba;

     @Column(name = "opis", length = 50)
     private String opis;

     @ManyToOne
     @JoinColumn(name = "idApartman")
     private Apartman apartman;

     @OneToMany(mappedBy = "soba")
     private List<Rezervacija> rezervacije;

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public long getRedBr() {
          return redBr;
     }

     public void setRedBr(long redBr) {
          this.redBr = redBr;
     }

     public long getBrOsoba() {
          return brOsoba;
     }

     public void setBrOsoba(long brOsoba) {
          this.brOsoba = brOsoba;
     }

     public String getOpis() {
          return opis;
     }

     public void setOpis(String opis) {
          this.opis = opis;
     }

     public Apartman getApartman() {
          return apartman;
     }

     public void setApartman(Apartman apartman) {
          this.apartman = apartman;
     }

     public List<Rezervacija> getRezervacije() {
          return rezervacije;
     }

     public void setRezervacije(List<Rezervacija> rezervacije) {
          this.rezervacije = rezervacije;
     }

     @Override
     public int hashCode() {
          int hash = 0;
          hash += (id != null ? id.hashCode() : 0);
          return hash;
     }

     @Override
     public boolean equals(Object object) {
          // TODO: Warning - this method won't work in the case the id fields are not set
          if (!(object instanceof Soba)) {
               return false;
          }
          Soba other = (Soba) object;
          if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
               return false;
          }
          return true;
     }

     @Override
     public String toString() {
          return "beans.Soba[ id=" + id + " ]";
     }

}
