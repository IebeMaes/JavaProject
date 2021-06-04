package fact.it.restaurantapp.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Entity
public class Bestelling {
    public Bestelling() {
        betaalStrategie = new NormaleBetaling();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private GregorianCalendar datum;
    private Boolean betaald;


    @Transient
    private BetaalStrategie betaalStrategie;

    public BetaalStrategie getBetaalStrategie() {
        return betaalStrategie;
    }

    public void setBetaalStrategie(BetaalStrategie betaalStrategie) {
        this.betaalStrategie = betaalStrategie;
    }

    @OneToMany(cascade = {CascadeType.ALL})
    private List<BesteldItem> besteldItems = new ArrayList<>();

    @ManyToOne
    private Tafel tafel;

    @ManyToOne
    private Zaalpersoneel zaalpersoneel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GregorianCalendar getDatum() {
        return datum;
    }

    public void setDatum(GregorianCalendar datum) {
        this.datum = datum;
    }

    public Boolean getBetaald() {
        return betaald;
    }

    public void setBetaald(Boolean betaald) {
        this.betaald = betaald;
    }

    public List<BesteldItem> getBesteldItems() {
        return besteldItems;
    }

    public void setBesteldItems(List<BesteldItem> besteldItems) {
        this.besteldItems = besteldItems;
    }

    public Tafel getTafel() {
        return tafel;
    }

    public void setTafel(Tafel tafel) {
        this.tafel = tafel;
    }

    public Zaalpersoneel getZaalpersoneel() {
        return zaalpersoneel;
    }

    public void setZaalpersoneel(Zaalpersoneel zaalpersoneel) {
        this.zaalpersoneel = zaalpersoneel;
    }


    public void addItem(Gerecht gerecht, int aantal) {
        BesteldItem besteldItem = new BesteldItem();
        besteldItem.setAantal(aantal);
        besteldItem.setBestelling(this);
        besteldItem.setGerecht(gerecht);
        Double prijs = betaalStrategie.getToegepastePrijs(gerecht.getActuelePrijs());
        besteldItem.setToegepastePrijs(prijs);
        besteldItems.add(besteldItem);

    }

    public Double getTotaal() {
        Double totaal = 0.0;
        for (BesteldItem besteldItem : besteldItems) {
            Double prijs = besteldItem.getAantal() * besteldItem.getToegepastePrijs();
            totaal += prijs;
        }
        return totaal;
    }
}
