package tester;

import entity.Address;
import entity.CityInfo;
import entity.InfoEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
        InfoEntity IE = new InfoEntity("test@test.com", add);
        em.getTransaction().begin();
        em.persist(city);
        em.persist(add);
        em.persist(IE);
        em.getTransaction().commit();
    }
}
