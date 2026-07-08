package de.iu.ipwa.ghostnet.service;

import de.iu.ipwa.ghostnet.model.GhostNet;
import de.iu.ipwa.ghostnet.model.BergendePerson;
import de.iu.ipwa.ghostnet.model.GhostNetStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class GhostNetService {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ghostnetPU");

    public void saveGhostNet(GhostNet ghostNet) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(ghostNet);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void saveBergendePerson(BergendePerson bergendePerson) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(bergendePerson);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<GhostNet> findAll() {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("SELECT g FROM GhostNet g", GhostNet.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public GhostNet findGhostNetById(Long id) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(GhostNet.class, id);
        } finally {
            em.close();
        }
    }

    public List<GhostNet> findOpenGhostNets() {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery(
                    "SELECT g FROM GhostNet g WHERE g.status IN (:status1, :status2)",
                    GhostNet.class)
                    .setParameter("status1", GhostNetStatus.GEMELDET)
                    .setParameter("status2", GhostNetStatus.BERGUNG_BEVORSTEHEND)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<BergendePerson> findAllBergendePersonen() {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("SELECT b FROM BergendePerson b", BergendePerson.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void updateGhostNet(GhostNet ghostNet) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(ghostNet);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    public void assignBergendePerson(GhostNet ghostNet, BergendePerson bergendePerson) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            GhostNet managedGhostNet = em.merge(ghostNet);
            BergendePerson managedBergendePerson = em.merge(bergendePerson);
            managedGhostNet.setBergendePerson(managedBergendePerson);
            managedGhostNet.setStatus(GhostNetStatus.BERGUNG_BEVORSTEHEND);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    public void deleteGhostNet(GhostNet ghostNet) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            GhostNet managedGhostNet = em.merge(ghostNet);
            em.remove(managedGhostNet);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}