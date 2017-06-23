package beans;

import beans.Adresa;
import beans.Prodavac;
import beans.Soba;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2017-06-20T22:33:27")
@StaticMetamodel(Apartman.class)
public class Apartman_ { 

    public static volatile SingularAttribute<Apartman, String> ime;
    public static volatile SingularAttribute<Apartman, Adresa> adresa;
    public static volatile ListAttribute<Apartman, Soba> sobe;
    public static volatile SingularAttribute<Apartman, Long> id;
    public static volatile SingularAttribute<Apartman, Prodavac> prodavac;

}