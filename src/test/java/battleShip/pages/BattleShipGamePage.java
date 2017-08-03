package battleShip.pages;

import battleShip.enums.Notifications;
import org.openqa.selenium.By;
import smartframework.base.BasePage;
import smartframework.elements.Label;

import java.util.Random;

import static battleShip.enums.Notifications.*;

public class BattleShipGamePage extends BasePage {

    private static final By pageLocator = By.xpath("//h1[@class='logo']");
    private Label randomEnemyLink = new Label(By.xpath("//div[@class='battlefield-start']//a[not(contains(@class,'connect'))]"));
    private Label arrangeShipsLink = new Label(By.xpath("//ul[@class='placeships']//li[contains(@class,'randomly')]//span"));
    private Label startButton = new Label(By.xpath("//div[@class='battlefield-start']//div[contains(@class,'button')]"));
    private Label actualNotification = new Label(By.xpath("//div[@class='notifications']//div[not(contains(@class,'none'))]//div[contains(@class,'message')]"));
    private static final String DO_SHOT_LINK_TEMPLATE = "//div[contains(@class,'rival')]//div[@class='battlefield-gap']//table//tr[%d]//td[%d]";

    private int[][] firingOrder;

    public BattleShipGamePage() {

        super(pageLocator);
        firingOrder = prepareFiringOrder();
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
            if (notificationText.equals(GAME_STARTED_MOVE_ON.notification()) ||
                    notificationText.equals(GAME_STARTED_MOVE_OFF.notification()))
                break;
        }
    }

    public boolean play() {
        boolean isWin;

        while (true) {
            shot();
            if (isWin()) {
                isWin = true;
                break;
            } else if (isLose()) {
                isWin = false;
                break;
            }
            shot();
        }
        return isWin;
    }

    private boolean isWin(){
        String notificationText = actualNotification.getText();
        return notificationText.equals(GAME__OVER_WIN.notification());
    }

    private boolean isLose() {
        String notificationText = actualNotification.getText();
        return notificationText.equals(RIVAL_LEAVE.notification()) ||
                notificationText.equals(GAME__OVER_LOSE.notification()) ||
                notificationText.equals(GAME_ERROR.notification()) ||
                notificationText.equals(SERVER_ERROR.notification());
    }

    private void shot() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (firingOrder[i][j] == 1) {
                    if(i==0) i++;
                    if(j==0) j++;
                    doShot(i, j);
                    break;
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (firingOrder[i][j] == 2) {
                    doShot(i,j);
                    break;
                }
            }
        }
    }

    private void doShot(int line, int column) {
        Label doShotLink = new Label(By.xpath(String.format(DO_SHOT_LINK_TEMPLATE, line, column)));
        doShotLink.click();
        if (checkNotificationForNextStep())
            checkAreaAroundShot(line, column);
        else
            firingOrder[line][column] = -1;

    }

    private boolean checkAreaAroundShot(int line, int column) {
        Label missCell = new Label(By.xpath(String.format(DO_SHOT_LINK_TEMPLATE, line + 1, column)));
        if (missCell.waitForElementsPresent().isEmpty())
            doShot(line + 1, column);

        Label missCell2 = new Label(By.xpath(String.format(DO_SHOT_LINK_TEMPLATE, line - 1, column)));
        if (missCell2.waitForElementsPresent().isEmpty())
            doShot(line - 1, column);

        Label missCell3 = new Label(By.xpath(String.format(DO_SHOT_LINK_TEMPLATE, line, column + 1)));
        if (missCell2.waitForElementsPresent().isEmpty())
            doShot(line - 1, column);

        Label missCell4 = new Label(By.xpath(String.format(DO_SHOT_LINK_TEMPLATE, line, column - 1)));
        if (missCell2.waitForElementsPresent().isEmpty())
            doShot(line - 1, column);
        return false;
        //TODO [DA] need do it
    }


    private boolean checkNotificationForNextStep() {
        String notificationText = actualNotification.getText();
        return notificationText.equals(MOVE_ON.notification());
    }


    private int[][] prepareFiringOrder() {
        int[][] firingOrder = new int[10][10];
        for (int i = 3; i <= 15; i += 4) {
            int x = 0, y = i;
            while (x < 10 && y >= 0) {
                if (x >= 0 && y >= 0 && x < 10 && y < 10) firingOrder[x][y] = 1;
                x++;
                y--;
            }
        }

        for (int i = 1; i <= 15; i += 4) {
            int x = 0, y = i;
            while (x < 10 && y >= 0) {
                if (x >= 0 && y >= 0 && x < 10 && y < 10) firingOrder[x][y] = 2;
                x++;
                y--;
            }
        }
        return firingOrder;
    }
    

}
