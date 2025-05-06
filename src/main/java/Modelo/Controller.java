/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Control.AdmController;
import Control.exceptions.IllegalOrphanException;
import Control.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author ambro
 */
public class Controller {
    AdmController admController = new AdmController();
    
    public void crearTutorado(Tutorado tutorado) throws Exception{
        admController.crearTutorado(tutorado);    
    }
    
    public void crearTutor(Tutor tutor){
        admController.crearTutor(tutor);
    }
    
    public void crearTutoria(Tutoria tutoria){
        admController.crearTutoria(tutoria);
    }
    public void crearCita(Cita cita){
        admController.crearCita(cita);
    }
    public void eliminarTutorado(String noControl) throws IllegalOrphanException, NonexistentEntityException{
        admController.eliminarTutorado(noControl);
    }
    
    public void eliminarCita(int idCita) throws NonexistentEntityException{
        admController.eliminarCita(idCita);
    }
    
    public void eliminarTutor(int idTutor) throws IllegalOrphanException, NonexistentEntityException{
        admController.eliminarTutor(idTutor);
    }
    
    public void eliminarTutoria(int idTutoria) throws NonexistentEntityException{
        admController.eliminarTutoria(idTutoria);
    }
    
    public void editarTutorado(Tutorado tutorado) throws Exception{
        admController.editarTutorado(tutorado);
    }
    
    public void editarCita(Cita cita) throws Exception{
        admController.editarCita(cita);
    }
    
    public void editarTutor(Tutor tutor) throws Exception{
        admController.editarTutor(tutor);
    }
    
    public void editarTutoria(Tutoria tutoria) throws Exception{
        admController.editarTutoria(tutoria);
    }
    
    public Tutorado traerTutorado(String idTutorado){
        return admController.traerTutorado(idTutorado);
    }
    
    public List<Tutorado> traerTutorado(){
        return admController.traerTutorado();
    }

    public List<Tutor> traerTutores() {
        return admController.traerTutores();
    }
}
