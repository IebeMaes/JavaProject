package fact.it.restaurantapp.model;

public final class IngangTeller extends Subject {
    private static IngangTeller ingangTeller;
    private int aantal;

    private IngangTeller() {

    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
        notifyObservers();
    }

    public static IngangTeller getInstance() {
        if (ingangTeller == null) {
            ingangTeller = new IngangTeller();
        }
        return ingangTeller;
    }

    public void attachObserver(Personeel observer) {
        observers.add(observer);
    }

    ;

    public void detachObserver(Personeel observer) {
        observers.remove(observer);
    }

    ;

    public void notifyObservers() {
        for (Personeel observer : observers) {
            observer.update();
        }
    }

    ;


}
