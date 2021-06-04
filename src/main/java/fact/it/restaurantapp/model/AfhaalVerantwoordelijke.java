package fact.it.restaurantapp.model;

public class AfhaalVerantwoordelijke extends ExtraTaak {

    @Override
    public void update() {
        System.out.println("AfhaalVerantwoordelijke "+getPersoneel().getNaam()+" heet de klant welkom en vraagt het bestelnummer op");

    }

    public void leverBestellingAf(){
        System.out.println("AfhaalVerantwoordelijke "+getPersoneel().getNaam()+" geeft de bestelling door aan de klant");
    }
}
