/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ambro
 */
@Entity
@Table(name = "tutorado")
@NamedQueries({
    @NamedQuery(name = "Tutorado.findAll", query = "SELECT t FROM Tutorado t"),
    @NamedQuery(name = "Tutorado.findByNoControl", query = "SELECT t FROM Tutorado t WHERE t.noControl = :noControl"),
    @NamedQuery(name = "Tutorado.findByNombre", query = "SELECT t FROM Tutorado t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tutorado.findByGenero", query = "SELECT t FROM Tutorado t WHERE t.genero = :genero"),
    @NamedQuery(name = "Tutorado.findByFecha", query = "SELECT t FROM Tutorado t WHERE t.fecha = :fecha")})
public class Tutorado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "no_control")
    private String noControl;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "genero")
    private Character genero;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "tutor_id", referencedColumnName = "id_tutor")
    @ManyToOne
    private Tutor tutorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "noControl")
    private List<Cita> citaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "noControl")
    private List<Tutoria> tutoriaList;

    public Tutorado() {
    }

    public Tutorado(String noControl) {
        this.noControl = noControl;
    }

    public Tutorado(String noControl, String nombre, Character genero, Date fecha ) {
        this.noControl = noControl;
        this.nombre = nombre;
        this.genero = genero;
        this.fecha = fecha;
        
        
    }

    public String getNoControl() {
        return noControl;
    }

    public void setNoControl(String noControl) {
        this.noControl = noControl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Tutor getTutorId() {
        return tutorId;
    }

    public void setTutorId(Tutor tutorId) {
        this.tutorId = tutorId;
    }

    public List<Cita> getCitaList() {
        return citaList;
    }

    public void setCitaList(List<Cita> citaList) {
        this.citaList = citaList;
    }

    public List<Tutoria> getTutoriaList() {
        return tutoriaList;
    }

    public void setTutoriaList(List<Tutoria> tutoriaList) {
        this.tutoriaList = tutoriaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noControl != null ? noControl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tutorado)) {
            return false;
        }
        Tutorado other = (Tutorado) object;
        if ((this.noControl == null && other.noControl != null) || (this.noControl != null && !this.noControl.equals(other.noControl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Tutorado[ noControl=" + noControl + " nombre="+nombre+"]";
    }
    
}
