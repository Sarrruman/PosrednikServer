package beans;

import beans.Kupac;
import beans.Soba;
import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2017-06-24T23:17:22")
@StaticMetamodel(Rezervacija.class)
public class Rezervacija_ { 

    public static volatile SingularAttribute<Rezervacija, Date> datumPrijave;
    public static volatile SingularAttribute<Rezervacija, Kupac> kupac;
    public static volatile SingularAttribute<Rezervacija, Date> datumOdjave;
    public static volatile SingularAttribute<Rezervacija, Long> id;
    public static volatile SingularAttribute<Rezervacija, Soba> soba;

}