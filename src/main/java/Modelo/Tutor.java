/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ambro
 */
@Entity
@Table(name = "tutor")
@NamedQueries({
    @NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t"),
    @NamedQuery(name = "Tutor.findByIdTutor", query = "SELECT t FROM Tutor t WHERE t.idTutor = :idTutor"),
    @NamedQuery(name = "Tutor.findByNumTarjeta", query = "SELECT t FROM Tutor t WHERE t.numTarjeta = :numTarjeta"),
    @NamedQuery(name = "Tutor.findByNombre", query = "SELECT t FROM Tutor t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tutor.findByCarrera", query = "SELECT t FROM Tutor t WHERE t.carrera = :carrera"),
    @NamedQuery(name = "Tutor.findByDiasHoras", query = "SELECT t FROM Tutor t WHERE t.diasHoras = :diasHoras")})
public class Tutor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tutor")
    private Integer idTutor;
    @Column(name = "num_tarjeta")
    private Integer numTarjeta;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "carrera")
    private String carrera;
    @Column(name = "dias_horas")
    private String diasHoras;
    @OneToMany(mappedBy = "tutorId")
    private List<Tutorado> tutoradoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutorId")
    private List<Cita> citaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutorId")
    private List<Tutoria> tutoriaList;

    public Tutor() {
    }

    public Tutor(Integer idTutor) {
        this.idTutor = idTutor;
    }

    public Tutor(Integer idTutor, Integer numTarjeta, String nombre, String carrera) {
        this.idTutor = idTutor;
        this.nombre = nombre;
        this.carrera = carrera;
        this.numTarjeta=numTarjeta;
    }

    public Integer getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Integer idTutor) {
        this.idTutor = idTutor;
    }

    public Integer getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(Integer numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getDiasHoras() {
        return diasHoras;
    }

    public void setDiasHoras(String diasHoras) {
        this.diasHoras = diasHoras;
    }

    public List<Tutorado> getTutoradoList() {
        return tutoradoList;
    }

    public void setTutoradoList(List<Tutorado> tutoradoList) {
        this.tutoradoList = tutoradoList;
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
        hash += (idTutor != null ? idTutor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tutor)) {
            return false;
        }
        Tutor other = (Tutor) object;
        if ((this.idTutor == null && other.idTutor != null) || (this.idTutor != null && !this.idTutor.equals(other.idTutor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Tutor[ idTutor=" + idTutor + " ]";
    }
    
}
