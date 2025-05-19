package Model;

import Model.Cita;
import Model.Tutor;
import Model.Tutoria;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-05-13T17:30:49")
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