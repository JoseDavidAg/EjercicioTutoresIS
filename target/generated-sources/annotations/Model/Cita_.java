package Model;

import Model.Tutor;
import Model.Tutorado;
import Model.Tutoria;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-05-13T17:30:49")
@StaticMetamodel(Cita.class)
public class Cita_ { 

    public static volatile SingularAttribute<Cita, Integer> idCita;
    public static volatile SingularAttribute<Cita, Date> fecha;
    public static volatile SingularAttribute<Cita, Tutor> tutorId;
    public static volatile SingularAttribute<Cita, String> motivo;
    public static volatile SingularAttribute<Cita, String> estado;
    public static volatile SingularAttribute<Cita, Date> hora;
    public static volatile SingularAttribute<Cita, Tutorado> noControl;
    public static volatile ListAttribute<Cita, Tutoria> tutoriaList;

}