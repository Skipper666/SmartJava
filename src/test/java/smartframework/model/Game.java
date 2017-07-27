package smartframework.model;

public class Game {

    public String nameGame;
    public String link;
    public String originPrice;
    public String discount;
    public String finalPrice;

    public Game(String nameGame, String link, String originPrice, String discount, String finalPrice) {
        this.nameGame = nameGame;
        this.link = link;
        this.originPrice = originPrice;
        this.discount = discount;
        this.finalPrice = finalPrice;
    }

    public String getNameGame() {
        return nameGame;
    }

    public String getLink() {
        return link;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    @Override
    public String toString() {
        return "Game{" +
                "nameGame='" + nameGame + '\'' +
                ", link='" + link + '\'' +
                ", originPrice='" + originPrice + '\'' +
                ", discount='" + discount + '\'' +
                ", finalPrice='" + finalPrice + '\'' +
                '}';
    }
}
