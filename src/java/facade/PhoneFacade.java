package facade;

import entity.Hobby;
import entity.Phone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class PhoneFacade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2SEM3PUTest");
    EntityManager em = emf.createEntityManager();
    
    /**
     * Get list of all phone numbers in database
     * 
     * @return list "of phones"
     */
    public List<Phone> getListOfAllPhoneNumbers() {
        TypedQuery<Phone> tq = em.createQuery("SELECT p FROM Phone p", Phone.class);
        return tq.getResultList();
    }
    
    /**
     * Get phone object by phone number
     * 
     * @return phone object
     */
    public Phone getPhoneInfo(String number) {
        return em.find(Phone.class, number);
    }
    
    /**
     * Create phone and persist in database
     * 
     * @return phone object
     */
    public Phone createPhone(Phone phone) {
        em.getTransaction().begin();
        em.persist(phone);
        em.getTransaction().commit();
        return phone;
    }
    
    /**
     * Update phone and persist in database if exists
     * 
     * @return phone object
     */
    public Phone updatePhone(Phone phone) {
        if(em.find(Phone.class, phone.getNumber()) != null) {
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
            return phone;
        } else {
            return null;
        }
    }
    
    /**
     * Delete phone from database if exists
     * 
     * @return phone object
     */
    public Phone deletePhone(String phoneNumber) {
        Phone phone = em.find(Phone.class, phoneNumber);
        if(phone != null) {
            em.remove(phone);
            return phone;
        } else {
            return null;
        }
    }
}
