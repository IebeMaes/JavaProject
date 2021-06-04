package fact.it.restaurantapp.model;

public class TooGoodToGoBetaling implements BetaalStrategie {

    public Double getToegepastePrijs(Double actuelePrijs) {
        return actuelePrijs*0.5;
    }
}
