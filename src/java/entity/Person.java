package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Person extends InfoEntity implements Serializable{

    private String firstName;
    private String lastName;
    
    @ElementCollection(targetClass=Hobby.class)
    private Collection<Hobby> hobbyList = new ArrayList();

    public Person() {
    }
    
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addHobbytoPerson(Hobby hobby) {
        hobbyList.add(hobby);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Collection<Hobby> getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(Collection<Hobby> hobbyList) {
        this.hobbyList = hobbyList;
    }

    @Embeddable
    class HobbyList {

        @ManyToMany
        @JoinTable(name = "Hobbies")
        public Collection<Hobby> getHobbies() {
            return getHobbyList();
        }

    }

}
