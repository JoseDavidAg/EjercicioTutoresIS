/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Control.exceptions.IllegalOrphanException;
import Control.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Tutorado;
import java.util.ArrayList;
import java.util.List;
import Modelo.Cita;
import Modelo.Tutor;
import Modelo.Tutoria;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ambro
 */
public class TutorJpaController implements Serializable {

    public TutorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public TutorJpaController(){
        emf= Persistence.createEntityManagerFactory("TutoresPrueba4TPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tutor tutor) {
        if (tutor.getTutoradoList() == null) {
            tutor.setTutoradoList(new ArrayList<Tutorado>());
        }
        if (tutor.getCitaList() == null) {
            tutor.setCitaList(new ArrayList<Cita>());
        }
        if (tutor.getTutoriaList() == null) {
            tutor.setTutoriaList(new ArrayList<Tutoria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Tutorado> attachedTutoradoList = new ArrayList<Tutorado>();
            for (Tutorado tutoradoListTutoradoToAttach : tutor.getTutoradoList()) {
                tutoradoListTutoradoToAttach = em.getReference(tutoradoListTutoradoToAttach.getClass(), tutoradoListTutoradoToAttach.getNoControl());
                attachedTutoradoList.add(tutoradoListTutoradoToAttach);
            }
            tutor.setTutoradoList(attachedTutoradoList);
            List<Cita> attachedCitaList = new ArrayList<Cita>();
            for (Cita citaListCitaToAttach : tutor.getCitaList()) {
                citaListCitaToAttach = em.getReference(citaListCitaToAttach.getClass(), citaListCitaToAttach.getIdCita());
                attachedCitaList.add(citaListCitaToAttach);
            }
            tutor.setCitaList(attachedCitaList);
            List<Tutoria> attachedTutoriaList = new ArrayList<Tutoria>();
            for (Tutoria tutoriaListTutoriaToAttach : tutor.getTutoriaList()) {
                tutoriaListTutoriaToAttach = em.getReference(tutoriaListTutoriaToAttach.getClass(), tutoriaListTutoriaToAttach.getIdTutoria());
                attachedTutoriaList.add(tutoriaListTutoriaToAttach);
            }
            tutor.setTutoriaList(attachedTutoriaList);
            em.persist(tutor);
            for (Tutorado tutoradoListTutorado : tutor.getTutoradoList()) {
                Tutor oldTutorIdOfTutoradoListTutorado = tutoradoListTutorado.getTutorId();
                tutoradoListTutorado.setTutorId(tutor);
                tutoradoListTutorado = em.merge(tutoradoListTutorado);
                if (oldTutorIdOfTutoradoListTutorado != null) {
                    oldTutorIdOfTutoradoListTutorado.getTutoradoList().remove(tutoradoListTutorado);
                    oldTutorIdOfTutoradoListTutorado = em.merge(oldTutorIdOfTutoradoListTutorado);
                }
            }
            for (Cita citaListCita : tutor.getCitaList()) {
                Tutor oldTutorIdOfCitaListCita = citaListCita.getTutorId();
                citaListCita.setTutorId(tutor);
                citaListCita = em.merge(citaListCita);
                if (oldTutorIdOfCitaListCita != null) {
                    oldTutorIdOfCitaListCita.getCitaList().remove(citaListCita);
                    oldTutorIdOfCitaListCita = em.merge(oldTutorIdOfCitaListCita);
                }
            }
            for (Tutoria tutoriaListTutoria : tutor.getTutoriaList()) {
                Tutor oldTutorIdOfTutoriaListTutoria = tutoriaListTutoria.getTutorId();
                tutoriaListTutoria.setTutorId(tutor);
                tutoriaListTutoria = em.merge(tutoriaListTutoria);
                if (oldTutorIdOfTutoriaListTutoria != null) {
                    oldTutorIdOfTutoriaListTutoria.getTutoriaList().remove(tutoriaListTutoria);
                    oldTutorIdOfTutoriaListTutoria = em.merge(oldTutorIdOfTutoriaListTutoria);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tutor tutor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutor persistentTutor = em.find(Tutor.class, tutor.getIdTutor());
            List<Tutorado> tutoradoListOld = persistentTutor.getTutoradoList();
            List<Tutorado> tutoradoListNew = tutor.getTutoradoList();
            List<Cita> citaListOld = persistentTutor.getCitaList();
            List<Cita> citaListNew = tutor.getCitaList();
            List<Tutoria> tutoriaListOld = persistentTutor.getTutoriaList();
            List<Tutoria> tutoriaListNew = tutor.getTutoriaList();
            List<String> illegalOrphanMessages = null;
            for (Cita citaListOldCita : citaListOld) {
                if (!citaListNew.contains(citaListOldCita)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cita " + citaListOldCita + " since its tutorId field is not nullable.");
                }
            }
            for (Tutoria tutoriaListOldTutoria : tutoriaListOld) {
                if (!tutoriaListNew.contains(tutoriaListOldTutoria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tutoria " + tutoriaListOldTutoria + " since its tutorId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Tutorado> attachedTutoradoListNew = new ArrayList<Tutorado>();
            for (Tutorado tutoradoListNewTutoradoToAttach : tutoradoListNew) {
                tutoradoListNewTutoradoToAttach = em.getReference(tutoradoListNewTutoradoToAttach.getClass(), tutoradoListNewTutoradoToAttach.getNoControl());
                attachedTutoradoListNew.add(tutoradoListNewTutoradoToAttach);
            }
            tutoradoListNew = attachedTutoradoListNew;
            tutor.setTutoradoList(tutoradoListNew);
            List<Cita> attachedCitaListNew = new ArrayList<Cita>();
            for (Cita citaListNewCitaToAttach : citaListNew) {
                citaListNewCitaToAttach = em.getReference(citaListNewCitaToAttach.getClass(), citaListNewCitaToAttach.getIdCita());
                attachedCitaListNew.add(citaListNewCitaToAttach);
            }
            citaListNew = attachedCitaListNew;
            tutor.setCitaList(citaListNew);
            List<Tutoria> attachedTutoriaListNew = new ArrayList<Tutoria>();
            for (Tutoria tutoriaListNewTutoriaToAttach : tutoriaListNew) {
                tutoriaListNewTutoriaToAttach = em.getReference(tutoriaListNewTutoriaToAttach.getClass(), tutoriaListNewTutoriaToAttach.getIdTutoria());
                attachedTutoriaListNew.add(tutoriaListNewTutoriaToAttach);
            }
            tutoriaListNew = attachedTutoriaListNew;
            tutor.setTutoriaList(tutoriaListNew);
            tutor = em.merge(tutor);
            for (Tutorado tutoradoListOldTutorado : tutoradoListOld) {
                if (!tutoradoListNew.contains(tutoradoListOldTutorado)) {
                    tutoradoListOldTutorado.setTutorId(null);
                    tutoradoListOldTutorado = em.merge(tutoradoListOldTutorado);
                }
            }
            for (Tutorado tutoradoListNewTutorado : tutoradoListNew) {
                if (!tutoradoListOld.contains(tutoradoListNewTutorado)) {
                    Tutor oldTutorIdOfTutoradoListNewTutorado = tutoradoListNewTutorado.getTutorId();
                    tutoradoListNewTutorado.setTutorId(tutor);
                    tutoradoListNewTutorado = em.merge(tutoradoListNewTutorado);
                    if (oldTutorIdOfTutoradoListNewTutorado != null && !oldTutorIdOfTutoradoListNewTutorado.equals(tutor)) {
                        oldTutorIdOfTutoradoListNewTutorado.getTutoradoList().remove(tutoradoListNewTutorado);
                        oldTutorIdOfTutoradoListNewTutorado = em.merge(oldTutorIdOfTutoradoListNewTutorado);
                    }
                }
            }
            for (Cita citaListNewCita : citaListNew) {
                if (!citaListOld.contains(citaListNewCita)) {
                    Tutor oldTutorIdOfCitaListNewCita = citaListNewCita.getTutorId();
                    citaListNewCita.setTutorId(tutor);
                    citaListNewCita = em.merge(citaListNewCita);
                    if (oldTutorIdOfCitaListNewCita != null && !oldTutorIdOfCitaListNewCita.equals(tutor)) {
                        oldTutorIdOfCitaListNewCita.getCitaList().remove(citaListNewCita);
                        oldTutorIdOfCitaListNewCita = em.merge(oldTutorIdOfCitaListNewCita);
                    }
                }
            }
            for (Tutoria tutoriaListNewTutoria : tutoriaListNew) {
                if (!tutoriaListOld.contains(tutoriaListNewTutoria)) {
                    Tutor oldTutorIdOfTutoriaListNewTutoria = tutoriaListNewTutoria.getTutorId();
                    tutoriaListNewTutoria.setTutorId(tutor);
                    tutoriaListNewTutoria = em.merge(tutoriaListNewTutoria);
                    if (oldTutorIdOfTutoriaListNewTutoria != null && !oldTutorIdOfTutoriaListNewTutoria.equals(tutor)) {
                        oldTutorIdOfTutoriaListNewTutoria.getTutoriaList().remove(tutoriaListNewTutoria);
                        oldTutorIdOfTutoriaListNewTutoria = em.merge(oldTutorIdOfTutoriaListNewTutoria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tutor.getIdTutor();
                if (findTutor(id) == null) {
                    throw new NonexistentEntityException("The tutor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutor tutor;
            try {
                tutor = em.getReference(Tutor.class, id);
                tutor.getIdTutor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tutor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cita> citaListOrphanCheck = tutor.getCitaList();
            for (Cita citaListOrphanCheckCita : citaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tutor (" + tutor + ") cannot be destroyed since the Cita " + citaListOrphanCheckCita + " in its citaList field has a non-nullable tutorId field.");
            }
            List<Tutoria> tutoriaListOrphanCheck = tutor.getTutoriaList();
            for (Tutoria tutoriaListOrphanCheckTutoria : tutoriaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tutor (" + tutor + ") cannot be destroyed since the Tutoria " + tutoriaListOrphanCheckTutoria + " in its tutoriaList field has a non-nullable tutorId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Tutorado> tutoradoList = tutor.getTutoradoList();
            for (Tutorado tutoradoListTutorado : tutoradoList) {
                tutoradoListTutorado.setTutorId(null);
                tutoradoListTutorado = em.merge(tutoradoListTutorado);
            }
            em.remove(tutor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tutor> findTutorEntities() {
        return findTutorEntities(true, -1, -1);
    }

    public List<Tutor> findTutorEntities(int maxResults, int firstResult) {
        return findTutorEntities(false, maxResults, firstResult);
    }

    private List<Tutor> findTutorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tutor.class));
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

    public Tutor findTutor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tutor.class, id);
        } finally {
            em.close();
        }
    }

    public int getTutorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tutor> rt = cq.from(Tutor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
