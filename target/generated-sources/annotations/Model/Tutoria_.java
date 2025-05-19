package Model;

import Model.Cita;
import Model.Tutorado;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2025-05-13T17:30:49")
@StaticMetamodel(Tutoria.class)
public class Tutoria_ { 

    public static volatile SingularAttribute<Tutoria, Integer> idTutoria;
    public static volatile SingularAttribute<Tutoria, Cita> citaId;
    public static volatile SingularAttribute<Tutoria, Tutorado> noControl;
    public static volatile SingularAttribute<Tutoria, String> observaciones;

}