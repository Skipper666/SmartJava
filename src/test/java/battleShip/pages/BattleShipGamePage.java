package battleShip.pages;

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
    private int accurateShotPositionColumn = 0;
    private int accurateShotPositionLine = 0;

    private int accurateCurrentShotPositionColumn = 0;
    private int accurateTopShotPositionLine = 0;
    private int accurateBottomShotPositionLine = 0;


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
            while (true) {
                if (checkNotificationForNextStep())
                    break;
            }
        }
        return isWin;
    }

    private void shot() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (firingOrder[j][i] == 1) {
                    int line = j + 1;
                    int column = i + 1;
                    doShot(line, column);
                    firingOrder[j][i] = -1;
                    return;
                }

            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (firingOrder[j][i] == 2) {
                    doShot(i, j);
                    return;
                }
            }
        }
    }



    private boolean doShot(int line, int column) {
        Label doShotLink = new Label(By.xpath(String.format(DO_SHOT_LINK_TEMPLATE, line, column)));
        doShotLink.click();
        if (checkNotificationForNextStep()) {
            if (accurateShotPositionColumn == 0 && accurateShotPositionLine == 0) {
                accurateShotPositionLine = line;
                accurateShotPositionColumn = column;
            }
            firingOrder[column - 1][line - 1] = -1;
            checkAreaAroundShot(accurateShotPositionLine, accurateShotPositionColumn);
            return true;
        }
        return false;
    }

    private void doShotAroundBaseShot(int line, int column) {
        Label doShotLink = new Label(By.xpath(String.format(DO_SHOT_LINK_TEMPLATE, line, column)));
        doShotLink.click();
        if (checkNotificationForNextStep()) {
            firingOrder[column - 1][line - 1] = -1;

            accurateCurrentShotPositionColumn = column;
            accurateTopShotPositionLine = line + 1;
            accurateBottomShotPositionLine = line - 1;
        }
    }

    private void checkAreaAroundShot(int line, int column) {
        Label shotLeftBaseShot = new Label(By.xpath(String.format(DO_SHOT_LINK_TEMPLATE, line, column - 1)));
        if (checkMissOrEmptyCell(shotLeftBaseShot))
            doShotAroundBaseShot(line, column - 1);
        while (true) {
            if (checkNotificationForNextStep())
                break;
        }
        Label shotRightBaseShot = new Label(By.xpath(String.format(DO_SHOT_LINK_TEMPLATE, line, column + 1)));
        if (checkMissOrEmptyCell(shotRightBaseShot))
            doShotAroundBaseShot(line, column - 1);
        while (true) {
            if (checkNotificationForNextStep())
                break;
        }

        Label shotAboveBaseShot = new Label(By.xpath(String.format(DO_SHOT_LINK_TEMPLATE, line + 1, column)));
        if (checkMissOrEmptyCell(shotAboveBaseShot))
            doShotAroundBaseShot(line, column - 1);

        while (true) {
            if (checkNotificationForNextStep())
                break;
        }
        Label shotUnderBaseShot = new Label(By.xpath(String.format(DO_SHOT_LINK_TEMPLATE, line - 1, column)));
        if (checkMissOrEmptyCell(shotUnderBaseShot))
            doShotAroundBaseShot(line, column - 1);
        while (true) {
            if (checkNotificationForNextStep())
                break;
        }

        if (accurateCurrentShotPositionColumn != 0 && accurateTopShotPositionLine != 0 && accurateBottomShotPositionLine != 0)
            finishShip(line, column);
    }

    private void finishShip(int line, int column) {
        Label finishShipShotAbove = new Label(By.xpath(String.format(DO_SHOT_LINK_TEMPLATE, line, column - 1)));
        if (checkMissOrEmptyCell(finishShipShotAbove))
            doShotAroundBaseShot(line, column - 1);
        while (true) {
            if (checkNotificationForNextStep())
                break;
        }

        Label finishShipShotUnder = new Label(By.xpath(String.format(DO_SHOT_LINK_TEMPLATE, line, column - 1)));
        if (checkMissOrEmptyCell(finishShipShotUnder))
            doShotAroundBaseShot(line, column - 1);
        while (true) {
            if (checkNotificationForNextStep())
                break;
        }
    }

    private boolean checkMissOrEmptyCell(Label cell){
        return cell.getAttribute("class").contains("empty");
    }

    private boolean checkNotificationForNextStep() {
        String notificationText = actualNotification.getText();
        return notificationText.equals(MOVE_ON.notification());
    }

    private boolean isWin() {
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
