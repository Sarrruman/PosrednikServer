package beans;

import beans.Apartman;
import beans.Rezervacija;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2017-06-20T22:33:27")
@StaticMetamodel(Soba.class)
public class Soba_ { 

    public static volatile SingularAttribute<Soba, Long> brOsoba;
    public static volatile SingularAttribute<Soba, Long> redBr;
    public static volatile SingularAttribute<Soba, Apartman> apartman;
    public static volatile SingularAttribute<Soba, Long> id;
    public static volatile ListAttribute<Soba, Rezervacija> rezervacije;
    public static volatile SingularAttribute<Soba, String> opis;

}