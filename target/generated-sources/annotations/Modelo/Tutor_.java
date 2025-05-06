package Modelo;

import Modelo.Cita;
import Modelo.Tutorado;
import Modelo.Tutoria;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-05-05T21:36:08", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Tutor.class)
public class Tutor_ { 

    public static volatile SingularAttribute<Tutor, Integer> idTutor;
    public static volatile SingularAttribute<Tutor, Integer> numTarjeta;
    public static volatile ListAttribute<Tutor, Tutoria> tutoriaList;
    public static volatile ListAttribute<Tutor, Tutorado> tutoradoList;
    public static volatile SingularAttribute<Tutor, String> carrera;
    public static volatile SingularAttribute<Tutor, String> nombre;
    public static volatile SingularAttribute<Tutor, String> diasHoras;
    public static volatile ListAttribute<Tutor, Cita> citaList;

}