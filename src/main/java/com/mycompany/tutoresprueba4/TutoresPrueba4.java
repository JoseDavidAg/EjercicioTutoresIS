/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tutoresprueba4;


import Modelo.Controller;
import Modelo.Tutor;
import Modelo.Tutorado;
import java.util.Date;
import java.util.List;


/**
 *
 * @author ambro
 */
public class TutoresPrueba4 {

    public static void main(String[] args) throws Exception {
        Controller control = new Controller();
        /*Tutorado tutorado = new Tutorado("2216089","Jose David",'M',new Date(2004,05,28));
        Tutor tutor = new Tutor(11,1011,"Adelina Ñieto","Ingenieria en Sistemas");
        control.crearTutorado(tutorado);
        control.crearTutor(tutor);
        tutorado.setTutorId(tutor);
        control.editarTutorado(tutorado);
        //control.eliminarTutorado("20031");
        */
        
        Tutorado tutorado = control.traerTutorado("2216099");
        System.out.println(tutorado.toString());
        
        List<Tutorado> lisTutorado = control.traerTutorado();
        
        for(Tutorado t: lisTutorado){
            System.out.println(t.toString());
        }
        
        
        
        

    }
}
