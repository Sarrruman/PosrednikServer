package beans;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Rezervacija implements Serializable {

     private static final long serialVersionUID = 1L;
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

     @Column(name = "datumPrijave")
     private Date datumPrijave;

     @Column(name = "datumOdjave")
     private Date datumOdjave;

     @ManyToOne
     @JoinColumn(name = "idSoba")
     private Soba soba;

     @ManyToOne
     @JoinColumn(name = "idKupac")
     private Kupac kupac;

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public Date getDatumPrijave() {
          return datumPrijave;
     }

     public void setDatumPrijave(Date datumPrijave) {
          this.datumPrijave = datumPrijave;
     }

     public Date getDatumOdjave() {
          return datumOdjave;
     }

     public void setDatumOdjave(Date datumOdjave) {
          this.datumOdjave = datumOdjave;
     }

     public Soba getSoba() {
          return soba;
     }

     public void setSoba(Soba soba) {
          this.soba = soba;
     }

     public Kupac getKupac() {
          return kupac;
     }

     public void setKupac(Kupac kupac) {
          this.kupac = kupac;
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
          if (!(object instanceof Rezervacija)) {
               return false;
          }
          Rezervacija other = (Rezervacija) object;
          if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
               return false;
          }
          return true;
     }

     @Override
     public String toString() {
          return "beans.Rezervacija[ id=" + id + " ]";
     }

}
