package fact.it.restaurantapp.model;

public class NormaleBetaling implements BetaalStrategie {

    public Double getToegepastePrijs(Double actuelePrijs) {
        return actuelePrijs;
    }
}
