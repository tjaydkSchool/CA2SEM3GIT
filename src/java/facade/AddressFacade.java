/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Address;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Asnorrason
 */
public class AddressFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2SEM3PUTest");
    EntityManager em = emf.createEntityManager();

    /**
     * Create an address in database.
     *
     * @param address creating the address object to database
     * @return Address object
     */
    public Address createAddress(Address address) {
        em.getTransaction().begin();
        em.persist(address);
        em.getTransaction().commit();
        return address;
    }

    /**
     * Getting all addresses in database.
     *
     * @return List of address objects
     */
    public List<Address> getAllAddresses() {
        TypedQuery<Address> q = em.createQuery("SELECT a FROM Address a", Address.class);
        return q.getResultList();
    }

    /**
     * Getting all addresses with given street in database.
     *
     * @param street getting addresses by street name
     * @return List of address objects with given street
     */
    public List<Address> getAddressesByStreet(String street) {
        TypedQuery<Address> q = em.createQuery("SELECT c FROM Address c WHERE c.street =: street", Address.class);
        q.setParameter("street", street);
        return q.getResultList();
    }

//    public List<Address> getAddressesByEvenNumbers(){
//        Scanner scan = new Scanner();
//        TypedQuery<Address> q = em.createQuery("SELECT c FROM Address c WHERE c.additionalInfo % 2 = 0", Address.class);
//        return q.getResultList();
//    }
    /**
     * Updating an address in database.
     *
     * @param address updating the address object from database
     * @return Address object
     */
    public Address updateAddress(Address address) {
        if (em.find(Address.class, address.getStreet()) != null) {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
            return address;
        } else {
            return null;
        }
    }

    /**
     * Deleting an address in database.
     *
     * @param address deleting the address object from database
     * @return Address object
     */
    public Address deleteAddresses(Address address) {
        if (em.find(Address.class, address.getStreet()) != null) {
            em.getTransaction().begin();
            em.remove(address);
            em.getTransaction().commit();
            return address;
        } else {
            return null;
        }
    }

}
