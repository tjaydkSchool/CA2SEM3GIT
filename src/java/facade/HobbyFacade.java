package facade;

import entity.Hobby;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class HobbyFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2SEM3PUTest");
    EntityManager em = emf.createEntityManager();

    /**
     * Get list of all hobbies in database
     * 
     * @return list "of hobbies"
     */
    public List<Hobby> getListOfAllHobbies() {
        TypedQuery<Hobby> tq = em.createQuery("SELECT h FROM Hobby h", Hobby.class);
        return tq.getResultList();
    }
    
    /**
     * Get hobby object by hobby name
     * 
     * @return hobby object
     */
    public Hobby getHobbyByName(String hobby) {
        return em.find(Hobby.class, hobby);
    }
    
    /**
     * Create hobby and persist in database
     * 
     * @return hobby object
     */
    public Hobby createHobby(Hobby hobby) {
        em.getTransaction().begin();
        em.persist(hobby);
        em.getTransaction().commit();
        return hobby;
    }
    
    /**
     * Updates given hobby if exists in database
     * 
     * @return hobby object
     */
    public Hobby updateHobby(Hobby hobby) {
        if(em.find(Hobby.class, hobby.getName()) != null) {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
            return hobby;
        } else {
            return null;
        }
    }
    
    /**
     * Delete hobby from database if exists
     * 
     * @return hobby object
     */
    public Hobby deleteHobby(String hobby) {
        Hobby hob = em.find(Hobby.class, hobby);
        if(hob != null) {
            em.remove(hob);
            return hob;
        } else {
            return null;
        }
    }
}
