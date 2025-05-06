/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ambro
 */
@Entity
@Table(name = "tutoria")
@NamedQueries({
    @NamedQuery(name = "Tutoria.findAll", query = "SELECT t FROM Tutoria t"),
    @NamedQuery(name = "Tutoria.findByIdTutoria", query = "SELECT t FROM Tutoria t WHERE t.idTutoria = :idTutoria"),
    @NamedQuery(name = "Tutoria.findByFecha", query = "SELECT t FROM Tutoria t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "Tutoria.findByTema", query = "SELECT t FROM Tutoria t WHERE t.tema = :tema")})
public class Tutoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tutoria")
    private Integer idTutoria;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "tema")
    private String tema;
    @Lob
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "tutor_id", referencedColumnName = "id_tutor")
    @ManyToOne(optional = false)
    private Tutor tutorId;
    @JoinColumn(name = "no_control", referencedColumnName = "no_control")
    @ManyToOne(optional = false)
    private Tutorado noControl;

    public Tutoria() {
    }

    public Tutoria(Integer idTutoria) {
        this.idTutoria = idTutoria;
    }

    public Tutoria(Integer idTutoria, Date fecha) {
        this.idTutoria = idTutoria;
        this.fecha = fecha;
    }

    public Integer getIdTutoria() {
        return idTutoria;
    }

    public void setIdTutoria(Integer idTutoria) {
        this.idTutoria = idTutoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Tutor getTutorId() {
        return tutorId;
    }

    public void setTutorId(Tutor tutorId) {
        this.tutorId = tutorId;
    }

    public Tutorado getNoControl() {
        return noControl;
    }

    public void setNoControl(Tutorado noControl) {
        this.noControl = noControl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTutoria != null ? idTutoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tutoria)) {
            return false;
        }
        Tutoria other = (Tutoria) object;
        if ((this.idTutoria == null && other.idTutoria != null) || (this.idTutoria != null && !this.idTutoria.equals(other.idTutoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Tutoria[ idTutoria=" + idTutoria + " ]";
    }
    
}
