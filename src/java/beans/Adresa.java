package beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "adresa")
public class Adresa implements Serializable {

     private static final long serialVersionUID = 1L;
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

     @OneToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "idApartman")
     private Apartman apartman;

     @Column(name = "drzava", length = 50)
     private String drzava;
     @Column(name = "grad", length = 50)
     private String grad;
     @Column(name = "ulica", length = 50)
     private String ulica;
     @Column(name = "broj", length = 50)
     private long broj;
     @Column(name = "opis", length = 50)
     private String opis;

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public Apartman getApartman() {
          return apartman;
     }

     public void setApartman(Apartman apartman) {
          this.apartman = apartman;
     }

     public String getDrzava() {
          return drzava;
     }

     public void setDrzava(String drzava) {
          this.drzava = drzava;
     }

     public String getGrad() {
          return grad;
     }

     public void setGrad(String grad) {
          this.grad = grad;
     }

     public String getUlica() {
          return ulica;
     }

     public void setUlica(String ulica) {
          this.ulica = ulica;
     }

     public long getBroj() {
          return broj;
     }

     public void setBroj(long broj) {
          this.broj = broj;
     }

     public String getOpis() {
          return opis;
     }

     public void setOpis(String opis) {
          this.opis = opis;
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
          if (!(object instanceof Adresa)) {
               return false;
          }
          Adresa other = (Adresa) object;
          if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
               return false;
          }
          return true;
     }

     @Override
     public String toString() {
          return "beans.Adresa[ id=" + id + " ]";
     }

}
