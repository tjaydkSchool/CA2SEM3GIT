package tester;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.InfoEntity;
import entity.Person;
import entity.Phone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Tester {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2SEM3PUTest");
    EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        Tester test = new Tester();
        test.createCityInfo();
    }

    public void createCityInfo() {
        CityInfo city = new CityInfo("3000", "Test");
        Address add = new Address("Street 2", "2nd right", city);
        Person person = new Person("Alju", "Hara");
        Hobby hobby = new Hobby("Paracour", "Awesome ass shit");
        person.addHobbytoPerson(hobby);
        person.setAddress(add);
        Company company = new Company("Apple", "Electronics", "5843905", 20, 10000000);
        Phone phone = new Phone("555-555-test", "Mobile", person);
        Phone phoneComp = new Phone("555-555-comp", "Company Phone", company);
        
        em.getTransaction().begin();
        em.persist(city);
        em.persist(add);
        em.persist(hobby);
        em.persist(person);
        em.persist(company);
        em.persist(phone);
        em.persist(phoneComp);
        em.getTransaction().commit();
    }
    
    /**
     * Create a person in database.
     * 
     * @param Person object
     * @return Person object
     */
    public Person createPerson(Person person) {
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        return person;
    }
    
    /**
     * Update a person in database.
     * 
     * @param Person object
     * @return Person object
     */
    public Person updatePerson(Person person) {
        if(em.find(Person.class, person.getId()) != null) {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return person;
        } else {
            return null;
        }
    }
    
    /**
     * Delete a person in database.
     * 
     * @param Person object
     * @return Person object
     */
    public Person deletePerson(Person person) {
        if(em.find(Person.class, person.getId()) != null) {
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
            return person;
        } else {
            return null;
        }
    }
    
    /**
     * Create a company in database.
     * 
     * @param Company object
     * @return Company object
     */
    public Company createCompany(Company company) {
        em.getTransaction().begin();
        em.persist(company);
        em.getTransaction().commit();
        return company;
    }
    
    /**
     * Update a company in database.
     * 
     * @param Company object
     * @return Company object
     */
    public Company updateCompany(Company company) {
        if(em.find(Company.class, company.getId()) != null) {
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
     * @param Company object
     * @return Company object
     */
    public Company deleteCompany(Company company) {
        if(em.find(Company.class, company.getId()) != null) {
            em.getTransaction().begin();
            em.remove(company);
            em.getTransaction().commit();
            return company;
        } else {
            return null;
        }
    }
    
    /**
     * Returns the person with a given id.
     * 
     * @param Long 'id'
     * @return Person object
     */
    public Person getPersonById(Long id) {
        return em.find(Person.class, id);
    }
    
    
    /**
     * Returns the person with a given phone number.
     * 
     * @param String 'Phone number'
     * @return Person object
     */
    public Person getPersonByPhone(String number) {
        Phone phone = em.find(Phone.class, number);
        return em.find(Person.class, phone.getIE().getId());
    }
    
    /**
     * Returns a list of persons with a given hobby.
     * 
     * @param String 'hobby'
     * @return Person list
     */
    public List<Person> getPersonsWithHobby(String hobby) {
        return (List<Person>)em.find(Hobby.class, hobby).getPersons();
    }
    
    /**
     * Returns the number of persons with a given hobby.
     * 
     * @param String 'hobby'
     * @return int
     */
    public int getNumberOfPersonsWithHobby(String hobby) {
        return (int)em.find(Hobby.class, hobby).getPersons().size();
    }
    
    /**
     * Returns a list of person living i given city.
     * 
     * @param String 'city'
     * @return Person list
     */
    public List<Person> getPersonsLivingInCity(String city) {
        throw new UnsupportedOperationException(); //NOT SUPPOERTED YET
    }
    
    /**
     * Returns a company with a given cvr number.
     * 
     * @param String 'cvr'
     * @return Company object
     */
    public Company getCompanyByCVR(String cvr) {
        Query q = em.createNamedQuery("Company.findByCvr");
        q.setParameter("cvr", cvr);
        return (Company)q.getResultList().get(0);
    }
    
    /**
     * Returns a company with a given phone number.
     * 
     * @param String 'Phone number'
     * @return Company object
     */
    public Company getCompanyByPhone(String number) {
        Phone phone = em.find(Phone.class, number);
        return em.find(Company.class, phone.getIE().getId());
    }
    
    /**
     * Returns a list of companies with more than a given number of employees.
     * 
     * @param int 'number of employees'
     * @return Company list
     */
    public List<Company> getCompaniesWithMoreThanXEmployees(int number) {
        Query q = em.createNamedQuery("Company.findByNumberemployeesGreaterThan");
        q.setParameter("numberEmployees", number);
        return q.getResultList();
    }
    
    /**
     * Create phone number in database to either person or company.
     * 
     * @param phone object
     * @return phone object
     */
    public Phone createPhone(Phone phone) {
        em.getTransaction().begin();
        em.persist(phone);
        em.getTransaction().commit();
        return phone;
    }
}
