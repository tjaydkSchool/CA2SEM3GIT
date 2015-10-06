package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Company extends InfoEntity{
    
    private String name;
    private String description;
    private String cvr;
    private int numberEmployees;
    private long marketValue;

    public Company() {
    }

    public Company(String name, String description, String cvr, int numberEmployees, long marketValue) {
        this.name = name;
        this.description = description;
        this.cvr = cvr;
        this.numberEmployees = numberEmployees;
        this.marketValue = marketValue;
    }

    public long getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(long marketValue) {
        this.marketValue = marketValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCvr() {
        return cvr;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    public int getNumberEmployees() {
        return numberEmployees;
    }

    public void setNumberEmployees(int numberEmployees) {
        this.numberEmployees = numberEmployees;
    } 
    
}
