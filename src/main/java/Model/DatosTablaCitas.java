/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 *
 * @author ambro
 */
public class DatosTablaCitas {
     private Tutorado t;
    private JCheckBox asistencia;
    private JTextField accion;

    public DatosTablaCitas(Tutorado t) {
        this.t = t;
        asistencia=new JCheckBox();
        accion= new JTextField(30);
        accion.setEditable(true);
    }
    
    
    

    public Tutorado getT() {
        return t;
    }

    public void setT(Tutorado t) {
        this.t = t;
    }

    public boolean getAsistencia() {
        return asistencia.isSelected();
    }

    public void setAsistencia(boolean v) {
        asistencia.setSelected(v);
    }

    public String getAccion() {
        return accion.getText();
    }

    public void setAccion(String t) {
        accion.setText(t);
    }
    
}
