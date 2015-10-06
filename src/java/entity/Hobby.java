package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Hobby implements Serializable{

    @Id
    private String name;

    private String description;
    
    @ElementCollection(targetClass = Person.class)
    private Collection<Person> persons;

    public Hobby() {
    }

    public Hobby(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addPersontoHobby(Person person){
        persons.add(person);
    }
    
    public Collection<Person> getPersons() {
        return persons;
    }

    public void setPersons(Collection<Person> persons) {
        this.persons = persons;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Embeddable
    class PersonList {

        @ManyToMany(mappedBy = "hobbyList")
        public Collection<Person> getPersons() {
            return persons;
        }
    }

}
