/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import java.lang.Boolean;
import java.util.List;
/**
 *
 * @author ambro
 */
public class MTCita extends AbstractTableModel{
    private List<DatosTablaCitas> datosCitas;
    String encabezado[]={"Tutorado","Asistencia","Accion"};
    Class clasesC[]={String.class, Boolean.class,String.class};
    
    public MTCita(List<DatosTablaCitas> mtc){
        datosCitas=mtc;
    }
    @Override
    public boolean isCellEditable(int r, int c){
        if(c==1)return true;
        if(c==2){
            if(datosCitas.get(r).getAsistencia()){
                return true;
            }
        }
        return false;
    }
    
    public String getColumnaNombre(int c){
        return encabezado[c];
    }
    
    public Class getColumnClass(int c){
        return clasesC[c];
    }
    
    @Override
    public int getRowCount() {
        return datosCitas.size();
    }

    @Override
    public int getColumnCount() {
        return encabezado.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0: return datosCitas.get(row).getT().getNombre();
            case 1: return datosCitas.get(row).getAsistencia();
            case 2: return datosCitas.get(row).getAccion();
            default: return null;
        }
    }
    
    @Override
    public void setValueAt(Object dato, int r, int c){
        if(c==1){
            datosCitas.get(r).setAsistencia((Boolean)dato);
        }
    }
    
    
}
