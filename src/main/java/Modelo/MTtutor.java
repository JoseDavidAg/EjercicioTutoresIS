/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ambro
 */
public class MTtutor extends AbstractTableModel{
    private List<Tutor>tutores;
    private final String encabezado[]={ "No.Tarjeta","Nombre","Carrera"};
    public MTtutor(List<Tutor>tutores){
        this.tutores=tutores;
    }
    @Override
    public int getRowCount() {
        if(tutores!=null)
            return tutores.size();
        return 0;
    }

    @Override
    public int getColumnCount() {
       if(tutores!=null)
           return encabezado.length;
       return 0;
    }   

    @Override
    public Object getValueAt(int row, int column) {
        Tutor t= tutores.get(row);
        return switch (column) {
            case 0 -> t.getNumTarjeta();
            case 1 -> t.getNombre();
            default -> t.getCarrera();
        };
    }
    
}
