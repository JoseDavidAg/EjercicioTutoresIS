package Modelo;

import Modelo.Tutor;
import Modelo.Tutorado;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-05-05T21:36:08", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Tutoria.class)
public class Tutoria_ { 

    public static volatile SingularAttribute<Tutoria, Date> fecha;
    public static volatile SingularAttribute<Tutoria, Tutor> tutorId;
    public static volatile SingularAttribute<Tutoria, Integer> idTutoria;
    public static volatile SingularAttribute<Tutoria, String> tema;
    public static volatile SingularAttribute<Tutoria, Tutorado> noControl;
    public static volatile SingularAttribute<Tutoria, String> observaciones;

}