/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Control.exceptions.IllegalOrphanException;
import Control.exceptions.NonexistentEntityException;
import Control.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.Tutor;
import Model.Cita;
import Model.Tutorado;
import java.util.ArrayList;
import java.util.List;
import Model.Tutoria;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author ambro
 */
public class TutoradoJpaController implements Serializable {

    public TutoradoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public TutoradoJpaController(){
        emf= Persistence.createEntityManagerFactory("TutoresPrueba4TPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tutorado tutorado) throws PreexistingEntityException, Exception {
        if (tutorado.getCitaList() == null) {
            tutorado.setCitaList(new ArrayList<Cita>());
        }
        if (tutorado.getTutoriaList() == null) {
            tutorado.setTutoriaList(new ArrayList<Tutoria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutor tutorId = tutorado.getTutorId();
            if (tutorId != null) {
                tutorId = em.getReference(tutorId.getClass(), tutorId.getIdTutor());
                tutorado.setTutorId(tutorId);
            }
            List<Cita> attachedCitaList = new ArrayList<Cita>();
            for (Cita citaListCitaToAttach : tutorado.getCitaList()) {
                citaListCitaToAttach = em.getReference(citaListCitaToAttach.getClass(), citaListCitaToAttach.getIdCita());
                attachedCitaList.add(citaListCitaToAttach);
            }
            tutorado.setCitaList(attachedCitaList);
            List<Tutoria> attachedTutoriaList = new ArrayList<Tutoria>();
            for (Tutoria tutoriaListTutoriaToAttach : tutorado.getTutoriaList()) {
                tutoriaListTutoriaToAttach = em.getReference(tutoriaListTutoriaToAttach.getClass(), tutoriaListTutoriaToAttach.getIdTutoria());
                attachedTutoriaList.add(tutoriaListTutoriaToAttach);
            }
            tutorado.setTutoriaList(attachedTutoriaList);
            em.persist(tutorado);
            if (tutorId != null) {
                tutorId.getTutoradoList().add(tutorado);
                tutorId = em.merge(tutorId);
            }
            for (Cita citaListCita : tutorado.getCitaList()) {
                Tutorado oldNoControlOfCitaListCita = citaListCita.getNoControl();
                citaListCita.setNoControl(tutorado);
                citaListCita = em.merge(citaListCita);
                if (oldNoControlOfCitaListCita != null) {
                    oldNoControlOfCitaListCita.getCitaList().remove(citaListCita);
                    oldNoControlOfCitaListCita = em.merge(oldNoControlOfCitaListCita);
                }
            }
            for (Tutoria tutoriaListTutoria : tutorado.getTutoriaList()) {
                Tutorado oldNoControlOfTutoriaListTutoria = tutoriaListTutoria.getNoControl();
                tutoriaListTutoria.setNoControl(tutorado);
                tutoriaListTutoria = em.merge(tutoriaListTutoria);
                if (oldNoControlOfTutoriaListTutoria != null) {
                    oldNoControlOfTutoriaListTutoria.getTutoriaList().remove(tutoriaListTutoria);
                    oldNoControlOfTutoriaListTutoria = em.merge(oldNoControlOfTutoriaListTutoria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTutorado(tutorado.getNoControl()) != null) {
                throw new PreexistingEntityException("Tutorado " + tutorado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tutorado tutorado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutorado persistentTutorado = em.find(Tutorado.class, tutorado.getNoControl());
            Tutor tutorIdOld = persistentTutorado.getTutorId();
            Tutor tutorIdNew = tutorado.getTutorId();
            List<Cita> citaListOld = persistentTutorado.getCitaList();
            List<Cita> citaListNew = tutorado.getCitaList();
            List<Tutoria> tutoriaListOld = persistentTutorado.getTutoriaList();
            List<Tutoria> tutoriaListNew = tutorado.getTutoriaList();
            List<String> illegalOrphanMessages = null;
            for (Cita citaListOldCita : citaListOld) {
                if (!citaListNew.contains(citaListOldCita)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cita " + citaListOldCita + " since its noControl field is not nullable.");
                }
            }
            for (Tutoria tutoriaListOldTutoria : tutoriaListOld) {
                if (!tutoriaListNew.contains(tutoriaListOldTutoria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tutoria " + tutoriaListOldTutoria + " since its noControl field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tutorIdNew != null) {
                tutorIdNew = em.getReference(tutorIdNew.getClass(), tutorIdNew.getIdTutor());
                tutorado.setTutorId(tutorIdNew);
            }
            List<Cita> attachedCitaListNew = new ArrayList<Cita>();
            for (Cita citaListNewCitaToAttach : citaListNew) {
                citaListNewCitaToAttach = em.getReference(citaListNewCitaToAttach.getClass(), citaListNewCitaToAttach.getIdCita());
                attachedCitaListNew.add(citaListNewCitaToAttach);
            }
            citaListNew = attachedCitaListNew;
            tutorado.setCitaList(citaListNew);
            List<Tutoria> attachedTutoriaListNew = new ArrayList<Tutoria>();
            for (Tutoria tutoriaListNewTutoriaToAttach : tutoriaListNew) {
                tutoriaListNewTutoriaToAttach = em.getReference(tutoriaListNewTutoriaToAttach.getClass(), tutoriaListNewTutoriaToAttach.getIdTutoria());
                attachedTutoriaListNew.add(tutoriaListNewTutoriaToAttach);
            }
            tutoriaListNew = attachedTutoriaListNew;
            tutorado.setTutoriaList(tutoriaListNew);
            tutorado = em.merge(tutorado);
            if (tutorIdOld != null && !tutorIdOld.equals(tutorIdNew)) {
                tutorIdOld.getTutoradoList().remove(tutorado);
                tutorIdOld = em.merge(tutorIdOld);
            }
            if (tutorIdNew != null && !tutorIdNew.equals(tutorIdOld)) {
                tutorIdNew.getTutoradoList().add(tutorado);
                tutorIdNew = em.merge(tutorIdNew);
            }
            for (Cita citaListNewCita : citaListNew) {
                if (!citaListOld.contains(citaListNewCita)) {
                    Tutorado oldNoControlOfCitaListNewCita = citaListNewCita.getNoControl();
                    citaListNewCita.setNoControl(tutorado);
                    citaListNewCita = em.merge(citaListNewCita);
                    if (oldNoControlOfCitaListNewCita != null && !oldNoControlOfCitaListNewCita.equals(tutorado)) {
                        oldNoControlOfCitaListNewCita.getCitaList().remove(citaListNewCita);
                        oldNoControlOfCitaListNewCita = em.merge(oldNoControlOfCitaListNewCita);
                    }
                }
            }
            for (Tutoria tutoriaListNewTutoria : tutoriaListNew) {
                if (!tutoriaListOld.contains(tutoriaListNewTutoria)) {
                    Tutorado oldNoControlOfTutoriaListNewTutoria = tutoriaListNewTutoria.getNoControl();
                    tutoriaListNewTutoria.setNoControl(tutorado);
                    tutoriaListNewTutoria = em.merge(tutoriaListNewTutoria);
                    if (oldNoControlOfTutoriaListNewTutoria != null && !oldNoControlOfTutoriaListNewTutoria.equals(tutorado)) {
                        oldNoControlOfTutoriaListNewTutoria.getTutoriaList().remove(tutoriaListNewTutoria);
                        oldNoControlOfTutoriaListNewTutoria = em.merge(oldNoControlOfTutoriaListNewTutoria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tutorado.getNoControl();
                if (findTutorado(id) == null) {
                    throw new NonexistentEntityException("The tutorado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutorado tutorado;
            try {
                tutorado = em.getReference(Tutorado.class, id);
                tutorado.getNoControl();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tutorado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cita> citaListOrphanCheck = tutorado.getCitaList();
            for (Cita citaListOrphanCheckCita : citaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tutorado (" + tutorado + ") cannot be destroyed since the Cita " + citaListOrphanCheckCita + " in its citaList field has a non-nullable noControl field.");
            }
            List<Tutoria> tutoriaListOrphanCheck = tutorado.getTutoriaList();
            for (Tutoria tutoriaListOrphanCheckTutoria : tutoriaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tutorado (" + tutorado + ") cannot be destroyed since the Tutoria " + tutoriaListOrphanCheckTutoria + " in its tutoriaList field has a non-nullable noControl field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tutor tutorId = tutorado.getTutorId();
            if (tutorId != null) {
                tutorId.getTutoradoList().remove(tutorado);
                tutorId = em.merge(tutorId);
            }
            em.remove(tutorado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tutorado> findTutoradoEntities() {
        return findTutoradoEntities(true, -1, -1);
    }
    
    public List<Tutorado> findTutoradoEntities(Integer tutorId) {
        EntityManager em = getEntityManager();
        List<Tutorado> citas = new ArrayList<>();

        try {
            TypedQuery<Tutorado> query = em.createQuery(
                "SELECT c FROM Tutorado c WHERE c.tutorId.idTutor = :tutorId ",
                Tutorado.class
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
    
    
    public List<Tutorado> findTutoradoEntities(int maxResults, int firstResult) {
        return findTutoradoEntities(false, maxResults, firstResult);
    }

    private List<Tutorado> findTutoradoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tutorado.class));
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

    public Tutorado findTutorado(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tutorado.class, id);
        } finally {
            em.close();
        }
    }

    public int getTutoradoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tutorado> rt = cq.from(Tutorado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
