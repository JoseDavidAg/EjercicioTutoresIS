package Modelo;

import Modelo.Tutor;
import Modelo.Tutorado;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-05-05T21:36:08", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Cita.class)
public class Cita_ { 

    public static volatile SingularAttribute<Cita, Integer> idCita;
    public static volatile SingularAttribute<Cita, Date> fecha;
    public static volatile SingularAttribute<Cita, Tutor> tutorId;
    public static volatile SingularAttribute<Cita, String> motivo;
    public static volatile SingularAttribute<Cita, String> estado;
    public static volatile SingularAttribute<Cita, Date> hora;
    public static volatile SingularAttribute<Cita, Tutorado> noControl;

}