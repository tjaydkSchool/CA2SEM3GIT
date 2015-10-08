/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Company;
import entity.Phone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Asnorrason
 */
public class CompanyFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2SEM3PUTest");
    EntityManager em = emf.createEntityManager();

    /**
     * Create a company in database.
     *
     * @param company creating the company object to database
     * @return Company object
     */
    public Company createCompany(Company company) {
        em.getTransaction().begin();
        em.persist(company);
        em.getTransaction().commit();
        return company;
    }

    /**
     * Returns a company with a given cvr number.
     *
     * @param cvr the cvr of the company searched for
     * @return Company object
     */
    public Company getCompanyByCVR(String cvr) {
        TypedQuery<Company> q = em.createQuery("SELECT c FROM Company c WHERE c.cvr = :cvr", Company.class);
        q.setParameter("cvr", cvr);
        return q.getSingleResult();
    }

    /**
     * Returns a company with a given phone number.
     *
     * @param number the phone number of the company searched for
     * @return Company object
     */
    public Company getCompanyByPhone(String number) {
        Phone phone = em.find(Phone.class, number);
        return em.find(Company.class, phone.getIE().getId());
    }

    /**
     * Get a Company by given Id
     *
     * @param id the id number of the company searched for
     * @return Company object
     */
    public Company getCompanyById(long id) {
        return (Company) em.find(Company.class, id);
    }

    /**
     * Returns a list of companies with more than a given number of employees.
     *
     * @param number the number of companies with employees greater than
     * searched for
     * @return Company list
     */
    public List<Company> getCompaniesWithMoreThanXEmployees(int number) {
        TypedQuery<Company> q = em.createQuery("SELECT c from Company c WHERE c.numberEmployees > :number", Company.class);
        q.setParameter("number", number);
        return q.getResultList();
    }

    /**
     * Update a company in database.
     *
     * @param company updating the company object from database
     * @return Company object
     */
    public Company updateCompany(Company company) {
        if (em.find(Company.class, company.getId()) != null) {
            em.getTransaction().begin();
            em.persist(company);
            em.getTransaction().commit();
            return company;
        } else {
            return null;
        }
    }

    /**
     * Delete a company in database.
     *
     * @param company deleting the company object from database
     * @return Company object
     */
    public Company deleteCompany(Company company) {
        if (em.find(Company.class, company.getId()) != null) {
            em.getTransaction().begin();
            em.remove(company);
            em.getTransaction().commit();
            return company;
        } else {
            return null;
        }
    }

}
