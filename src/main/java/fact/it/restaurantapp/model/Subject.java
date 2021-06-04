package fact.it.restaurantapp.model;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    @OneToMany
    public List<Personeel> observers = new ArrayList<>();

    public List<Personeel> getObservers() {
        return observers;
    }

    public void setObservers(List<Personeel> observers) {
        this.observers = observers;
    }

    public void attachObserver(Personeel observer) {
    }

    ;

    public void detachObserver(Personeel observer) {
    }

    ;

    public void notifyObservers() {
    }

    ;

}
