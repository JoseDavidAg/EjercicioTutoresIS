/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Control.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Tutor;
import Modelo.Tutorado;
import Modelo.Tutoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author ambro
 */
public class TutoriaJpaController implements Serializable {

    public TutoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public TutoriaJpaController(){
        emf= Persistence.createEntityManagerFactory("TutoresPrueba4TPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tutoria tutoria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutor tutorId = tutoria.getTutorId();
            if (tutorId != null) {
                tutorId = em.getReference(tutorId.getClass(), tutorId.getIdTutor());
                tutoria.setTutorId(tutorId);
            }
            Tutorado noControl = tutoria.getNoControl();
            if (noControl != null) {
                noControl = em.getReference(noControl.getClass(), noControl.getNoControl());
                tutoria.setNoControl(noControl);
            }
            em.persist(tutoria);
            if (tutorId != null) {
                tutorId.getTutoriaList().add(tutoria);
                tutorId = em.merge(tutorId);
            }
            if (noControl != null) {
                noControl.getTutoriaList().add(tutoria);
                noControl = em.merge(noControl);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tutoria tutoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutoria persistentTutoria = em.find(Tutoria.class, tutoria.getIdTutoria());
            Tutor tutorIdOld = persistentTutoria.getTutorId();
            Tutor tutorIdNew = tutoria.getTutorId();
            Tutorado noControlOld = persistentTutoria.getNoControl();
            Tutorado noControlNew = tutoria.getNoControl();
            if (tutorIdNew != null) {
                tutorIdNew = em.getReference(tutorIdNew.getClass(), tutorIdNew.getIdTutor());
                tutoria.setTutorId(tutorIdNew);
            }
            if (noControlNew != null) {
                noControlNew = em.getReference(noControlNew.getClass(), noControlNew.getNoControl());
                tutoria.setNoControl(noControlNew);
            }
            tutoria = em.merge(tutoria);
            if (tutorIdOld != null && !tutorIdOld.equals(tutorIdNew)) {
                tutorIdOld.getTutoriaList().remove(tutoria);
                tutorIdOld = em.merge(tutorIdOld);
            }
            if (tutorIdNew != null && !tutorIdNew.equals(tutorIdOld)) {
                tutorIdNew.getTutoriaList().add(tutoria);
                tutorIdNew = em.merge(tutorIdNew);
            }
            if (noControlOld != null && !noControlOld.equals(noControlNew)) {
                noControlOld.getTutoriaList().remove(tutoria);
                noControlOld = em.merge(noControlOld);
            }
            if (noControlNew != null && !noControlNew.equals(noControlOld)) {
                noControlNew.getTutoriaList().add(tutoria);
                noControlNew = em.merge(noControlNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tutoria.getIdTutoria();
                if (findTutoria(id) == null) {
                    throw new NonexistentEntityException("The tutoria with id " + id + " no longer exists.");
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
            Tutoria tutoria;
            try {
                tutoria = em.getReference(Tutoria.class, id);
                tutoria.getIdTutoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tutoria with id " + id + " no longer exists.", enfe);
            }
            Tutor tutorId = tutoria.getTutorId();
            if (tutorId != null) {
                tutorId.getTutoriaList().remove(tutoria);
                tutorId = em.merge(tutorId);
            }
            Tutorado noControl = tutoria.getNoControl();
            if (noControl != null) {
                noControl.getTutoriaList().remove(tutoria);
                noControl = em.merge(noControl);
            }
            em.remove(tutoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tutoria> findTutoriaEntities() {
        return findTutoriaEntities(true, -1, -1);
    }

    public List<Tutoria> findTutoriaEntities(int maxResults, int firstResult) {
        return findTutoriaEntities(false, maxResults, firstResult);
    }

    private List<Tutoria> findTutoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tutoria.class));
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

    public Tutoria findTutoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tutoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getTutoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tutoria> rt = cq.from(Tutoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
