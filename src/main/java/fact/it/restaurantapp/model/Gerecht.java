package fact.it.restaurantapp.model;

import javax.persistence.*;

@Entity
public class Gerecht {

    public Gerecht() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String naam;
    private Double actuelePrijs;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Double getActuelePrijs() {
        return actuelePrijs;
    }

    public void setActuelePrijs(Double actuelePrijs) {
        this.actuelePrijs = actuelePrijs;
    }
}
