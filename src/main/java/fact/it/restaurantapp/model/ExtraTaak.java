package fact.it.restaurantapp.model;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public abstract class ExtraTaak extends Personeel {

    @ManyToOne
    private Personeel personeel;

    public Personeel getPersoneel() {
        return personeel;
    }

    public void setPersoneel(Personeel personeel) {
        this.personeel = personeel;
    }

    public void update() {
        personeel.update();
    }
}
