/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Hobby;
import entity.Person;
import entity.Phone;
import exceptions.PersonNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PersonFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2SEM3PUTest");
    EntityManager em = emf.createEntityManager();

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
        if (em.find(Person.class, person.getId()) != null) {
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
        if (em.find(Person.class, person.getId()) != null) {
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
            return person;
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
    public Person getPersonById(Long id) throws PersonNotFoundException {
        Person p = em.find(Person.class, id);
        if (p != null) {
            return p;
        } else {
            throw new PersonNotFoundException("Person not found");
        }
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
        return (List<Person>) em.find(Hobby.class, hobby).getPersons();
    }

    /**
     * Returns the number of persons with a given hobby.
     *
     * @param String 'hobby'
     * @return int
     */
    public int getNumberOfPersonsWithHobby(String hobby) {
        return (int) em.find(Hobby.class, hobby).getPersons().size();
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

    //TEST THIS WHEN NAMEDQUERY WORKS
    public List<Person> getListOfAllPersons() {
        Query q = em.createNamedQuery("Infoentity.findByDtype");
        q.setParameter("dtype", "Person");
        return q.getResultList();
    }

}
