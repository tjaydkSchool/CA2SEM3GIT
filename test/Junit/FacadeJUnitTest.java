/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Junit;

import entity.Person;
import entity.Phone;
import org.junit.Test;
import static org.junit.Assert.*;
import tester.Tester;

/**
 *
 * @author Dennis
 */
public class FacadeJUnitTest {
    Tester test;
    
    public FacadeJUnitTest() {
        test = new Tester();
    }
    
    
    @Test
    public void createPerson() {
        Person person = new Person();
        person.setId(9999L);
        assertNotNull(test.createPerson(person));
    }
    
    @Test
    public void updatePerson() {
        Person person = new Person();
        person.setId(8888L);
        person.setFirstName("Original");
        test.createPerson(person);
        assertEquals(test.getPersonById(person.getId()).getFirstName(), "Original");
        
        Person updatedPerson = test.getPersonById(person.getId());
        updatedPerson.setFirstName("Updated");
        test.updatePerson(updatedPerson);
        assertEquals(test.getPersonById(person.getId()).getFirstName(), "Updated");
        
    }
    
    @Test
    public void deletePerson() {
        Person person = new Person();
        person.setId(7777L);
        assertNotNull(test.createPerson(person));
        test.deletePerson(person);
        assertNull(test.getPersonById(person.getId()));
    }
    
    @Test
    public void getPersonByPhoneNumber() {
        Person person = new Person();
        person.setId(6666L);
        test.createPerson(person);
        
        Phone phone = new Phone();
        phone.setNumber("4444");
        phone.setIE(person);
        assertNotNull(test.createPhone(phone));
        
        assertEquals(test.getPersonByPhone("4444"), person);
    }
    
}
