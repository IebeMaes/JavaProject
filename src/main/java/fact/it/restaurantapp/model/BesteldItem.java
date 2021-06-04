package fact.it.restaurantapp.model;

import javax.persistence.*;

@Entity
public class BesteldItem {

    public BesteldItem() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int aantal;
    private Double toegepastePrijs;

    @ManyToOne
    private Gerecht gerecht;

    @ManyToOne
    private Bestelling bestelling;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public Double getToegepastePrijs() {
        return toegepastePrijs;
    }

    public void setToegepastePrijs(Double toegepastePrijs) {
        this.toegepastePrijs = toegepastePrijs;
    }

    public Gerecht getGerecht() {
        return gerecht;
    }

    public void setGerecht(Gerecht gerecht) {
        this.gerecht = gerecht;
    }

    public Bestelling getBestelling() {
        return bestelling;
    }

    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }
}
