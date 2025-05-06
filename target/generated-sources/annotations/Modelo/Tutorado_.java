package Modelo;

import Modelo.Cita;
import Modelo.Tutor;
import Modelo.Tutoria;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-05-05T21:36:08", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Tutorado.class)
public class Tutorado_ { 

    public static volatile SingularAttribute<Tutorado, Date> fecha;
    public static volatile SingularAttribute<Tutorado, Tutor> tutorId;
    public static volatile SingularAttribute<Tutorado, String> noControl;
    public static volatile SingularAttribute<Tutorado, Character> genero;
    public static volatile ListAttribute<Tutorado, Tutoria> tutoriaList;
    public static volatile SingularAttribute<Tutorado, String> nombre;
    public static volatile ListAttribute<Tutorado, Cita> citaList;

}