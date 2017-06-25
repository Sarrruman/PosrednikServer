package beans;

import beans.Apartman;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2017-06-24T23:17:22")
@StaticMetamodel(Prodavac.class)
public class Prodavac_ extends Korisnik_ {

    public static volatile SingularAttribute<Prodavac, Long> POSBr;
    public static volatile ListAttribute<Prodavac, Apartman> apartmani;

}