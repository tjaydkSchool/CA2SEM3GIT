package facade;

import entity.CityInfo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class CityInfoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2SEM3PUTest");
    EntityManager em = emf.createEntityManager();

    /**
     * Create cityInfo in database.
     *
     * @param cityinfo creating the cityinfo object to database
     * @return CityInfo object
     */
    public CityInfo createCityInfo(CityInfo cityinfo) {
        em.getTransaction().begin();
        em.persist(cityinfo);
        em.getTransaction().commit();
        return cityinfo;
    }

    /**
     * Return a list of all cityInfoes from database.
     *
     * @return CityInfo list
     */
    public List<CityInfo> getAllCityInfoes() {
        TypedQuery<CityInfo> q = em.createQuery("SELECT c FROM CityInfo c", CityInfo.class);
        return q.getResultList();
    }

    /**
     * Return a single CityInfo by zipcode
     *
     * @param zipcode getting a single cityinfo by given zipcode
     * @return CityInfo object
     */
    public CityInfo getCityByZipCode(String zipcode) {
        TypedQuery<CityInfo> q = em.createQuery("SELECT c FROM CityInfo c WHERE c.zipCode = :zipcode", CityInfo.class);
        q.setParameter("zipcode", zipcode);
        return q.getSingleResult();
    }

    /**
     * Update CityInfo from database.
     *
     * @param cityinfo updating the cityinfo object from database
     * @return CityInfo object
     */
    public CityInfo updateCityInfo(CityInfo cityinfo) {
        if (em.find(CityInfo.class, cityinfo.getZipCode()) != null) {
            em.getTransaction().begin();
            em.persist(cityinfo);
            em.getTransaction().commit();
            return cityinfo;
        } else {
            return null;
        }
    }

        /**
     * Delete CityInfo from database.
     *
     * @param cityinfo deleting cityinfo object from database
     * @return Company object
     */
    public CityInfo deleteCityInfo(CityInfo cityinfo) {
        if (em.find(CityInfo.class, cityinfo.getZipCode()) != null) {
            em.getTransaction().begin();
            em.remove(cityinfo);
            em.getTransaction().commit();
            return cityinfo;
        } else {
            return null;
        }
    }

}
