package battleShip.pages;

import org.openqa.selenium.By;
import smartframework.base.BasePage;
import smartframework.elements.Label;

import java.util.Random;

public class BattleShipGamePage extends BasePage {

    private static final By pageLocator = By.xpath("//h1[@class='logo']");
    private Label randomEnemyLink = new Label(By.xpath("//div[@class='battlefield-start']//a[not(contains(@class,'connect'))]"));
    private Label arrangeShipsLink = new Label(By.xpath("//ul[@class='placeships']//li[contains(@class,'randomly')]//span"));
    private Label startButton = new Label(By.xpath("//div[@class='battlefield-start']//div[contains(@class,'button')]"));
    private Label actualNotification = new Label(By.xpath("//div[@class='notifications']//div[not(contains(@class,'none'))]//div[contains(@class,'message')]"));

    private int[][] cats = new int[10][10];

    public BattleShipGamePage() {
        super(pageLocator);
    }

    public void chooseRandomEnemy() {
        randomEnemyLink.jsClick();
    }

    public void arrangeShips() {
        Random random = new Random();
        int randomNumber = random.nextInt(16);
        for (int i = 0; i < randomNumber; i++) {
            arrangeShipsLink.click();
        }
    }

    public void clickStartButton() {
        startButton.click();
    }

    public void waitEnemy() {
        while (true) {
            String notificationText = actualNotification.getText();
            if (notificationText.equals("Игра началась, ваш ход.") || notificationText.equals("Игра началась, противник ходит."))
                break;
        }
    }

    public void play() {
        centerStrike();
        mainAlgorithm();
    }

    private void centerStrike() {

    }

    private void mainAlgorithm() {

    }
    

}
