package beans;

import beans.Rezervacija;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2017-06-29T16:37:28")
@StaticMetamodel(Kupac.class)
public class Kupac_ extends Korisnik_ {

    public static volatile ListAttribute<Kupac, Rezervacija> rezervacije;
    public static volatile SingularAttribute<Kupac, String> brKartice;

}