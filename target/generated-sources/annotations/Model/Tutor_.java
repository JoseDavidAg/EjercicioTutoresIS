package Model;

import Model.Cita;
import Model.Tutorado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-05-13T17:30:49")
@StaticMetamodel(Tutor.class)
public class Tutor_ { 

    public static volatile SingularAttribute<Tutor, Integer> idTutor;
    public static volatile SingularAttribute<Tutor, Integer> numTarjeta;
    public static volatile ListAttribute<Tutor, Tutorado> tutoradoList;
    public static volatile SingularAttribute<Tutor, String> carrera;
    public static volatile SingularAttribute<Tutor, String> nombre;
    public static volatile SingularAttribute<Tutor, String> diasHoras;
    public static volatile ListAttribute<Tutor, Cita> citaList;

}