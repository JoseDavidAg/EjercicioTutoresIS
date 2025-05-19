/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Control.exceptions.NonexistentEntityException;
import Model.Cita;
import Model.DatosTablaCitas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.Tutor;
import Model.Tutorado;
import Model.Tutoria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author ambro
 */
public class CitaJpaController implements Serializable {

    public CitaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public CitaJpaController(){
        emf=Persistence.createEntityManagerFactory("TutoresPrueba4TPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cita cita) {
        if (cita.getTutoriaList() == null) {
            cita.setTutoriaList(new ArrayList<Tutoria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutor tutorId = cita.getTutorId();
            if (tutorId != null) {
                tutorId = em.getReference(tutorId.getClass(), tutorId.getIdTutor());
                cita.setTutorId(tutorId);
            }
            Tutorado noControl = cita.getNoControl();
            if (noControl != null) {
                noControl = em.getReference(noControl.getClass(), noControl.getNoControl());
                cita.setNoControl(noControl);
            }
            List<Tutoria> attachedTutoriaList = new ArrayList<Tutoria>();
            for (Tutoria tutoriaListTutoriaToAttach : cita.getTutoriaList()) {
                tutoriaListTutoriaToAttach = em.getReference(tutoriaListTutoriaToAttach.getClass(), tutoriaListTutoriaToAttach.getIdTutoria());
                attachedTutoriaList.add(tutoriaListTutoriaToAttach);
            }
            cita.setTutoriaList(attachedTutoriaList);
            em.persist(cita);
            if (tutorId != null) {
                tutorId.getCitaList().add(cita);
                tutorId = em.merge(tutorId);
            }
            if (noControl != null) {
                noControl.getCitaList().add(cita);
                noControl = em.merge(noControl);
            }
            for (Tutoria tutoriaListTutoria : cita.getTutoriaList()) {
                Cita oldCitaIdOfTutoriaListTutoria = tutoriaListTutoria.getCitaId();
                tutoriaListTutoria.setCitaId(cita);
                tutoriaListTutoria = em.merge(tutoriaListTutoria);
                if (oldCitaIdOfTutoriaListTutoria != null) {
                    oldCitaIdOfTutoriaListTutoria.getTutoriaList().remove(tutoriaListTutoria);
                    oldCitaIdOfTutoriaListTutoria = em.merge(oldCitaIdOfTutoriaListTutoria);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cita cita) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cita persistentCita = em.find(Cita.class, cita.getIdCita());
            Tutor tutorIdOld = persistentCita.getTutorId();
            Tutor tutorIdNew = cita.getTutorId();
            Tutorado noControlOld = persistentCita.getNoControl();
            Tutorado noControlNew = cita.getNoControl();
            List<Tutoria> tutoriaListOld = persistentCita.getTutoriaList();
            List<Tutoria> tutoriaListNew = cita.getTutoriaList();
            if (tutorIdNew != null) {
                tutorIdNew = em.getReference(tutorIdNew.getClass(), tutorIdNew.getIdTutor());
                cita.setTutorId(tutorIdNew);
            }
            if (noControlNew != null) {
                noControlNew = em.getReference(noControlNew.getClass(), noControlNew.getNoControl());
                cita.setNoControl(noControlNew);
            }
            List<Tutoria> attachedTutoriaListNew = new ArrayList<Tutoria>();
            for (Tutoria tutoriaListNewTutoriaToAttach : tutoriaListNew) {
                tutoriaListNewTutoriaToAttach = em.getReference(tutoriaListNewTutoriaToAttach.getClass(), tutoriaListNewTutoriaToAttach.getIdTutoria());
                attachedTutoriaListNew.add(tutoriaListNewTutoriaToAttach);
            }
            tutoriaListNew = attachedTutoriaListNew;
            cita.setTutoriaList(tutoriaListNew);
            cita = em.merge(cita);
            if (tutorIdOld != null && !tutorIdOld.equals(tutorIdNew)) {
                tutorIdOld.getCitaList().remove(cita);
                tutorIdOld = em.merge(tutorIdOld);
            }
            if (tutorIdNew != null && !tutorIdNew.equals(tutorIdOld)) {
                tutorIdNew.getCitaList().add(cita);
                tutorIdNew = em.merge(tutorIdNew);
            }
            if (noControlOld != null && !noControlOld.equals(noControlNew)) {
                noControlOld.getCitaList().remove(cita);
                noControlOld = em.merge(noControlOld);
            }
            if (noControlNew != null && !noControlNew.equals(noControlOld)) {
                noControlNew.getCitaList().add(cita);
                noControlNew = em.merge(noControlNew);
            }
            for (Tutoria tutoriaListOldTutoria : tutoriaListOld) {
                if (!tutoriaListNew.contains(tutoriaListOldTutoria)) {
                    tutoriaListOldTutoria.setCitaId(null);
                    tutoriaListOldTutoria = em.merge(tutoriaListOldTutoria);
                }
            }
            for (Tutoria tutoriaListNewTutoria : tutoriaListNew) {
                if (!tutoriaListOld.contains(tutoriaListNewTutoria)) {
                    Cita oldCitaIdOfTutoriaListNewTutoria = tutoriaListNewTutoria.getCitaId();
                    tutoriaListNewTutoria.setCitaId(cita);
                    tutoriaListNewTutoria = em.merge(tutoriaListNewTutoria);
                    if (oldCitaIdOfTutoriaListNewTutoria != null && !oldCitaIdOfTutoriaListNewTutoria.equals(cita)) {
                        oldCitaIdOfTutoriaListNewTutoria.getTutoriaList().remove(tutoriaListNewTutoria);
                        oldCitaIdOfTutoriaListNewTutoria = em.merge(oldCitaIdOfTutoriaListNewTutoria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cita.getIdCita();
                if (findCita(id) == null) {
                    throw new NonexistentEntityException("The cita with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cita cita;
            try {
                cita = em.getReference(Cita.class, id);
                cita.getIdCita();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cita with id " + id + " no longer exists.", enfe);
            }
            Tutor tutorId = cita.getTutorId();
            if (tutorId != null) {
                tutorId.getCitaList().remove(cita);
                tutorId = em.merge(tutorId);
            }
            Tutorado noControl = cita.getNoControl();
            if (noControl != null) {
                noControl.getCitaList().remove(cita);
                noControl = em.merge(noControl);
            }
            List<Tutoria> tutoriaList = cita.getTutoriaList();
            for (Tutoria tutoriaListTutoria : tutoriaList) {
                tutoriaListTutoria.setCitaId(null);
                tutoriaListTutoria = em.merge(tutoriaListTutoria);
            }
            em.remove(cita);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cita> findCitaEntities() {
        return findCitaEntities(true, -1, -1);
    }

    public List<Cita> findCitaEntities(int maxResults, int firstResult) {
        return findCitaEntities(false, maxResults, firstResult);
    }

    private List<Cita> findCitaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cita.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cita findCita(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cita.class, id);
        } finally {
            em.close();
        }
    }
                      
    public List<Cita> findCitaPorTutorEntities(Integer tutorId) {
        EntityManager em = getEntityManager();
        List<Cita> citas = new ArrayList<>();

        try {
            TypedQuery<Cita> query = em.createQuery(
                "SELECT c FROM Cita c WHERE c.tutorId.idTutor = :tutorId ",
                Cita.class
            );
            query.setParameter("tutorId", tutorId);
            citas = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return citas;
    }
    
    



    public int getCitaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cita> rt = cq.from(Cita.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
