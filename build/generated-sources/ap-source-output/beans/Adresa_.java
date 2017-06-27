package beans;

import beans.Apartman;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2017-06-28T00:17:31")
@StaticMetamodel(Adresa.class)
public class Adresa_ { 

    public static volatile SingularAttribute<Adresa, String> ulica;
    public static volatile SingularAttribute<Adresa, String> drzava;
    public static volatile SingularAttribute<Adresa, Long> broj;
    public static volatile SingularAttribute<Adresa, Apartman> apartman;
    public static volatile SingularAttribute<Adresa, Long> id;
    public static volatile SingularAttribute<Adresa, String> grad;
    public static volatile SingularAttribute<Adresa, String> opis;

}