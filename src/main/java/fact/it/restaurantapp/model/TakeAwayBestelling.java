package fact.it.restaurantapp.model;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
public class TakeAwayBestelling {

    public TakeAwayBestelling() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int aantal;
    private GregorianCalendar datum;

    @ManyToOne
    private Keukenpersoneel keukenpersoneel;

    @ManyToOne
    private Gerecht gerecht;

    @Transient
    private BetaalStrategie betaalStrategie;

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

    public GregorianCalendar getDatum() {
        return datum;
    }

    public void setDatum(GregorianCalendar datum) {
        this.datum = datum;
    }

    public Keukenpersoneel getKeukenpersoneel() {
        return keukenpersoneel;
    }

    public void setKeukenpersoneel(Keukenpersoneel keukenpersoneel) {
        this.keukenpersoneel = keukenpersoneel;
    }

    public Gerecht getGerecht() {
        return gerecht;
    }

    public void setGerecht(Gerecht gerecht) {
        this.gerecht = gerecht;
    }

    public BetaalStrategie getBetaalStrategie() {
        return betaalStrategie;
    }

    public void setBetaalStrategie(BetaalStrategie betaalStrategie) {
        this.betaalStrategie = betaalStrategie;
    }

    public Double getTotaalTakeAway(Gerecht gerecht){
        Double totaal = aantal * gerecht.getActuelePrijs();
        return totaal;
    }

    public Double getTotaal(){
        Double totaal = aantal * betaalStrategie.getToegepastePrijs(gerecht.getActuelePrijs());
        return totaal;
    }
}
