/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Control.exceptions.IllegalOrphanException;
import Control.exceptions.NonexistentEntityException;
import Modelo.Cita;
import Modelo.Tutor;
import Modelo.Tutorado;
import Modelo.Tutoria;
import java.util.List;

/**
 *
 * @author ambro
 */
public class AdmController {
    private final CitaJpaController citaJpa;
    private final TutorJpaController tutorJpa;
    private final TutoradoJpaController tutoradoJpa;
    private final TutoriaJpaController tutoriaJpa;
    
    public AdmController(){
        citaJpa= new CitaJpaController();
        tutorJpa= new TutorJpaController();
        tutoradoJpa= new TutoradoJpaController();
        tutoriaJpa= new TutoriaJpaController();
    }

    public void crearTutorado(Tutorado tutorado) throws Exception {
        tutoradoJpa.create(tutorado);
    }
    
    public void crearCita(Cita cita){
        citaJpa.create(cita);
    }
    
    public void crearTutor(Tutor tutor){
        tutorJpa.create(tutor);
    }
    
    public void crearTutoria(Tutoria tutoria){
        tutoriaJpa.create(tutoria);
    }

    public void eliminarTutorado(String noControl) throws IllegalOrphanException, NonexistentEntityException {
        tutoradoJpa.destroy(noControl);
    }
    
    public void eliminarCita(int idCita) throws NonexistentEntityException{
        citaJpa.destroy(idCita);
    }

    public void eliminarTutor(int idTutor) throws IllegalOrphanException, NonexistentEntityException {
        tutorJpa.destroy(idTutor);
    }

    public void eliminarTutoria(int idTutoria) throws NonexistentEntityException {
       tutoriaJpa.destroy(idTutoria);
    }

    public void editarTutorado(Tutorado tutorado) throws NonexistentEntityException, Exception {
        tutoradoJpa.edit(tutorado);
    }

    public void editarCita(Cita cita) throws Exception {
        citaJpa.edit(cita);
    }

    public void editarTutor(Tutor tutor) throws NonexistentEntityException, Exception {
        tutorJpa.edit(tutor);
    }

    public void editarTutoria(Tutoria tutoria) throws Exception {
        tutoriaJpa.edit(tutoria);
    }

    public Tutorado traerTutorado(String idTutorado) {
        return tutoradoJpa.findTutorado(idTutorado);
    }

    public List<Tutorado> traerTutorado() {
        return tutoradoJpa.findTutoradoEntities();
    }

    public List<Tutor> traerTutores() {
       return tutorJpa.findTutorEntities();
    }
    
    
    
}
