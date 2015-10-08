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
import javax.persistence.TypedQuery;

public class PersonFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2SEM3PUTest");
    EntityManager em = emf.createEntityManager();

    /**
     * Create a person in database.
     *
     * @param person the person object to persist in database
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
     * @param person the person object to update in database
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
     * @param person the person to be deleted from database
     * @return Person object
     */
    public Person deletePerson(Person person) {
        if (em.find(Person.class, person.getId()) != null) {
            TypedQuery<Phone> tq = em.createQuery("SELECT p FROM Phone p WHERE p.IE = :ie", Phone.class);
            tq.setParameter("ie", person);
            List<Phone> phoneList = tq.getResultList();
            if (phoneList != null) {
                for (int i = 0; i < phoneList.size(); i++) {
                    Phone removeAssociationPhone = phoneList.get(i);
                    removeAssociationPhone.setIE(null);
                    em.getTransaction().begin();
                    em.persist(removeAssociationPhone);
                    em.getTransaction().commit();
                }
            }
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
     * @param id the id number of the person searched for
     * @return Person object
     * @throws exceptions.PersonNotFoundException
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
     * @param number the phone number of the person searched for
     * @return Person object
     */
    public Person getPersonByPhone(String number) {
        Phone phone = em.find(Phone.class, number);
        return em.find(Person.class, phone.getIE().getId());
    }

    /**
     * Returns a list of persons with a given hobby.
     *
     * @param hobby the hobby of which you want list of persons interested in
     * @return Person list
     */
    public List<Person> getPersonsWithHobby(String hobby) {
        return (List<Person>) em.find(Hobby.class, hobby).getPersons();
    }

    /**
     * Returns the number of persons with a given hobby.
     *
     * @param hobby the hobby of which you want count of persons interested in
     * @return int
     */
    public int getNumberOfPersonsWithHobby(String hobby) {
        return (int) em.find(Hobby.class, hobby).getPersons().size();
    }

    /**
     * Returns a list of person living i given city.
     *
     * @param city the zipcode of the city you want to find persons from
     * @return Person list
     */
    public List<Person> getPersonsLivingInCity(String zipcode) {
        TypedQuery<Person> q = em.createQuery("SELECT i FROM InfoEntity i WHERE i.address.cityInfo.zipCode = :zipcode", Person.class);
        q.setParameter("zipcode", zipcode);
        return q.getResultList();
    }

    /**
     * Returns list of all persons i database
     *
     * @return List 'of persons'
     */
    public List<Person> getListOfAllPersons() {
        TypedQuery<Person> q = em.createQuery("SELECT p Person p", Person.class);
        return q.getResultList();
    }

}
