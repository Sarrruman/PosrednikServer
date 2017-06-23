package beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "apartman")
public class Apartman implements Serializable {

     private static final long serialVersionUID = 1L;
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

     @Column(name = "ime", nullable = false, length = 50)
     private String ime;

     @OneToOne(mappedBy = "apartman", cascade = CascadeType.ALL,
             fetch = FetchType.LAZY, optional = false)
     private Adresa adresa;

     @ManyToOne
     @JoinColumn(name = "idProdavac")
     private Prodavac prodavac;

     @OneToMany(mappedBy = "apartman")
     private List<Soba> sobe;

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public Prodavac getProdavac() {
          return prodavac;
     }

     public void setProdavac(Prodavac prodavac) {
          this.prodavac = prodavac;
     }

     public String getIme() {
          return ime;
     }

     public void setIme(String ime) {
          this.ime = ime;
     }

     public Adresa getAdresa() {
          return adresa;
     }

     public void setAdresa(Adresa adresa) {
          this.adresa = adresa;
          adresa.setApartman(this);
     }

     public List<Soba> getSobe() {
          return sobe;
     }

     public void setSobe(List<Soba> sobe) {
          this.sobe = sobe;
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
          if (!(object instanceof Apartman)) {
               return false;
          }
          Apartman other = (Apartman) object;
          if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
               return false;
          }
          return true;
     }

     @Override
     public String toString() {
          return "beans.Apartman[ id=" + id + " ]";
     }

}
